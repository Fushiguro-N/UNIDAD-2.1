import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, inject, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ApiService } from '../../core/api.service';
import { ENTITIES, EntityConfig } from '../../core/entities.config';
import { ToastService } from '../../core/toast.service';

@Component({
  selector: 'app-generic-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './generic-form.component.html',
  styleUrl: './generic-form.component.scss',
})
export class GenericFormComponent implements OnChanges {
  /** When set, the form runs embedded (e.g. inside a modal) instead of reading route params. */
  @Input() resourceOverride?: string;
  @Input() idOverride?: string | null;
  @Output() saved = new EventEmitter<void>();
  @Output() cancelled = new EventEmitter<void>();

  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly api = inject(ApiService);
  private readonly toast = inject(ToastService);

  readonly config = signal<EntityConfig>(ENTITIES[0]);
  readonly form = signal(new FormGroup({}));
  readonly editing = signal(false);
  readonly recordId = signal<string | null>(null);
  readonly saving = signal(false);
  readonly error = signal<string | null>(null);
  readonly optionsByField = signal<Record<string, any[]>>({});
  readonly suggestionsByField = signal<Record<string, string[]>>({});
  readonly uploadingByField = signal<Record<string, boolean>>({});

  readonly hours12 = Array.from({ length: 12 }, (_, i) => String(i + 1).padStart(2, '0'));
  readonly minutes = Array.from({ length: 12 }, (_, i) => String(i * 5).padStart(2, '0'));
  readonly ampmOptions = ['AM', 'PM'];

  private usingOverride = false;

  constructor() {
    this.route.paramMap.subscribe((params) => {
      if (this.resourceOverride) return;
      const resource = params.get('resource')!;
      const id = params.get('id');
      this.load(resource, id);
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['resourceOverride'] && this.resourceOverride) {
      this.usingOverride = true;
      this.load(this.resourceOverride, this.idOverride ?? null);
    }
  }

  private load(resource: string, id: string | null): void {
    this.error.set(null);
    const cfg = ENTITIES.find((e) => e.resource === resource);
    if (!cfg) {
      this.error.set(`Recurso desconocido: ${resource}`);
      return;
    }
    this.config.set(cfg);
    this.buildForm();
    this.loadOptions();
    if (id) {
      this.editing.set(true);
      this.recordId.set(id);
      this.api.get(resource, id).subscribe({
        next: (data) => this.form().patchValue(this.flattenIdFields(data as Record<string, any>)),
        error: (err) => this.error.set(err?.error?.message || err?.message || 'Error al cargar registro'),
      });
    } else {
      this.editing.set(false);
      this.recordId.set(null);
    }
  }

  private buildForm(): void {
    const group: Record<string, FormControl> = {};
    for (const field of this.config().fields) {
      group[field.name] = new FormControl(
        field.type === 'boolean' ? false : '',
        field.required ? Validators.required : [],
      );
    }
    this.form.set(new FormGroup(group));
  }

  private loadOptions(): void {
    this.optionsByField.set({});
    this.suggestionsByField.set({});
    for (const field of this.config().fields) {
      if (!field.optionsResource) continue;
      this.api.list(field.optionsResource).subscribe({
        next: (data) => this.optionsByField.update((m) => ({ ...m, [field.name]: data ?? [] })),
        error: () => this.optionsByField.update((m) => ({ ...m, [field.name]: [] })),
      });
    }

    const idFields = this.config().fields.filter((f) => !f.optionsResource && this.isIdField(f.name));
    if (!idFields.length) return;
    this.api.list(this.config().resource).subscribe({
      next: (rows) => {
        for (const field of idFields) {
          const values = Array.from(
            new Set((rows ?? []).map((r: any) => r[field.name]).filter((v: any) => v !== null && v !== undefined)),
          ).map(String);
          this.suggestionsByField.update((m) => ({ ...m, [field.name]: values }));
        }
      },
      error: () => {},
    });
  }

  private flattenIdFields(data: Record<string, any>): Record<string, any> {
    const result = { ...data };
    for (const field of this.config().fields) {
      if (result[field.name] !== undefined && result[field.name] !== null) continue;
      if (!/Id$/.test(field.name)) continue;
      const nestedKey = field.name.slice(0, -2);
      const nested = data[nestedKey];
      if (nested && typeof nested === 'object' && nested.id !== undefined) {
        result[field.name] = nested.id;
      }
    }
    return result;
  }

  private isIdField(name: string): boolean {
    return /id$/i.test(name) || /^id[A-Z]/.test(name);
  }

  optionLabel(opt: any, field: { optionsLabel?: string }): string {
    if (!field.optionsLabel) return opt?.id;
    return field.optionsLabel
      .split(',')
      .map((path) => path.split('.').reduce((v, k) => v?.[k], opt))
      .filter((v) => v !== undefined && v !== null && v !== '')
      .join(' - ');
  }

  private to12(hh: string): { h: string; ap: 'AM' | 'PM' } {
    const h = parseInt(hh, 10) || 0;
    const ap: 'AM' | 'PM' = h >= 12 ? 'PM' : 'AM';
    let h12 = h % 12;
    if (h12 === 0) h12 = 12;
    return { h: String(h12).padStart(2, '0'), ap };
  }

  private to24(h12: string, ap: string): string {
    let h = parseInt(h12, 10) % 12;
    if (ap === 'PM') h += 12;
    return String(h).padStart(2, '0');
  }

  timePart(fieldName: string, idx: 0 | 1 | 2): string {
    const [hh, mm] = String(this.form().get(fieldName)?.value || '00:00').split(':');
    if (idx === 0) return this.to12(hh || '00').h;
    if (idx === 1) return mm || '00';
    return this.to12(hh || '00').ap;
  }

  setTimePart(fieldName: string, idx: 0 | 1 | 2, event: Event): void {
    const value = (event.target as HTMLSelectElement).value;
    const [hhRaw, mmRaw] = String(this.form().get(fieldName)?.value || '00:00').split(':');
    const hh = hhRaw || '00';
    const mm = mmRaw || '00';
    const cur12 = this.to12(hh);
    let newHh = hh;
    let newMm = mm;
    if (idx === 0) newHh = this.to24(value, cur12.ap);
    else if (idx === 1) newMm = value;
    else newHh = this.to24(cur12.h, value);
    this.form().get(fieldName)?.setValue(`${newHh}:${newMm}`);
  }

  save(): void {
    if (this.form().invalid) {
      this.form().markAllAsTouched();
      return;
    }
    this.saving.set(true);
    this.error.set(null);
    const resource = this.config().resource;
    const body = this.form().getRawValue();
    const req = this.editing()
      ? this.api.update(resource, this.recordId()!, body)
      : this.api.create(resource, body);
    req.subscribe({
      next: () => {
        this.saving.set(false);
        this.toast.success(this.editing() ? 'Registro actualizado correctamente' : 'Registro creado correctamente');
        if (this.usingOverride) {
          this.saved.emit();
        } else {
          this.router.navigate(['/', resource]);
        }
      },
      error: (err) => {
        const message = err?.error?.message || err?.message || 'Error al guardar';
        this.error.set(message);
        this.toast.error(message);
        this.applyFieldErrors(err?.error?.fieldErrors);
        this.saving.set(false);
      },
    });
  }

  private applyFieldErrors(fieldErrors?: Record<string, string>): void {
    if (!fieldErrors) return;
    for (const [field, message] of Object.entries(fieldErrors)) {
      const control = this.form().get(field);
      if (control) {
        control.setErrors({ ...(control.errors ?? {}), server: message });
        control.markAsTouched();
      }
    }
  }

  fieldError(fieldName: string): string | null {
    const control = this.form().get(fieldName);
    if (!control || !control.touched) return null;
    if (control.errors?.['server']) return control.errors['server'];
    if (control.errors?.['required']) return 'Este campo es obligatorio';
    return null;
  }

  cancel(): void {
    this.cancelled.emit();
  }

  resolveUrl(path: string): string {
    return this.api.resolveUrl(path);
  }

  onFileSelected(fieldName: string, event: Event): void {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (!file) return;
    this.uploadingByField.update((m) => ({ ...m, [fieldName]: true }));
    this.api.upload(this.config().resource, file).subscribe({
      next: ({ url }) => {
        this.form().get(fieldName)?.setValue(url);
        this.uploadingByField.update((m) => ({ ...m, [fieldName]: false }));
      },
      error: (err) => {
        this.error.set(err?.error?.message || err?.message || 'Error al subir archivo');
        this.uploadingByField.update((m) => ({ ...m, [fieldName]: false }));
      },
    });
  }
}

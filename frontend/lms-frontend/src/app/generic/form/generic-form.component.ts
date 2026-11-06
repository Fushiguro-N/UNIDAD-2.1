import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ApiService } from '../../core/api.service';
import { ENTITIES, EntityConfig } from '../../core/entities.config';

@Component({
  selector: 'app-generic-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './generic-form.component.html',
  styleUrl: './generic-form.component.scss',
})
export class GenericFormComponent {
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly api = inject(ApiService);

  readonly config = signal<EntityConfig>(ENTITIES[0]);
  readonly form = signal(new FormGroup({}));
  readonly editing = signal(false);
  readonly recordId = signal<string | null>(null);
  readonly saving = signal(false);
  readonly error = signal<string | null>(null);
  readonly optionsByField = signal<Record<string, any[]>>({});

  constructor() {
    this.route.paramMap.subscribe((params) => {
      const resource = params.get('resource')!;
      const id = params.get('id');
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
          next: (data) => this.form().patchValue(data as object),
          error: (err) => this.error.set(err?.error?.message || err?.message || 'Error al cargar registro'),
        });
      } else {
        this.editing.set(false);
        this.recordId.set(null);
      }
    });
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
    for (const field of this.config().fields) {
      if (!field.optionsResource) continue;
      this.api.list(field.optionsResource).subscribe({
        next: (data) => this.optionsByField.update((m) => ({ ...m, [field.name]: data ?? [] })),
        error: () => this.optionsByField.update((m) => ({ ...m, [field.name]: [] })),
      });
    }
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
      next: () => this.router.navigate(['/', resource]),
      error: (err) => {
        this.error.set(err?.error?.message || err?.message || 'Error al guardar');
        this.saving.set(false);
      },
    });
  }
}

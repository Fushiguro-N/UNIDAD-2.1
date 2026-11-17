import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
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
export class GenericFormComponent {
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
      if (id) {
        this.editing.set(true);
        this.recordId.set(id);
        this.api.get(resource, id).subscribe({
          next: (data) => {
            this.form().patchValue(data as object);
            if (cfg.readOnly) {
              this.form().disable();
            }
          },
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
        this.router.navigate(['/', resource]);
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
}

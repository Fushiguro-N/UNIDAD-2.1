import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../../core/api.service';
import { ToastService } from '../../core/toast.service';

@Component({
  selector: 'app-crear-estudiante',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './crear-estudiante.component.html',
  styleUrl: './crear-estudiante.component.scss',
})
export class CrearEstudianteComponent {
  private readonly fb = inject(FormBuilder);
  private readonly api = inject(ApiService);
  private readonly toast = inject(ToastService);

  readonly form = this.fb.group({
    dni: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
    nombres: ['', Validators.required],
    apellidos: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    celular: [''],
  });

  readonly saving = signal(false);
  readonly error = signal<string | null>(null);
  readonly success = signal<any | null>(null);

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.saving.set(true);
    this.error.set(null);
    this.success.set(null);
    this.api.create('estudiantes', this.form.getRawValue()).subscribe({
      next: (res) => {
        this.success.set(res);
        this.saving.set(false);
        this.toast.success('Estudiante creado correctamente');
        this.form.reset();
      },
      error: (err) => {
        const message = err?.error?.message || err?.message || 'Error al crear estudiante';
        this.error.set(message);
        this.toast.error(message);
        this.saving.set(false);
      },
    });
  }
}

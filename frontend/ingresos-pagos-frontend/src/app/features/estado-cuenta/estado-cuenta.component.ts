import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { environment } from '../../../environments/environment';
import { ToastService } from '../../core/toast.service';

interface EstadoCuenta {
  idEstudiante: string;
  nombres: string;
  apellidos: string;
  dni: string;
  tieneDeudaPendiente: boolean;
}

@Component({
  selector: 'app-estado-cuenta',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './estado-cuenta.component.html',
  styleUrl: './estado-cuenta.component.scss',
})
export class EstadoCuentaComponent {
  private readonly fb = inject(FormBuilder);
  private readonly http = inject(HttpClient);
  private readonly toast = inject(ToastService);

  readonly form = this.fb.group({
    idEstudiante: ['', Validators.required],
  });

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);
  readonly result = signal<EstadoCuenta | null>(null);

  buscar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const id = this.form.getRawValue().idEstudiante;
    this.loading.set(true);
    this.error.set(null);
    this.result.set(null);
    this.http.get<EstadoCuenta>(`${environment.apiBase}/estudiantes/${id}/estado-cuenta`).subscribe({
      next: (res) => {
        this.result.set(res);
        this.loading.set(false);
      },
      error: (err) => {
        const message = err?.status === 404
          ? 'No existe un estudiante con ese ID.'
          : err?.error?.message || 'Error al consultar el estado de cuenta';
        this.error.set(message);
        this.toast.error(message);
        this.loading.set(false);
      },
    });
  }
}

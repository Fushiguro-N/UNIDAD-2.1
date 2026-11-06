import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { environment } from '../../../environments/environment';

interface EstadoCuenta {
  idEstudiante: string;
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
        this.error.set(err?.error?.message || err?.message || 'No se encontro informacion');
        this.loading.set(false);
      },
    });
  }
}

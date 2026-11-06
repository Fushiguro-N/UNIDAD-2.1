import { CommonModule } from '@angular/common';
import { Component, OnInit, inject, signal } from '@angular/core';
import { FormArray, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../../core/api.service';

const PAGO_INFO: Record<string, string> = {
  TARJETA: 'Pago con tarjeta: se validará el cargo antes de confirmar la matrícula.',
  TRANSFERENCIA: 'Transferencia bancaria: adjuntar N° de operación en ventanilla antes de 48h.',
  EFECTIVO: 'Pago en efectivo: acercarse a caja para completar el pago y recoger el comprobante.',
  YAPE: 'Pago por Yape: enviar captura del N° de operación a tesorería.',
  PLIN: 'Pago por Plin: enviar captura del N° de operación a tesorería.',
};

@Component({
  selector: 'app-enrolar',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './enrolar.component.html',
  styleUrl: './enrolar.component.scss',
})
export class EnrolarComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly api = inject(ApiService);

  readonly form = this.fb.group({
    periodoAcademico: [''],
    metodoPago: ['', Validators.required],
    detalles: this.fb.array([] as any[]),
  });

  readonly contenidos = signal<any[]>([]);
  readonly pagoInfo = signal('');
  readonly saving = signal(false);
  readonly error = signal<string | null>(null);
  readonly success = signal<any | null>(null);

  get detallesArray(): FormArray {
    return this.form.controls.detalles as FormArray;
  }

  constructor() {
    this.form.controls.metodoPago.valueChanges.subscribe((v) => {
      this.pagoInfo.set(PAGO_INFO[v ?? ''] ?? '');
    });
  }

  ngOnInit(): void {
    this.api.list('contenidos').subscribe({
      next: (data) => this.contenidos.set(data ?? []),
      error: () => this.contenidos.set([]),
    });
    this.addDetalle();
  }

  addDetalle(): void {
    this.detallesArray.push(
      this.fb.group({
        idContenido: ['', Validators.required],
        cantidad: [1, [Validators.required, Validators.min(1)]],
      }),
    );
  }

  removeDetalle(i: number): void {
    this.detallesArray.removeAt(i);
  }

  submit(): void {
    if (this.form.invalid || this.detallesArray.length === 0) {
      this.form.markAllAsTouched();
      return;
    }
    this.saving.set(true);
    this.error.set(null);
    this.success.set(null);
    const raw = this.form.getRawValue();
    const payload = {
      periodoAcademico: raw.periodoAcademico,
      metodoPago: raw.metodoPago,
      detalles: (raw.detalles ?? []).map((d: any) => ({
        idContenido: Number(d.idContenido),
        cantidad: Number(d.cantidad),
      })),
    };
    this.api.post('matriculas/enrolar', payload).subscribe({
      next: (res) => {
        this.success.set(res);
        this.saving.set(false);
        this.form.reset();
        this.detallesArray.clear();
        this.addDetalle();
      },
      error: (err) => {
        this.error.set(err?.error?.message || err?.message || 'Error al enrolar');
        this.saving.set(false);
      },
    });
  }
}

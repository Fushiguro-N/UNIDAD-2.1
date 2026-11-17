import { Component } from '@angular/core';

@Component({
  selector: 'app-realizar-pago',
  standalone: true,
  template: `
    <div class="form-page">
      <h1>Realizar Pago</h1>
      <p>
        Los pagos se procesan a traves de la pasarela de pago institucional. Consulta tu
        "Estado de Cuenta" para ver tu deuda pendiente antes de continuar con el pago.
      </p>
    </div>
  `,
})
export class RealizarPagoComponent {}

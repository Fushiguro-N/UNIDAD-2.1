import { Component } from '@angular/core';

@Component({
  selector: 'app-sin-acceso',
  standalone: true,
  template: `
    <div class="form-page">
      <h1>Acceso no autorizado</h1>
      <p>No tienes permisos para acceder a este modulo del Portal de Ingresos y Pagos.</p>
    </div>
  `,
})
export class SinAccesoComponent {}

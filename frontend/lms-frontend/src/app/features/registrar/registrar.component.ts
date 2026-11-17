import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ENTITIES } from '../../core/entities.config';

@Component({
  selector: 'app-registrar',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.scss',
})
export class RegistrarComponent {
  private readonly router = inject(Router);

  readonly entidades = ENTITIES.filter((e) => !e.readOnly);
  readonly resourceSeleccionado = signal<string>('');

  continuar(): void {
    if (!this.resourceSeleccionado()) return;
    this.router.navigate(['/', this.resourceSeleccionado(), 'nuevo']);
  }
}

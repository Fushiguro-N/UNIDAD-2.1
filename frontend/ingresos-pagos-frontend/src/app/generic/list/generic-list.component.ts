import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../core/api.service';
import { ENTITIES } from '../../core/entities.config';
import { ToastService } from '../../core/toast.service';
import { ConfirmService } from '../../core/confirm.service';

@Component({
  selector: 'app-generic-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './generic-list.component.html',
  styleUrl: './generic-list.component.scss',
})
export class GenericListComponent {
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly api = inject(ApiService);
  private readonly toast = inject(ToastService);
  private readonly confirmService = inject(ConfirmService);

  readonly rows = signal<any[]>([]);
  readonly columns = signal<string[]>([]);
  readonly loading = signal(true);
  readonly error = signal<string | null>(null);
  readonly config = signal(ENTITIES[0]);

  constructor() {
    this.route.paramMap.subscribe((params) => {
      const resource = params.get('resource')!;
      const cfg = ENTITIES.find((e) => e.resource === resource);
      if (!cfg) {
        this.error.set(`Recurso desconocido: ${resource}`);
        return;
      }
      this.config.set(cfg);
      this.load(resource);
    });
  }

  load(resource: string): void {
    this.loading.set(true);
    this.error.set(null);
    this.api.list(resource).subscribe({
      next: (data) => {
        this.rows.set(data ?? []);
        const keys = data && data.length ? Object.keys(data[0]).filter((k) => k !== 'password') : [];
        this.columns.set(keys);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err?.error?.message || err?.message || 'Error al cargar datos');
        this.loading.set(false);
      },
    });
  }

  cell(row: any, col: string): string {
    const v = row[col];
    if (v === null || v === undefined) return '';
    if (typeof v === 'object') return JSON.stringify(v);
    return String(v);
  }

  idOf(row: any): string {
    return row.id ?? row.idPersona ?? row.uuid ?? '';
  }

  edit(row: any): void {
    this.router.navigate([this.config().resource, this.idOf(row), 'editar']);
  }

  create(): void {
    this.router.navigate([this.config().resource, 'nuevo']);
  }

  async remove(row: any): Promise<void> {
    const ok = await this.confirmService.confirm('¿Eliminar este registro? Esta acción no se puede deshacer.', {
      title: 'Eliminar registro',
      confirmText: 'Eliminar',
      danger: true,
    });
    if (!ok) return;
    this.api.delete(this.config().resource, this.idOf(row)).subscribe({
      next: () => {
        this.load(this.config().resource);
        this.toast.success('Registro eliminado correctamente');
      },
      error: (err) => {
        const message = err?.error?.message || err?.message || 'Error al eliminar';
        this.error.set(message);
        this.toast.error(message);
      },
    });
  }
}

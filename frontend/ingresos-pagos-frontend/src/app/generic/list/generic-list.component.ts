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

  readonly page = signal(0);
  readonly size = signal(20);
  readonly totalPages = signal(0);
  readonly totalElements = signal(0);

  constructor() {
    this.route.paramMap.subscribe((params) => {
      const resource = params.get('resource')!;
      const cfg = ENTITIES.find((e) => e.resource === resource);
      if (!cfg) {
        this.error.set(`Recurso desconocido: ${resource}`);
        return;
      }
      this.config.set(cfg);
      this.page.set(0);
      this.load(resource);
    });
  }

  load(resource: string): void {
    this.loading.set(true);
    this.error.set(null);
    this.api.listPaged(resource, this.page(), this.size()).subscribe({
      next: (res) => {
        const data = res?.content ?? [];
        this.rows.set(data);
        const keys = data.length ? Object.keys(data[0]).filter((k) => k !== 'password') : [];
        this.columns.set(keys);
        this.totalPages.set(res?.totalPages ?? 0);
        this.totalElements.set(res?.totalElements ?? 0);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err?.error?.message || err?.message || 'Error al cargar datos');
        this.loading.set(false);
      },
    });
  }

  nextPage(): void {
    if (this.page() + 1 >= this.totalPages()) return;
    this.page.set(this.page() + 1);
    this.load(this.config().resource);
  }

  prevPage(): void {
    if (this.page() <= 0) return;
    this.page.set(this.page() - 1);
    this.load(this.config().resource);
  }

  cell(row: any, col: string): string {
    const v = row[col];
    if (v === null || v === undefined) return '';
    if (typeof v === 'object') return v.nombre ?? v.codigo ?? v.id ?? JSON.stringify(v);
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

import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../core/api.service';
import { ENTITIES } from '../../core/entities.config';

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

  remove(row: any): void {
    if (!confirm('¿Eliminar este registro?')) return;
    this.api.delete(this.config().resource, this.idOf(row)).subscribe({
      next: () => this.load(this.config().resource),
      error: (err) => this.error.set(err?.error?.message || err?.message || 'Error al eliminar'),
    });
  }
}

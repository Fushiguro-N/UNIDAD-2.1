import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../core/api.service';
import { AuthService } from '../../core/auth.service';
import { ENTITIES, currentRole, effectiveConfig } from '../../core/entities.config';
import { ToastService } from '../../core/toast.service';
import { ConfirmService } from '../../core/confirm.service';

@Component({
  selector: 'app-generic-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './generic-list.component.html',
  styleUrl: './generic-list.component.scss',
})
export class GenericListComponent implements OnChanges {
  /** When set, the list runs embedded (e.g. inside a module tab) instead of reading route params. */
  @Input() resourceOverride?: string;
  @Output() editRequested = new EventEmitter<any>();
  @Output() createRequested = new EventEmitter<void>();

  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly api = inject(ApiService);
  private readonly auth = inject(AuthService);
  private readonly toast = inject(ToastService);
  private readonly confirmService = inject(ConfirmService);

  readonly rows = signal<any[]>([]);
  readonly columns = signal<string[]>([]);
  readonly loading = signal(true);
  readonly error = signal<string | null>(null);
  readonly config = signal(ENTITIES[0]);

  constructor() {
    this.route.paramMap.subscribe((params) => {
      if (this.resourceOverride) return;
      const resource = params.get('resource')!;
      this.setResource(resource);
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['resourceOverride'] && this.resourceOverride) {
      this.setResource(this.resourceOverride);
    }
  }

  private setResource(resource: string): void {
    const cfg = ENTITIES.find((e) => e.resource === resource);
    if (!cfg) {
      this.error.set(`Recurso desconocido: ${resource}`);
      return;
    }
    this.config.set(effectiveConfig(cfg, currentRole(this.auth)));
    this.load(resource);
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

  columnLabel(col: string): string {
    return col
      .replace(/([a-z0-9])([A-Z])/g, '$1 $2')
      .replace(/^./, (c) => c.toUpperCase());
  }

  private objectLabel(v: Record<string, any>): string {
    if (v['nombres'] || v['apellidos']) {
      return [v['nombres'], v['apellidos']].filter(Boolean).join(' ');
    }
    for (const key of ['nombre', 'titulo', 'codigo', 'nombreSeccion', 'email', 'descripcion']) {
      if (v[key]) return String(v[key]);
    }
    return v['id'] !== undefined ? `#${v['id']}` : JSON.stringify(v);
  }

  cell(row: any, col: string): string {
    const v = row[col];
    if (v === null || v === undefined) return '';
    if (typeof v === 'boolean') return v ? 'Si' : 'No';
    if (Array.isArray(v)) return v.length ? `${v.length} elemento(s)` : '';
    if (typeof v === 'object') return this.objectLabel(v);
    const field = this.config().fields.find((f) => f.name === col);
    if (field?.type === 'time') return this.formatTime12(String(v));
    return String(v);
  }

  private formatTime12(v: string): string {
    const match = /^(\d{1,2}):(\d{2})/.exec(v);
    if (!match) return v;
    const h = parseInt(match[1], 10);
    const ap = h >= 12 ? 'PM' : 'AM';
    let h12 = h % 12;
    if (h12 === 0) h12 = 12;
    return `${String(h12).padStart(2, '0')}:${match[2]} ${ap}`;
  }

  idOf(row: any): string {
    return row.id ?? row.idPersona ?? row.uuid ?? '';
  }

  edit(row: any): void {
    if (this.resourceOverride) {
      this.editRequested.emit(row);
    } else {
      this.router.navigate([this.config().resource, this.idOf(row), 'editar']);
    }
  }

  create(): void {
    if (this.resourceOverride) {
      this.createRequested.emit();
    } else {
      this.router.navigate([this.config().resource, 'nuevo']);
    }
  }

  reload(): void {
    this.load(this.config().resource);
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

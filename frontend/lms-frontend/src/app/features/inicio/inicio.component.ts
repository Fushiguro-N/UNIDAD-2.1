import { CommonModule } from '@angular/common';
import { Component, OnInit, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../core/api.service';
import { AuthService } from '../../core/auth.service';
import { currentRole } from '../../core/entities.config';
import { MODULES } from '../../core/modules.config';

interface CursoCard {
  id: number;
  nombre: string;
  seccion: string;
  periodo: string;
  docente: string;
}

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.scss',
})
export class InicioComponent implements OnInit {
  private readonly auth = inject(AuthService);
  private readonly api = inject(ApiService);
  private readonly router = inject(Router);

  readonly cursos = signal<CursoCard[]>([]);
  readonly loading = signal(true);

  ngOnInit(): void {
    if (currentRole(this.auth) !== 'ESTUDIANTE') {
      this.router.navigate(['/', MODULES[0].key]);
      return;
    }

    this.api.list('secciones-curso').subscribe({
      next: (data) => {
        const cursos = (data ?? [])
          .filter((s: any) => s.activo)
          .map((s: any, i: number) => ({
            id: s.id,
            nombre: s.curso?.nombre ?? 'Curso',
            seccion: s.nombreSeccion ?? '',
            periodo: s.periodoAcademico?.nombre ?? '',
            docente: [s.docente?.nombres, s.docente?.apellidos].filter(Boolean).join(' ') || 'Sin asignar',
          }))
          .slice(0, 6);
        this.cursos.set(cursos);
        this.loading.set(false);
      },
      error: () => {
        this.cursos.set([]);
        this.loading.set(false);
      },
    });
  }

  initials(nombre: string): string {
    return nombre
      .split(' ')
      .filter(Boolean)
      .slice(0, 2)
      .map((w) => w[0]?.toUpperCase())
      .join('');
  }
}

import { Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: '',
        pathMatch: 'full',
        loadComponent: () => import('./features/inicio/inicio.component').then((m) => m.InicioComponent),
      },
      {
        path: 'enrolar',
        loadComponent: () => import('./features/enrolar/enrolar.component').then((m) => m.EnrolarComponent),
      },
      {
        path: 'registrar',
        loadComponent: () => import('./features/registrar/registrar.component').then((m) => m.RegistrarComponent),
      },
      {
        path: 'crear-estudiante',
        loadComponent: () =>
          import('./features/crear-estudiante/crear-estudiante.component').then((m) => m.CrearEstudianteComponent),
      },
      {
        path: ':moduleKey',
        loadComponent: () =>
          import('./features/module-page/module-page.component').then((m) => m.ModulePageComponent),
      },
    ],
  },
];

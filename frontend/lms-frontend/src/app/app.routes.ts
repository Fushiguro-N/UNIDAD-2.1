import { Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { ENTITIES } from './core/entities.config';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: ENTITIES[0].resource, pathMatch: 'full' },
      {
        path: 'enrolar',
        loadComponent: () => import('./features/enrolar/enrolar.component').then((m) => m.EnrolarComponent),
      },
      {
        path: ':resource',
        loadComponent: () => import('./generic/list/generic-list.component').then((m) => m.GenericListComponent),
      },
      {
        path: ':resource/nuevo',
        loadComponent: () => import('./generic/form/generic-form.component').then((m) => m.GenericFormComponent),
      },
      {
        path: ':resource/:id/editar',
        loadComponent: () => import('./generic/form/generic-form.component').then((m) => m.GenericFormComponent),
      },
    ],
  },
];

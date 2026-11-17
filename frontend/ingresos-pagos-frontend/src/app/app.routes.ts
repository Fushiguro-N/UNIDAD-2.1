import { Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { HomeRedirectComponent } from './features/home-redirect/home-redirect.component';
import { adminGuard, estudianteGuard, estudianteOrAdminGuard, roleGateGuard } from './core/guards';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    canActivate: [roleGateGuard],
    children: [
      { path: '', component: HomeRedirectComponent },
      {
        path: 'sin-acceso',
        loadComponent: () => import('./features/sin-acceso/sin-acceso.component').then((m) => m.SinAccesoComponent),
      },
      {
        path: 'estado-cuenta',
        canActivate: [estudianteOrAdminGuard],
        loadComponent: () =>
          import('./features/estado-cuenta/estado-cuenta.component').then((m) => m.EstadoCuentaComponent),
      },
      {
        path: 'realizar-pago',
        canActivate: [estudianteGuard],
        loadComponent: () =>
          import('./features/realizar-pago/realizar-pago.component').then((m) => m.RealizarPagoComponent),
      },
      {
        path: ':resource',
        canActivate: [adminGuard],
        loadComponent: () => import('./generic/list/generic-list.component').then((m) => m.GenericListComponent),
      },
      {
        path: ':resource/nuevo',
        canActivate: [adminGuard],
        loadComponent: () => import('./generic/form/generic-form.component').then((m) => m.GenericFormComponent),
      },
      {
        path: ':resource/:id/editar',
        canActivate: [adminGuard],
        loadComponent: () => import('./generic/form/generic-form.component').then((m) => m.GenericFormComponent),
      },
    ],
  },
];

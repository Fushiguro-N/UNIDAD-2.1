import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const roleGateGuard: CanActivateFn = () => {
  const auth = inject(AuthService);
  const router = inject(Router);
  if (auth.isDocente) {
    return router.parseUrl('/sin-acceso');
  }
  return true;
};

export const adminGuard: CanActivateFn = () => {
  const auth = inject(AuthService);
  const router = inject(Router);
  return auth.isAdmin ? true : router.parseUrl('/sin-acceso');
};

export const estudianteOrAdminGuard: CanActivateFn = () => {
  const auth = inject(AuthService);
  const router = inject(Router);
  return auth.isEstudiante || auth.isAdmin ? true : router.parseUrl('/sin-acceso');
};

export const estudianteGuard: CanActivateFn = () => {
  const auth = inject(AuthService);
  const router = inject(Router);
  return auth.isEstudiante ? true : router.parseUrl('/sin-acceso');
};

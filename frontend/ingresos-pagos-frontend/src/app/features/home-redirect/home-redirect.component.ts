import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../core/auth.service';
import { ENTITIES } from '../../core/entities.config';

@Component({
  selector: 'app-home-redirect',
  standalone: true,
  template: '',
})
export class HomeRedirectComponent {
  constructor() {
    const auth = inject(AuthService);
    const router = inject(Router);
    if (auth.isAdmin) {
      router.navigate(['/', ENTITIES[0].resource]);
    } else if (auth.isEstudiante) {
      router.navigate(['/estado-cuenta']);
    } else {
      router.navigate(['/sin-acceso']);
    }
  }
}

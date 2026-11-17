import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AuthService } from '../core/auth.service';
import { currentRole } from '../core/entities.config';
import { MODULES } from '../core/modules.config';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss',
})
export class LayoutComponent {
  readonly auth = inject(AuthService);
  readonly modules = MODULES.filter((m) => !m.roles || m.roles.includes(currentRole(this.auth)));
  readonly navOpen = signal(true);

  toggleNav(): void {
    this.navOpen.set(!this.navOpen());
  }

  logout(): void {
    this.auth.logout();
  }
}

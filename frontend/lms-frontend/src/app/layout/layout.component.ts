import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AuthService } from '../core/auth.service';
import { ENTITIES } from '../core/entities.config';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss',
})
export class LayoutComponent {
  readonly auth = inject(AuthService);
  readonly entities = ENTITIES;
  readonly navOpen = signal(true);

  toggleNav(): void {
    this.navOpen.set(!this.navOpen());
  }

  logout(): void {
    this.auth.logout();
  }
}

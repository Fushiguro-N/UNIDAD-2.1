import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AuthService } from '../core/auth.service';
import { ENTITIES, MenuGroup } from '../core/entities.config';

const GROUP_ORDER: MenuGroup[] = [
  'Caja y Transacciones',
  'Configuracion Financiera',
  'Convenios y Becas',
  'Auditoria y Sistema',
];

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss',
})
export class LayoutComponent {
  readonly auth = inject(AuthService);
  readonly navOpen = signal(true);
  readonly openGroups = signal(new Set<string>());

  readonly adminGroups = GROUP_ORDER.map((group) => ({
    group,
    entities: ENTITIES.filter((e) => e.group === group),
  })).filter((g) => g.entities.length > 0);

  toggleNav(): void {
    this.navOpen.set(!this.navOpen());
  }

  toggleGroup(group: string): void {
    const next = new Set(this.openGroups());
    if (next.has(group)) {
      next.delete(group);
    } else {
      next.add(group);
    }
    this.openGroups.set(next);
  }

  isGroupOpen(group: string): boolean {
    return this.openGroups().has(group);
  }

  logout(): void {
    this.auth.logout();
  }
}

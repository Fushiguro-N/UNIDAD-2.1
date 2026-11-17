import { CommonModule } from '@angular/common';
import { Component, ViewChild, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ENTITIES, currentRole, effectiveConfig } from '../../core/entities.config';
import { MODULES } from '../../core/modules.config';
import { GenericListComponent } from '../../generic/list/generic-list.component';
import { GenericFormComponent } from '../../generic/form/generic-form.component';
import { ModalComponent } from '../../shared/modal/modal.component';
import { AuthService } from '../../core/auth.service';

@Component({
  selector: 'app-module-page',
  standalone: true,
  imports: [CommonModule, GenericListComponent, GenericFormComponent, ModalComponent],
  templateUrl: './module-page.component.html',
  styleUrl: './module-page.component.scss',
})
export class ModulePageComponent {
  private readonly route = inject(ActivatedRoute);
  private readonly auth = inject(AuthService);

  @ViewChild(GenericListComponent) list?: GenericListComponent;

  readonly tabs = signal<{ resource: string; label: string; readOnly?: boolean; noCreate?: boolean }[]>([]);
  readonly moduleLabel = signal('');
  readonly activeResource = signal('');
  readonly showForm = signal(false);
  readonly editId = signal<string | null>(null);

  constructor() {
    this.route.paramMap.subscribe((params) => {
      const key = params.get('moduleKey')!;
      const mod = MODULES.find((m) => m.key === key);
      if (!mod) return;
      this.moduleLabel.set(mod.label);
      const role = currentRole(this.auth);
      const tabs = mod.resources
        .map((r) => ENTITIES.find((e) => e.resource === r))
        .filter((e): e is NonNullable<typeof e> => !!e)
        .map((e) => effectiveConfig(e, role))
        .filter((e) => !e.hidden)
        .map((e) => ({ resource: e.resource, label: e.label, readOnly: e.readOnly, noCreate: e.noCreate }));
      this.tabs.set(tabs);
      this.activeResource.set(tabs[0]?.resource ?? '');
      this.showForm.set(false);
    });
  }

  activeReadOnly(): boolean {
    return !!this.tabs().find((t) => t.resource === this.activeResource())?.readOnly;
  }

  activeLabel(): string {
    return this.tabs().find((t) => t.resource === this.activeResource())?.label ?? this.moduleLabel();
  }

  activeNoCreate(): boolean {
    return !!this.tabs().find((t) => t.resource === this.activeResource())?.noCreate;
  }

  selectTab(resource: string): void {
    this.activeResource.set(resource);
    this.showForm.set(false);
  }

  openCreate(): void {
    if (this.activeReadOnly() || this.activeNoCreate()) return;
    this.editId.set(null);
    this.showForm.set(true);
  }

  openEdit(row: any): void {
    if (this.activeReadOnly()) return;
    this.editId.set(String(row.id ?? row.idPersona ?? row.uuid ?? ''));
    this.showForm.set(true);
  }

  closeForm(): void {
    this.showForm.set(false);
  }

  onSaved(): void {
    this.showForm.set(false);
    this.list?.reload();
  }
}

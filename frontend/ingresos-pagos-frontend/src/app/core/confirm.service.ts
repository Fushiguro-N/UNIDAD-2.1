import { Injectable, signal } from '@angular/core';

export interface ConfirmRequest {
  title: string;
  message: string;
  confirmText: string;
  cancelText: string;
  danger: boolean;
  resolve: (value: boolean) => void;
}

@Injectable({ providedIn: 'root' })
export class ConfirmService {
  readonly request = signal<ConfirmRequest | null>(null);

  confirm(message: string, options?: { title?: string; confirmText?: string; cancelText?: string; danger?: boolean }): Promise<boolean> {
    return new Promise<boolean>((resolve) => {
      this.request.set({
        title: options?.title ?? 'Confirmar',
        message,
        confirmText: options?.confirmText ?? 'Confirmar',
        cancelText: options?.cancelText ?? 'Cancelar',
        danger: options?.danger ?? false,
        resolve,
      });
    });
  }

  respond(value: boolean): void {
    const req = this.request();
    if (!req) return;
    this.request.set(null);
    req.resolve(value);
  }
}

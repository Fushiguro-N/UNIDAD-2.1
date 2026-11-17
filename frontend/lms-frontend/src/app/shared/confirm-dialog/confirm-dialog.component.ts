import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ConfirmService } from '../../core/confirm.service';

@Component({
  selector: 'app-confirm-dialog',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './confirm-dialog.component.html',
  styleUrl: './confirm-dialog.component.scss',
})
export class ConfirmDialogComponent {
  constructor(public confirmService: ConfirmService) {}
}

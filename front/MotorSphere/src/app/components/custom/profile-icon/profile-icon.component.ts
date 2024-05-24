import { Component, HostListener, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { User } from '../../../models/User';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-profile-icon',
  standalone: true,
  imports: [RouterLink, NgClass],
  templateUrl: './profile-icon.component.html',
  styleUrl: './profile-icon.component.css',
})
export class ProfileIconComponent {
  public show: boolean = false;

  @Input()
  public profileImage: string | ArrayBuffer | null = null;
  @Input()
  public user: User = <User>{};

  showHide() {
    console.log(this.show);
    console.log(this.user);
    this.show = !this.show;
  }
}

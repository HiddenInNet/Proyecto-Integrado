import { Component, Input, OnInit, inject } from '@angular/core';
import { User } from '../../../models/User';
import { BidderService } from '../../../services/bidder.service';
import { AuthService } from '../../../services/auth.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-delete-profile',
  standalone: true,
  imports: [],
  templateUrl: './delete-profile.component.html',
  styleUrl: './delete-profile.component.css',
})
export class DeleteProfileComponent implements OnInit {
  @Input()
  public user: User = <User>{};
  private userService: UserService = inject(UserService);
  private ruta: Router = inject(Router);
  private authService: AuthService = inject(AuthService);

  ngOnInit(): void {}

  removeProfile() {
    this.userService.removeUser(this.user.id).subscribe({
      next: (data) => {
        this.authService.removeAllCookies();
        this.ruta.navigate(['/login']);
      },
      error: (err) => {
      },
    });
  }
}

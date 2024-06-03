import { Component, Input, OnInit, inject } from '@angular/core';
import { User } from '../../../models/User';
import { BidderService } from '../../../services/bidder.service';
import { AuthService } from '../../../services/auth.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { DataService } from '../../../services/data.service';

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
  private bidderService: BidderService = inject(BidderService);
  private dataService: DataService = inject(DataService);

  ngOnInit(): void {}

  removeProfile() {
    this.userService.removeUser(this.user.id).subscribe({
      next: (data) => {
        this.authService.removeAllCookies();
        this.dataService.clearBidder();
        this.dataService.clearFechas();
        this.dataService.clearUser();
        this.dataService.clearOfertante();
        this.authService.logOut();
        this.ruta.navigate(['/login']);
      },
      error: (err) => {},
    });
  }
}

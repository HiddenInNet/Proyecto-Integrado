import { Component, Input, OnInit, inject } from '@angular/core';
import { BidderService } from '../../../services/bidder.service';
import { User } from '../../../models/User';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-bidder-user',
  standalone: true,
  imports: [],
  templateUrl: './bidder-user.component.html',
  styleUrl: './bidder-user.component.css',
})
export class BidderUserComponent implements OnInit {
  @Input()
  public user: User = <User>{};

  private bidderService: BidderService = inject(BidderService);
  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);
  private dataService: DataService = inject(DataService);

  ngOnInit(): void {}

  removeBidder() {
    this.bidderService.removeBidder(this.user.id).subscribe({
      next: (data) => {
        this.authService.removeCookie('bidder');
        this.dataService.setBidder(false);
        this.router.navigate(['/home']);
      },
    });
  }
}

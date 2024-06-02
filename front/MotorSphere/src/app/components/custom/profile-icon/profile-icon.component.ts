import { Component, HostListener, Input, OnInit, inject } from '@angular/core';
import { Router, RouterLink, UrlTree } from '@angular/router';
import { User } from '../../../models/User';
import { NgClass } from '@angular/common';
import { BidderService } from '../../../services/bidder.service';
import { LogService } from '../../../services/log.service';
import { AuthService } from '../../../services/auth.service';
import { Bidder } from '../../../models/Bidder';
import { UserService } from '../../../services/user.service';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-profile-icon',
  standalone: true,
  imports: [RouterLink, NgClass],
  templateUrl: './profile-icon.component.html',
  styleUrl: './profile-icon.component.css',
})
export class ProfileIconComponent implements OnInit {
  public show: boolean = false;
  private bidderService: BidderService = inject(BidderService);
  private authService: AuthService = inject(AuthService);
  private dataService: DataService = inject(DataService);
  private route: Router = inject(Router);

  @Input()
  public profileImage: string | ArrayBuffer | null = null;
  @Input()
  public user: User = <User>{};
  public bidder: Bidder | null = <Bidder>{};
  public bidderBool: boolean | null = false;

  ngOnInit(): void {
    this.dataService.bidder$.subscribe((data) => {
      this.bidderBool = data;
    });

    const userId = Number(this.authService.getCookie('user_id'));
    if (!isNaN(userId)) {
      this.bidderService.getBidderByUserId(userId).subscribe({
        next: (data) => {
          if (data !== null) {
            this.dataService.setBidder(true);
            this.bidder = data;
            this.authService.setCookie('bidder', JSON.stringify(data));
          } else {
            this.bidder = null;
            this.dataService.setBidder(false);
          }
        },
        error: (err) => {
        },
      });
    } else {
    }
  }

  showHide() {
    this.show = !this.show;
  }

  newEvent() {
    this.show = false;
    this.route.navigate(['/create-event']);
  }
}

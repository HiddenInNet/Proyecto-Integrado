import { Component, HostListener, Input, OnInit, inject } from '@angular/core';
import { RouterLink, UrlTree } from '@angular/router';
import { User } from '../../../models/User';
import { NgClass } from '@angular/common';
import { BidderService } from '../../../services/bidder.service';
import { LogService } from '../../../services/log.service';
import { AuthService } from '../../../services/auth.service';
import { Bidder } from '../../../models/Bidder';

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

  @Input()
  public profileImage: string | ArrayBuffer | null = null;
  @Input()
  public user: User = <User>{};
  public bidder: Bidder | null = <Bidder>{};

  ngOnInit(): void {
    this.bidderService.getBidderByUserId(Number(this.authService.getCookie('user_id'))).subscribe({
      next: (data) => {
        console.log('Resultado: ', data);
        data !== null
          ? this.bidder = data
          : this.bidder = null;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  showHide() {
    console.log(this.show);
    console.log(this.user);
    this.show = !this.show;
  }
}

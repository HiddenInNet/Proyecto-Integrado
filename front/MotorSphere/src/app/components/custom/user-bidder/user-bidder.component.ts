import { Component, Input, OnInit, inject } from '@angular/core';
import { User } from '../../../models/User';
import { BidderService } from '../../../services/bidder.service';
import { Router } from '@angular/router';
import { DataService } from '../../../services/data.service';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-user-bidder',
  standalone: true,
  imports: [],
  templateUrl: './user-bidder.component.html',
  styleUrl: './user-bidder.component.css',
})
export class UserBidderComponent implements OnInit {
  @Input()
  public user: User = <User>{};

  private bidderService: BidderService = inject(BidderService);
  private router: Router = inject(Router);
  private dataService: DataService = inject(DataService);
  private authService: AuthService = inject(AuthService);

  ngOnInit(): void {
    console.log('Usuario: ', this.user);
  }

  createBidder() {
    this.bidderService.createBidder(this.user).subscribe({
      next: (data) => {
        console.log('Respuesta nuevo ofertante insertado: ', data);
        this.authService.setCookie('bidder', JSON.stringify(data));
        this.dataService.setBidder(true);
        this.dataService.setOfertante(data);
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Error de insercion', err);
      },
    });
  }
}

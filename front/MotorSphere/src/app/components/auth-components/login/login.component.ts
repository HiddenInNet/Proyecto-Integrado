import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Login } from '../../../models/Login';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { LogService } from '../../../services/log.service';
import { BidderService } from '../../../services/bidder.service';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  public loginForm: Login = <Login>{};
  private logService: LogService = inject(LogService);
  private bidderService: BidderService = inject(BidderService);
  private dataService: DataService = inject(DataService);

  public invalid: boolean = false;

  constructor(
    private peti: AuthService,
    private ruta: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.invalid = false;
  }

  onSubmit(loginForm: Login) {
    console.log('Dentro de onSubmit: Data -> ', loginForm);

    this.peti.login(loginForm).subscribe({
      next: (data) => {
        console.log('Respuesta de login: ', data);
        this.logService.isLoggedIn$.next(true);
        this.peti.setCookie('user_jwt', data.jwt);
        this.peti.setCookie('user_id', String(data.userId));

        this.bidderService.getBidderByUserId(data.userId).subscribe({
          next: (data) => {
            console.log('Es un ofertante: ', data);
            this.dataService.setBidder(true);
            this.dataService.setOfertante(data);
          },
          error: (err) => {
            this.dataService.setBidder(false);
          },
        });

        this.ruta.navigate(['home']);
      },
      error: (error) => {
        console.log(error);
        this.invalid = true;
      },
    });
  }
}

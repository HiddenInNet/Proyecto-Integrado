import { Component, Input, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ButtonRegisterComponent } from '../../custom/button-register/button-register.component';
import { ButtonLoginComponent } from '../../custom/button-login/button-login.component';
import { ProfileIconComponent } from '../../custom/profile-icon/profile-icon.component';
import { User } from '../../../models/User';
import { UserService } from '../../../services/user.service';
import { AuthService } from '../../../services/auth.service';
import { LogService } from '../../../services/log.service';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink,
    ButtonRegisterComponent,
    ButtonLoginComponent,
    ProfileIconComponent,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  private authService = inject(AuthService);
  private logService: LogService = inject(LogService);

  public logged: boolean = false;

  public user: User = <User>{};
  public profileImage: string | ArrayBuffer | null = null;

  constructor(
    private peti: UserService,
    private ruta: Router,
    private activatedRoute: ActivatedRoute,
    private dataService: DataService
  ) {}

  ngOnInit(): void {
    this.logService.isLoggedIn$.subscribe((res) => {
      this.dataService.user$.subscribe((response) => {
        this.logged = this.authService.isLogged();
      if (this.logged && response.id !== 0) {
        this.peti
          .getUserById(parseInt(this.authService.getCookie('user_id')))
          .subscribe({
            next: (data) => {
              this.user = data;
              this.authService.setCookie('user', JSON.stringify(data));

              if (this.user) {
                this.peti.getImage(this.user.profileImage).subscribe(
                  (data: Blob) => {
                    const reader = new FileReader();
                    reader.onload = () => {
                      this.profileImage = reader.result;
                    };
                    reader.readAsDataURL(data);
                  },
                  (error) => {
                  }
                );
              }
            },
          });
      }
      })
    });
  }
}

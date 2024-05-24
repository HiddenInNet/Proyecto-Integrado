import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Login } from '../../../models/Login';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { LogService } from '../../../services/log.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {

  public loginForm: Login = <Login>{};
  private logService: LogService = inject(LogService)

  constructor(private peti: AuthService, private ruta: Router, private activatedRoute: ActivatedRoute) { }

  onSubmit(loginForm: Login) {
    console.log("Dentro de onSubmit: Data -> ",loginForm)

    this.peti.login(loginForm).subscribe({
      next: data => {
        console.log("Respuesta de login: ",data);
        this.logService.isLoggedIn$.next(true);
        this.peti.setCookie('user_jwt', data.jwt)
        this.peti.setCookie('user_id', String(data.userId));
        this.ruta.navigate(['home'])
      },
      error: error => console.log(error)
    })
  }
}

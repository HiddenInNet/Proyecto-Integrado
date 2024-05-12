import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Login } from '../../../models/Login';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {

  public loginForm: Login = <Login>{};

  constructor(private peti: AuthService, private ruta: Router, private activatedRoute: ActivatedRoute) { }

  onSubmit(loginForm: Login) {
    console.log("Dentro de onSubmit: Data -> ",loginForm)

    this.peti.login(loginForm).subscribe({
      next: data => {console.log(data)},
      error: error => console.log(error)
    })
  }
}

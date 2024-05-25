import { Component } from '@angular/core';
import { Register } from '../../../models/Register';
import { AuthService } from '../../../services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { environment } from '../../../../environments/environment.development';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  public registerForm: Register = <Register>{};
  public terms: boolean = false;

  constructor(
    private peti: AuthService,
    private ruta: Router,
    private activatedRoute: ActivatedRoute,
    private authService: AuthService
  ) {}

  onSubmit(formValue: Register) {
    console.log('Valor del formulario register: ', formValue);
    if (!this.terms) {
      alert('Debe aceptar los términos y condiciones para registrarse.');
      return;
    }
    formValue.profileImage = environment.DEFAULT_PROFILE_IMAGE;
    formValue.password = "qwerty"

    this.authService.createUser(formValue).subscribe({
      next: (data) => {
        console.log(data);
        this.ruta.navigate(['/login'])
      },
      error: (err) => {
        console.error(err);
      },
    });
  }
}

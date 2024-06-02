import { Component, OnInit } from '@angular/core';
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
export class RegisterComponent implements OnInit {
  public registerForm: Register = <Register>{};
  public terms: boolean = false;
  public invalid: boolean = false;

  constructor(
    private peti: AuthService,
    private ruta: Router,
    private activatedRoute: ActivatedRoute,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.invalid = false;
  }

  onSubmit(formValue: Register) {
    if (!this.terms) {
      alert('Debe aceptar los términos y condiciones para registrarse.');
      return;
    }
    formValue.profileImage = environment.DEFAULT_PROFILE_IMAGE;

    if (formValue.password === formValue.password1) {
      this.invalid = false;

      this.authService.createUser(formValue).subscribe({
        next: (data) => {
          this.ruta.navigate(['/login']);
        },
        error: (err) => {
        },
      });
    } else {
      this.invalid = true;
      return;
    }
  }
}

import { Component, Input, OnInit, inject } from '@angular/core';
import { User } from '../../../models/User';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-form-profile',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './form-profile.component.html',
  styleUrl: './form-profile.component.css',
})
export class FormProfileComponent implements OnInit {
  @Input()
  public user: User = <User>{};
  public formUser: User = <User>{};
  public formattedBirthDate: string = '';

  private userService: UserService = inject(UserService);
  private authService: AuthService = inject(AuthService);

  ngOnInit(): void {
    this.formUser = { ...this.user };

    // Formatear la fecha de nacimiento para el input de tipo date
    this.formUser.birthDate = this.formatDateForDateInput(this.user.birthDate);
    this.formattedBirthDate = this.formatDateForDateInput(this.user.birthDate);

    console.log('Fecha de nacimiento formateada:', this.formattedBirthDate);
  }

  formatDateForDateInput(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2); // Agrega un 0 delante si el mes es menor a 10
    const day = ('0' + date.getDate()).slice(-2); // Agrega un 0 delante si el día es menor a 10
    return `${year}-${month}-${day}`;
  }

  onSubmit(user: User) {
    user.id = this.formUser.id;
    user.profileDate = this.formUser.profileDate;
    user.profileImage = this.formUser.profileImage;

    console.log('Usuario actualizado', user);

    // Convertir la fecha de nacimiento a un formato aceptable por el backend
    user.birthDate = new Date(this.formUser.birthDate).toISOString();

    this.userService.updateUser(user).subscribe({
      next: (data) => {
        console.log('Nuevo usuario: ', data);
        this.user = data;
        this.authService.setCookie('user', String(data));
      },
      error: (err) => {
        console.log('Error: ', err);
      },
    });
  }

  cancel() {
    console.log('Cancelado');
    this.formUser = { ...this.user };
    this.formUser.birthDate = this.formatDateForDateInput(this.user.birthDate);
    this.formattedBirthDate = this.formatDateForDateInput(this.user.birthDate);
    this.authService.setCookie('user', String(this.formUser));
  }
}

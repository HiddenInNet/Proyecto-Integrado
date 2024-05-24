import { Component, OnInit, inject } from '@angular/core';
import { LogService } from '../../../services/log.service';
import { User } from '../../../models/User';
import { UserService } from '../../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { FormProfileComponent } from '../../custom/form-profile/form-profile.component';

@Component({
  selector: 'app-tab-profile',
  standalone: true,
  imports: [FormProfileComponent],
  templateUrl: './tab-profile.component.html',
  styleUrl: './tab-profile.component.css',
})
export class TabProfileComponent implements OnInit {
  private logService: LogService = inject(LogService);
  private userService: UserService = inject(UserService);
  private activedRoute: ActivatedRoute = inject(ActivatedRoute);

  public user: User = <User>{};
  public userBirthDate: string = '';
  public show: number = 0;

  ngOnInit(): void {
    const user_id = this.activedRoute.snapshot.params['id'];

    this.userService.getUserById(user_id).subscribe({
      next: (data) => {
        console.log('Profile user: ', data);
        this.user = data;

        this.userBirthDate = this.setDate(data);
      },
    });
  }

  setDate(data: User): string {
    const date = new Date(data.birthDate);
    const day = String(date.getDate()).padStart(2, '0'); // Agrega un cero al principio si es necesario
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Los meses en JS van de 0 a 11
    const year = date.getFullYear();

    return `${day}-${month}-${year}`;
  }

  mostrar(value: number) {
    if (value === this.show) {
      this.show = 0;
    } else {
      this.show = value;
    }
  }
}

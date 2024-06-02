import { Component, OnInit, inject } from '@angular/core';
import { LogService } from '../../../services/log.service';
import { User } from '../../../models/User';
import { UserService } from '../../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { FormProfileComponent } from '../../custom/form-profile/form-profile.component';
import { DeleteProfileComponent } from '../../custom/delete-profile/delete-profile.component';
import { Bidder } from '../../../models/Bidder';
import { AuthService } from '../../../services/auth.service';
import { UserBidderComponent } from '../../custom/user-bidder/user-bidder.component';
import { BidderUserComponent } from '../../custom/bidder-user/bidder-user.component';
import { MyEventsComponent } from '../../custom/my-events/my-events.component';
import { DataService } from '../../../services/data.service';
import { BidderService } from '../../../services/bidder.service';

@Component({
  selector: 'app-tab-profile',
  standalone: true,
  imports: [
    FormProfileComponent,
    DeleteProfileComponent,
    UserBidderComponent,
    BidderUserComponent,
    MyEventsComponent,
  ],
  templateUrl: './tab-profile.component.html',
  styleUrl: './tab-profile.component.css',
})
export class TabProfileComponent implements OnInit {
  private authService: AuthService = inject(AuthService);
  private logService: LogService = inject(LogService);
  private userService: UserService = inject(UserService);
  private activedRoute: ActivatedRoute = inject(ActivatedRoute);
  private dataService: DataService = inject(DataService);
  private bidderService: BidderService = inject(BidderService);

  public user: User = <User>{};
  public profileImage: string | ArrayBuffer | null = null;
  public bidder: Bidder | null = null;

  public userBirthDate: string = '';
  public show: number = 0;

  ngOnInit(): void {
    const user_id = this.activedRoute.snapshot.params['id'];

    this.dataService.user$.subscribe((data) => {
      this.user = data;
    });

    this.userService.getUserById(user_id).subscribe({
      next: (data) => {
        console.log('Profile user: ', data);
        this.user = data;
        this.userBirthDate = this.setDate(data);

        this.userService.getImage(this.user.profileImage).subscribe(
          (data: Blob) => {
            const reader = new FileReader();
            reader.onload = () => {
              this.profileImage = reader.result;
              console.log('Imagen de perfil: ', !!reader.result);
            };
            reader.readAsDataURL(data);
          },
          (error) => {
            console.error('Error al obtener la imagen:', error);
          }
        );
      },
    });

    if (!!this.authService.getCookie('bidder')) {
      this.bidder = JSON.parse(this.authService.getCookie('bidder'));
      console.log('Hola ', this.bidder);
    } else {
      this.bidder = null;
      console.log('No es un ofertante');
    }
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

  verifyAccount() {
    if (this.bidder?.id) {
      this.bidderService
        .setChecker(this.bidder.id, !this.bidder.checker)
        .subscribe({
          next: (data) => {
            this.bidder = this.bidder;
            console.log(data);
          },
          error: (err) => {
            console.error(err);
          },
        });
    }
  }

}

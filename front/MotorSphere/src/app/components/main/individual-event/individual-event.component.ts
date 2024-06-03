import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from '../../../services/event.service';
import { AddUserToEvent, Event } from '../../../models/Event';
import { UserService } from '../../../services/user.service';
import {
  GoogleMapsModule,
  MapAdvancedMarker,
  MapInfoWindow,
  MapMarker,
} from '@angular/google-maps';
import { BidderService } from '../../../services/bidder.service';
import { BidderUser } from '../../../models/BidderUser';
import { AuthService } from '../../../services/auth.service';
import { DataService } from '../../../services/data.service';
import { User } from '../../../models/User';

@Component({
  selector: 'app-individual-event',
  standalone: true,
  imports: [GoogleMapsModule, MapMarker, MapAdvancedMarker, MapInfoWindow],
  templateUrl: './individual-event.component.html',
  styleUrl: './individual-event.component.css',
})
export class IndividualEventComponent implements OnInit {
  private userService: UserService = inject(UserService);
  private route: ActivatedRoute = inject(ActivatedRoute);
  private eventService: EventService = inject(EventService);
  private bidderService: BidderService = inject(BidderService);
  private authService: AuthService = inject(AuthService);
  private dataService: DataService = inject(DataService);

  public event: Event = <Event>{};
  public eventImage: string | ArrayBuffer | null = null;
  public bidderUser: BidderUser = <BidderUser>{};
  public bidderProfileImage: string | ArrayBuffer | null = null;
  public fechasFormateadas: Array<String> = [];
  public fechaSubscribedStatus: { [key: number]: boolean } = {}; // Objeto para estados de suscripción de fechas

  public fechasParaBotones: { [key: number]: boolean } = {};

  public lat: number = 0;
  public lng: number = 0;

  @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow = <MapInfoWindow>{};

  display: any;
  center: google.maps.LatLngLiteral = {
    lat: this.lat,
    lng: this.lng,
  };
  zoom = 12;

  ngOnInit(): void {
    const eventId = this.route.snapshot.params['id'];
    this.dataService.fechas$.subscribe((data) => {
      this.fechasParaBotones = data
    })

    this.eventService.getEventById(eventId).subscribe({
      next: (data) => {
        this.event = data;
        this.lat = Number(this.event.localization.latitude);
        this.lng = Number(this.event.localization.longitude);

        // Update the center of the map
        this.center = {
          lat: this.lat,
          lng: this.lng,
        };

        // Formateamos las fechas
        this.event.dates.forEach((fecha) => {
          this.fechasFormateadas.push(
            this.formatDateForDateInput(fecha.startDate)
          );
        });

        // Vemos si el usuario está apuntado en alguna fecha
        this.eventService
          .isSubscribed(
            JSON.parse(this.authService.getCookie('user_id')),
            eventId
          )
          .subscribe({
            next: (data) => {
              console.log(data);
              const subscribedIds = data; // Array de IDs devuelto por isSubscribed
              this.event.dates.forEach((fecha) => {
                this.fechaSubscribedStatus[fecha.id] = subscribedIds.includes(
                  fecha.id
                );
              });
              console.log(this.fechaSubscribedStatus); // Mostrar los estados de suscripción
              this.dataService.setFechas(this.fechaSubscribedStatus);
            },
            error: (err) => {},
          });

        if (this.event) {
          this.bidderService.getBidderById(this.event.bidderId).subscribe({
            next: (data) => {
              this.bidderUser = data;

              if (data) {
                this.userService
                  .getImage(this.bidderUser.profileImage)
                  .subscribe(
                    (data: Blob) => {
                      const reader = new FileReader();
                      reader.onload = () => {
                        this.bidderProfileImage = reader.result;
                      };
                      reader.readAsDataURL(data);
                    },
                    (error) => {}
                  );
              }
            },
            error: (err) => {},
          });

          this.userService.getImage(this.event.image).subscribe(
            (data: Blob) => {
              const reader = new FileReader();
              reader.onload = () => {
                this.eventImage = reader.result;
              };
              reader.readAsDataURL(data);
            },
            (error) => {}
          );
        }
      },
      error: (err) => {},
    });
  }

  openInfoWindow(marker: MapMarker) {
    this.infoWindow.open(marker);
    this.zoom = 20;
  }

  move(event: google.maps.MapMouseEvent) {
    if (event.latLng != null) this.display = event.latLng.toJSON();
  }

  add(indice: number) {
    let usuario: User = {} as User;
    if (this.authService.getCookie('user') !== null) {
      usuario = JSON.parse(this.authService.getCookie('user'));
    } else {
      usuario = this.dataService.getUser();
    }

    const addUser: AddUserToEvent = <AddUserToEvent>{
      userId: usuario.id,
      eventId: this.event.eventId,
      fechaId: this.event.dates[indice].id,
      incriptionDate: new Date().toISOString(),
    };

    this.eventService.addUserToEvent(addUser).subscribe({
      next: (data) => {
        console.log(data);
        this.eventService
          .isSubscribed(
            JSON.parse(this.authService.getCookie('user_id')),
            this.event.eventId
          )
          .subscribe({
            next: (data) => {
              console.log(data);
              const subscribedIds = data; // Array de IDs devuelto por isSubscribed
              this.event.dates.forEach((fecha) => {
                this.fechaSubscribedStatus[fecha.id] = subscribedIds.includes(
                  fecha.id
                );
              });
              console.log(this.fechaSubscribedStatus); // Mostrar los estados de suscripción
              this.dataService.setFechas(this.fechaSubscribedStatus);
            },
            error: (err) => {},
          });
      },
    });
  }

  formatDateForDateInput(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2); // Agrega un 0 delante si el mes es menor a 10
    const day = ('0' + date.getDate()).slice(-2); // Agrega un 0 delante si el día es menor a 10
    return `${day} / ${month} / ${year}`;
  }
}

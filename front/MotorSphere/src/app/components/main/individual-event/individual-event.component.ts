import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from '../../../services/event.service';
import { Event } from '../../../models/Event';
import { UserService } from '../../../services/user.service';
import {
  GoogleMapsModule,
  MapAdvancedMarker,
  MapInfoWindow,
  MapMarker,
} from '@angular/google-maps';
import { BidderService } from '../../../services/bidder.service';
import { BidderUser } from '../../../models/BidderUser';

@Component({
  selector: 'app-individual-event',
  standalone: true,
  imports: [GoogleMapsModule, MapMarker, MapAdvancedMarker, MapInfoWindow],
  templateUrl: './individual-event.component.html',
  styleUrl: './individual-event.component.css',
})
export class IndividualEventComponent implements OnInit {
  public event: Event = <Event>{};
  public eventImage: string | ArrayBuffer | null = null;
  public bidderUser: BidderUser = <BidderUser>{};
  public bidderProfileImage: string | ArrayBuffer | null = null;
  public fechasFormateadas: Array<String> = [];

  public lat: number = 0;
  public lng: number = 0;

  @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow = <MapInfoWindow>{};

  private userService: UserService = inject(UserService);
  private route: ActivatedRoute = inject(ActivatedRoute);
  private eventService: EventService = inject(EventService);
  private bidderService: BidderService = inject(BidderService);

  display: any;
  center: google.maps.LatLngLiteral = {
    lat: this.lat,
    lng: this.lng,
  };
  zoom = 12;

  ngOnInit(): void {
    const eventId = this.route.snapshot.params['id'];
    console.log(this.event);

    this.eventService.getEventById(eventId).subscribe({
      next: (data) => {
        console.log('Evento con id ' + eventId, data);
        this.event = data;
        this.lat = Number(this.event.localization.latitude);
        this.lng = Number(this.event.localization.longitude);
        console.log(`Latitud: ${this.lat} longitud: ${this.lng}`);

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

        if (this.event) {
          this.bidderService.getBidderById(this.event.bidderId).subscribe({
            next: (data) => {
              console.log('Datos obtenidos', data);
              this.bidderUser = data;

              if (data) {
                this.userService
                  .getImage(this.bidderUser.profileImage)
                  .subscribe(
                    (data: Blob) => {
                      const reader = new FileReader();
                      reader.onload = () => {
                        this.bidderProfileImage = reader.result;
                        console.log('Imagen de perfil: ', !!reader.result);
                      };
                      reader.readAsDataURL(data);
                    },
                    (error) => {
                      console.error('Error al obtener la imagen:', error);
                    }
                  );
              }
            },
            error: (err) => {
              console.error(err);
            },
          });

          this.userService.getImage(this.event.image).subscribe(
            (data: Blob) => {
              const reader = new FileReader();
              reader.onload = () => {
                this.eventImage = reader.result;
                console.log('Imagen de perfil: ', !!reader.result);
              };
              reader.readAsDataURL(data);
            },
            (error) => {
              console.error('Error al obtener la imagen:', error);
            }
          );
        }
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  openInfoWindow(marker: MapMarker) {
    this.infoWindow.open(marker);
    this.zoom = 20;
  }

  move(event: google.maps.MapMouseEvent) {
    if (event.latLng != null) this.display = event.latLng.toJSON();
  }

  formatDateForDateInput(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2); // Agrega un 0 delante si el mes es menor a 10
    const day = ('0' + date.getDate()).slice(-2); // Agrega un 0 delante si el día es menor a 10
    return `${day} / ${month} / ${year}`;
  }
}

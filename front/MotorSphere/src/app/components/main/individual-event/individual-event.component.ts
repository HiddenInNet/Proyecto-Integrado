import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from '../../../services/event.service';
import { Event } from '../../../models/Event';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-individual-event',
  standalone: true,
  imports: [],
  templateUrl: './individual-event.component.html',
  styleUrl: './individual-event.component.css',
})
export class IndividualEventComponent implements OnInit {
  public event: Event = <Event>{};
  public eventImage: string | ArrayBuffer | null = null;

  private userService: UserService = inject(UserService);
  private route: ActivatedRoute = inject(ActivatedRoute);
  private eventService: EventService = inject(EventService);

  ngOnInit(): void {
    const eventId = this.route.snapshot.params['id'];
    console.log(this.event)

    let divMapa = document.querySelector("#map");

    //var centro = new { lat: this.event.localization.latitude, lng: this.event.localization.longitude };


    this.eventService.getEventById(eventId).subscribe({
      next: (data) => {
        console.log('Evento con id ' + eventId, data);
        this.event = data;

        if (this.event) {
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
    });
  }
}

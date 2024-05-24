import { Component, Input, OnInit, inject } from '@angular/core';
import { Event } from '../../../models/Event';
import { AuthService } from '../../../services/auth.service';
import { EventService } from '../../../services/event.service';
import { UserService } from '../../../services/user.service';
import { Localization } from '../../../models/Localization';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-event-card',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './event-card.component.html',
  styleUrl: './event-card.component.css',
})
export class EventCardComponent implements OnInit {
  private authService: AuthService = inject(AuthService);
  private userService: UserService = inject(UserService);
  private eventService: EventService = inject(EventService);

  @Input()
  public event: Event = <Event>{};
  public eventImage: string | ArrayBuffer | null = null;

  ngOnInit(): void {
    console.log('Imagen del evento: ', this.event.image);
    this.userService.getImage(this.event.image).subscribe(
      (data: Blob) => {
        const reader = new FileReader();
        reader.onload = () => {
          this.eventImage = reader.result;
        };
        reader.readAsDataURL(data);
      },
      (error) => {
        console.error('Error al obtener la imagen:', error);
      }
    );
  }

  getStarArray(score: number): Array<number> {
    const filledStars = Math.floor(score / 20);
    const halfStar = score % 20 >= 10 ? 1 : 0;
    return new Array(filledStars + halfStar)
      .fill(1)
      .concat(new Array(5 - filledStars - halfStar).fill(0));
  }

  getAllPlaces(): number {
    return this.event.dates.reduce(
      (total, date) => total + date.placesAvailable,
      0
    );
  }
}
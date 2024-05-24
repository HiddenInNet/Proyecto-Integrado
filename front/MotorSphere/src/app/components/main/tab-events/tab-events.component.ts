import { Component, OnInit, inject } from '@angular/core';
import { EventCardComponent } from '../../custom/event-card/event-card.component';
import { EventService } from '../../../services/event.service';
import { Event } from '../../../models/Event';

@Component({
  selector: 'app-tab-events',
  standalone: true,
  imports: [EventCardComponent],
  templateUrl: './tab-events.component.html',
  styleUrl: './tab-events.component.css',
})
export class TabEventsComponent implements OnInit {
  // Servicios
  private eventService: EventService = inject(EventService);

  public events: Event[] = [];

  ngOnInit(): void {
    this.eventService.getAllEvents().subscribe({
      next: (data) => {
        console.log(data);
        console.log('Eventos: ', data)
        this.events = data;
      },
    });
  }
}

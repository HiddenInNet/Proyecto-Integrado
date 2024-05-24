import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from '../../../services/event.service';
import { Event } from '../../../models/Event';

@Component({
  selector: 'app-individual-event',
  standalone: true,
  imports: [],
  templateUrl: './individual-event.component.html',
  styleUrl: './individual-event.component.css'
})
export class IndividualEventComponent implements OnInit {

  public event: Event = <Event>{}
  private route: ActivatedRoute = inject(ActivatedRoute);
  private eventService: EventService = inject(EventService);

  ngOnInit(): void {
    const eventId = this.route.snapshot.params['id'];

    this.eventService.getEventById(eventId).subscribe({
      next: data => {
        console.log('Evento con id ' + eventId, data);
        this.event = data;
      }
    })
  }
}

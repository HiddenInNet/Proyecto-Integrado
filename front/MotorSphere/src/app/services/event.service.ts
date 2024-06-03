import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { AddUserToEvent, Event } from '../models/Event';
import { CreateEventForm } from '../models/CreateEventForm';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  private SERVER_URL: string = environment.SERVER_API_URL;

  constructor(private http: HttpClient) {}

  getAllEvents() {
    const route = this.SERVER_URL + '/evento/all';
    return this.http.get<Event[]>(route);
  }

  getEventById(id: number) {
    const route = this.SERVER_URL + '/evento/' + id;
    return this.http.get<Event>(route);
  }

  addEvent(data: CreateEventForm) {
    const route = this.SERVER_URL + '/evento/add';
    return this.http.post<Event>(route, data);
  }

  addUserToEvent(user: AddUserToEvent) {
    const route = this.SERVER_URL+"/evento/add-user"
    return this.http.post<AddUserToEvent>(route, user);
  }

  isSubscribed (userId: number, eventId: number) {

    const params = {
      userId: userId,
      eventId: eventId
    }

    const route = this.SERVER_URL+"/evento/isSuscribed"
    return this.http.post<Array<number>>(route, params);
  }
}

import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  isLoggedIn$ = new BehaviorSubject<boolean>(false);
  user$ = new BehaviorSubject<User>(<User>{});

  constructor() { }
}

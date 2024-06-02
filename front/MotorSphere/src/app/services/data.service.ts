import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../models/User';
import { Bidder } from '../models/Bidder';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private isBidder: BehaviorSubject<boolean | null>;
  public bidder$: Observable<boolean | null>;

  private user: BehaviorSubject<User>;
  public user$: Observable<User>;

  private ofertante: BehaviorSubject<Bidder>;
  public ofertante$: Observable<Bidder>;

  constructor() {
    // Inicializa el BehaviorSubject con null o con un usuario inicial si lo tienes
    this.isBidder = new BehaviorSubject<boolean | null>(null);
    this.bidder$ = this.isBidder.asObservable();

    this.user = new BehaviorSubject<User>(<User>{});
    this.user$ = this.user.asObservable();

    this.ofertante = new BehaviorSubject<Bidder>(<Bidder>{});
    this.ofertante$ = this.ofertante.asObservable();
  }

  // Método para obtener el valor actual del usuario
  getBidder() {
    return this.isBidder.value;
  }

  // Método para actualizar el usuario
  setBidder(boolean : boolean) {
    this.isBidder.next(boolean);
  }

  // Método para limpiar el usuario
  clearBidder(): void {
    this.isBidder.next(null);
  }

  // Método para obtener el valor actual del usuario
  getUser() {
    return this.user.value;
  }

  // Método para actualizar el usuario
  setUser(user : User) {
    this.user.next(user);
  }

  // Método para limpiar el usuario
  clearUser(): void {
    this.user.next(<User>{});
  }

  // Método para obtener el valor actual del usuario
  getOfertante() {
    return this.ofertante.value;
  }

  // Método para actualizar el usuario
  setOfertante(ofertante : Bidder) {
    this.ofertante.next(ofertante);
  }

  // Método para limpiar el usuario
  clearOfertante(): void {
    this.ofertante.next(<Bidder>{});
  }
}

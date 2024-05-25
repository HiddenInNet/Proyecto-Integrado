import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Bidder } from '../models/Bidder';

@Injectable({
  providedIn: 'root',
})
export class BidderService {
  private SERVER_URL: string = environment.SERVER_API_URL;

  constructor(private http: HttpClient) {}

  getBidderByUserId(id: number) {
    console.log('Obteniendo ofertante: ', id);

    const route = this.SERVER_URL + `/ofertante/getByUserId/${id}`;

    return this.http.get<Bidder>(route);
  }
}

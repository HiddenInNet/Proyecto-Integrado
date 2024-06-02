import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Bidder } from '../models/Bidder';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root',
})
export class BidderService {
  private SERVER_URL: string = environment.SERVER_API_URL;

  constructor(private http: HttpClient) {}

  getBidderByUserId(id: number) {

    const route = this.SERVER_URL + `/ofertante/getByUserId/${id}`;

    return this.http.get<Bidder>(route);
  }

  getBidderById(id: number) {

    const route = this.SERVER_URL + `/ofertante/getById/${id}`;

    return this.http.get<any>(route);
  }

  createBidder(user: User) {

    const params = {
      userId: user.id,
      checker: false,
    };

    const route = this.SERVER_URL + '/ofertante/add';

    return this.http.post<Bidder>(route, params);
  }

  removeBidder(id: number) {

    const route = this.SERVER_URL + `/ofertante/removeByUserId/${id}`;

    return this.http.delete<any>(route);
  }

  setChecker(id: number, checker: boolean) {
    const params = {
      bidderId: id,
      checker: checker
    }
    const route = this.SERVER_URL + `/ofertante/checker`;
    return this.http.put<Bidder>(route, params);
  }
}

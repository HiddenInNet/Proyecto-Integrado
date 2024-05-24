import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { User } from '../models/User';
import { Register } from '../models/Register';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private SERVER_URL: string = environment.SERVER_API_URL;

  constructor(private http: HttpClient) {}

  getUserById(id: number) {
    console.log(`getUserById(${id})`);

    const route = this.SERVER_URL + `/usuario/${id}`;

    return this.http.get<User>(route);
  }

  updateUser(user: User) {
    console.log('Actualizando usuario: ', user.id);

    const params = {
      id: user.id,
      email: user.email,
      username: user.username,
      name: user.name,
      lastName: user.lastName,
      birthDate: user.birthDate,
      phone: user.phone,
      biography: user.biography,
      profileImage: user.profileImage,
    };

    const route = this.SERVER_URL + `/usuario/update`;
    return this.http.put<User>(route, params);
  }

  getImage(link: string) {
    console.log('Obteniendo imagen: ', link);
    const route = link;
    return this.http.get(route, { responseType: 'blob' });
  }
}

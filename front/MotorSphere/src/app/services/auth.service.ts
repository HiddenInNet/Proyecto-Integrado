import { Injectable, inject } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Login } from '../models/Login';
import { UserLogged } from '../models/UserLogged';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, tap } from 'rxjs';
import { Register } from '../models/Register';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private SERVER_URL: string = environment.SERVER_API_URL;

  private http: HttpClient = inject(HttpClient);

  constructor(private cookie: CookieService) {}

  getAuthToken() {
    return this.getCookie('user_jwt');
  }

  isLogged() {
    return !!this.getCookie('user_jwt');
  }

  login(login: Login) {

    const params = {
      username: login.username,
      password: login.password,
    };

    const route = this.SERVER_URL + '/auth/login';

    return this.http.post<UserLogged>(route, params).pipe(
      tap((response) => {
        // Guardar el JWT y el userId en el CookieService
        this.setCookie('user_jwt', response.jwt);
        this.setCookie('user_id', response.userId.toString());
      })
    );
  }

  createUser(registerUser: Register) {

    const params = {
      username: registerUser.username,
      password: registerUser.password,

      email: registerUser.email,
      name: registerUser.name,
      lastName: registerUser.lastName,
      birthDate: registerUser.birthDate,
      phone: registerUser.phone,
      biography: registerUser.biography,
      profileImage: registerUser.profileImage,
    };

    const route = this.SERVER_URL + `/auth/register`;

    return this.http.post<any>(route, params);
  }

  logOut(): void {
    sessionStorage.removeItem('user_jwt');

    this.removeCookie('user_jwt');
    this.removeCookie('user_id');
  }

  getCookie(name: string) {
    return this.cookie.get(name);
  }

  setCookie(name: string, value: string) {
    if (this.cookie.get(name)) {
      this.cookie.delete(name);
      this.cookie.set(name, value);
    } else {
      this.cookie.set(name, value);
    }
  }

  removeCookie(name: string) {
    this.cookie.delete(name);
  }

  removeAllCookies() {
    this.cookie.deleteAll();
  }
}

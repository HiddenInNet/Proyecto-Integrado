import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Login } from '../models/Login';
import { UserLogged } from '../models/UserLogged';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private SERVER_URL: string = environment.SERVER_URL;

  constructor(private http: HttpClient) { }

  getAuthToken(): boolean {
    return !!environment.getJWTFromLocalStorage();
  }

  login(login: Login) {
    console.log("Dentro de login (service)", login)
    
    const params = {
      username: login.username,
      password: login.password
    }

    const route = "/api/v0/auth/login";

    return this.http.post<UserLogged>("http://localhost:8081/api/v0/auth/login", params);
  }
}

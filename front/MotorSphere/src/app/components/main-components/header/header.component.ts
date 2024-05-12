import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ButtonRegisterComponent } from '../../custom/button-register/button-register.component';
import { ButtonLoginComponent } from '../../custom/button-login/button-login.component';
import { ProfileIconComponent } from '../../custom/profile-icon/profile-icon.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, ButtonRegisterComponent, ButtonLoginComponent, ProfileIconComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  public logged: boolean = false;


}

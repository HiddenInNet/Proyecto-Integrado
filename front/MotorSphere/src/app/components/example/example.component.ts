import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProfileIconComponent } from '../custom/profile-icon/profile-icon.component';

@Component({
  selector: 'app-example',
  standalone: true,
  imports: [RouterLink, ProfileIconComponent],
  templateUrl: './example.component.html',
  styleUrl: './example.component.css'
})
export class ExampleComponent {
  public logged: boolean = true

}

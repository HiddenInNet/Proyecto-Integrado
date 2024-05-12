import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { initFlowbite } from 'flowbite';
import { HeaderComponent } from './components/main-components/header/header.component';
import { User } from './models/User';
import { FooterComponent } from './components/main-components/footer/footer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Motor Sphere';

  public user: User = <User>{};

  ngOnInit():void {
    initFlowbite();
  }
}

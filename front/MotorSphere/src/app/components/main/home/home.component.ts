import { Component } from '@angular/core';
import { LoadingService } from '../../../services/loading.service';
import { LoadingComponent } from '../../custom/loading/loading.component';
import { GetStartedComponent } from '../../custom/get-started/get-started.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [LoadingComponent, GetStartedComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(public loadingService: LoadingService) {}

  ngOnInit(): void {
    
  }
}

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

  public title: String = ""

  constructor(public loadingService: LoadingService) {}

  ngOnInit(): void {
    // this.loadingService.isLoading =true;
    // setTimeout(() => {
    //   console.log("dentro: ",false)
    //   this.loadingService.isLoading = false;
    // }, 1000);
  }
}

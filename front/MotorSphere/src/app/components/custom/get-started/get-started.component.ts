import { Component, OnInit, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LogService } from '../../../services/log.service';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-get-started',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './get-started.component.html',
  styleUrl: './get-started.component.css',
})
export class GetStartedComponent implements OnInit {
  
  // services
  private logService: LogService = inject(LogService);
  private authService: AuthService = inject(AuthService);
  public logged: boolean = false;

  ngOnInit(): void {
    this.logService.isLoggedIn$.subscribe((res) => {
      this.logged = this.authService.isLogged();
    });
  }
}

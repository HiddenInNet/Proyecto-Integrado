import { Component, OnInit, inject } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';
import { LogService } from '../../../services/log.service';

@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css',
})
export class LogoutComponent implements OnInit {
  private logService: LogService = inject(LogService)

  constructor(private peti: AuthService, private route: Router) {}

  ngOnInit(): void {
    this.peti.removeAllCookies();
    this.logService.isLoggedIn$.next(false);
    this.route.navigate(['home'])
  }
}

import { inject } from '@angular/core';
import { CanActivateChildFn, CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router)
  const authService = inject(AuthService)
  if(!authService.getAuthToken()){
    return router.createUrlTree(['permision-denied'])
  }
  return true;
};

export const authGuardChild: CanActivateChildFn = (route, state) => {
  const router = inject(Router)
  const authService = inject(AuthService)
  if(!authService.getAuthToken()){
    return router.createUrlTree(['permision-denied'])
  }
  return true;
}
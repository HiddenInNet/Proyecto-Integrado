import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './services/auth.service';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  console.log("Dentro del interceptor")
  const service = inject(AuthService)
    const authToken = service.getAuthToken()

    if (authToken === null) {

        const auth = req.clone({})

        return(next(auth))
    }

    const authReq = req.clone({
        setHeaders: {
            Authorization: `Bearer ${authToken}`
        }
    })

    return next(authReq);
};

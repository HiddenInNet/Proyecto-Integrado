import { HttpInterceptorFn } from "@angular/common/http";
import { environment } from "../environments/environment.development";

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
    const authToken = environment.getJWTFromLocalStorage();

    const authReq = req.clone({
        setHeaders: {
            Authorization: `Bearer ${authToken}`
        }
    })

    return next(authReq);
}
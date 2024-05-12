import { Routes } from '@angular/router';
import { HomeComponent } from './components/main-components/home/home.component';
import { LoginComponent } from './components/auth-components/login/login.component';
import { RegisterComponent } from './components/auth-components/register/register.component';
import { PermisionDeniedComponent } from './components/auth-components/permision-denied/permision-denied.component';
import { PageNotFoundComponent } from './components/auth-components/page-not-found/page-not-found.component';
import { ExampleComponent } from './components/example/example.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    },
    {
        path: "home",
        component: HomeComponent
    },
    {
        path: "login",
        component: LoginComponent
    },
    {
        path: "register",
        component: RegisterComponent
    },
    {
        path: 'example',
        component: ExampleComponent
    },
    {path: 'permision-denied', component: PermisionDeniedComponent},
    {path: '**', component: PageNotFoundComponent},
];

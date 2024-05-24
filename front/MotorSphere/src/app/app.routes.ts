import { Routes } from '@angular/router';
import { HomeComponent } from './components/main/home/home.component';
import { LoginComponent } from './components/auth-components/login/login.component';
import { RegisterComponent } from './components/auth-components/register/register.component';
import { PermisionDeniedComponent } from './components/auth-components/permision-denied/permision-denied.component';
import { PageNotFoundComponent } from './components/auth-components/page-not-found/page-not-found.component';
import { ExampleComponent } from './components/example/example.component';
import { TabEventsComponent } from './components/main/tab-events/tab-events.component';
import { TabBiddersComponent } from './components/main/tab-bidders/tab-bidders.component';
import { TabComunityComponent } from './components/main/tab-comunity/tab-comunity.component';
import { TabContactComponent } from './components/main/tab-contact/tab-contact.component';
import { LogoutComponent } from './components/auth-components/logout/logout.component';
import { Title } from '@angular/platform-browser';
import { IndividualEventComponent } from './components/main/individual-event/individual-event.component';
import { authGuard } from './guards/auth.guard';
import { TabProfileComponent } from './components/main/tab-profile/tab-profile.component';

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
        path: "events",
        component: TabEventsComponent,
        data: { title: 'Events' },
        //canActivate: [authGuard]
    },
    {
        path: "event/:id",
        component: IndividualEventComponent
    },
    {
        path: "bidders",
        component: TabBiddersComponent
    },
    {
        path: "comunity",
        component: TabComunityComponent
    },
    {
        path: "contact",
        component: TabContactComponent
    },
    {
        path: "login",
        component: LoginComponent
    },
    {
        path: "logout",
        component: LogoutComponent
    },
    {
        path: "register",
        component: RegisterComponent
    },
    {
        path: 'profile/:id',
        component: TabProfileComponent
    },
    {path: 'permision-denied', component: PermisionDeniedComponent},
    {path: '**', component: PageNotFoundComponent},
];

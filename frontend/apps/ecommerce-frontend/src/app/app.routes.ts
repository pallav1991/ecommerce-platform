import { Routes } from '@angular/router';
import { AuthGuard } from './services/auth/authGuard/auth-guard.service';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () =>
      import('./Components/home/home.component').then((m) => m.HomeComponent),
    canActivate: [AuthGuard],
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./Components/login/login.component').then(
        (m) => m.LoginComponent
      ),
  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];

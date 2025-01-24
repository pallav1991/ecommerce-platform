import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginModel } from '../../../model/login.model';
import { TokenModel } from '../../../model/token.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private refreshTokenUrl = '/api/auth/refresh'; // Replace with your endpoint

  private token: TokenModel = new TokenModel('', '');

  constructor(private http: HttpClient, private router: Router) {}

  refreshToken(): Observable<any> {
    const refreshToken = this.token.getToken().refreshToken;
    return this.http.post(this.refreshTokenUrl, { refreshToken });
  }

  login(loginData: LoginModel): void {
    if (!loginData) return;
    if (!loginData.username || !loginData.password) return;

    this.http.post('/api/auth/login', loginData).subscribe({
      next: (response: any) => {
        this.token.setToken(response.authToken, response.refreshToken);
        this.router.navigate(['home']);
      },
      error: (error) => {
        // Handle login error
        console.error(error);
      },
    });
  }

  logout(): void {
    this.token.removeToken();
    // Redirect to login page if needed
    this.router.navigate(['login']);
  }

  isLoggedIn(): boolean {
    return !!this.token.getToken().authToken;
  }
}

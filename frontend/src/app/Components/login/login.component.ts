import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.less'
})
export class LoginComponent {
  credentials = { username: '', password: '' };
  constructor(private http: HttpClient) { }

  onSubmit() {
    this.http.post('http://localhost:8080/login', this.credentials).subscribe({
      next: (response: any) => {
        // Handle successful login (e.g., store JWT token)
        console.log(response);
      },
      error: (error) => {
        // Handle login error
        console.error(error);
      }
    });
  }
}

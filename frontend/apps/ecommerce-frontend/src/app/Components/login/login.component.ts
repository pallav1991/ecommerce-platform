import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { LoginModel } from '../../model/login.model';
import {
  ReactiveFormsModule,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { JsonPipe } from '@angular/common';
import { AuthService } from '../../services/auth/authService/auth-service.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, JsonPipe], // Add ReactiveFormsModule here
  templateUrl: './login.component.html',
  styleUrl: './login.component.less',
})
export class LoginComponent {
  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });
  constructor(private http: HttpClient, private authService: AuthService) {}

  onSubmit(event: any) {
    event.preventDefault();
    if (this.loginForm.value.username && this.loginForm.value.password) {
      const loginData = new LoginModel(
        this.loginForm.value.username,
        this.loginForm.value.password
      );
      this.authService.login(loginData);
    }
  }
}

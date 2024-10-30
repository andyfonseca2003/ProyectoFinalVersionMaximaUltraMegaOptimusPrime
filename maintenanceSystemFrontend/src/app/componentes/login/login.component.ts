import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../../services/auth/login.service';
import { LoginRequest } from '../../services/auth/LoginRequest';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule,RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private loginService:LoginService, private router:Router) {
    this.crearFormulario();
  }
  private crearFormulario() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.maxLength(30), Validators.minLength(5)]]
    });
  }

  public loguear() {
    this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
      next:(userData) => {
        // console.log(userData);
        localStorage.setItem("token",userData.respuesta['token']);
      },
      error: (errorData) =>{
         console.error(errorData);
      },
      complete: () =>{
        this.router.navigate(['inicio']);
        this.loginForm.reset();
     },
    })
  }

}

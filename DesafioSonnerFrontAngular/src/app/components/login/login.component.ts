import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  senha: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.authService.login(this.email, this.senha).subscribe(authenticated => {
      if (authenticated) {
        this.router.navigate(['/home']);
      } else {
        this.errorMessage = 'Email ou senha incorretos';
      }
    }, error => {
      this.errorMessage = 'Erro ao tentar realizar login';
    });
  }
}

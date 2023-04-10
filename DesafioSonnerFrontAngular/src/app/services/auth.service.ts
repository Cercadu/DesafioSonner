import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

interface User {
  id: number;
  name: string;
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authenticated = false;
  private currentUser: User | undefined;

  constructor(private http: HttpClient) {}

  login(email: string, senha: string): Observable<boolean | null> {
    return this.getUserByEmailAndSenha(email, senha).pipe(
      map((user: User | null) => {
        if (user) {
          this.authenticated = true;
          this.currentUser = user;
        } else {
          this.authenticated = false;
        }
        return this.authenticated;
      }),
      catchError(() => {
        return of(null);
      })
    );
  }

  logout(): void {
    this.authenticated = false;
    this.currentUser = undefined;
  }

  isLoggedIn(): boolean {
    return this.authenticated;
  }

  getCurrentUser(): User | undefined {
    return this.currentUser;
  }

  getUsername(): string {
    return this.currentUser?.name || '';
  }

  getUserByEmailAndSenha(email: string, senha: string): Observable<User | null> {
    const url = `http://localhost:8080/usuario?email=${email}&senha=${senha}`;
    return this.http.get<User>(url).pipe(
      catchError(() => {
        return of(null);
      })
    );
  }


}

import { Injectable } from '@angular/core';
import { LoginRequest } from './LoginRequest';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(credentials: LoginRequest): Observable<any> {
    return this.http.post("http://localhost:8080/api/sinAuth/login", credentials).pipe(
      catchError(this.handleError) // Añadimos el manejo de errores
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('Se ha producido un error:', error.error);
    } else {
      console.error('Backend retornó el código de estado:', error.status, error.error);
      alert(error.error.respuesta);
    }
    return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'));
  }

}

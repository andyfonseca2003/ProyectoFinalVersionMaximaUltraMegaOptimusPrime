import { Injectable } from '@angular/core';
import { ActividadRequest } from './ActividadRequest';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActividadService {

  constructor(private http: HttpClient) { }

  login(actividad: ActividadRequest): Observable<any> {
    return this.http.post("http://localhost:8080/api/operador/registrar-actividad", actividad).pipe(
      catchError(this.handleError) // Añadimos el manejo de errores
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('Se ha producido un error:', error.error);
    } else {
      console.error('Backend retornó el código de estado:', error.status, error.error);
      alert('Backend retornó el código de estado:' + error.error.respuesta);
    }
    return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'));
  }
}

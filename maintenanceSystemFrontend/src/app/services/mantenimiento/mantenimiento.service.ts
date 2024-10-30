import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { MantenimientoRequest } from './MantenimientoRequest';

@Injectable({
  providedIn: 'root'
})
export class MantenimientoService {

  constructor(private http: HttpClient) { }

  public getAllMantenimientos(): Observable<any> {
    return this.http.get("http://localhost:8080/api/sinAuth/obtener-lista-gantt").pipe(
      catchError(this.handleError) // Añadimos el manejo de errores
    );
  }

  public getAllCartasGrant(): Observable<any> {
    return this.http.get("http://localhost:8080/api/sinAuth/obtener-lista-gantt").pipe(
      catchError(this.handleError) // Añadimos el manejo de errores
    );
  }

  crearMantenimiento(mantenimiento: MantenimientoRequest): Observable<any> {
    return this.http.post("http://localhost:8080/api/operador/programar-mantenimiento", mantenimiento).pipe(
      catchError(this.handleError) // Añadimos el manejo de errores
    );
  }
  
  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('Se ha producido un error:', error.error);
    } else {
      console.log('Backend retornó el código de estado:', error.status, error.error.text);
    }
    return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'));
  }
}

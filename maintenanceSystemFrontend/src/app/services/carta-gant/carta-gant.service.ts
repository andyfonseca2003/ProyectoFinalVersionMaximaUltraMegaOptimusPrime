import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { cartaGanttRequest } from './CartaGanttRequest';
import { editarCartaGanttRequest } from './EditarCartaGanttRequest';

@Injectable({
  providedIn: 'root'
})
export class CartaGantService {
  constructor(private http: HttpClient) { }

  public getAllCartaGant(): Observable<any> {
    return this.http.get("http://localhost:8080/api/sinAuth/obtener-lista-gantt").pipe(
      catchError(this.handleError) // Añadimos el manejo de errores
    );
  }

  public createCartaGant(cartaGant: cartaGanttRequest): Observable<any> {
    return this.http.post("http://localhost:8080/api/operador/crear-carta-Gantt", cartaGant).pipe(
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

  deleteCartaGant(idCartGannt: string): Observable<any> {
    return this.http.delete("http://localhost:8080/api/operador/eliminar",{"body": idCartGannt}).pipe(
      catchError(this.handleError) // Añadimos el manejo de errores
    );
  }

  updateCartaGant(cartaGant: editarCartaGanttRequest): Observable<any> {
    return this.http.put<any>("http://localhost:8080/api/operador/editar", cartaGant).pipe(
      catchError(this.handleError) // Añadimos el manejo de errores
    );
  }

}
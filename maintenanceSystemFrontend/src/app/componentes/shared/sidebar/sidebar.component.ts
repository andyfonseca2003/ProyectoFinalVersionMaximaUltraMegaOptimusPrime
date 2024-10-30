import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  constructor(private router:Router){

  }
    // Método para cerrar sesión
    logout(): void {
      // Elimina el token de almacenamiento local o de sesión
      localStorage.removeItem('token');  // O sessionStorage.removeItem('authToken');
      
      // Redirige al usuario a la página de inicio de sesión
      this.router.navigate(['']);
    }
}

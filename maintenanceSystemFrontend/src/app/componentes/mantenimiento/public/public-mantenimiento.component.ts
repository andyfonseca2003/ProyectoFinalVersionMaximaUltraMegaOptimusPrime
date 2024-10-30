import { Component } from '@angular/core';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { MantenimientoService } from '../../../services/mantenimiento/mantenimiento.service';
import { Router, RouterModule } from '@angular/router';


@Component({
  selector: 'app-mantenimiento',
  standalone: true,
  imports: [SidebarComponent,RouterModule],
  templateUrl: './public-mantenimiento.component.html',
  styleUrl: './public-mantenimiento.component.css'
})
export class PublicMantenimientoComponent {
  mantenimientos: any[] = [];  // Inicializa la variable cartas como un array vacío
  constructor(private mantenimientoService:MantenimientoService, private router:Router) {
    this.getAllMantenimientos();
  }

  public getAllMantenimientos() {
    this.mantenimientoService.getAllMantenimientos().subscribe({
      next:(response) => {
        console.log(response);
        this.mantenimientos = response;
      },
      error: (errorData) =>{
         console.error(errorData);
      },
      complete: () =>{
        console.log("well done");
     },
    })
  }
}

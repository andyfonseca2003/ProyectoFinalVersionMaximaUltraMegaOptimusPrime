import { Component } from '@angular/core';
import { SidebarComponent } from "../shared/sidebar/sidebar.component";
import { CartaGantService } from '../../services/carta-gant/carta-gant.service';
import { MantenimientoService } from '../../services/mantenimiento/mantenimiento.service';


@Component({
  selector: 'app-mantenimiento',
  standalone: true,
  imports: [SidebarComponent],
  templateUrl: './mantenimiento.component.html',
  styleUrl: './mantenimiento.component.css'
})
export class MantenimientoComponent {
  mantenimientos: any[] = [];  // Inicializa la variable cartas como un array vacío
  constructor(private mantenimientoService:MantenimientoService) {
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

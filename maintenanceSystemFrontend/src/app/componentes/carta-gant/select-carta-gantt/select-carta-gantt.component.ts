import { Component } from '@angular/core';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { CartaGantService } from '../../../services/carta-gant/carta-gant.service';

@Component({
  selector: 'app-select-carta-gantt',
  standalone: true,
  imports: [SidebarComponent],
  templateUrl: './select-carta-gantt.component.html',
  styleUrl: './select-carta-gantt.component.css'
})
export class SelectCartaGanttComponent {

  cartas: any[] = [];  // Inicializa la variable cartas como un array vacío

  constructor(private cartaGanttService:CartaGantService) {
    this.getAllCartaGant();
  }

  public getAllCartaGant(): void {
    this.cartaGanttService.getAllCartaGant().subscribe({
      next:(response) => {
        console.log(response);
        this.cartas = response;
        // Swal.fire("Carta Gantt creada con el id: " + userData.respuesta);
      },
      error: (errorData) =>{
        return console.log(errorData);
      },
      complete: () =>{
        console.log("Consulta completada");  // Si necesitas hacer algo al completar
     },
    })
  }
}

import { ChangeDetectorRef, Component } from '@angular/core';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { CartaGantService } from '../../../services/carta-gant/carta-gant.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-select-carta-gantt',
  standalone: true,
  imports: [SidebarComponent],
  templateUrl: './select-carta-gantt.component.html',
  styleUrl: './select-carta-gantt.component.css'
})
export class SelectCartaGanttComponent {

  cartas: any[] = [];  // Inicializa la variable cartas como un array vacío

  constructor(private cartaGanttService: CartaGantService,  private cdr: ChangeDetectorRef) {
    this.getAllCartaGant();
  }

  public getAllCartaGant(): void {
    this.cartaGanttService.getAllCartaGant().subscribe({
      next: (response) => {
        console.log(response);
        this.cartas = response;
        // Swal.fire("Carta Gantt creada con el id: " + userData.respuesta);
      },
      error: (errorData) => {
        return console.log(errorData);
      },
      complete: () => {
        console.log("Consulta completada");  // Si necesitas hacer algo al completar
      },
    })
  }

  // Método para eliminar una carta por su ID
  public eliminarCarta(id: string): void {
    this.cartaGanttService.deleteCartaGant(id).subscribe({
      next: () => {
        Swal.fire("Carta Gantt eliminada con ID:", id);
      },
      error: (errorData) => {
        console.log("Error al eliminar la carta:", errorData);
      },
      complete: () => {
        this.getAllCartaGant();
      },
    });
  }

  public editarCarta(carta: any): void {
    console.log(carta);
    Swal.fire({
      title: 'Editar Carta Gantt',
      html: `
        <input id="nombre" class="swal2-input" placeholder="Nombre" value="${carta.nombreCartaGantt}">
      `,
      focusConfirm: false,
      showCancelButton: true,
      confirmButtonText: 'Guardar',
      cancelButtonText: 'Cancelar',
      preConfirm: () => {
        const nombre = (Swal.getPopup()?.querySelector('#nombre') as HTMLInputElement).value;
        
        if (!nombre) {
          Swal.showValidationMessage('Por favor completa todos los campos');
          return;
        }
        
        // Retornar un objeto con los datos nuevos para editar
        return { nombre };
      }
    }).then((result) => {
      if (result.isConfirmed) {
        // Llamar al servicio para actualizar la carta
        carta.nombreCartaGantt = result.value?.nombre;
        this.cartaGanttService.updateCartaGant(carta).subscribe({
          next: () => {
            // Actualizar el array local `cartas` con los nuevos datos
            Swal.fire('Editado!', 'La carta Gantt ha sido editada correctamente.', 'success');
          },
          error: (errorData) => {
            Swal.fire('Error', 'Hubo un problema al editar la carta.', 'error');
            console.log("Error al editar la carta:", errorData);
          },
          complete: () => {
            this.getAllCartaGant();
          },
        });
      }
    });
  }
}

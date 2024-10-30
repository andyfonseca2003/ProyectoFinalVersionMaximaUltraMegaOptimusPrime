import { Component } from '@angular/core';
import { SidebarComponent } from "../shared/sidebar/sidebar.component";
import { ReactiveFormsModule, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { CartaGantService} from '../../services/carta-gant/carta-gant.service';
import { cartaGanttRequest } from '../../services/carta-gant/CartaGanttRequest';
import  Swal  from 'sweetalert2';

@Component({
  selector: 'app-carta-gant',
  standalone: true,
  imports: [SidebarComponent, ReactiveFormsModule, RouterModule],
  templateUrl: './carta-gant.component.html',
  styleUrl: './carta-gant.component.css'
})
export class CartaGantComponent {
  cartaGantForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private cartaGanttService:CartaGantService, private router:Router) {
    this.crearFormulario();
  }
  private crearFormulario() {
    this.cartaGantForm = this.formBuilder.group({
      nombreCartaGantt: ['', [Validators.required, Validators.maxLength(0), Validators.minLength(5)]]
    });
  }

  public createCartaGant() {
    this.cartaGanttService.createCartaGant(this.cartaGantForm.value as cartaGanttRequest).subscribe({
      next:(userData) => {
        Swal.fire("Carta Gantt creada con el id: " + userData.respuesta);
      },
      error: (errorData) =>{
         console.log(errorData);
      },
      complete: () =>{
        console.log("listo");
     },
    })
  }

}


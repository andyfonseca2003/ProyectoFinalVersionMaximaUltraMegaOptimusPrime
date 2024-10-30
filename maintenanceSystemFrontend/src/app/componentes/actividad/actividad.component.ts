import { Component } from '@angular/core';
import { SidebarComponent } from "../shared/sidebar/sidebar.component";
import { ReactiveFormsModule, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { ActividadService } from '../../services/actividad/actividad.service';
import { ActividadRequest } from '../../services/actividad/ActividadRequest';

@Component({
  selector: 'app-actividad',
  standalone: true,
  imports: [SidebarComponent,ReactiveFormsModule,RouterModule],
  templateUrl: './actividad.component.html',
  styleUrl: './actividad.component.css'
})
export class ActividadComponent {
  actividadForm!: FormGroup;
  id = "";
  constructor(private formBuilder: FormBuilder, private actividadService:ActividadService, private router:Router) {
    this.crearFormulario();
    this.id = "67159114262e2e7155e423d9";
  }
  private crearFormulario() {
    this.actividadForm = this.formBuilder.group({
      id: ['', [Validators.required]],
      nombre: ['', [Validators.required, Validators.maxLength(30), Validators.minLength(5)]],
      IdOperador: ['', [Validators.required, Validators.maxLength(30), Validators.minLength(5)]],
      descripcion: ['', [Validators.required, Validators.maxLength(100), Validators.minLength(5)]],
      fechaInicioPlanificada: ['', [Validators.required]],
      fechaFinPlanificada: ['', [Validators.required]],
      fechaInicioReal: [''],
      fechaFinReal: [''],
    });
  }

  public crearActividad() {
    this.actividadService.login(this.actividadForm.value as ActividadRequest).subscribe({
      next:(userData) => {
        console.log(userData);
      },
      error: (errorData) =>{
         console.error(errorData);
      },
      complete: () =>{
        // this.router.navigate(['inicio']);
        // this.actividadForm.reset();
     },
    })
  }
}

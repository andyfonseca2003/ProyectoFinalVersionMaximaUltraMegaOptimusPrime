import { Component } from '@angular/core';
import { SidebarComponent } from "../shared/sidebar/sidebar.component";
import { ReactiveFormsModule, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { ActividadService } from '../../services/actividad/actividad.service';
import { ActividadRequest } from '../../services/actividad/ActividadRequest';
import Swal from 'sweetalert2';

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
  constructor(private formBuilder: FormBuilder, private actividadService:ActividadService, private router:Router, private route: ActivatedRoute) {
    this.crearFormulario();
      this.id = this.route.snapshot.paramMap.get('id')  || '';
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

    var fechaInicio = this.actividadForm.get("fechaInicioPlanificada")?.value;
    var horaInicio = this.actividadForm.get("fechaInicioReal")?.value;
    fechaInicio = new Date(fechaInicio + "T" + horaInicio + ":00");
    this.actividadForm.get("fechaInicioPlanificada")?.setValue(fechaInicio);
    
    var fechaFin = this.actividadForm.get("fechaFinPlanificada")?.value;
    var horaFin = this.actividadForm.get("fechaFinReal")?.value;
    fechaFin = new Date(fechaFin + "T" + horaFin + ":00");
    this.actividadForm.get("fechaFinPlanificada")?.setValue(fechaFin);

    this.actividadForm.get("id")?.setValue(this.id);

    this.actividadService.login(this.actividadForm.value as ActividadRequest).subscribe({
      next:(userData) => {
        Swal.fire(userData.respuesta);
      },
      error: (errorData) =>{
         console.error(errorData);
      },
      complete: () =>{
        // this.router.navigate(['inicio']);
        this.actividadForm.reset();
     },
    })
  }
}

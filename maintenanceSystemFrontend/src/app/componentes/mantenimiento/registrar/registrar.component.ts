import { Component } from '@angular/core';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { MantenimientoService } from '../../../services/mantenimiento/mantenimiento.service';
import { Router } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MantenimientoRequest } from '../../../services/mantenimiento/MantenimientoRequest';

@Component({
  selector: 'app-registrar',
  standalone: true,
  imports: [SidebarComponent,ReactiveFormsModule],
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css'
})
export class RegistrarComponent {
  cartas: any[] = [];  // Inicializa la variable cartas como un array vacío
  maquinas: any[] = [];  // Inicializa la variable cartas como un array vacío
  supervisores: any[] = [];  // Inicializa la variable cartas como un array vacío
  mantenimientoForm!: FormGroup;
  idCarta:string = "";

  constructor(private mantenimientoService:MantenimientoService, private router:Router, private formBuilder: FormBuilder) {
    this.crearFormulario();
    this.getAllCartasGrant();
    this.getAllMaquinas();
    this.getAllSupervisores();
  }

  private crearFormulario() {
    this.mantenimientoForm = this.formBuilder.group({
      idMaquina: ['', [Validators.required]],
      idSupervisor: ['', [Validators.required, Validators.maxLength(30), Validators.minLength(5)]],
      nombre: ['', [Validators.required, Validators.maxLength(30), Validators.minLength(5)]],
      fechaInicio: ['', [Validators.required, Validators.maxLength(100), Validators.minLength(5)]],
      fechaFin: ['', [Validators.required]],
      fechaInicioReal: ['', [Validators.required]],
      fechaFinReal: [''],
      observaciones: [''],
      estadoMantenimiento: [''],
      idCartaGantt: [''],
    });
  }

  public crearMantenimiento() {
    this.mantenimientoService.crearMantenimiento(this.mantenimientoForm.value as MantenimientoRequest).subscribe({
      next:(userData) => {
        console.log(userData);
      },
      error: (errorData) =>{
         console.error(errorData);
      },
      complete: () =>{
        this.mantenimientoForm.reset();
     },
    })
  }


  public getAllCartasGrant() {
    this.mantenimientoService.getAllCartasGrant().subscribe({
      next:(response) => {
        console.log(response);
        this.cartas = response;
      },
      error: (errorData) =>{
         console.error(errorData);
      },
      complete: () =>{
        console.log("well done");
     },
    })
  }

  public getAllMaquinas() {
    this.mantenimientoService.getAllMaquinas().subscribe({
      next:(response) => {
        console.log(response);
        this.maquinas = response;
      },
      error: (errorData) =>{
         console.error(errorData);
      },
      complete: () =>{
        console.log("well done");
     },
    })
  }

  public getAllSupervisores() {
    this.mantenimientoService.getAllSupervisores().subscribe({
      next:(response) => {
        console.log(response);
        this.supervisores = response;
      },
      error: (errorData) =>{
         console.error(errorData);
      },
      complete: () =>{
        console.log("well done");
     },
    })
  }

  public mostrarDetalle(id:string){
    this.idCarta = id;
  }
}
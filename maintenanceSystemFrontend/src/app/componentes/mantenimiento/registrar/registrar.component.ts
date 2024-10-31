import { Component } from '@angular/core';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { MantenimientoService } from '../../../services/mantenimiento/mantenimiento.service';
import { Router } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MantenimientoRequest } from '../../../services/mantenimiento/MantenimientoRequest';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar',
  standalone: true,
  imports: [SidebarComponent, ReactiveFormsModule, CommonModule],
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css'
})
export class RegistrarComponent {
  cartas: any[] = [];  // Inicializa la variable cartas como un array vacío
  maquinas: any[] = [];  // Inicializa la variable cartas como un array vacío
  supervisores: any[] = [];  // Inicializa la variable cartas como un array vacío
  mantenimientoForm!: FormGroup;
  idCarta: string = "";

  constructor(private mantenimientoService: MantenimientoService, private router: Router, private formBuilder: FormBuilder) {
    this.crearFormulario();
    this.getAllCartasGrant();
    this.getAllMaquinas();
    this.getAllSupervisores();
  }

  private crearFormulario() {
    this.mantenimientoForm = this.formBuilder.group({
      idCartaGantt: ['', [Validators.required]],
      idMaquina: ['', [Validators.required]],
      idSupervisor: ['', [Validators.required, Validators.maxLength(30), Validators.minLength(5)]],
      nombre: ['', [Validators.required, Validators.maxLength(40), Validators.minLength(5)]],
      fechaInicio: ['', [Validators.required, Validators.maxLength(100), Validators.minLength(5)]],
      fechaFin: ['', [Validators.required]],
      horaInicio: [''],
      horaFin: [''],
      observaciones: ['', [Validators.minLength(0)]],
    });
  }


  public obtenerMensajeError(): string {
    for (const controlName in this.mantenimientoForm.controls) {
      const control = this.mantenimientoForm.get(controlName);
      if (control && control.errors) {
        if (control.errors['required']) {
          return `${controlName} es obligatorio.`;
        } else if (control.errors['minlength']) {
          return `${controlName} debe tener al menos ${control.errors['minlength'].requiredLength} caracteres.`;
        } else if (control.errors['maxlength']) {
          return `${controlName} no debe exceder los ${control.errors['maxlength'].requiredLength} caracteres.`;
        }
      }
    }
    return '';
  }

  public crearMantenimiento() {
    if (this.mantenimientoForm.invalid) {
      const errorMsg = this.obtenerMensajeError();
      console.log(errorMsg); // O muestra el mensaje en el HTML
      return;
    }
    var fechaInicio = this.mantenimientoForm.get("fechaInicio")?.value;
    var horaInicio = this.mantenimientoForm.get("horaInicio")?.value;
    fechaInicio = new Date(fechaInicio + "T" + horaInicio + ":00");
    this.mantenimientoForm.get("fechaInicio")?.setValue(fechaInicio);

    var fechaFin = this.mantenimientoForm.get("fechaFin")?.value;
    var horaFin = this.mantenimientoForm.get("horaFin")?.value;
    fechaFin = new Date(fechaFin + "T" + horaFin + ":00");
    this.mantenimientoForm.get("fechaFin")?.setValue(fechaFin);
    
    this.mantenimientoService.crearMantenimiento(this.mantenimientoForm.value as MantenimientoRequest).subscribe({
      next: (userData) => {
        Swal.fire(userData.respuesta);
      },
      error: (errorData) => {
        console.error(errorData);
      },
      complete: () => {
        this.mantenimientoForm.reset();
      },
    })
  }


  public getAllCartasGrant() {
    this.mantenimientoService.getAllCartasGrant().subscribe({
      next: (response) => {
        console.log(response);
        this.cartas = response;
      },
      error: (errorData) => {
        console.error(errorData);
      },
      complete: () => {
        console.log("well done");
      },
    })
  }

  public getAllMaquinas() {
    this.mantenimientoService.getAllMaquinas().subscribe({
      next: (response) => {
        console.log(response);
        this.maquinas = response;
      },
      error: (errorData) => {
        console.error(errorData);
      },
      complete: () => {
        console.log("well done");
      },
    })
  }

  public getAllSupervisores() {
    this.mantenimientoService.getAllSupervisores().subscribe({
      next: (response) => {
        console.log(response);
        this.supervisores = response;
      },
      error: (errorData) => {
        console.error(errorData);
      },
      complete: () => {
        console.log("well done");
      },
    })
  }

  public mostrarDetalle(id: string) {
    this.idCarta = id;
    this.mantenimientoForm.get('idCartaGantt')?.setValue(id);
    this.mantenimientoForm.get('idCartaGantt')?.markAsTouched(); // Asegura que el control sea evaluado
  }
}
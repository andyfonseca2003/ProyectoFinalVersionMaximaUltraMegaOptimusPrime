import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SidebarComponent } from "../shared/sidebar/sidebar.component";



@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [RouterModule, SidebarComponent],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent {

}

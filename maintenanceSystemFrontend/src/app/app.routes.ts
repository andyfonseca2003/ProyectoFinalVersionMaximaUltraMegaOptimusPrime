import { Routes } from '@angular/router';
import { InicioComponent } from './componentes/inicio/inicio.component';
import { LoginComponent } from './componentes/login/login.component';
import { MantenimientoComponent } from './componentes/mantenimiento/mantenimiento.component';
import { CartaGantComponent } from './componentes/carta-gant/carta-gant.component';
import { authGuard } from './custom/auth.guard';
import { SelectCartaGanttComponent } from './componentes/carta-gant/select-carta-gantt/select-carta-gantt.component';
import { ActividadComponent } from './componentes/actividad/actividad.component';
import { PublicMantenimientoComponent } from './componentes/mantenimiento/public/public-mantenimiento.component';
import { RegistrarComponent } from './componentes/mantenimiento/registrar/registrar.component';


export const routes: Routes = [
   { path: '', component: LoginComponent },
   { path: 'inicio', component: InicioComponent, canActivate:[authGuard] },
   { path: 'mantenimiento', component: MantenimientoComponent, canActivate:[authGuard]  },
   { path: 'mantenimiento/programar', component: RegistrarComponent, canActivate:[authGuard]  },
   { path: 'cartaGantt', component: CartaGantComponent, canActivate:[authGuard]  },
   { path: 'cartaGantt/all', component: SelectCartaGanttComponent, canActivate:[authGuard]  },
   { path: 'actividad', component: ActividadComponent, canActivate:[authGuard]  },
   { path: "**", pathMatch: "full", redirectTo: "login" },
   { path: 'historico', component: PublicMantenimientoComponent },
   
];

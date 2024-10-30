export interface ActividadRequest{
    id:string,
    nombre:string,
    IdOperador:string,
    descripcion:string,
    fechaInicioPlanificada: Date; // Cambiado a Date
    fechaFinPlanificada: Date;    // Cambiado a Date
    fechaInicioReal: Date | null; // Puede ser null
    fechaFinReal: Date | null;   
}
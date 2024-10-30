export interface MantenimientoRequest{
    idMaquina:string,
    idSupervisor:string,
    nombre:string,
    fechaInicio:Date,
    fechaFin:Date,
    fechaInicioReal:Date, // Asumiendo que este campo puede ser null
    fechaFinReal: Date,
    observaciones:string, // Cambiado a un objeto// Convertir a una lista
    estadoMantenimiento:string,
    idCartaGantt:string
}
export interface MantenimientoRequest{
    idMaquina: string;
    idSupervisor: string;
    nombre: string;
    fechaInicio: Date; // Fecha de inicio del mantenimiento
    fechaFin: Date; // Fecha de fin del mantenimiento
    fechaInicioReal: Date | null; // Puede ser null
    fechaFinReal: Date | null; // Puede ser null
    observaciones: string | null; // Cambiado a un objeto si es necesario
    idCartaGantt: string;
    horaInicio: string; // Agrega hora de inicio en formato 'HH:mm:ss' o un objeto Date
    horaFin: string; // Agrega hora de fin en formato 'HH:mm:ss' o un objeto Date
}
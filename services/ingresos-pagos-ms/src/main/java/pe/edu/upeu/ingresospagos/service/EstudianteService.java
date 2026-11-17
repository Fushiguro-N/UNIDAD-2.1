package pe.edu.upeu.ingresospagos.service;

import pe.edu.upeu.ingresospagos.dto.CrearEstudianteRequest;
import pe.edu.upeu.ingresospagos.dto.CrearEstudianteResponse;
import pe.edu.upeu.ingresospagos.dto.EstadoCuentaResponse;
import pe.edu.upeu.ingresospagos.dto.EstudianteInfoResponse;

public interface EstudianteService {
    EstadoCuentaResponse obtenerEstadoCuenta(String idEstudiante);

    EstudianteInfoResponse obtenerInfo(String idEstudiante);

    void registrarSiNoExiste(String idEstudiante, String nombres, String apellidos);

    CrearEstudianteResponse crearNuevo(CrearEstudianteRequest request);
}

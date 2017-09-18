package ec.gob.sri.cst.controlador;

import java.util.List;

import ec.gob.sri.cst.modelo.ServicioConsumidorWS;

public class ControladorEjercicioProfesional {

	// TODO validar si esta informacion la obtiene de sesion
	private String ruc;
	
	// TODO cambiar por el servicio real
	private ServicioConsumidorWS servicioWs;
	
	// TODO cambiar el tipo de la Lista
	private List<String> profesiones;
	
	// TODO Cambiar el tipo de dato
	private String profesionSeleccionada;
	
	private boolean aplicaEjercicioProfesional;
	
	public ServicioConsumidorWS getServicioWs() {
		return servicioWs;
	}

	public void setServicioWs(ServicioConsumidorWS servicioWs) {
		this.servicioWs = servicioWs;
	}

	public List<String> getProfesiones() {
		return profesiones;
	}

	public void setProfesiones(List<String> profesiones) {
		this.profesiones = profesiones;
	}

	public String getProfesionSeleccionada() {
		return profesionSeleccionada;
	}

	public void setProfesionSeleccionada(String profesionSeleccionada) {
		this.profesionSeleccionada = profesionSeleccionada;
	}

	public boolean isAplicaEjercicioProfesional() {
		return aplicaEjercicioProfesional;
	}

	public void setAplicaEjercicioProfesional(boolean aplicaEjercicioProfesional) {
		this.aplicaEjercicioProfesional = aplicaEjercicioProfesional;
	}
	
	public boolean verificarTieneProfesiones() {
		
		boolean tieneProfesiones = false;
		
		cargarProfesiones();
		if (null != profesiones && profesiones.size() > 0) {
			tieneProfesiones = true;
		}
		
		return tieneProfesiones;
	}
	
	private void cargarProfesiones() {
		
		if(null == profesiones || profesiones.isEmpty()) {
			profesiones = servicioWs.obtenerProfesiones(ruc);
		}
	}
 }

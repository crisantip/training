package ec.gob.sri.cst.actualizacion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ec.gob.sri.cst.controlador.ControladorEjercicioProfesional;
import ec.gob.sri.cst.modelo.ServicioConsumidorWS;

@RunWith(MockitoJUnitRunner.class)
public class ControladorEjercicioProfesionalTest {

	/*
	 * Dado un contribuyente, identificado mediante su CI:
	 * Si tiene al menos un título de 3er o 4to nivel .:
	 * 	  verificarTieneProfesiones = true (habilitado)
	 *    profesiones (contiene uno o mas elementos)
	 * 
	 * Caso contrario .:
	 *    verificarTieneProfesiones = false (deshabilitado)
	 *    profesiones (lista nula o vacia)
	 */
	@InjectMocks
	ControladorEjercicioProfesional controladorEjercicioProfesional;
	
	@Mock
	ServicioConsumidorWS servicioWS;
	
	@Test
	public void testVerificarTieneProfesiones_True() {
		
		boolean tieneProfesiones = false;
		List<String> listaConProfesiones = Arrays.asList("Ingeniero", "Abogado");
		given(servicioWS.obtenerProfesiones(Mockito.anyString())).willReturn(listaConProfesiones);
		
		tieneProfesiones = controladorEjercicioProfesional.verificarTieneProfesiones();
		
		assertThat("Deberia tener profesiones", tieneProfesiones, is(true));
		verify(servicioWS, Mockito.times(1)).obtenerProfesiones(Mockito.anyString());

		// Valida que ya no se vuelva a invocar a 'obtenerProfesiones'
		tieneProfesiones = controladorEjercicioProfesional.verificarTieneProfesiones();
		verify(servicioWS, Mockito.times(1)).obtenerProfesiones(Mockito.anyString());
	}
	
	@Test
	public void testVerificarTieneProfesiones_False() {
		
		boolean tieneProfesiones = false;
		List<String> listaSinProfesiones = Arrays.asList();
		given(servicioWS.obtenerProfesiones(Mockito.anyString())).willReturn(listaSinProfesiones);
		
		tieneProfesiones = controladorEjercicioProfesional.verificarTieneProfesiones();
		
		assertThat("NO deberia tener profesiones", tieneProfesiones, is(false));
		
		// Valida que se vuelva a invocar a 'obtenerProfesiones', N veces (2 para este caso)
		tieneProfesiones = controladorEjercicioProfesional.verificarTieneProfesiones();
		verify(servicioWS, Mockito.times(2)).obtenerProfesiones(Mockito.anyString());
	}
}

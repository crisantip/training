package beans;

import clases.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "personaBean")
@RequestScoped
public class PersonaBean {

    private Persona persona = new Persona();
    private static List<Persona> lstPersonas = new ArrayList<Persona>();
    
    public PersonaBean() {
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        PersonaBean.lstPersonas = lstPersonas;
    }
    
    public void agregarPersona() {
        PersonaBean.lstPersonas.add(this.persona);
    }
    
    public void eliminarPersona(Persona p) {
        PersonaBean.lstPersonas.remove(p);
    }
}

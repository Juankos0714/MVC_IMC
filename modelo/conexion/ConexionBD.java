package MVC_IMC.modelo.conexion;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.dto.PersonaDTO;

import java.util.HashMap;

public class ConexionBD {

    public static HashMap<String, PersonaDTO> personasMap;
    private Coordinador miCoordinador;

    public ConexionBD() {
        if (personasMap == null) {
            personasMap = new HashMap<String, PersonaDTO>();
        }
    }

    public void guardarPersona(PersonaDTO persona) {
        if (persona != null && persona.getDocumento() != null) {
            String clave = persona.getDocumento().trim().toLowerCase();
            personasMap.put(clave, persona);
            System.out.println("Persona guardada en BD: " + persona.getDocumento());
        }
    }

    public PersonaDTO buscarPersona(String Documento) {
        if (Documento == null || Documento.trim().isEmpty()) {
            return null;
        }
        String clave = Documento.trim().toLowerCase();
        return personasMap.get(clave);
    }

    public java.util.List<PersonaDTO> obtenerTodasLasPersonas() {
        return new java.util.ArrayList<>(personasMap.values());
    }

    public boolean eliminarPersona(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        String clave = nombre.trim().toLowerCase();
        PersonaDTO personaEliminada = personasMap.remove(clave);
        if (personaEliminada != null) {
            System.out.println("Persona eliminada de BD: " + nombre);
            return true;
        }
        return false;
    }

    public int obtenerTotalPersonas() {
        return personasMap.size();
    }

    public boolean existePersona(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return false;
        }
        String clave = documento.trim().toLowerCase();
        return personasMap.containsKey(clave);
    }

    public void limpiarBaseDatos() {
        personasMap.clear();
        System.out.println("Base de datos limpiada.");
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public Coordinador getCoordinador() {
        return miCoordinador;
    }
}
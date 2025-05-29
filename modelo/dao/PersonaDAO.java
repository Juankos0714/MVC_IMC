package MVC_IMC.modelo.dao;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.conexion.ConexionBD;
import MVC_IMC.modelo.dto.PersonaDTO;
import java.util.ArrayList;
import java.util.List;

import static MVC_IMC.modelo.conexion.ConexionBD.personasMap;

public class PersonaDAO {
    private Coordinador miCoordinador;
    private ConexionBD miConexionBD;

    public PersonaDAO() {
        this.miConexionBD = new ConexionBD();
    }

    public void registrarPersona(PersonaDTO persona) {
        if (persona != null) {
            try {
                miConexionBD.guardarPersona(persona);
                System.out.println("Persona registrada exitosamente: " + persona.getNombre());
            } catch (Exception e) {
                System.err.println("Error al registrar persona: " + e.getMessage());
            }
        } else {
            System.err.println("Error: No se puede registrar una persona nula");
        }
    }

    public PersonaDTO consultarPersona(String Documento) {
        if (Documento == null || Documento.trim().isEmpty()) {
            System.out.println("Error: Nombre no puede estar vacío");
            return null;
        }

        try {
            PersonaDTO persona = miConexionBD.buscarPersona(Documento);
            if (persona != null) {
                System.out.println("Persona encontrada: " + persona.getNombre());
            } else {
                System.out.println("Persona no encontrada: " + Documento);
            }
            return persona;
        } catch (Exception e) {
            System.err.println("Error al consultar persona: " + e.getMessage());
            return null;
        }
    }

    public List<PersonaDTO> consultarTodasLasPersonas() {
        try {
            List<PersonaDTO> listaPersonas = miConexionBD.obtenerTodasLasPersonas();
            System.out.println("Consultadas " + listaPersonas.size() + " personas de la base de datos");
            return listaPersonas;
        } catch (Exception e) {
            System.err.println("Error al consultar todas las personas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public String eliminarPersona(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return "error";
        }
        String clave = documento.trim().toLowerCase();
        if (personasMap.containsKey(clave)) {
            personasMap.remove(clave);
            System.out.println("Persona eliminada: " + documento);
            return "ok";
        } else {
            System.out.println("Persona no encontrada para eliminar: " + documento);
            return "error";
        }
    }


    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public void setConexionBD(ConexionBD conexionBD) {
        this.miConexionBD = conexionBD;
    }

    public ConexionBD getConexionBD() {
        return miConexionBD;
    }
    public String actualizarPersona(PersonaDTO persona) {
        if (persona == null || persona.getDocumento() == null || persona.getDocumento().trim().isEmpty()) {
            return "error";
        }
        String clave = persona.getDocumento().trim().toLowerCase();
        if (personasMap.containsKey(clave)) {
            PersonaDTO personaExistente = personasMap.get(clave);
            personaExistente.setNombre(persona.getNombre());
            personaExistente.setEdad(persona.getEdad());
            personasMap.put(clave, personaExistente);
            return "ok";
        } else {
            return "error";
        }
    }
}
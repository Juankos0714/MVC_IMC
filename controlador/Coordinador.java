package MVC_IMC.controlador;

import MVC_IMC.modelo.Procesos;
import MVC_IMC.modelo.conexion.ConexionBD;
import MVC_IMC.modelo.dao.PersonaDAO;
import MVC_IMC.modelo.dto.PersonaDTO;
import MVC_IMC.vista.*;

public class Coordinador {
    private VentanaPrincipal ventanaPrincipal;
    private VentanaRegistro ventanaRegistro;
    private VentanaCalculo ventanaCalculo;
    private VentanaConsultaIndividual ventanaConsultaIndividual;
    private VentanaConsultarLista ventanaConsultarLista;
    private Procesos misProcesos;
    private PersonaDAO miPersonaDAO;
    private ConexionBD miConexionBD;

    public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public void setVentanaRegistro(VentanaRegistro ventanaRegistro) {
        this.ventanaRegistro = ventanaRegistro;
    }

    public void setVentanaCalculo(VentanaCalculo ventanaCalculo) {
        this.ventanaCalculo = ventanaCalculo;
    }

    public void setVentanaConsultaIndividual(VentanaConsultaIndividual ventanaConsultaIndividual) {
        this.ventanaConsultaIndividual = ventanaConsultaIndividual;
    }

    public void setVentanaConsultarLista(VentanaConsultarLista ventanaConsultarLista) {
        this.ventanaConsultarLista = ventanaConsultarLista;
    }

    public void setProcesos(Procesos misProcesos) {
        this.misProcesos = misProcesos;
    }

    public void setMiPersonaDAO(PersonaDAO miPersonaDAO) {
        this.miPersonaDAO = miPersonaDAO;
    }

    public void setMiConexionBD(ConexionBD miConexionBD) {
        this.miConexionBD = miConexionBD;
    }

    public void mostrarVentanaPrincipal() {
        ventanaPrincipal.setVisible(true);
    }

    public void mostrarVentanaCalculo() {
        ventanaCalculo.setVisible(true);
    }

    public void mostrarVentanaRegistro() {
        ventanaRegistro.setVisible(true);
    }

    public void mostrarVentanaConsultaIndividual() {
        ventanaConsultaIndividual.setVisible(true);
    }

    public void mostrarVentanaConsultarLista() {
        ventanaConsultarLista.setVisible(true);
    }

    public PersonaDTO calcularIMC(PersonaDTO persona) {
        misProcesos.calcularIMC(persona);
        return persona;
    }

    public void registrarPersona(PersonaDTO persona) {
        miPersonaDAO.registrarPersona(persona);
    }

    public PersonaDTO consultarPersona(String documento) {
        return miPersonaDAO.consultarPersona(documento);
    }

    public java.util.List<PersonaDTO> consultarTodasLasPersonas() {
        return miPersonaDAO.consultarTodasLasPersonas();
    }

    public boolean existePersona(String Documento) {
        PersonaDTO persona = miPersonaDAO.consultarPersona(Documento);
        return persona != null;
    }

    public String actualizarPersona(PersonaDTO persona) {
        return miPersonaDAO.actualizarPersona(persona);
    }

    public void cerrarAplicacion() {
        System.exit(0);
    }

    public String eliminarPersona(String documento) {

        return miPersonaDAO.eliminarPersona(documento);
    }
}
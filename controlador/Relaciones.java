package MVC_IMC.controlador;

import MVC_IMC.modelo.Procesos;
import MVC_IMC.modelo.conexion.ConexionBD;
import MVC_IMC.modelo.dao.PersonaDAO;
import MVC_IMC.vista.*;

public class Relaciones {

    public Relaciones(){
        VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
        VentanaCalculo ventanaCalculo=new VentanaCalculo(ventanaPrincipal,true);
        VentanaRegistro ventanaRegistro = new VentanaRegistro(ventanaPrincipal,true);
        VentanaConsultaIndividual ventanaConsultaIndividual=new VentanaConsultaIndividual(ventanaPrincipal, true);
        VentanaConsultarLista ventanaConsultarLista=new VentanaConsultarLista(ventanaPrincipal,true);
        Procesos misProcesos = new Procesos();
        PersonaDAO miPersonaDAO=new PersonaDAO();
        ConexionBD miConexionBD=new ConexionBD();
        Coordinador miCoordinador = new Coordinador();

        // Se establecen las relaciones entre clases
        ventanaPrincipal.setCoordinador(miCoordinador);
        ventanaRegistro.setCoordinador(miCoordinador);
        ventanaCalculo.setCoordinador(miCoordinador);
        ventanaConsultaIndividual.setCoordinador(miCoordinador);
        ventanaConsultarLista.setCoordinador(miCoordinador);
        miPersonaDAO.setCoordinador(miCoordinador);
        miConexionBD.setCoordinador(miCoordinador);
        misProcesos.setCoordinador(miCoordinador);

        // Se establecen relaciones con la clase coordinador
        miCoordinador.setVentanaPrincipal(ventanaPrincipal);
        miCoordinador.setVentanaRegistro(ventanaRegistro);
        miCoordinador.setVentanaCalculo(ventanaCalculo);
        miCoordinador.setVentanaConsultaIndividual(ventanaConsultaIndividual);
        miCoordinador.setVentanaConsultarLista(ventanaConsultarLista);
        miCoordinador.setProcesos(misProcesos);
        miCoordinador.setMiPersonaDAO(miPersonaDAO);
        miCoordinador.setMiConexionBD(miConexionBD);



        // Mostrar ventana principal
        miCoordinador.mostrarVentanaPrincipal();
    }
}

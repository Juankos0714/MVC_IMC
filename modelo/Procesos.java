package MVC_IMC.modelo;



import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.dto.PersonaDTO;

public class Procesos {

    public Procesos() {
    }

    public void calcularIMC(PersonaDTO persona) {
        double peso = persona.peso;
        double talla = persona.talla;
        double imcCalculado = 0;

        if (talla > 0) {
            imcCalculado = peso / (talla * talla);
        }
        persona.imc = imcCalculado;

        if (imcCalculado < 18.5) {
            persona.estado = "Anorexia";
            persona.mensaje = "Visite a su médico para mejorar su alimentación.";
        } else if (imcCalculado >= 18.5 && imcCalculado < 20) {
            persona.estado = "Delgadez";
            persona.mensaje = "Puede comer otro poquito.";
        } else if (imcCalculado >= 20 && imcCalculado < 27) {
            persona.estado = "Normalidad";
            persona.mensaje = "¡Estado de peso saludable!";
        } else if (imcCalculado >= 27 && imcCalculado < 30) {
            persona.estado = "Obesidad Grado I (Sobrepeso)";
            persona.mensaje = "Pilas, debería considerar ir al gym y mejorar hábitos.";
        } else if (imcCalculado >= 30 && imcCalculado < 35) {
            persona.estado = "Obesidad Grado II";
            persona.mensaje = "Debe ir al gym y mejorar su alimentación.";
        } else if (imcCalculado >= 35 && imcCalculado < 40) {
            persona.estado = "Obesidad Grado III";
            persona.mensaje = "Debería ir al gym, mejorar su alimentación y consultar a un médico.";
        } else if (imcCalculado >= 40) {
            persona.estado = "Obesidad Mórbida";
            persona.mensaje = "Debe cuidar mucho su peso, mejorar su alimentación y consultar a un médico urgentemente.";
        } else {
            persona.estado = "Datos insuficientes o incorrectos";
            persona.mensaje = "Verifique el peso y la talla ingresados. Deben ser mayores a cero.";
        }
    }

    public void setCoordinador(Coordinador miCoordinador) {
    }
}


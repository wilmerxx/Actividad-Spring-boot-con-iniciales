package wilmer.practica2.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wilmer.practica2.modelos.WSPersona;
import wilmer.practica2.repositorio.WSPersonaRepositorio;

import java.util.List;

@Service
public class WSPersonaServicio {

    @Autowired
    WSPersonaRepositorio wmPersonarepositorio;

    public void wsagregarPersona(WSPersona wsPersona){
        wmPersonarepositorio.wmagregarPersonas(wsPersona);
    }

    public void wseliminarPersona(WSPersona wsPersona){
        wmPersonarepositorio.wsEliminarPersona(wsPersona);
    }

    public List<WSPersona> wsListaPersona(){
        return wmPersonarepositorio.wslistarPersonas();
    }

    public WSPersona wsBuscarPersona(String wsidentificacion){
        return wmPersonarepositorio.wsbuscarPersona(wsidentificacion);
    }

    public void wsactualizar(WSPersona wsPersona){
        wmPersonarepositorio.wsactualizarPersona(wsPersona);
    }

    public static String convertirMayusculas(String entrada){
        return entrada.toUpperCase();
    }
    public static boolean verificarCedulaEcuatoriana(String identificacion){
        // Verificar que la longitud de la cédula sea correcta
        if (identificacion.length() != 10) return false;
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            // Convertir cada carácter de la cédula a un dígito numérico
            int digito = Character.getNumericValue(identificacion.charAt(i));

            // Si el índice es par, multiplicar el dígito por 2, si no, dejarlo como está
            int valor;
            if (i % 2 == 0) {
                valor = 2 * digito;
            } else {
                valor = digito;
            }
            // Luego, si el resultado es mayor que 9, restarle 9
            if (valor > 9) {
                valor = valor - 9;
            }
            // Sumar el resultado a la suma total
            suma += valor;
        }
        // Calcular el dígito verificador y compararlo con el último dígito de la cédula
        // Si son iguales, la cédula es válida
        return 10 - suma % 10 == Character.getNumericValue(identificacion.charAt(9));
    };
}

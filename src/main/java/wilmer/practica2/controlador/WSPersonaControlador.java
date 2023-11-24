package wilmer.practica2.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import wilmer.practica2.modelos.WSPersona;
import wilmer.practica2.servicios.WSPersonaServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Para tener acceso
@RestController
public class WSPersonaControlador {
    @Autowired
    WSPersonaServicio wsPersonaServicio;
    List<WSPersona> listaWSPersonas = new ArrayList<>();


    @PostMapping("/guardarPersona")
    public String guardarPersona(
            @RequestParam(value = "ide") String wsidentificacion,
            @RequestParam(value = "nom") String wmnombre,
            @RequestParam(value = "ape") String apellido,
            @RequestParam(value = "dir") String direccion,
            @RequestParam(value = "tel") String telefono,
            @RequestParam(value = "fechaNac") String fechaNacimiento)
            {
        WSPersona obj = new WSPersona();

        if(wsPersonaServicio.verificarCedulaEcuatoriana(wsidentificacion)){
            obj = new WSPersona(
                    wsPersonaServicio.convertirMayusculas(wsidentificacion),
                    wsPersonaServicio.convertirMayusculas(wmnombre),
                    wsPersonaServicio.convertirMayusculas(apellido),
                    wsPersonaServicio.convertirMayusculas(direccion),
                    wsPersonaServicio.convertirMayusculas(telefono),
                    wsPersonaServicio.convertirMayusculas(fechaNacimiento)
            );

            wsPersonaServicio.wsagregarPersona(obj);
            return "Persona guardada con exito";
        }else {
            return "Ingrese una cedula valida";
        }

    }

    /**
     * Obtener persona por wsidentificacion
     * */
    @GetMapping("/obtenerPersonaPorIdentificacion/{cedula}")
    public String obtenerPersona(@PathVariable(name ="cedula") String wsidentificacion){
        WSPersona wsPersonaEncontrada = wsPersonaServicio.wsBuscarPersona(wsidentificacion);

        if (wsPersonaEncontrada != null){
            return wsPersonaEncontrada.toString();
        }else{
            return "Persona no existe";
        }
    }

    @PutMapping("/actualizarPersona")
    public String actualizarPersona(@RequestBody WSPersona wspersona){
       wsPersonaServicio.wsactualizar(wspersona);
      return "Persona actualizada";
    }

    @GetMapping("/listarPersonas")
    public String wsListaPersonas(){
        List<WSPersona> wslistaPersona = wsPersonaServicio.wsListaPersona();
        if (!wslistaPersona.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (WSPersona wsPersona : wslistaPersona) {
                result.append(wsPersona.toString()).append("\n");
            }
            return result.toString();
        } else {
            return "No se encontraron personas";
        }

    }


}

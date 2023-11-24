package wilmer.practica2.repositorio;

import org.springframework.stereotype.Repository;
import wilmer.practica2.modelos.WSPersona;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class WSPersonaRepositorio {

    public List<WSPersona> wmlistapersona = new ArrayList<>();

    public void wmagregarPersonas(WSPersona wsPersona){
        this.wmlistapersona.add(wsPersona);
    }

    public void wsEliminarPersona(WSPersona wsPersona){ this.wmlistapersona.remove(wsPersona);    }

    public List<WSPersona> wslistarPersonas(){
        return this.wmlistapersona;
    }
    public WSPersona wsbuscarPersona(String wsidentificacion){
        //lambdas
        return wslistarPersonas().stream()
                .filter(item -> item.getIdentificacion().equals(wsidentificacion)).findAny().get();
    }

    public void wsactualizarPersona(WSPersona wsPersona){
        WSPersona wspersonaBuscada = wsbuscarPersona(wsPersona.getIdentificacion());

        if(!Objects.isNull(wspersonaBuscada)){
            wmlistapersona.remove(wmlistapersona.indexOf(wspersonaBuscada));
            wspersonaBuscada.setDireccion(wsPersona.getDireccion());
            wspersonaBuscada.setNombre(wsPersona.getNombre());
            wspersonaBuscada.setApellido(wsPersona.getApellido());
            wspersonaBuscada.setTelefono(wsPersona.getTelefono());
            wspersonaBuscada.setFechaNac(wsPersona.getFechaNac());
            wmlistapersona.add(wspersonaBuscada);
        }

    }


}

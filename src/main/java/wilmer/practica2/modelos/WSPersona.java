package wilmer.practica2.modelos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//Constructor de lombok
@AllArgsConstructor
@NoArgsConstructor
public class WSPersona {
    @JsonProperty("id")
    private String identificacion;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("apellido")
    private String apellido;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("telefono")
    private String telefono;
    @JsonProperty("fechaNac")
    private String fechaNac;
}
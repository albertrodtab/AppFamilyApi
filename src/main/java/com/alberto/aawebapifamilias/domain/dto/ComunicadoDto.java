package com.alberto.aawebapifamilias.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ComunicadoDto {

   /* Es una especie de clon del Centro pero no tiene correspondencia con la base de datos solo sirve para definir
    como quiero mostrar mis datos de una forma acotada
    Así envio los campos del centro y un campo a mayores que me servirá para saber con que residentes está relacionado.
    */


    private String nombreComunicado;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaEnvio;
    private String descripcion;
    private long profesional;


}

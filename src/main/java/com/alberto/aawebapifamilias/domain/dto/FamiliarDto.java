package com.alberto.aawebapifamilias.domain.dto;

import com.alberto.aawebapifamilias.domain.Residente;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class FamiliarDto {

   /* Es una especie de clon del plan pero no tiene correspondencia con la base de datos solo sirve para definir
    como quiero mostrar mis datos de una forma acotada
    Así envio los campos del familiar y un campo a mayores que me servira para saber con que residente está relacionado.
    */
    //debo indicarle en que formato voy a introducir las fechas

    private String nombre;
    private String apellidos;
    private String dni;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    private String telefono;
    private long residente;


}

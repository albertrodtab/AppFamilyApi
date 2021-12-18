package com.alberto.aawebapifamilias.domain.dto;

import com.alberto.aawebapifamilias.domain.Residente;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CentroDto {

   /* Es una especie de clon del Centro pero no tiene correspondencia con la base de datos solo sirve para definir
    como quiero mostrar mis datos de una forma acotada
    Así envio los campos del centro y un campo a mayores que me servirá para saber con que residentes está relacionado.
    */

    private String nombre;
    private String direccion;
    private String numRegistro;
    private String email;
    private String telefono;


}

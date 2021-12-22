package com.alberto.aawebapifamilias.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comunicados")
public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre_comunicado")
    private String nombreComunicado;
    @Column (name = "fecha_envio")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaEnvio;
    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "profesional_id")
    @JsonBackReference(value = "comunicadoProfesional")
    private Profesional profesional;
}

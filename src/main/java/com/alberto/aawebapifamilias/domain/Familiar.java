package com.alberto.aawebapifamilias.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@Entity(name = "familiares")
public class Familiar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private String dni;
    @Column(name = "fecha_nacimiento")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    @Column
    private String telefono;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rel_fam_res",
            joinColumns = @JoinColumn(name = "familiar_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="residente_id", nullable = false)
    )
    List<Residente> residentes;

    public Familiar(){
        residentes = new ArrayList<>();
    }



}
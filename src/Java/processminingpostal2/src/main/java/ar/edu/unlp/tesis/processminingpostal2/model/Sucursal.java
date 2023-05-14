package ar.edu.unlp.tesis.processminingpostal2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "suc_descripcion")
    private String descripcion;
    @Column(name = "suc_direccion")
    private String direccion;
    @Column(name = "suc_partido")
    private String partido;
    @Column(name = "suc_provincia")
    private String provincia;
    @Column(name = "suc_telefono")
    private String telefono;
    @Column(name = "suc_mail")
    private String mail;

}

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
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String direccion;
    private String localidad;
    private String partido;
    private String provincia;
    @Column(name = "nro_seguimiento")
    private String seguimiento;
    private LocalDate fechaingreso;
    @Column(name = "case_id")
    private Long caseId;
    @ManyToOne
    @JoinColumn(name = "suc_id")
    private Sucursal sucursal;

}

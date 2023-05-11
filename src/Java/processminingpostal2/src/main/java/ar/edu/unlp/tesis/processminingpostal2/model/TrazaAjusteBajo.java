package ar.edu.unlp.tesis.processminingpostal2.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trazasajustebajo")
public class TrazaAjusteBajo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "case_id")
    private String case_id;
    @Column(name = "trace_fitness")
    private Float ajuste;
    private boolean resuelta;
    @Column(name = "timestamp")
    private LocalDate fechaIngreso;
}

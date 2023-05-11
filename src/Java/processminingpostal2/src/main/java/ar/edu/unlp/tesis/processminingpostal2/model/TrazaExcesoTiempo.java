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
@Table(name = "trazasexcesotiempo")
public class TrazaExcesoTiempo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "case_id")
    private String case_id;
    private boolean resuelta;
    @Column(name = "timestamp")
    private LocalDate fechaIngreso;
}

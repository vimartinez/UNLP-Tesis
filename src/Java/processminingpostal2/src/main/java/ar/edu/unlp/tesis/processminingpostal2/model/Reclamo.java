package ar.edu.unlp.tesis.processminingpostal2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reclamos")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "rec_fecha")
    private LocalDate fechaReclamo;
    @Column(name = "case_id")
    private Long caseId;

}

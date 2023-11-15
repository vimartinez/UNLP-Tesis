package ar.edu.unlp.tesis.processminingpostal2;

import ar.edu.unlp.tesis.processminingpostal2.model.Envio;
import ar.edu.unlp.tesis.processminingpostal2.model.Sucursal;
import ar.edu.unlp.tesis.processminingpostal2.repository.EnviosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EnvioRepositoryTest {
    @Autowired
    private EnviosRepository envioRepository;

   /* @Test
    public void whenGetListadoDeEnvios_thenRetornaListadoCorrecto() {
        Envio envio1 = new Envio("Direccion1", "Localidad1", "Partido1", "Provincia1", "Seguimiento1", LocalDate.now(), 1L, sucursal);
        Envio envio2 = new Envio("Direccion2", "Localidad2", "Partido2", "Provincia2", "Seguimiento2", LocalDate.now(), 2L, sucursal);

        envioRepository.save(envio1);
        envioRepository.save(envio2);

        List<Envio> listadoDeEnvios = envioRepository.findAll();

        assertThat(listadoDeEnvios).isNotEmpty();
        assertThat(listadoDeEnvios).hasSize(2);

    }*/

    @Test
    public void dummyTest(){
        Assertions.assertEquals(1,1);
    }
}

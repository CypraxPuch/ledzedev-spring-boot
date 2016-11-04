package com.ledzedev.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * Código generado por Gerado Pucheta Figueroa
 * Twitter: @ledzedev
 * http://ledze.mx
 * 27/Oct/2016.
 */
//1. empecemos con una entidad para escribirle a la base de datos.
@Entity
class Noticia{

	@Id
	@GeneratedValue
	private Long id;

	private String tituloNoticia;

	public Noticia() { //constructor vacío porque así lo dice JPA
	}

	public Noticia(String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTituloNoticia() {
		return tituloNoticia;
	}

	public void setTituloNoticia(String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	@Override
	public String toString() {
		//es buena práctica sobreescribir el método toString para poder conocer el contenido de cada objeto
		return "Noticia{" +
				"id=" + id +
				", tituloNoticia='" + tituloNoticia + '\'' +
				'}';
	}
}

/**
 * Código generado por Gerado Pucheta Figueroa
 * Twitter: @ledzedev
 * http://ledze.mx
 * 27/Oct/2016.
 */
//2. necesito una interfaz que hable con la base de datos y que escriba las noticias que quiero almacenar.
interface  NoticiasRepositorio extends JpaRepository<Noticia, Long>{
	//por convención hay que llamar los métodos en inglés y automáticamente SpringBoot intuye el nombre de la propiedad
	// en este caso solo agrego el findBy=buscaPor
	// TituloNoticia = nuestro objeto Noticia tiene una propiedad tituloNoticia
	Collection<Noticia> findByTituloNoticia(String tn);
}



/**
 * Código generado por Gerado Pucheta Figueroa
 * Twitter: @ledzedev
 * http://ledze.mx
 * 27/Oct/2016.
 */
@SpringBootApplication
public class DemoSpringBootApplication {
/*
 3. Agregamos código para escribir en la base de datos algunas noticias.
 - la clase principal debe tener la anotación @SpringBootApplication.
*/
	private static final Logger log = LoggerFactory.getLogger(DemoSpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}

	/*
 - Utilizamos la interfa CommandLineRunner que nos proporciona SpringBoot y
 	tiene un solo método que tiene la función de cargar cuando se ejecuta el main.
 - Recuerda que nuestra base de datos es h2 para este ejemplo, pero puedes utilizar el manejador de BD que te guste.
 - Escribimos el runner como un bean para que sea detectado por spring boot y que cargue lo que necesitamos
	al arranque*/
	@Bean
	CommandLineRunner commandLineRunner(NoticiasRepositorio nr){
		//como es java 8 puedo ocupar un lambda ya que esta interfaz solo tiene 1 método
		return args -> {
			//inserto en la BD
			log.info("Guardando en la BD.");
			Arrays.asList("Donald Trump gana la presidencia en EUA.",
					"El peso mexicano alcanza al dolar y su paridad es 1 a 1.",
					"Nigeria tiene crisis por el tomate.",
					"Brasil reinstala a Dilma Rouseff y la disculpan públicamente.",
					"EPN renuncia a su cargo en México y la gente no se lo permite.")
			.forEach( n -> nr.save(new Noticia(n)) );


			log.info("Consulto todos los registros existentes en la BD.");
			//busco los elementos y los imprimo en el log
			nr.findAll().forEach(
					n -> {log.info(n.getTituloNoticia() + " id: "+n.getId());
					});


			log.info("Consulta por el método que agregamos.");
			//ahora busco con el método que nosotros hicimos.
			nr.findByTituloNoticia("Donald Trump gana la presidencia en EUA.").forEach(
					n -> {log.info("El id de la noticia es: "+n.getId());
					});
		};
	};
}


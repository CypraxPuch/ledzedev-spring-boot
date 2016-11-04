# ledzedev-spring-boot
Demo de spring boot con h2, jpa y spring boot starter test

1. empecemos con una entidad para escribirle a la base de datos.

2. necesito una interfaz que hable con la base de datos y que escriba las noticias que quiero almacenar.

3. Agregamos código para escribir en la base de datos algunas noticias.
 - la clase principal debe tener la anotación @SpringBootApplication.
 - Utilizamos la interfa CommandLineRunner que nos proporciona SpringBoot y tiene un solo método que tiene la función de cargar cuando se ejecuta el main.
 - Recuerda que nuestra base de datos es h2 para este ejemplo, pero puedes utilizar el manejador de BD que te guste.
 - Escribimos el runner como un bean para que sea detectado por spring boot y que cargue lo que necesitamos al arranque.

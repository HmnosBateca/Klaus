package com.Leather.springboot.backend.apirest;

/*
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Leather.models.dao.IUsuarioDao;
import com.Leather.models.entity.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
class KlausApplicationTests {//inyeccion a la capa de repositorio para traer las operaciones de insercion
	
	@Autowired
	private IUsuarioDao iUsuarioDao;

	// @Autowired
	// private BCryptPasswordEncoder encoder;

    @Test
    public void crearUsuarioTest() {//Se definen los parametros de entrada
      Usuario us = new Usuario();
    
      us.setId(1);
      us.setNombre("redi");
      us.setClave("leo");
      //us.setClave(encoder.encode("leonardo"));//vamos a tranformar la cadena de testo en algo no legible
      Usuario retorno = iUsuarioDao.save(us);//base de datos
      System.out.println(retorno);
      assertTrue(retorno.getClave().equalsIgnoreCase(us.getClave()));
    }         
    // se verifica lo que retorna el usuario insertado su clave es igual a
    // clave que estoy mandandole en el objeto que se instancio anteriormente
    // con eso me aseguro que la clave de base de datos es la misma que la que guardo aquí y la insercion es correcta
    // La prueba se hace con un Run AS-Junit Test
}           

/*
@RunWith(SpringRunner.class)
@SpringBootTest
class KlausApplicationTests {
	
	@Autowired
	private IUsuarioDao repo;
	
	@Test
	public void CrearUsuarioTest() {
		Usuario us = new Usuario();
		us.setId(1);
		us.setNombre("redi");
		us.setClave("leonardo");
		Usuario retorno = repo.save(us);
		assertTrue(retorno.getClave().equalsIgnoreCase(us.getClave()));
	}
}*/
 import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class CrudApplicationTests {

	@Test
	void contextLoads() {
	}

}
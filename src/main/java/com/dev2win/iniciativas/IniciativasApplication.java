package com.dev2win.iniciativas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dev2win.iniciativas.usuarios.Usuario;
import com.dev2win.iniciativas.usuarios.UsuarioService;

@SpringBootApplication
public class IniciativasApplication {

	@Autowired
	UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(IniciativasApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (args) -> {

			usuarioService.getAllUsuarios().forEach(Usuario -> usuarioService.deleteUsuario(Usuario.getUsuarioId()));

			System.out.println("Adding Usuarios....\n");
			usuarioService.addUsuario(new Usuario("Ricardo Pulido", "prueba", "Proponente", "", "Estudiante"));

			System.out.println("\nGetting all Usuarios....");
			usuarioService.getAllUsuarios().forEach(Usuario -> System.out.println(Usuario));

			/*
			 * System.out.println("\nGetting Usuario with id = 1....");
			 * System.out.println(usuarioService.getUsuario(1L));
			 */

			/*
			 * System.out.println("\nDeleting Usuario....");
			 * usuarioService.deleteUsuario(1L);
			 */
		};
	}

}

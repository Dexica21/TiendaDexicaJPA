package edu.pe.idat.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import edu.pe.idat.repository.*;

import edu.pe.idat.Model.*;
import edu.pe.idat.service.*;


@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home(Model model,@RequestParam(value = "usuario", required = false) String nombre_Usuario ) {
		model.addAttribute("nombre_Usuario", nombre_Usuario);
		return "home.html";
	}
	
	@GetMapping("/User/Registro")
	public String Registro(Model model) {
		
		Usuario user = new Usuario();
		model.addAttribute("usuario", user);
		
		return "Registro.html";
	}
	
	@GetMapping("/Producto/Catalogo")
	public String CatalogoProductos(Model model) {
		
		return "Catalogo.html";
	}
	
	@GetMapping("/Usuario/Configuracion")
	public String ConfiguracionCuenta(Model model) {
		
		return "ConfiguracionUsuario.html";
	}
	
	@GetMapping("/Login")
	public String Login(Model model) {
			
		Login login = new Login();
		model.addAttribute("log", login);
		
		return "Login.html";
	}
	
	@GetMapping("/OlvidasteContraseña")
	public String OlvidasteContraseña(Model model) {
		
		return "OlvidasteContraseña.html";
	}
	
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	//Aca tomamos los inputs y llamamos al servicio validar credencial para identificar que este en la base de datos 
	@PostMapping("/Login/Felicitaciones")
	public String BotonLogin(@ModelAttribute("log") Login login, Model model) {
		String nombre_Usuario = login.getNombre_Usuario();
        String pass = login.getPass();
        if (usuarioService.validarCredenciales(nombre_Usuario, pass)) {
        	model.addAttribute("usuario", nombre_Usuario);
        	return "redirect:/home?usuario=" + nombre_Usuario;
        }else {
        	return "Login.html";
        }  
	}
	
	
	
	
	
	
	@Autowired
	private UsuarioService UsuarioService;
	
	//Para registrar un nuevo usuario
	@PostMapping("/User/CrearCuenta")
	public String BotonFormulario(@ModelAttribute("usuario") Usuario usuario) {
	    usuario.setFecha_registro(new Date()); // Establecer la fecha
	    usuarioService.createUser(usuario);
	    return "register_success.html";
	}
	
	
	
	
	//aca utilizamos un metodo Post para actualizar el Usuario
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
    @ResponseBody
    public String sendPostMessage(
    		@RequestParam("idUsuario") String idUsuario,
            @RequestParam("pass") String pass,
            @RequestParam("correo") String correo,
            @RequestParam("Activo") String activo){
{

	//creamos un nuevo objeto usuario y llamamos al servicio de actualizar usuario
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Integer.parseInt(idUsuario));
        usuario.setPass(pass);
        usuario.setCorreo(correo);
        usuario.setActivo(Boolean.parseBoolean(activo));

        UsuarioService.ActualizarUsuario(usuario);

        return "redirect:/Administracion.html";
        
        }
    }
	
	
	
	//Para hacer la lista de usuarios 
	@GetMapping("/findAll")
	public String finAll(Model model) {
	    List<Usuario> usuarios = usuarioService.findAll();
	    model.addAttribute("usuarios", usuarios);
	    model.addAttribute("usuario", new Usuario());

	    return "Administracion.html";
	}
	
	
}



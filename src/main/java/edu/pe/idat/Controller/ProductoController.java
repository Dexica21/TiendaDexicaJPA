package edu.pe.idat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {

	@GetMapping("/Catalogo/Vividi")
	public String Vividi(Model model) {
		return "/DetalleProducto/Vividi.html";
	}
	
	@GetMapping("/Catalogo/WishStop")
	public String WishStop(Model model) {
		return "/DetalleProducto/WishStop.html";
	}
	
	@GetMapping("/Catalogo/Universe")
	public String Universe(Model model) {
		return "/DetalleProducto/Universe.html";
	}
	
	@GetMapping("/Catalogo/TayDay")
	public String TayDay(Model model) {
		return "/DetalleProducto/TayDay.html";
	}
	
	@GetMapping("/Catalogo/index")
	public String index(Model model) {
		return "/DetalleProducto/index.html";
	}
	
	@GetMapping("/Catalogo/Fly")
	public String Fly(Model model) {
		return "/DetalleProducto/Fly.html";
	}
	
	@GetMapping("/Catalogo/DeportivoBasico")
	public String DeportivoBasico(Model model) {
		return "/DetalleProducto/DeportivoBasico.html";
	}
	
	@GetMapping("/Catalogo/Deportivo")
	public String Deportivo(Model model) {
		return "/DetalleProducto/Deportivo2.html";
	}
}

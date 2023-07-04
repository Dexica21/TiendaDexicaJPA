package edu.pe.idat.service;

import edu.pe.idat.Model.*;
import edu.pe.idat.repository.*;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public void createUser (Usuario usuario) {
		LocalDateTime fechaRegistro = LocalDateTime.ofInstant(usuario.getFecha_registro().toInstant(), ZoneId.systemDefault());
		
		usuarioRepository.crearUsuario(usuario.getNombre_Usuario(), usuario.getCorreo(), usuario.getPass(),
				fechaRegistro);	
	}
	
	public List<Usuario> findAll(){
	    return usuarioRepository.findAllOrderedByNombreUsuario();
	}
	
	@Transactional
    public boolean validarCredenciales(String nombre_Usuario, String pass) {
        Usuario usuario = usuarioRepository.findByNombreUsuarioAndPass(nombre_Usuario, pass);
        return usuario != null;
    }
	
	public void ActualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(usuario.getIdUsuario(), usuario.getCorreo(), usuario.getPass(),
                usuario.isActivo());
    }
	
	
}

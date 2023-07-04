package edu.pe.idat.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import edu.pe.idat.Model.*;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

	@Query("SELECT u FROM Usuario u WHERE u.nombre_Usuario = :nombre_Usuario AND u.pass = :pass")
    Usuario findByNombreUsuarioAndPass(@Param("nombre_Usuario") String nombreUsuario, @Param("pass") String pass);
	
	
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_crear_usuario(:nombre_Usuario, :correo, :pass, :fecha_registro)}",
	nativeQuery = true)
	void crearUsuario(@Param("nombre_Usuario")String nombre_Usuario,
			@Param("correo")String correo,
			@Param("pass")String pass,
			@Param("fecha_registro")LocalDateTime fecha_registro);
	
	
	
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_actualizar_usuario(:id_Usuario, :pass, :correo, :Activo)}",nativeQuery = true)
	void actualizarUsuario(
			@Param("id_Usuario")Integer idUsuario,
			@Param("correo")String correo,
			@Param("pass")String pass,
			@Param("Activo")boolean activo)
	;
	
	
	
	//Lista mediante un query para consultar los usuarios registrados
	@Query("SELECT u FROM Usuario u ORDER BY u.nombre_Usuario")
	List<Usuario> findAllOrderedByNombreUsuario();
	
}






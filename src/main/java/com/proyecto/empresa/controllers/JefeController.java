package com.proyecto.empresa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.empresa.mappers.JefeMapper;
import com.proyecto.empresa.models.Jefe;
import com.proyecto.empresa.services.JefeServices;
import com.proyecto.empresa.to.JefeTO;

@RestController
@RequestMapping("/api/jefes")
public class JefeController {

	private final JefeServices jefeServices;
	private final JefeMapper jefeMapper;

	@Autowired
	public JefeController(JefeServices jefeServices, JefeMapper jefeMapper) {
		this.jefeServices = jefeServices;
		this.jefeMapper = jefeMapper;
	}

//MÉTODOS COMUNES	
	// Endpoint para obtener todos los jefes
	@GetMapping
	public ResponseEntity<List<JefeTO>> getAllJefes() {
		List<Jefe> obtenerJefes = jefeServices.getAll();
		// Si no existen
		if (obtenerJefes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// Si existen
		List<JefeTO> jefeTO = jefeMapper.convertirListaAJefeTO(obtenerJefes);
		return new ResponseEntity<>(jefeTO, HttpStatus.OK);
	}

	// Endpoint para buscar jefes por Id
	@GetMapping(path = "/{id}")
	public ResponseEntity<JefeTO> findById(@PathVariable("id") Long id) {
		Optional<Jefe> obtenerJefeId = jefeServices.findById(id);
		// Si no existen
		if (obtenerJefeId.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// Si existen
		JefeTO jefeTO = jefeMapper.convertirAJefeTO(obtenerJefeId.get());
		return new ResponseEntity<>(jefeTO, HttpStatus.OK);
	}

//MÉTODOS PROPIOS   	
	// Endpoint para CREAR un jefe
	@PostMapping
	public ResponseEntity<JefeTO> createJefe(@RequestBody JefeTO jefeTo) {
		// Llamada al servicio para guardar el jefe
		Jefe createJefe = jefeServices.createJefe(jefeTo);

		// Devuelve una respuesta 201
		JefeTO jefeTO = jefeMapper.convertirAJefeTO(createJefe);

		return new ResponseEntity<>(jefeTO, HttpStatus.CREATED);
	}

	// Endpoint para ACTUALIZAR un jefe
	@PutMapping(path = "/{id}")
	public ResponseEntity<JefeTO> updateJefe(@PathVariable("id") Long id, @RequestBody JefeTO jefeAct) {
		// Llamada al servicio para actualizar el jefe
		Jefe jefeActualizado = jefeServices.updateJefe(id, jefeAct);
		// Devuelve el jefe actualizado con un código HTTP 200 OK
		JefeTO jefeTO = jefeMapper.convertirAJefeTO(jefeActualizado);

		return new ResponseEntity<>(jefeTO, HttpStatus.OK);

	}

	// Endpoint para BORRAR un jefe
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteJefeById(@PathVariable("id") Long id) {
		jefeServices.deleteJefeById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Endpoint para buscar por nombre
	@GetMapping(path = "/nombre/{nombre}")
	public ResponseEntity<List<JefeTO>> findByNombre(@PathVariable("nombre") String nombre) {
		List<Jefe> jefes = jefeServices.findByNombre(nombre);

		// Si no encuentra el nombre, devuelve un 404. Si existe, devuelve un 200
		if (jefes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<JefeTO> jefeTO = jefeMapper.convertirListaAJefeTO(jefes);
		return new ResponseEntity<>(jefeTO, HttpStatus.OK);

	}

	// Endpoint para buscar por edad
	@GetMapping(path = "/edad/{edad}")
	public ResponseEntity<List<JefeTO>> findByEdad(@PathVariable("edad") Integer edad) {
		List<Jefe> jefes = jefeServices.findByEdad(edad);

		// Si no encuentra la edad, devuelve un 404. Si existe, devuelve un 200
		if (jefes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<JefeTO> jefeTO = jefeMapper.convertirListaAJefeTO(jefes);
		return new ResponseEntity<>(jefeTO, HttpStatus.OK);

	}

	// Endpoint para buscar por un salario superior a x.
	@GetMapping(path = "/salariosup/{salario}")
	public ResponseEntity<List<JefeTO>> findBySuperiorASalario(@PathVariable("salario") Long salario) {
		List<Jefe> jefes = jefeServices.findBySuperiorASalario(salario);

		// Si no encuentra el salario, devuelve un 404. Si existe, devuelve un 200
		if (jefes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<JefeTO> jefeTO = jefeMapper.convertirListaAJefeTO(jefes);
		return new ResponseEntity<>(jefeTO, HttpStatus.OK);
	}

	// Endpoint para buscar por un salario inferior a x.
	@GetMapping(path = "/salariosinf/{salario}")
	public ResponseEntity<List<JefeTO>> findByInferiorASalario(@PathVariable("salario") Long salario) {
		List<Jefe> jefes = jefeServices.findByInferiorASalario(salario);

		// Si no encuentra el salario, devuelve un 404. Si existe, devuelve un 200
		if (jefes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<JefeTO> jefeTO = jefeMapper.convertirListaAJefeTO(jefes);
		return new ResponseEntity<>(jefeTO, HttpStatus.OK);
	}

	// Endpoint para buscar entre dos valores salario.
	@GetMapping(path = "/salariosbet/{salarioA}/{salarioB}")
	public ResponseEntity<List<JefeTO>> findByEntreSalarios(@PathVariable("salarioA") Long salarioA,
			@PathVariable("salarioB") Long salarioB) {
		List<Jefe> jefes = jefeServices.findByEntreSalarios(salarioA, salarioB);
		if (jefes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<JefeTO> jefeTO = jefeMapper.convertirListaAJefeTO(jefes);
		return new ResponseEntity<>(jefeTO, HttpStatus.OK);
	}

}

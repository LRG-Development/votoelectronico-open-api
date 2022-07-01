package ec.edu.insteclrg.app.api.v1;

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

import ec.edu.insteclrg.common.dto.ApiResponseDTO;
import ec.edu.insteclrg.domain.Provincia;
import ec.edu.insteclrg.dto.ProvinciaDTO;
import ec.edu.insteclrg.service.crud.ProvinciaService;

@RestController
@RequestMapping(value = "/api/v1.0/provincia")
public class ProvinciaController {

	@Autowired
	ProvinciaService service;

//guardar	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody ProvinciaDTO dto) {
		service.save(dto);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, null), HttpStatus.CREATED);

	}

	// actualizar
	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ProvinciaDTO dto) {
		service.update(id,dto);
		return new ResponseEntity<>(new ApiResponseDTO<>(true,"Actualizado"), HttpStatus.CREATED);
	}

//listar
	@GetMapping
	public ResponseEntity<Object> findAll() {
		List<ProvinciaDTO> list = service.findAll(new ProvinciaDTO());
		if (!list.isEmpty()) {
			ApiResponseDTO<List<ProvinciaDTO>> response = new ApiResponseDTO<>(true, list);
			return (new ResponseEntity<Object>(response, HttpStatus.OK));
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}

//buscar
	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> find(@PathVariable Long id) {
		ProvinciaDTO dto = new ProvinciaDTO();
		dto.setId(id);
		Optional<Provincia> test = service.find(dto);
		if (test.isPresent()) {
			ApiResponseDTO<Provincia> response = new ApiResponseDTO<>(true, test.get());
			return (new ResponseEntity<Object>(response, HttpStatus.OK));
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}
// eliminar
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		
			ProvinciaDTO dto = new ProvinciaDTO();
			dto.setId(id);
			service.delete(dto);
			return new ResponseEntity<>(new ApiResponseDTO<>(true, "eliminado"), HttpStatus.CREATED);
		}

}
package br.com.vestcasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vestcasa.data.vo.v1.PersonVO;
import br.com.vestcasa.exceptions.ResourceNotFoundException;
import br.com.vestcasa.repositories.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;

	public List<PersonVO> findAll() {
		return repository.findAll();
	}

	public PersonVO findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No recrds found for this ID"));
	}

	public PersonVO create(PersonVO person) {
		return repository.save(person);
	}

	public PersonVO update(PersonVO person) {
		PersonVO entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		entity.setFirstName(PersonVO.getFirstName());
		entity.setLastName(PersonVO.getLastName());
		entity.setAddress(PersonVO.getAddress());
		entity.setGender(PersonVO.getGender());

		return repository.save(entity);
	}

	public void delete(Long id) {
		PersonVO entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		repository.delete(entity);
	}
}

package br.com.vestcasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vestcasa.data.vo.v1.PersonVO;
import br.com.vestcasa.exceptions.ResourceNotFoundException;
import br.com.vestcasa.mapper.DozerMapper;
import br.com.vestcasa.models.Person;
import br.com.vestcasa.repositories.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;

	public List<PersonVO> findAll() {
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No recrds found for this ID"));		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		Person entity = DozerMapper.parseObject(person, Person.class);				
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);				
	}

	public PersonVO update(PersonVO person) {
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);				
	}

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		repository.delete(entity);
	}
}

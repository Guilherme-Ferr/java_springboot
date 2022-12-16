package br.com.vestcasa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vestcasa.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
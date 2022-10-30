package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Category;
import com.example.demo.entities.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long>{

	Page<Attribute> findAll(Pageable pageable);
	
	@Query("select a from Attribute as a where code = :acode")
	Attribute findByAttributeName(@Param("acode") String attributeName);
}

package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.Attribute;
import com.example.demo.repository.AttributeRepository;

@Service
public class AttributeService {

	@Autowired
	private AttributeRepository attributeRepository;
	
	public List<String> getAllAttributes(){
		List<Attribute> attributes = attributeRepository.findAll();
		List<String> attributeList = new ArrayList<>();
		for (Attribute attribute : attributes) {
			attributeList.add(attribute.getName());			
		}
		return attributeList;
	}
	
	public List<Attribute> getAttributes() {
		return this.attributeRepository.findAll();
	}

	public Attribute saveAtribute(Attribute attribute) {
		Attribute savedAttribute = attributeRepository.save(attribute);
		return savedAttribute;
	}

	public Attribute updateAttribute(Attribute attribute, Long id) {
		Attribute currentAttribute = findAttributeById(id);
		currentAttribute.setName(attribute.getName());
		currentAttribute.setValue(attribute.getValue());
		currentAttribute.setDisplayName(attribute.getDisplayName());
		
		return attributeRepository.save(currentAttribute);
	}

	public void deleteAttribute(Long id) {
		attributeRepository.deleteById(id);
	}

	public Attribute findAttributeById(Long id) {
		Optional<Attribute> optionalAttribute = attributeRepository.findById(id);
		return optionalAttribute.get();
	}

	public Attribute getByAttributeName(String attributeName) {
		return attributeRepository.findByAttributeName(attributeName);

	}

	public Page<Attribute> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Attribute> pageAttribute = attributeRepository.findAll(pageable);
		return pageAttribute;
	}
}

package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Category;
import com.example.demo.entities.Attribute;
import com.example.demo.service.AttributeService;

@Controller
@RequestMapping(value = "/admin")
public class AttributeController {

	@Autowired
	private AttributeService attributeService;
	
	@GetMapping("/attributes")
	public String showAttributeList(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "attribute");
		int pageSize = 10;
		Page<Attribute> pageAttribute = attributeService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Attribute> attributes = pageAttribute.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageAttribute.getTotalPages());
		model.addAttribute("totalItems", pageAttribute.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("attributes", attributes);
		return "attributes";
	}

	@GetMapping("/addAttribute")
	public String showSignUpForm(Model model) {
		model.addAttribute("attribute", new Attribute());
		return "add-attribute";
	}

	@GetMapping("/editAttribute")
	public String showEditForm(@RequestParam(name = "attributeId") Long id, Model model) {
		Attribute attribute = attributeService.findAttributeById(id);
		model.addAttribute("attribute", attribute);
		return "edit-attribute";
	}
	
	@GetMapping("/deleteAttribute")
	public String deleteAttribute(@RequestParam(name = "attributeId") Long id) {
		attributeService.deleteAttribute(id);
		return "redirect:/admin/attributes";
	}

	@PostMapping("/addAttribute")
	public String addAttribute(@Valid Attribute attribute, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-attribute";
		}		
		attributeService.saveAtribute(attribute);

		return "redirect:/admin/attributes";
	}

	@PostMapping("/updateAttribute")
	public String updateAttribute(@RequestParam(name = "attributeId") Long id, @Valid Attribute attribute, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			attribute.setId(id);
			return "edit-attribute";
		}	
		attributeService.updateAttribute(attribute, id);
		return "redirect:/admin/attributes";
	}
}

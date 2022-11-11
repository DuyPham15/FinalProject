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

import com.example.demo.entities.Permission;
import com.example.demo.service.PermissionService;

@Controller
@RequestMapping(value = "/admin")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@GetMapping("/permissions")
	public String showPermissionList(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "permission");
		int pageSize = 8;
		Page<Permission> pagePermission = permissionService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Permission> permissions = pagePermission.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pagePermission.getTotalPages());
		model.addAttribute("totalItems", pagePermission.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("permissions", permissions);
		return "permissions";
	}

	@GetMapping("/addPermission")
	public String showSignUpForm(Model model) {
		model.addAttribute("permission", new Permission());
		return "add-permission";
	}

	@GetMapping("/editPermission")
	public String showEditForm(@RequestParam(name = "permissionId") Long id, Model model) {
		Permission permission = permissionService.findPermissionById(id);
		model.addAttribute("permission", permission);
		return "edit-permission";
	}
	
	@GetMapping("/deletePermission")
	public String deletePermission(@RequestParam(name = "permissionId") Long id) {
		permissionService.deletePermission(id);
		return "redirect:/admin/permissions";
	}

	@PostMapping("/addPermission")
	public String addPermission(@Valid Permission permission, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-permission";
		}		
		permissionService.savePermission(permission);

		return "redirect:/admin/permissions";
	}

	@PostMapping("/updatePermission")
	public String updatePermission(@RequestParam(name = "permissionId") Long id, @Valid Permission permission, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			permission.setId(id);
			return "edit-permission";
		}	
		permissionService.updatePermission(permission, id);
		return "redirect:/admin/permissions";
	}
}

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

import com.example.demo.entities.Permission;
import com.example.demo.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public List<String> getAllPermissions(){
		List<Permission> permissions = permissionRepository.findAll();
		List<String> permissionList = new ArrayList<>();
		for (Permission permission : permissions) {
			permissionList.add(permission.getRole());			
		}
		return permissionList;
	}
	
	public List<Permission> getPermissions() {
		return this.permissionRepository.findAll();
	}

	public Permission savePermission(Permission permission) {
		Permission savedPermission = permissionRepository.save(permission);
		return savedPermission;
	}

	public Permission updatePermission(Permission permission, Long id) {
		Permission currentPermission = findPermissionById(id);
		currentPermission.setRole(permission.getRole());
		
		
		return permissionRepository.save(currentPermission);
	}

	public void deletePermission(Long id) {
		permissionRepository.deleteById(id);
	}

	public Permission findPermissionById(Long id) {
		Optional<Permission> optionalPermission = permissionRepository.findById(id);
		return optionalPermission.get();
	}

	public Page<Permission> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Permission> pagePermission = permissionRepository.findAll(pageable);
		return pagePermission;
	}
	
}

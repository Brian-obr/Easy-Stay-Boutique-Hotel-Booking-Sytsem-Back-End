package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Role;
import za.ac.cput.repository.RoleRepository;
import za.ac.cput.service.IRoleService;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Role registerRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}

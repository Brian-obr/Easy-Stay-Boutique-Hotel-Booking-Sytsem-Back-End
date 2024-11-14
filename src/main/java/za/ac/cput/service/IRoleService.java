package za.ac.cput.service;

import za.ac.cput.domain.Role;

import java.util.List;

public interface IRoleService {
    Role registerRole(Role role);
    Role updateRole(Role role);
    void deleteRole(Role role);
    List<Role> getAllRoles();
}

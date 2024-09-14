package pl.ShauryaSuri.taskmanager.service;

import org.springframework.beans.PropertyValues;

import pl.ShauryaSuri.taskmanager.model.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    List<Role> findAll();
}

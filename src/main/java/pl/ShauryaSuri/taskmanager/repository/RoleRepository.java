package pl.ShauryaSuri.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.ShauryaSuri.taskmanager.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String user);
}

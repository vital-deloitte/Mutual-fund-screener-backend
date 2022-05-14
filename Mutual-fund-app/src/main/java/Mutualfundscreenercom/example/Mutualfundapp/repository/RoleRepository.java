package Mutualfundscreenercom.example.Mutualfundapp.repository;

import Mutualfundscreenercom.example.Mutualfundapp.entities.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Roles, Integer> {
    Roles findByName(String name);
}

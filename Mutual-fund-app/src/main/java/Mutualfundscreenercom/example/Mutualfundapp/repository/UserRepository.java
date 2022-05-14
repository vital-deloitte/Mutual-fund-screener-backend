package Mutualfundscreenercom.example.Mutualfundapp.repository;

import Mutualfundscreenercom.example.Mutualfundapp.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Long> {

    Users getById(Long id);
    Users findByUsername(String username);
    Users findByEmail(String email);
    Boolean existsByUsername(String username);
}

package Mutualfundscreenercom.example.Mutualfundapp.service;

import Mutualfundscreenercom.example.Mutualfundapp.entities.MutualFund;
import Mutualfundscreenercom.example.Mutualfundapp.entities.Roles;
import Mutualfundscreenercom.example.Mutualfundapp.entities.Users;
import Mutualfundscreenercom.example.Mutualfundapp.extrabody.ReturnUserDetails;
import Mutualfundscreenercom.example.Mutualfundapp.extrabody.UnSuccessfull;
import Mutualfundscreenercom.example.Mutualfundapp.extrabody.UserExtraBody;
import Mutualfundscreenercom.example.Mutualfundapp.repository.MutualFundRepository;
import Mutualfundscreenercom.example.Mutualfundapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private BCryptPasswordEncoder bcryptEncoder;
    @Autowired
    private RoleService roleService;

    @Autowired
    private MutualFundRepository mutualFundRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if (!user.getIs_active()) {
            throw new UsernameNotFoundException("User is not activated!");
        }
        return new User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Users user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    private List<Users> addToListIfUserActive(List<Users> list) {
        List<Users> result = new ArrayList<>();
        for (Users user : list) {
            if (user.getIs_active()) {
                result.add(user);
            }
        }
        return result;
    }

    public List<Users> findAll() {
        List<Users> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return addToListIfUserActive(list);
    }

    public ResponseEntity<?> findOne(String userEmail) {
        Users user = userRepository.findByEmail(userEmail);
        if (!user.getIs_active()) {
            return ResponseEntity.status(404).body("user is deleted!");
        }
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<?> saveUserService(UserExtraBody user) {
        Users nUser = user.getUserFromExtraBody();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        Roles role = roleService.findByName("USER");
        Set<Roles> roleSet = new HashSet<>();
        roleSet.add(role);

        if (nUser.getEmail().split("@")[1].equals("admin.com")) {
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }

        nUser.setRoles(roleSet);
        return ResponseEntity.ok().body(userRepository.save(nUser));
    }

    public ResponseEntity<?> verifyEmailService(UserExtraBody user) {
        if (user == null) {
            return ResponseEntity.status(401).body("bad request!");
        }
        Users userFromDB = userRepository.findByEmail(user.getEmail());
        if (Objects.equals(userFromDB.getPassword(), bcryptEncoder.encode(user.getPassword()))) {
            userFromDB.setIs_active(true);
            userRepository.save(userFromDB);
            return ResponseEntity.ok().body("Account Activated!");
        } else {
            return ResponseEntity.status(401).body(new UnSuccessfull("Bad credentials!"));
        }
    }

    public ResponseEntity<?> deleteAccountService(Long userId) {
        Optional<Users> users = userRepository.findById(userId);
        if (users.isEmpty()) {
            return ResponseEntity.status(404).body("User does not exist!");
        }
        Users fetched = users.get();
        fetched.setIs_active(false);
        userRepository.save(fetched);
        return ResponseEntity.ok().body("Deleted the user!");
    }

    public ResponseEntity<?> addMutualFundToWatchList(Long userId, Long mutualFundId, ReturnUserDetails returnUserDetails) {
        if(!Objects.equals(returnUserDetails.getId(), userId)) {
            return ResponseEntity.status(401).body("you cannot add other users watchlist!");
        }

        if (!userRepository.existsById(userId) || !mutualFundRepository.existsById(mutualFundId)) {
            return ResponseEntity.status(404).body("cannot add non existing mutual or user");
        }

        Users users = userRepository.getById(userId);
        MutualFund mutualFund = mutualFundRepository.getById(mutualFundId);

        Set<MutualFund> mutualFunds = new HashSet<>(users.getMutualFundWatchList());
        mutualFunds.add(mutualFund);
        users.getMutualFundWatchList().clear();
        users.setMutualFundWatchList(mutualFunds);
        userRepository.save(users);
        return ResponseEntity.ok().body(userRepository.getById(userId));
    }

    public ResponseEntity<?> removeMutualFunFromUser(Long mutualFundId, Long userId, ReturnUserDetails returnUserDetails) {
        if(!Objects.equals(returnUserDetails.getId(), userId)) {
            return ResponseEntity.status(401).body("you cannot remove other users watchlist!");
        }

        Users users = userRepository.getById(userId);
        MutualFund mutualFund = mutualFundRepository.getById(mutualFundId);
        if (!userRepository.existsById(userId) || !mutualFundRepository.existsById(mutualFundId)) {
            return ResponseEntity.status(404).body("cannot add non existing mutual fund or user!");
        }
        Set<MutualFund> mutualFunds = new HashSet<>(users.getMutualFundWatchList());
        users.getMutualFundWatchList().clear();
        mutualFunds.remove(mutualFund);

        users.setMutualFundWatchList(mutualFunds);
        userRepository.save(users);
        return ResponseEntity.ok().body(userRepository.getById(userId));
    }
}

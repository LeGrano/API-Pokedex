package fr.iut.sj.pkdxapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import fr.iut.sj.pkdxapi.repositories.UserRepository;
import fr.iut.sj.pkdxapi.errors.UserAlreadyExistException;
import fr.iut.sj.pkdxapi.models.UserDTO;
import fr.iut.sj.pkdxapi.models.UserData;

@Service
public class UserDataService {

    private final UserRepository userRepository;

    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    

    public void createUser(UserDTO userDTO) {
        String username = userDTO.getUsername();
        boolean userExists = findByUsername(username);
        if (userExists) {
            throw new UserAlreadyExistException("User already exists");
        }
        String password = userDTO.getPassword();
        boolean role = userDTO.getAdmin();
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(password);
        
        UserData user = new UserData(username, encryptedPassword, role);
        userRepository.insert(user);
    }

    public boolean findByUsername(String username) {
        List<UserData> checkUsers = userRepository.findByUsername(username);

        if (checkUsers.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}

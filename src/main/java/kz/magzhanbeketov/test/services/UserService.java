package kz.magzhanbeketov.test.services;

import kz.magzhanbeketov.test.dto.UserDTO;
import kz.magzhanbeketov.test.exception.BadRequestException;
import kz.magzhanbeketov.test.exception.NotFoundException;
import kz.magzhanbeketov.test.models.User;
import kz.magzhanbeketov.test.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String username) throws NotFoundException, BadRequestException {

        if (username == null){
            throw new BadRequestException("username is null");
        }
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new NotFoundException("This username not exist");
        }
    }

    public User addUser(UserDTO user) throws BadRequestException {

        if (user.getUsername() == null || user.getUsername().isBlank()){
            throw new BadRequestException("username is null");
        }

        if(user.getPassword() == null || user.getPassword().isBlank()){
            throw new BadRequestException("password is null");
        }

        if (user.getFullName() == null || user.getFullName().isBlank()){
            throw new BadRequestException("full name is null");
        }

        if(userRepository.existsByUsername(user.getUsername())){
            throw  new BadRequestException("This username is exist");
        }
        if (!user.getPassword().equals(user.getRePassword())){
            throw new BadRequestException("Passwords arent equal!!!");
        }
        User newUser = User.builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .password(user.getPassword())
                .createdAt(Calendar.getInstance())
                .build();
        return userRepository.save(newUser);
    }

    public User deleteUser(String username) throws NotFoundException, BadRequestException {

        User user = getUser(username);
        userRepository.delete(user);
        return user;
    }

    public User updateUser(User user) throws NotFoundException, BadRequestException {

        User updateUser = getUser(user.getUsername());

        if (user.getPassword() != null){
            updateUser.setPassword(user.getPassword());
        }

        if (user.getFullName() != null){
            updateUser.setFullName(user.getFullName());
        }

        updateUser.setUpdatedAt(Calendar.getInstance());

        return userRepository.save(updateUser);
    }

}

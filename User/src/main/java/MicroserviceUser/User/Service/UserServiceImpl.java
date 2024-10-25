package MicroserviceUser.User.Service;

import MicroserviceUser.User.Model.User;
import MicroserviceUser.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        User user = userRepository.findByUserNameAndPassword(name, password);
        if(user == null){
            System.out.println("Ogilitigt användarnamn eller lösenord");
        }
        return user;
    }
}
package MicroserviceUser.User.Service;

import MicroserviceUser.User.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password);
}
package MicroserviceUser.User.Repository;

import MicroserviceUser.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUserNameAndPassword(String userName, String password);

    User findByUserName(String userName);
}
package MicroserviceUser.User.Controller;

import MicroserviceUser.User.Config.JwtUtil;
import MicroserviceUser.User.Model.User;
import MicroserviceUser.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil){
        this.userService=userService;
        this.jwtUtil=jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User authentication = userService.getUserByNameAndPassword(user.getUserName(), user.getPassword());
        String token = jwtUtil.generateToken(authentication);
        return ResponseEntity.ok().body("Användarnamn: " + user.getUserName() + " Token:" + token);
    }
}

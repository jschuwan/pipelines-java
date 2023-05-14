package info.schuwan.projectone.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping(path = "api/v1/user")
//@RequestMapping(path = "http://localhost:8080/v1/api/user")
public class UserController {

    private final UserService userService;                    //-------dependency injection- UserService

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //-----------------------Create your CRUD here using the UserService dependency---------------------------//
    //-----------------------for each method name, resolve by clicking implement method in UserService---------//

    @GetMapping(path = "/admin/list-all-users")                                   //----------------------------A restful API End point to [GET] retrieve all users
    public ResponseEntity<List<UserEntity>> getManyUsers(){
        return ResponseEntity.ok().body(userService.getManyUsers());
    }

    @GetMapping(path = "/admin/find-user-"+"{Id}")                                  //----------------------------A restful API End point to [GET] retrieve a users
    public ResponseEntity<Optional<UserEntity>> getOneUser(@PathVariable("Id") Integer employeeId){
        return ResponseEntity.ok().body(userService.getOneUser(employeeId));
    }

    @PostMapping(path = "/admin/add-new-user")                                  //----------------------------A restful API End point to [POST] add user
    public void addUser(@RequestBody UserEntity user) {                  //a post request coming from a client is mapped to the UserEntity
//        URI myuri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/admin/add-new-uesr").toUriString());
//        return ResponseEntity.created(myuri).body(userService.addUser(user));
        userService.addUser(user);
        ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/admin/delete-user-"+"{Id}")                       //----------------------------A restful API End point to [DELETE] remove user
    public void deleteEmployee(@PathVariable("Id") Integer userId){          //userId from a client is mapped to the id property/variable
        userService.deleteUser(userId);
        ResponseEntity.ok().build();
    }

    @PutMapping(path = "/admin/update-user-"+"{Id}")                          //----------------------------A restful API End point to [POST] update user
    public void updateUser(@RequestBody UserEntity user, @PathVariable("Id") int userId){ //a post request coming from a client is mapped to the UserEntity
        user.setId(userId);
        userService.updateUser(user);
        ResponseEntity.ok().build();
    }
}

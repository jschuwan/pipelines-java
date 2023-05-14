package info.schuwan.projectone.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//-----------------------This is the Service layer --------------------------------------------------------//
@Service
public class UserService {

     private final UserRepository userRepository;                    //  - inject this dependency after adding the UserRepository interface

     @Autowired
     public UserService(UserRepository userRepository) {             // Constructr
          this.userRepository = userRepository;
     }

     public List<UserEntity> getManyUsers() {
          return userRepository.findAll();
     }

     public Optional<UserEntity> getOneUser(Integer userId) {
          return userRepository.findById(userId);
     }

     public void addUser(UserEntity myuser) {

//          Optional<UserEntity> lookForUser= userRepository.findByUsername(myuser.getUsername());
//          if (lookForUser.isPresent()) {
//               throw new IllegalStateException("Cannot add user  " + myuser + "\n|||||||| a user account already exists |||||||||");
//          } else {
               System.out.println(myuser);                                          // testing---  display the post requests
               userRepository.save(myuser);                                      //if user emailaccount doesn not exist save this Employee as a new Employee
          }
//     }

     public void updateUser(UserEntity myuser) {

          Optional<UserEntity> lookForUser= userRepository.findByUsername(myuser.getUsername());
          if (!lookForUser.isPresent()) {
               throw new IllegalStateException("Cannot modify user information for  " + myuser + "\n|||||||| a user account does not exists |||||||||");
          } else {
               System.out.println(myuser);                                          // testing---  display the post requests
               userRepository.save(myuser);                                      //if user emailaccount doesn not exist save this Employee as a new Employee
          }
     }

     public void deleteUser(Integer userId) {
          boolean checkUserExists = userRepository.existsById(userId);
          if (!checkUserExists) {
               throw new IllegalStateException("|||||||| a user with an id: " + userId + "  does not exist in our database ||||||||");
          } else {
               userRepository.deleteById(userId);
          }

     }


}
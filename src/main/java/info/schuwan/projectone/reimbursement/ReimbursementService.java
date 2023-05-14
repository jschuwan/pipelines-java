package info.schuwan.projectone.reimbursement;

import info.schuwan.projectone.employee.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//-----------------------This is the Service layer --------------------------------------------------------//
@Service
public class ReimbursementService {

      final ReimbursementRepository reimbursementRepository;                    //  - inject this dependency after adding the ReimbursementRepository interface
    // final UserRepository userRepository;

     @Autowired
     public ReimbursementService(ReimbursementRepository reimbursementRepository, UserRepository userRepository) {

          this.reimbursementRepository = reimbursementRepository;
         // this.userRepository = userRepository;
     }

     public List<ReimbursementEntity> getManyReimbursements() {
          return reimbursementRepository.findAll();
     }

     public Optional<ReimbursementEntity> getOneReimbursement(Integer reimbursementId) {
          return reimbursementRepository.findById(reimbursementId);
     }

     public Integer addReimbursement(ReimbursementEntity myrequest) {

//          Optional<UserEntity> lookForRequest= userRepository.findByUsername(myuser.getUsername());
//          if (lookForRequest.isPresent()) {
//               throw new IllegalStateException("Cannot add user  " + myrequest + "\n|||||||| a user account already exists |||||||||");
//          } else {
               System.out.println(myrequest);                                          // testing---  display the post requests
               reimbursementRepository.save(myrequest);                                      //if request emailaccount doesn not exist save this requst as a new request
          return myrequest.getId();
     }
//     }

     public void updateReimbursement(ReimbursementEntity myrequest) {

          Optional<ReimbursementEntity> lookForRequest= reimbursementRepository.findById(myrequest.getId());
          if (!lookForRequest.isPresent()) {
               throw new IllegalStateException("Cannot modify user information for  " + myrequest + "\n|||||||| a user account does not exists |||||||||");
          } else {
               System.out.println(myrequest);                                          // testing---  display the post requests
               reimbursementRepository.save(myrequest);                                      //if request emailaccount doesn not exist save this requst as a new request
          }
     }

     public void deleteReimbursement(Integer reimbursementId) {
          boolean checkRequestExists = reimbursementRepository.existsById(reimbursementId);
          if (!checkRequestExists) {
               throw new IllegalStateException("|||||||| a user with an id: " + reimbursementId + "  does not exist in our database ||||||||");
          } else {
               reimbursementRepository.deleteById(reimbursementId);
          }

     }


}


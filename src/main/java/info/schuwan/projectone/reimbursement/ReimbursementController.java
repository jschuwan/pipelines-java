package info.schuwan.projectone.reimbursement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class ReimbursementController {

    private BusinesslogicRequests reimbursementService;                    //-------dependency injection- ReimbursementService

    @Autowired
    public ReimbursementController(BusinesslogicRequests reimbursementService) {
        this.reimbursementService = reimbursementService;
    }
//    public ReimbursementController(ReimbursementService reimbursementService) {
//        this.reimbursementService = reimbursementService;
//    }

    //-----------------------Create your CRUD here using the ReimbursementService dependency---------------------------//
    //-----------------------for each method name, resolve by clicking implement method in ReimbursementService---------//

    //    1-R *    CRUD
    @PostMapping(path = "/user/add-my-reimbursement-request")                                  //----------------------------A restful API End point to [POST] add user
    public void addMyRequest(@RequestBody ReimbursementEntity request) {                  //a post request coming from a client is mapped to the UserEntity
        reimbursementService.addMyRequest(request);
    }
    //    1-A *    CRUD
    @PostMapping(path = "/manager/add-new-reimbursement")                                  //----------------------------A restful API End point to [POST] add user
    public void addReimbursement(@RequestBody ReimbursementEntity request) {                  //a post request coming from a client is mapped to the UserEntity
        reimbursementService.addReimbursement(request);
    }
    //    1-A *    CRUD
    @GetMapping(path = "/manager/list-all-reimbursements")                                   //----------------------------A restful API End point to [GET] retrieve all users
    public List<ReimbursementEntity> getManyReimbursements(){
        return reimbursementService.getManyReimbursements();
    }
    //    1-A *    CRUD
    @GetMapping(path = "/manager/find-reimbursement-"+"{Id}")                                  //----------------------------A restful API End point to [GET] retrieve a users
    public Optional<ReimbursementEntity> getOneReimbursement(@PathVariable("Id") Integer reimbursementId){
        return reimbursementService.getOneReimbursement(reimbursementId);
    }
    //    1-A *    CRUD
    @DeleteMapping(path = "/manager/delete-reimbursement-"+"{Id}")                       //----------------------------A restful API End point to [DELETE] remove user
    public void deleteReimbursement(@PathVariable("Id") Integer reimbursementId){          //userId from a client is mapped to the id property/variable
        reimbursementService.deleteReimbursement(reimbursementId);
    }
    //    1-A *    CRUD
    @PutMapping(path = "/manager/update-reimbursement-"+"{Id}")                          //----------------------------A restful API End point to [POST] update user
    public void updateReimbursement(@RequestBody ReimbursementEntity request, @PathVariable("Id") int reimbursementId){ //a post request coming from a client is mapped to the UserEntity
        request.setId(reimbursementId);
        reimbursementService.updateReimbursement(request);
    }

    //    1-R  & 1-API *    The system to send a REST request to the email system for a new reimbursement request
    @GetMapping(path ="/admin/stant-request-initiate-emails")
    public ResponseEntity<String> addMyRequestWithEmails(@RequestBody ReimbursementEntity request) {
        reimbursementService.addMyRequest(request);
        return ResponseEntity.ok("A request has been initiated an email notifications were sent");
        }
}







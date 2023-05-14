package info.schuwan.projectone.reimbursement;

import info.schuwan.projectone.Global;
import info.schuwan.projectone.employee.UserRepository;
import info.schuwan.projectone.employee.UserService;
import info.schuwan.projectone.EmailController;
import info.schuwan.projectone.expense.ExpenseRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

////-----------------------This is the Business Logic layer --------------------------------------------------------//
@Service
public class BusinesslogicRequests extends ReimbursementService {

    UserService userService;
    ExpenseRepository expenseRepository;
    final ReimbursementRepository reimbursementRepository;
    ReimbursementEntity reimbursementEntity;
     UserRepository userRepository;
    ReimbursementService reimbursementService;

    @Autowired
    public BusinesslogicRequests(ReimbursementRepository reimbursementRepository, UserRepository userRepository) {
        super(reimbursementRepository,userRepository);
        this.reimbursementRepository = reimbursementRepository;

    }
    // Reimbursement request logic


    //     1 *    Email API to receive a reimbursement request and update is_requested, email1, email2, request_amount, request_log

    //    1-R *    Employee to submit a new reimbursement request  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<NOT WORKING
    public Integer addMyRequest(@NotNull ReimbursementEntity myrequest) {
        myrequest.setEmployeename(Global.getWhoAmI());
        System.out.println(Global.getWhoAmI() + " :\tYou have started the following request." +
                "  you will receive an email shortly \n" + myrequest);                   // testing---  display the post requests
        Integer myNewRequestId =reimbursementRepository.save(myrequest).getId();
        Global.setExpenseIdNumbers(null);
        Global.setSubtotalRequest(0);
        Optional<ReimbursementEntity> requestToEdit = reimbursementService.getOneReimbursement(myNewRequestId);

        String a = Global.getWhoAmI();// employeename
        String b = String.valueOf(userRepository.findByUsername(Global.whoAmI).get().getManagername());// managername
        String c = String.valueOf(userRepository.findByUsername(Global.whoAmI).get().getEmailaccount());// employeeemail
        LocalDate d = LocalDate.now();// date_request
        boolean e = true;// is_requested
        double f = Global.getSubtotalRequest();// request_amount
        String g = d + "\t" +a + "\t has started a new reimbursement request";// request_log
        String h = Global.getExpenseIdNumbers();// expenses
        String k = String.valueOf(userRepository.findByUsername(b).get().getEmailaccount());
        System.out.println("-------------"+"---"+a+"---"+b+"---"+c+"---"+d+"---"+e+"---"+f+"---"+g+"---"+k);
        requestToEdit.get().setMultipleVlues1(a,b,c,d,e,f,g,h);

        initiateEmailNotificationNewRequest( myNewRequestId);
        return myNewRequestId;
    }

    //    1-API *    The system to send a REST request to the email system for a new reimbursement request
    private void initiateEmailNotificationNewRequest( Integer requestId) {
        Optional<ReimbursementEntity> requestToEmail = reimbursementService.getOneReimbursement(requestId);

        String themanager = String.valueOf(userRepository.findByUsername(Global.whoAmI).get().getManagername());// managername
        Global.whoAmI = themanager;
        String manageremail = String.valueOf(userRepository.findByUsername(Global.whoAmI).get().getEmailaccount());// managername
        LocalDate emaildate = LocalDate.now();// date_request
        String thelog = String.valueOf(requestToEmail.get().getRequest_log()) +"  |  " + LocalDate.now() + "  |  " + "email notificaions sent";
        boolean emailsent = true;// is_requested
        System.out.println("-------------");
        requestToEmail.get().setMultipleValues2(manageremail,emailsent,thelog);

        new EmailController();
        /// This stil needs a lot of work
    }



    //     2 *    Upon manager intervention,  API to send out 2 emails and update is_emailed, request_log(again)
    //      The manager will lookup the the open reaquest that was submittied to him/her a couple of days ago
    public List<ReimbursementEntity> filtergetRequestsByManager(){        // get only all of the logged in user requests

        List<ReimbursementEntity> unfilteredList= getManyReimbursements();
        List<ReimbursementEntity>filteredList = unfilteredList.stream()
                .filter(entity -> entity.getEmployeename().toString().equals( Global.getWhoAmI()))
                .collect(Collectors.toList());
        return filteredList;
    }
    //      he will either approve the expense
    public void approveRequest(Integer requestId){
        Optional<ReimbursementEntity> requestToEmail = reimbursementService.getOneReimbursement(requestId);
        LocalDate emaildate = LocalDate.now();// date_request
        String thelog = String.valueOf(requestToEmail.get().getRequest_log()) +"  |  " + LocalDate.now() + "  |  " + "email notificaions sent";
        boolean emailsent = true;// is_requested
        new EmailController();                       /// This stil needs a lot of work
    }


    //      or he will reassign the expense to another manager
    public void reassignRequest(Integer requestId){
        Optional<ReimbursementEntity> requestToEmail = reimbursementService.getOneReimbursement(requestId);
        LocalDate emaildate = LocalDate.now();// date_request
        String thelog = String.valueOf(requestToEmail.get().getRequest_log()) +"  |  " + LocalDate.now() + "  |  " + "email notificaions sent";
        boolean emailsent = true;// is_requested
        new EmailController();                      /// This stil needs a lot of work
    }






    //     3 *    Email API to update the reimbursement request with------
    //     4 *    Manager to view the list of a specific requester's approved expense by id(employee)
    //       *
    //     5 *    Manager to view the list of his to approve open requests
    //     6 *    Manager to select a specific reimbursement request and to approve it and update is_approved, date_approve, date_topay, request_log(again)
    //     7 *    Email API to send out 2 emails(again) and update request_log(again)
    //       *
    //     8 *    Manager to select a specific reimbursement request and reassign it to another manager and update managername, manageremail, is_reassigned, date_topay, request_log(again)
    //     9 *    Email API to send out 3 emails(again) and update request_log(again)


// additional leftover logic from the expense
//   3 B *    Ability for employee to edit only expenses which are not yet reported


}
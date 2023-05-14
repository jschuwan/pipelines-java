package info.schuwan.projectone.expense;

import info.schuwan.projectone.Global;
import info.schuwan.projectone.employee.UserRepository;
import info.schuwan.projectone.employee.UserService;
import info.schuwan.projectone.reimbursement.ReimbursementEntity;
import info.schuwan.projectone.reimbursement.ReimbursementRepository;
import info.schuwan.projectone.reimbursement.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;



//-----------------------This is the Business Logic layer --------------------------------------------------------//
@Service
public class BusinesslogicExpenses extends ExpenseService {

    UserService userService;
    final UserRepository userRepository ;
    ExpenseRepository expenseRepository;
    ReimbursementRepository reimbursementRepository;
    final ReimbursementService reimbursementService = null;


    public BusinesslogicExpenses(ExpenseRepository expenseRepository, UserRepository userRepository, ReimbursementRepository reimbursementRepository) {
        super(expenseRepository);
        this.userRepository = userRepository;
        this.reimbursementRepository = reimbursementRepository;
    }

    @Autowired
    public void ExpenseService(ExpenseRepository expenseRepository) {              // Constructor
        this.expenseRepository = expenseRepository;
    }

//    @Query(value = "SELECT s FROM expense_detail s WHERE s.username = ?1", nativeQuery = true)
    public List<ExpenseEntity> filtergetManyExpenses(){        // get only all of the logged in user expenses
    return expenseRepository.findAll().stream().filter(x -> Global.getWhoAmI().equals(x.getUsername())).collect(Collectors.toList());
     }


    public List<ReimbursementEntity> filtergetManyRequests(){        // get only all of the logged in user requests
        System.out.println(Global.whoAmI);
        List<ReimbursementEntity> unfilteredList= reimbursementService.getManyReimbursements();
        List<ReimbursementEntity>filteredList = unfilteredList.stream()
                .filter(entity -> entity.getEmployeename().toString().equals( Global.getWhoAmI()))
                .collect(Collectors.toList());
        return filteredList;
    }

     // Expense logic

     //    1-U *    Employee to add an expense
    public void addMyExpense(ExpenseEntity myexpense){
        myexpense.setUsername(Global.getWhoAmI());
        System.out.println(Global.getWhoAmI() + "You have added the following expense."+
                "  If you have entered all expenses for the period, the next step to submit"+
                " a request for reimbursiment\n" + myexpense);                   // testing---  display the post requests
        expenseRepository.save(myexpense);
    }

     //    2 *    Ability for employee to edit only expenses which are not yet reported
     @Query(value = "SELECT s FROM expense_detail s WHERE s.username = ?1", nativeQuery = true)
     public List<ExpenseEntity> filtergetManyExpensesNotReported(){
         List<ExpenseEntity> unfilteredList= filtergetManyExpenses();
         unfilteredList.forEach(System.out::println);

         List<ExpenseEntity>filteredList = unfilteredList.stream()
                .filter(entity -> entity.isIs_reported() == false)
                 .collect(Collectors.toList());

         return filteredList;
     }

     //    3 A*    Employee to include the expense and update the is_reported field, and temporary total and nums for request

    public float includeExpensesInReport(){
        //---------------------------filtering for a list of expeses for the user

         List<ExpenseEntity> myexpenselist = expenseRepository.findAll()
                 .stream()
                 .filter(x -> Global.getWhoAmI().equals(x.getUsername()))
                 .collect(Collectors.toList());
         for(int ii =0; ii < myexpenselist.size();ii++){
             System.out.println("--------------------");
             System.out.println(myexpenselist.get(ii).getUsername());
             System.out.println("--------------------");
         }

         //--------------------------updte the Global variables with id numbers and a subtotal for the Request to be
         myexpenselist.stream().filter(entity -> entity.isIs_reported()==false)
                 .forEach((n) -> Global.setExpenseIdNumbers(Global.getExpenseIdNumbers() + ", " + n.getId()));
         myexpenselist.stream().filter(entity -> entity.isIs_reported()==false)
                 .forEach((n) -> Global.setSubtotalRequest(Global.getSubtotalRequest() + n.getExpense_amount()));

         //--------------------------save a reimbursement request with some updated values
        //------------to reflect the total amount, the expense id(s) and the names and emails of
        //-------------the employee and the manager

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

         Integer myRequestId =reimbursementService.addReimbursement(new ReimbursementEntity(a,b,c,d,e,f,g,h));
         System.out.println("-----------"+ myRequestId+"-----------");
         ReimbursementEntity myRequest = new ReimbursementEntity(a,b,c,d,e,f,g,h);

//         Integer myRequestId =reimbursementService.addReimbursement(myRequest);
//        myRequest.setMultipleVlues(a,b,c,d,e,f,g,h);


         for(int i = 0; i < myexpenselist.size(); i++){
             myexpenselist.get(i).setIs_reported(true);
             myexpenselist.get(i).setReimbursement_request_id(myRequest.getId());
             updateExpense(myexpenselist.get(i));

         }

         myexpenselist.stream().filter(entity -> entity.isIs_reported()==false)
                 .forEach((n) -> n.setIs_reported(true));
         System.out.println(Global.getExpenseIdNumbers());
         return Global.getSubtotalRequest();
    //     3 B*

     }

    //    4 *    Employee to view the list of expenses that are not submitted
    public List<ExpenseEntity> expensesReportedNotSubmitted(){
        List<ExpenseEntity> unfilteredList= filtergetManyExpenses();
        unfilteredList.forEach(System.out::println);

        List<ExpenseEntity>filteredList = unfilteredList.stream()
                .filter(entity -> entity.isIs_reported() == false)
                .collect(Collectors.toList());
        System.out.println(filteredList);
        return filteredList;
    }

     //    5 *    Employee to view and submit the open reimbursement request
    public ReimbursementRepository  SubmitReimbursementRequest(ReimbursementEntity myrequest) {
      reimbursementRepository.save(myrequest);
        System.out.println(reimbursementRepository);
        return reimbursementRepository;
    }

     //    6 *    Employee to view a list of all his submitted expenses
     public List<ExpenseEntity> expensesReportedSubmitted(){
         List<ExpenseEntity> unfilteredList= filtergetManyExpenses();
         unfilteredList.forEach(System.out::println);

         List<ExpenseEntity>filteredList = unfilteredList.stream()
                 .filter(entity -> entity.isIs_reported() == true && entity.getReimbursement_request_id() != 0)
                 .collect(Collectors.toList());
         System.out.println(filteredList);
         return filteredList;
     }

     //    7 A *    Employee to view the list of all his reimbursement requests submitted
     public List<ReimbursementEntity>  listAllMyReimbursementRequests(ReimbursementEntity myrequest) {
         List<ReimbursementEntity> filteredList = filtergetManyRequests().stream()
                 .filter(entity -> entity.getEmployeename() == Global.getWhoAmI())
                 .collect(Collectors.toList());
         System.out.println(filteredList);
         return filteredList;
     }

     //     8 *    Ability for employee to [view] all his requests is allowed*
    //--------- Skipping this for now
     //     9 *   initiate the request pending with emails to both employee and manager

     //     10 *   update the request log many many times
     //      ////   functions for sending API request to the Email application
     //      ////   functions for the email controller to send an email once received a request
     //      11 *   update request date and isEmailed
     //      12 *   update Reimbursement report in expense entity

}
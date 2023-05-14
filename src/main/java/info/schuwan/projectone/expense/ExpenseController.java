package info.schuwan.projectone.expense;

import info.schuwan.projectone.Global;
import info.schuwan.projectone.employee.UserService;
import info.schuwan.projectone.reimbursement.ReimbursementEntity;
import info.schuwan.projectone.reimbursement.ReimbursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping(path = "api/v1/expense")
//@RequestMapping(path = "http://localhost:8080/v1/api/expense")
public class ExpenseController {

    private BusinesslogicExpenses expenseService;                    //-------dependency injection- UserService
//    ReimbursementRepository reimbursementRepository;

    @Autowired
    public ExpenseController(BusinesslogicExpenses expenseService) {
        this.expenseService = expenseService;}

    //-----------------------Create your CRUD here using the ExpenseService dependency---------------------------//
    //-----------------------for each method name, resolve by clicking implement method in ExpenseService---------//

    //    1-U *    Employee to add an expense
    @PostMapping(path = "/user/add-new-expense")
    public void addMyExpense(@RequestBody ExpenseEntity expense) {                  //a post request coming from a client is mapped to the UserEntity
        expenseService.addMyExpense(expense);
    }
    //    1-A *    CRUD
    @PostMapping(path = "/user/add-new-expense-to-anyone")
    public void addNewExpense(@RequestBody ExpenseEntity expense) {                  //a post request coming from a client is mapped to the UserEntity
        expenseService.addExpense(expense);
    }
    //    1-A *    CRUD
    @GetMapping(path = "/user/list-all-expenses")
    public ResponseEntity<List<ExpenseEntity>> getManyExpenses(){
        return ResponseEntity.ok().body(expenseService.getManyExpenses());
    }
    //    1-A *    CRUD
    @GetMapping(path = "/user/list-all-my-expenses")
    public ResponseEntity<List<ExpenseEntity>> filtergetManyExpenses(){
        return ResponseEntity.ok().body(expenseService.filtergetManyExpenses());
    }
    //    1-A *    CRUD
    @GetMapping(path = "/user/find-expense-"+"{Id}")
    public Optional<ExpenseEntity> getOneExpense(@PathVariable("Id") Integer expenseId){
        return expenseService.getOneExpense(expenseId);
    }
    //    1-A *    CRUD
    @DeleteMapping(path = "/user/delete-expense-"+"{Id}")                       //----------------------------A restful API End point to [DELETE] remove user
    public ResponseEntity<String> deleteExpense(@PathVariable("Id") Integer expenseId){          //userId from a client is mapped to the id property/variable
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok("you have successfully deleted the expense with an id of \t"+expenseId);
    }
    //    1-A *    CRUD
    @PutMapping(path = "/user/update-expense-"+"{Id}")
    public ResponseEntity<String>  updateExpense(@RequestBody ExpenseEntity expense, @PathVariable("Id") Integer expenseId){ //a post request coming from a client is mapped to the UserEntity
        expense.setId(expenseId);
        expenseService.updateExpense(expense);
        return ResponseEntity.ok("you have successfully updated the expense with an id of \t"+expenseId);
    }



    //    2 *    Ability for employee to edit only expenses which are not yet reported
    @GetMapping(path = "/user/show-not-requested-expenses")
    public List<ExpenseEntity> filtergetManyExpensesNotReported(){
        return expenseService.filtergetManyExpensesNotReported();
    }
    //    3 A*    Employee to include the expense or reimbursement request
    @GetMapping(path = "/user/report-current-expenses-on-new-request")
    public ResponseEntity<Float> makeReportedExpenseById() {
        expenseService.includeExpensesInReport();
        return ResponseEntity.ok(Global.getSubtotalRequest());
    }


 /*   //    3 B*      Employee to include the expenses on the request
        @GetMapping(path = "/user/show-my-outstanding-expenses")
                public void some(){

        }*/


    //    4 *    Employee to view the list of expenses that are not submitted
    @GetMapping(path = "/user/list-ready-to-submit-expenses")
    public List<ExpenseEntity> expensesReportedNotSubmitted(){
        return expenseService.expensesReportedNotSubmitted();
    }

    //    5 *    Employee to view and submit the open reimbursement request
    @GetMapping(path = "/user/finalize-my-request")
    public ReimbursementRepository submitRequest(ReimbursementEntity myrequest){
        return expenseService.SubmitReimbursementRequest( myrequest);
    }

    //    6 *    Employee to view a list of all his submitted expenses
    @GetMapping(path = "/user/list-already-submitted-expenses")
    public List<ExpenseEntity> expensesReportedSubmitted(){
        return expenseService.expensesReportedSubmitted();
    }

    //    7 A *    Employee to view the list of all his reimbursement requests submitted
    @GetMapping(path = "/user/list-already-submitted-requests")
    public List<ReimbursementEntity>  listAllReimbursementRequests(ReimbursementEntity myrequest){
        return expenseService.listAllMyReimbursementRequests( myrequest);
    }



}

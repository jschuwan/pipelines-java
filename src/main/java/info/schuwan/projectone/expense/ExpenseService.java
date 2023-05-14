package info.schuwan.projectone.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//-----------------------This is the Service layer --------------------------------------------------------//
@Service
public class ExpenseService {

     private final ExpenseRepository expenseRepository;                         //  - inject this dependency after adding the UserRepository interface

     @Autowired
     public ExpenseService( ExpenseRepository expenseRepository) {              // Constructor
          this.expenseRepository = expenseRepository;
     }

     public List<ExpenseEntity> getManyExpenses() {
          return expenseRepository.findAll();
     }

     public Optional<ExpenseEntity> getOneExpense(Integer expenseId) {
          return expenseRepository.findById(expenseId);
     }

     public void addExpense(ExpenseEntity myexpense) {
          expenseRepository.save(myexpense);
//          Optional<ExpenseEntity> lookForExpense= expenseRepository.findById(myexpense.getId());
//          if (lookForExpense.isPresent()) {
//               throw new IllegalStateException("An expense with the id-  " + myexpense + " has been found in the system "+
//                                             "Please do not include an id when adding an expense");
//          } else {
               System.out.println("You have added the following expense."+
                       "  If you have entered all expenses for the period, the next step to submit"+
                       " a request for reimbursiment\n" + myexpense);                   // testing---  display the post requests
                                   //if user emailaccount doesn not exist save this Employee as a new Employee
     }

     public void updateExpense(ExpenseEntity myexpense) {

          Optional<ExpenseEntity> lookForExpense= expenseRepository.findById(myexpense.getId());
          if (!lookForExpense.isPresent()) {
               throw new IllegalStateException("Cannot find an expense with id-  " + myexpense );
          } else {
               System.out.println(
                       "you have successfully updated this expense- " + myexpense);                                          // testing---  display the post requests
               expenseRepository.save(myexpense);                                      //if user emailaccount doesn not exist save this Employee as a new Employee
          }
     }

     public void deleteExpense(Integer expenseId) {
          boolean checkExpenserExists = expenseRepository.existsById(expenseId);
          if (!checkExpenserExists) {
               throw new IllegalStateException("|||||||| an expense with an id: " + expenseId + "  does not exist in our database ||||||||");
          } else {
               expenseRepository.deleteById(expenseId);
          }

     }


}
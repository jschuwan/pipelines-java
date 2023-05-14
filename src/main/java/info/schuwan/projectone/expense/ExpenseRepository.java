package info.schuwan.projectone.expense;

import info.schuwan.projectone.Global;
import info.schuwan.projectone.expense.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//-----------------------This is the date access layer -------------------------------- ---------------------//
//-----------------------This interface must be referenced inside of the ExpenseService ---------------------//
@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {



    //custom function - find a user by email
    // which will act like the following query:
    //      SELECT * FROM student WHERE email = ?
    //Optional<Student>  findStudentByEmail_A(String emailaccount);
    //Or we can do it through annotations

//    @Query("SELECT s FROM ss_user s WHERE s.username = ?1")
//    Optional<UserEntity> findUserEntityByUsername(String name);


@Query(value = "SELECT s FROM expense_detail s WHERE s.username = ?1", nativeQuery = true)
Optional<ExpenseEntity> findByUsername(String name);

}

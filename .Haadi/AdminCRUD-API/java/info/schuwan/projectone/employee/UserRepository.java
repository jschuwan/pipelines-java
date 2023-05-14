package info.schuwan.projectone.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//-----------------------This is the date access layer -------------------------------- ---------------------//
//-----------------------This interface must be referenced inside of the EmployeeService ---------------------//
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {



    //custom function - find a user by email
    // which will act like the following query:
    //      SELECT * FROM student WHERE email = ?
    //Optional<Student>  findStudentByEmail_A(String emailaccount);
    //Or we can do it through annotations

//    @Query("SELECT s FROM ss_user s WHERE s.username = ?1")
//    Optional<UserEntity> findUserEntityByUsername(String name);

    Optional<UserEntity> findByUsername(String name);

}

package info.schuwan.projectone.expense;

import info.schuwan.projectone.Global;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expense_detail")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExpenseEntity {

    @Id
    @SequenceGenerator(        // [JPA]  it increases real database sequence by one and then uses this value as entity ID.
            name = "expense_detail_id_seq",
            sequenceName = "expense_detail_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "expense_detail_id_seq"
    )

    private int id;
    private String username;
    private int employee_id;
    private int reimbursement_request_id;
    private LocalDate expense_date;
    private String expense_description;
    private float expense_amount;
    private boolean is_reported;
    private boolean outstanding;
    private int cost_center;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = Global.whoAmI;                          //hard coded username to be whoever is logged in
    }

    public boolean isIs_reported() {
        return is_reported;
    }

    public void setIs_reported(boolean is_reported) {
        this.is_reported = is_reported;
    }

    public int getReimbursement_request_id() {
        return reimbursement_request_id;
    }

    public void setReimbursement_request_id(int reimbursement_request_id) {
        this.reimbursement_request_id = reimbursement_request_id;
    }

    public LocalDate getExpense_date() {
        return expense_date;
    }

    public void setExpense_date(LocalDate expense_date) {
        this.expense_date = expense_date;
    }

    public String getExpense_description() {
        return expense_description;
    }

    public void setExpense_description(String expense_description) {
        this.expense_description = expense_description;
    }

    public float getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(float expense_amount) {
        this.expense_amount = expense_amount;
    }

    public boolean isOutstanding() {
        return outstanding;
    }

    public void setOutstanding(boolean outstanding) {
        this.outstanding = outstanding;
    }

    public int getCost_center() {
        return cost_center;
    }

    public void setCost_center(int cost_center) {
        this.cost_center = cost_center;
    }
}




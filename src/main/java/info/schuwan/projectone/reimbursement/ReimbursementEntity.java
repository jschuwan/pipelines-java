package info.schuwan.projectone.reimbursement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reimbursement_request")                   // the table's name needs to be added only if the name is different from the class name
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReimbursementEntity {
    //This class is the backing a certain table . We will have a row and a table represented by the instance of this class
    @Id
    @SequenceGenerator(        // [JPA]  it increases real database sequence by one and then uses this value as entity ID.
            name = "reimbursement_request_id_seq",
            sequenceName = "reimbursement_request_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "reimbursement_request_id_seq"
    )
    private int id;
    private String employeename;
    private String managername;
    private String manageremail;
    private String employeeemail;
    private LocalDate date_request;
    private LocalDate date_reassign;
    private LocalDate date_topay;
    private LocalDate date_approve;
    private boolean is_requested;
    private boolean is_approved;
    private boolean is_emailed;
    private boolean is_reassigned;
    private double request_amount;
    private String request_log;
    private String expenses;

    public ReimbursementEntity(String employeename, String managername, String employeeemail, LocalDate date_request, boolean is_requested, double request_amount, String request_log, String expenses) {
        this.employeename = employeename;
        this.managername = managername;
        this.employeeemail = employeeemail;
        this.date_request = date_request;
        this.is_requested = is_requested;
        this.request_amount = request_amount;
        this.request_log = request_log;
        this.expenses = expenses;
    }
    public void setMultipleVlues1(String employeename, String managername, String employeeemail, LocalDate date_request, boolean is_requested, double request_amount, String request_log, String expenses) {
        this.employeename = employeename;
        this.managername = managername;
        this.employeeemail = employeeemail;
        this.date_request = date_request;
        this.is_requested = is_requested;
        this.request_amount = request_amount;
        this.request_log = request_log;
        this.expenses = expenses;
    }
    public void setMultipleValues2(String manageremail,  boolean is_emailed, String request_log) {
        this.employeename = employeename;
        this.is_emailed = is_emailed;
        this.request_log = request_log;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public String getManageremail() {
        return manageremail;
    }

    public void setManageremail(String manageremail) {
        this.manageremail = manageremail;
    }

    public String getEmployeeemail() {
        return employeeemail;
    }

    public void setEmployeeemail(String employeeemail) {
        this.employeeemail = employeeemail;
    }

    public LocalDate getDate_request() {
        return date_request;
    }

    public void setDate_request(LocalDate date_request) {
        this.date_request = date_request;
    }

    public LocalDate getDate_reassign() {
        return date_reassign;
    }

    public void setDate_reassign(LocalDate date_reassign) {
        this.date_reassign = date_reassign;
    }

    public LocalDate getDate_topay() {
        return date_topay;
    }

    public void setDate_topay(LocalDate date_topay) {
        this.date_topay = date_topay;
    }

    public LocalDate getDate_approve() {
        return date_approve;
    }

    public void setDate_approve(LocalDate date_approve) {
        this.date_approve = date_approve;
    }

    public boolean isIs_requested() {
        return is_requested;
    }

    public void setIs_requested(boolean is_requested) {
        this.is_requested = is_requested;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

    public boolean isIs_emailed() {
        return is_emailed;
    }

    public void setIs_emailed(boolean is_emailed) {
        this.is_emailed = is_emailed;
    }

    public boolean isIs_reassigned() {
        return is_reassigned;
    }

    public void setIs_reassigned(boolean is_reassigned) {
        this.is_reassigned = is_reassigned;
    }

    public double getRequest_amount() {
        return request_amount;
    }

    public void setRequest_amount(double request_amount) {
        this.request_amount = request_amount;
    }

    public String getRequest_log() {
        return request_log;
    }

    public void setRequest_log(String request_log) {
        this.request_log = request_log;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }
}




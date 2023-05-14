package info.schuwan.projectone;

public class EmailController {

    String mySubject1 = "A reimbursement request was submitted";
    String emailBody1 = "A reimbursement request was submitted to your Manager.  You will receive an email as soon as it is approved.  Thank you!";
    String mySubject2 = "A reimbursement request was submitted";
    String emailBody2 = "A reimbursement request was submitted for you to review and approve.  Please do so within 3 days. Thank you";

    public EmailController() {
    }

    public EmailController(String mySubject1, String emailBody1, String mySubject2, String emailBody2) {
        this.mySubject1 = mySubject1;
        this.emailBody1 = emailBody1;
        this.mySubject2 = mySubject2;
        this.emailBody2 = emailBody2;
    }
    //        new    SendMailController(mySubject1, emailBody1);
//        new    SendMailController(mySubject2, emailBody2);
}
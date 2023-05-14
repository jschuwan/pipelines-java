//

     // Expense logic
     //    1 *    Employee to add an expense
     //    2 *    Ability for employee to edit only expenses which are not yet reported
     //    3 *    Employee to report the expense and update the is_reported field
     //      *
     //    4 *    Employee to view the list of expenses that are not submitted
     //    5 *    Employee to view and submit the open reimbursement request
     //      *
     //    6 *    Employee to view a list of all his submitted expenses
     //    7 *    Employee to view the list of all his reimbursement requests requested
     //      *
     //    8 *    Ability for employee to [view] all his requests is allowed*    Ability for employee to [view] all his requests is allowed


     // Reimbursement request logic
     //     1 *    Email API to receive a reimbursement request and update is_requested, email1, email2, request_amount, request_log
     //     2 *    Email API to send out 2 emails and update is_emailed, request_log(again)
     //       *
     //     3 *    Email API to update the reimbursement request with------
     //     4 *    Manager to view the list of a specific requester's approved expense by id(employee)
     //       *
     //     5 *    Manager to view the list of his to approve open requests
     //     6 *    Manager to select a specific reimbursement request and to approve it and update is_approved, date_approve, date_topay, request_log(again)
     //     7 *    Email API to send out 2 emails(again) and update request_log(again)
     //       *
     //     8 *    Manager to select a specific reimbursement request and reassign it to another manager and update managername, manageremail, is_reassigned, date_topay, request_log(again)
     //     9 *    Email API to send out 3 emails(again) and update request_log(again)

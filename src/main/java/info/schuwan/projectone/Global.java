package info.schuwan.projectone;

import info.schuwan.projectone.employee.UserEntity;
import info.schuwan.projectone.employee.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

public class Global {
    public static String whoAmI;
    public static String whoMyManager;
    public static LocalDate whatstoday = LocalDate.now();
    public static String getWhoAmI() {
        return whoAmI;
    }

    public static String getWhoMyManager() {
        return whoMyManager;
    }

    public static void setWhoMyManager(String whoMyManager) {
        Global.whoMyManager = whoMyManager;
    }

    private static float subtotalRequest;
    private static String expenseIdNumbers;

    public static void setWhoAmI(String whoAmI) {
        Global.whoAmI = whoAmI;
    }

    public static LocalDate getWhatstoday() {
        return whatstoday;
    }

    public static float getSubtotalRequest() {
        return subtotalRequest;
    }

    public static void setSubtotalRequest(float subtotalRequest) {
        Global.subtotalRequest = subtotalRequest;
    }

    public static String getExpenseIdNumbers() {
        return expenseIdNumbers;
    }

    public static void setExpenseIdNumbers(String expenseIdNumbers) {
        Global.expenseIdNumbers = expenseIdNumbers;
    }
}

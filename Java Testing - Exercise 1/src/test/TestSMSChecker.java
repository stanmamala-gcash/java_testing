package test;

import sms.SMSChecker;
import sms.model.SMS;
import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.*;

public class TestSMSChecker {

    @Test
    public void testRegisterInput() {
        SMS sms = new SMS();

        // Ideal input
        HashMap<String, String> entryCorrect1 = new HashMap<>();
        entryCorrect1.put("Mobile Number", "9055271234");
        entryCorrect1.put("Message", "REGISTER");
        entryCorrect1.put("Short Code", "123455");

        // Input is a combination of upper and lower cases
        HashMap<String, String> entryCorrect2 = new HashMap<>();
        entryCorrect2.put("Mobile Number", "9055271234");
        entryCorrect2.put("Message", "Register");
        entryCorrect2.put("Short Code", "123455");

        // Input is in lowercase
        HashMap<String, String> entryCorrect3 = new HashMap<>();
        entryCorrect3.put("Mobile Number", "9055271234");
        entryCorrect3.put("Message", "register");
        entryCorrect3.put("Short Code", "123455");

        // Input is a combination of upper and lower cases
        HashMap<String, String> entryCorrect4 = new HashMap<>();
        entryCorrect4.put("Mobile Number", "9055271234");
        entryCorrect4.put("Message", "ReGiStEr");
        entryCorrect4.put("Short Code", "123455");

        // Input has spaces but the string still says, "register"
        HashMap<String, String> entryCorrect5 = new HashMap<>();
        entryCorrect5.put("Mobile Number", "9055271234");
        entryCorrect5.put("Message", " REG  IST ER ");
        entryCorrect5.put("Short Code", "123455");

        // Input has additional letters
        HashMap<String, String> entryWrong1 = new HashMap<>();
        entryWrong1.put("Mobile Number", "9055271234");
        entryWrong1.put("Message", "Unregister");
        entryWrong1.put("Short Code", "123455");

        // Input has other symbols and/or numbers
        HashMap<String, String> entryWrong2 = new HashMap<>();
        entryWrong2.put("Mobile Number", "9055271234");
        entryWrong2.put("Message", "Register1");
        entryWrong2.put("Short Code", "123455");

        // Input has other symbols and/or numbers
        HashMap<String, String> entryWrong3 = new HashMap<>();
        entryWrong3.put("Mobile Number", "9055271234");
        entryWrong3.put("Message", "Register    .");
        entryWrong3.put("Short Code", "123455");

        // Input is just spaces
        HashMap<String, String> entryWrong4 = new HashMap<>();
        entryWrong4.put("Mobile Number", "9055271234");
        entryWrong4.put("Message", "   ");
        entryWrong4.put("Short Code", "123455");

        // Input is NULL
        HashMap<String, String> entryWrong5 = new HashMap<>();
        entryWrong5.put("Mobile Number", "9055271234");
        entryWrong5.put("Message", "");
        entryWrong5.put("Short Code", "123455");

        assertTrue(SMSChecker.checkRegister(entryCorrect1, sms));
        assertTrue(SMSChecker.checkRegister(entryCorrect2, sms));
        assertTrue(SMSChecker.checkRegister(entryCorrect3, sms));
        assertTrue(SMSChecker.checkRegister(entryCorrect4, sms));
        assertTrue(SMSChecker.checkRegister(entryCorrect5, sms));

        assertFalse(SMSChecker.checkRegister(entryWrong1, sms));
        assertFalse(SMSChecker.checkRegister(entryWrong2, sms));
        assertFalse(SMSChecker.checkRegister(entryWrong3, sms));
        assertFalse(SMSChecker.checkRegister(entryWrong4, sms));
        assertFalse(SMSChecker.checkRegister(entryWrong5, sms));
    }

    @Test
    public void testUserInfoArguments() {
        SMS sms = new SMS();

        // Ideal Input
        HashMap<String, String> entryCorrect1 = new HashMap<>();
        entryCorrect1.put("Mobile Number", "9055271234");
        entryCorrect1.put("Message", "Marco Valmores, 1973-09-10, Marikina City");
        entryCorrect1.put("Short Code", "123455");

        // Input has excess spaces
        HashMap<String, String> entryCorrect2 = new HashMap<>();
        entryCorrect2.put("Mobile Number", "9055271234");
        entryCorrect2.put("Message", "Marco Valmores    ,   1973-09-10   ,   Marikina City");
        entryCorrect2.put("Short Code", "123455");

        // Input is not comma-separated
        HashMap<String, String> entryWrong1 = new HashMap<>();
        entryWrong1.put("Mobile Number", "9055271234");
        entryWrong1.put("Message", "Marco Valmores 1973-09-10 Marikina City");
        entryWrong1.put("Short Code", "123455");

        // Input has only one comma
        HashMap<String, String> entryWrong2 = new HashMap<>();
        entryWrong2.put("Mobile Number", "9055271234");
        entryWrong2.put("Message", "Marco Valmores, 1973-09-10 Marikina City");
        entryWrong2.put("Short Code", "123455");

        // Input has three or more commas
        // Catches full name in <Last Name, First Name Format>
        HashMap<String, String> entryWrong3 = new HashMap<>();
        entryWrong3.put("Mobile Number", "9055271234");
        entryWrong3.put("Message", "Marco Valmores,, 1973-09-10, Marikina City");
        entryWrong3.put("Short Code", "123455");

        // Input has only two or less than arguments
        // Catches full name in <Last Name, First Name Format>
        HashMap<String, String> entryWrong4 = new HashMap<>();
        entryWrong4.put("Mobile Number", "9055271234");
        entryWrong4.put("Message", "Marco Valmores, 1973-09-10");
        entryWrong4.put("Short Code", "123455");

        // Input has three or more arguments
        // Catches full name in <Last Name, First Name Format>
        HashMap<String, String> entryWrong5 = new HashMap<>();
        entryWrong5.put("Mobile Number", "9055271234");
        entryWrong5.put("Message", "Marco Valmores, Male, 1973-09-10, Marikina City");
        entryWrong5.put("Short Code", "123455");

        // Input is NULL
        HashMap<String, String> entryWrong6 = new HashMap<>();
        entryWrong6.put("Mobile Number", "9055271234");
        entryWrong6.put("Message", "");
        entryWrong6.put("Short Code", "123455");

        // Input is just spaces
        HashMap<String, String> entryWrong7 = new HashMap<>();
        entryWrong7.put("Mobile Number", "9055271234");
        entryWrong7.put("Message", "     ");
        entryWrong7.put("Short Code", "123455");

        // Input is just spaces
        HashMap<String, String> entryWrong8 = new HashMap<>();
        entryWrong8.put("Mobile Number", "9055271234");
        entryWrong8.put("Message", "   ,   ,   ");
        entryWrong8.put("Short Code", "123455");

        assertTrue(SMSChecker.checkUserInformation(entryCorrect1, sms));
        assertTrue(SMSChecker.checkUserInformation(entryCorrect2, sms));

        assertFalse(SMSChecker.checkUserInformation(entryWrong1, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong2, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong3, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong4, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong5, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong6, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong7, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong8, sms));
    }

    @Test
    public void testUserInfoFullName() {
        SMS sms = new SMS();

        // Ideal Input
        HashMap<String, String> entryCorrect1 = new HashMap<>();
        entryCorrect1.put("Mobile Number", "9055271234");
        entryCorrect1.put("Message", "Stan Mamala, 1997-10-12, Loyola Heights Quezon City");
        entryCorrect1.put("Short Code", "123455");

        // Ideal Input
        HashMap<String, String> entryCorrect2 = new HashMap<>();
        entryCorrect2.put("Mobile Number", "9055271234");
        entryCorrect2.put("Message", "Renz Joshua De Guzman, 1998-10-06, 12345 Antipolo Street Sampaloc Manila City");
        entryCorrect2.put("Short Code", "123455");

        // Input is in Last Name, First Name format
        HashMap<String, String> entryWrong1 = new HashMap<>();
        entryWrong1.put("Mobile Number", "9055271234");
        entryWrong1.put("Message", "De Guzman, Renz, 1998-10-06, 12345 Antipolo Street Sampaloc Manila City");
        entryWrong1.put("Short Code", "123455");

        // Input is First Name or Last Name only
        HashMap<String, String> entryWrong2 = new HashMap<>();
        entryWrong2.put("Mobile Number", "9055271234");
        entryWrong2.put("Message", "Stan, 1997-10-12, Loyola Heights Quezon City");
        entryWrong2.put("Short Code", "123455");

        assertTrue(SMSChecker.checkUserInformation(entryCorrect1, sms));
        assertTrue(SMSChecker.checkUserInformation(entryCorrect2, sms));

        assertFalse(SMSChecker.checkUserInformation(entryWrong1, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong2, sms));
    }

    @Test
    public void testUserInfoBirthdate() {
        SMS sms = new SMS();

        // Ideal Input
        HashMap<String, String> entryCorrect1 = new HashMap<>();
        entryCorrect1.put("Mobile Number", "9055271234");
        entryCorrect1.put("Message", "Marco Valmores, 1973-09-10, Marikina City");
        entryCorrect1.put("Short Code", "123455");

        // Input has no leading zeroes
        HashMap<String, String> entryWrong1 = new HashMap<>();
        entryWrong1.put("Mobile Number", "9055271234");
        entryWrong1.put("Message", "Marco Valmores, 1973-9-10, Marikina City");
        entryWrong1.put("Short Code", "123455");

        // Input is in an invalid date format
        HashMap<String, String> entryWrong2 = new HashMap<>();
        entryWrong2.put("Mobile Number", "9055271234");
        entryWrong2.put("Message", "Marco Valmores, 09-10-1973, Marikina City");
        entryWrong2.put("Short Code", "123455");

        // Input is in an invalid date format
        HashMap<String, String> entryWrong3 = new HashMap<>();
        entryWrong3.put("Mobile Number", "9055271234");
        entryWrong3.put("Message", "Marco Valmores, 73-09-10, Marikina City");
        entryWrong3.put("Short Code", "123455");

        // Input is in an invalid date format
        HashMap<String, String> entryWrong4 = new HashMap<>();
        entryWrong4.put("Mobile Number", "9055271234");
        entryWrong4.put("Message", "Marco Valmores, 1973/09/10, Marikina City");
        entryWrong4.put("Short Code", "123455");

        assertTrue(SMSChecker.checkUserInformation(entryCorrect1, sms));

        assertFalse(SMSChecker.checkUserInformation(entryWrong1, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong2, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong3, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong4, sms));
    }

    @Test
    public void testUserInfoAddress() {
        SMS sms = new SMS();

        // Ideal Input (City Only)
        HashMap<String, String> entryCorrect1 = new HashMap<>();
        entryCorrect1.put("Mobile Number", "9055271234");
        entryCorrect1.put("Message", "Stan Mamala, 1997-10-12, Quezon City");
        entryCorrect1.put("Short Code", "123455");

        // Ideal Input (Full Address with no commas)
        HashMap<String, String> entryCorrect2 = new HashMap<>();
        entryCorrect2.put("Mobile Number", "9055271234");
        entryCorrect2.put("Message", "Renz Joshua De Guzman, 1998-10-06, 12345 Antipolo Street Sampaloc Manila City");
        entryCorrect2.put("Short Code", "123455");

        // Input has commas
        HashMap<String, String> entryWrong1 = new HashMap<>();
        entryWrong1.put("Mobile Number", "9055271234");
        entryWrong1.put("Message", "De Guzman, Renz, 1998-10-06, 12345 Antipolo Street, Sampaloc, Manila City");
        entryWrong1.put("Short Code", "123455");

        // Input does not indicate the string "city"
        HashMap<String, String> entryWrong2 = new HashMap<>();
        entryWrong2.put("Mobile Number", "9055271234");
        entryWrong2.put("Message", "Renz De Guzman, 1998-10-06, 12345 Antipolo Street Sampaloc Manila");
        entryWrong2.put("Short Code", "123455");

        assertTrue(SMSChecker.checkUserInformation(entryCorrect1, sms));
        assertTrue(SMSChecker.checkUserInformation(entryCorrect2, sms));

        assertFalse(SMSChecker.checkUserInformation(entryWrong1, sms));
        assertFalse(SMSChecker.checkUserInformation(entryWrong2, sms));
    }
}

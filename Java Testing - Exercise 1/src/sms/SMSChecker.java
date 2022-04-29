package sms;

import sms.model.SMS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSChecker {
    final private static Logger logger = Logger.getLogger(SMSChecker.class.getName());

    public static boolean checkRegister(HashMap<String, String> smsMap, SMS sms) {
        boolean result = false;

        if (smsMap.size() == 3) {
            String mobileNumber = smsMap.get("Mobile Number");
            String message = smsMap.get("Message");
            String shortCode = smsMap.get("Short Code");

            if (shortCode.equals("123455")) {
                sms.setPromo("Piso Pizza");
                if (message.replaceAll("\\s", "").equalsIgnoreCase("register")) {
                    result = true;
                    sms.setMsisdn(mobileNumber);
                    sms.setSuccess(true);
                    logger.log(Level.INFO, "To complete your registration, reply with your Full Name, Birthdate and Address in the following format (NO COMMAS): <First Name  Last Name>, <YYYY-MM-DD>, <City>");
                } else {
                    sms.setSuccess(false);
                    logger.log(Level.INFO, "Invalid Registration Code!");
                }
            }
        }
        return result;
    }

    public static boolean checkUserInformation(HashMap<String, String> smsMap, SMS sms) {
        boolean result = false;

        if (smsMap.size() == 3) {
            String mobileNumber = smsMap.get("Mobile Number");
            String message = smsMap.get("Message");
            String shortCode = smsMap.get("Short Code");

            if (shortCode.equals("123455")) {
                sms.setPromo("Piso Pizza");
                if (message == null || message.isEmpty()) {
                    logger.log(Level.INFO, "Message is EMPTY!");
                    sms.setSuccess(false);
                } else {
                    List<String> user_info = Arrays.asList(message.split("\\s*,\\s*"));
                    if (user_info.size() == 3) {
                        // -- INSERT NAME, BIRTHDATE AND LOCATION HANDLER HERE
                        String fullName = user_info.get(0);
                        String birthDate = user_info.get(1);
                        String address = user_info.get(2);

                        if (fullName.split(" ").length >= 2) {
                            Pattern pattern = Pattern.compile("^\\d{4}-(02-(0[1-9]|[12][0-9])|(0[469]|11)-(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))$");
                            Matcher matcher = pattern.matcher(birthDate);
                            if (matcher.matches()) {
                                String city = "city";
                                if (address.split(" ").length <= 20 && address.toLowerCase().contains(city)) {
                                    result = true;
                                    sms.setSuccess(true);
                                    sms.setMsisdn(mobileNumber);
                                    logger.log(Level.INFO, "Successfully Registered!");
                                } else {
                                    logger.log(Level.INFO, "Invalid Address!");
                                    sms.setSuccess(false);
                                }
                            } else {
                                logger.log(Level.INFO, "Invalid Birthdate");
                                sms.setSuccess(false);
                            }
                        } else {
                            logger.log(Level.INFO, "Invalid Full Name!");
                            sms.setSuccess(false);
                        }
                    } else {
                        logger.log(Level.INFO, "Invalid Message Arguments!");
                        sms.setSuccess(false);
                    }
                }
            }
        }
        return result;
    }
}

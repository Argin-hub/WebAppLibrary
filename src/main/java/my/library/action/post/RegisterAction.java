package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Person;
import my.library.entity.User;
import my.library.service.UserService;
import my.library.util.Hasher;
import my.library.util.SqlDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static my.library.action.Constants.*;

public class RegisterAction implements Action {
    private boolean wrong= false;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws Exception {

        UserService userService = new UserService();
        Properties properties = new Properties();
        User user = new User();
        Person person = new Person();

        try {
            properties.load(RegisterAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));
        } catch (IOException e) {
            throw new Exception("Can't load properties", e);
        }

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String middleName = request.getParameter(MIDDLE_NAME);
        String phone = request.getParameter(PHONE);
        String birthday = request.getParameter(BIRTHDAY);

        try {
            if (!userService.isLoginAvailable(email)) {
                request.setAttribute(EMAIL_ERROR, TRUE);
                wrong = true;
            } else {
                checkParamValid(EMAIL, email, properties.getProperty(EMAIL_VALID), request);
            }
        } catch (Exception e) {
            throw new Exception("can't check login available", e);
        }

        if (!password.equals(passwordConfirm)) {
            wrong = true;
            request.setAttribute(PASSWORD_ERROR, TRUE);
        } else {
            checkParamValid(PASSWORD, password, properties.getProperty(PASSWORD_VALID), request);
        }

        checkParamValid(FIRST_NAME, firstName, properties.getProperty(NAME_VALID), request);
        checkParamValid(LAST_NAME, lastName, properties.getProperty(NAME_VALID), request);
        checkParamValid(MIDDLE_NAME, middleName, properties.getProperty(NAME_VALID), request);
        checkParamValid(PHONE, phone, properties.getProperty(LIMIT_NUMBER_VALID), request);
        checkParamValid(BIRTHDAY, birthday, properties.getProperty(DATE_VALID), request);

        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        person.setBirthday(SqlDate.stringToDate(birthday));
        person.setPhone(phone);
        user.setPerson(person);
        user.setEmail(email);
        user.setPassword(Hasher.MD5(password));

        if (wrong) {
            wrong = false;
            return new ActionResult(REGISTER);
        } else {
            try {
                userService.registerUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult(WELCOME ,true);
    }

    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + ERROR, TRUE);
            wrong = true;
        }
    }
}

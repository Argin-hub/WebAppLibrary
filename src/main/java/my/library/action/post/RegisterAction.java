package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Person;
import my.library.entity.User;
import my.library.service.UserService;
import my.library.util.Hasher;
import my.library.util.SqlDate;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.*;
import static my.library.validator.RegistrValidation.*;

public class RegisterAction implements Action {
    private static final Logger log = Logger.getLogger(RegisterAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) {

        UserService userService = new UserService();
        User user = new User();
        Person person = new Person();

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
                request.setAttribute(EMAIL_EXIST, TRUE);
                return new ActionResult(REGISTER);
            }
            if (!validateMailRegex(email)) {
                request.setAttribute(EMAIL_ERROR, TRUE);
                return new ActionResult(REGISTER);
            }
        } catch (Exception e) {
            try {
                throw new Exception("can't check login available", e);
            } catch (Exception exception) {
                log.info("Ошибка при регистрации email " + e.getMessage());
            }
        }

        if (!password.equals(passwordConfirm)) {
            request.setAttribute(PASSWORD_NOT_MACH, TRUE);
            return new ActionResult(REGISTER);
        }
        if (!validatePassRegex(password)) {
            request.setAttribute(PASSWORD_ERROR, TRUE);
            return new ActionResult(REGISTER);
        }
        if (!validateNameRegex(firstName)) {
            request.setAttribute(FIRST_NAME_ERROR, TRUE);
            return new ActionResult(REGISTER);
        }
        if (!validateNameRegex(lastName)) {
            request.setAttribute(LAST_NAME_ERROR, TRUE);
            return new ActionResult(REGISTER);
        }
        if (!validateNameRegex(middleName)) {
            request.setAttribute(MIDDLE_NAME_ERROR, TRUE);
            return new ActionResult(REGISTER);
        }
        if (!validatePhoneRegex(phone)) {
            request.setAttribute(PHONE_ERROR, TRUE);
            return new ActionResult(REGISTER);
        }
        if (!validateDateRegex(birthday)) {
            request.setAttribute(BIRTHDAY_ERROR, TRUE);
            return new ActionResult(REGISTER);
        } else {
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setMiddleName(middleName);
            person.setBirthday(SqlDate.stringToDate(birthday));
            person.setPhone(phone);
            user.setPerson(person);
            user.setEmail(email);
            user.setPassword(Hasher.MD5(password));
        }

        try {
            userService.registerUser(user);
        } catch (Exception e) {
            log.info("can't register user " + e.getMessage());
        }
        return new ActionResult(WELCOME, true);
    }
}

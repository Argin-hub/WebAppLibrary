package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.User;
import my.library.service.UserService;
import my.library.util.Hasher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static my.library.action.Constants.*;

public class LoginAction implements Action {
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService();
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        try {
            User user = userService.findByLoginPassword(login, Hasher.MD5(password));

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute(ATT_USER_ID, user.getId());
                session.setAttribute(ATT_ROLE, user.getUserRole().getName());
                session.setAttribute(ATT_ROLE_ID, user.getUserRole().getId());
                session.setAttribute(ATT_NAME, user.getPerson().getFirstName());
                return new ActionResult(MAIN, true);
            } else {
                req.setAttribute(LOGIN_ERROR, true);
                return new ActionResult(WELCOME);
            }
        } catch (Exception e) {
            new Exception("can't find user", e);
        }
        return null;
    }
}

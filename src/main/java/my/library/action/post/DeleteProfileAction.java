package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.controller.ControllerServlet;
import my.library.entity.User;
import my.library.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.*;

public class DeleteProfileAction implements Action {
    private static final Logger log = Logger.getLogger(ControllerServlet.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService();
        User user = new User();
        int idUser = Integer.valueOf(req.getParameter(DELETE_ID));

        try {
            user = userService.findUserById(idUser);
            userService.deleteUser(user);
        } catch (Exception e) {
            log.info("Ошибка при создании страницы DeleteProfileAction " + e.getMessage());
        }

        if (user.getUserRole().getName().equals(ADMIN)) {
            return new ActionResult(WELCOME);
        }

        return new ActionResult(READERS, true);
    }
}

package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.User;
import my.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.*;

public class DeleteProfileAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UserService userService = new UserService();
        User user = new User();
        Integer id = Integer.valueOf(req.getParameter(DELETE_ID));

        try {
            user = userService.findUserById(id);
            userService.deleteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user.getUserRole().getName().equals(ADMIN)) {
            return new ActionResult(WELCOME);
        }

        return new ActionResult(READERS, true);

    }
}

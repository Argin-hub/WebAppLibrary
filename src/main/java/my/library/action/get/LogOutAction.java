package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.WELCOME;

public class LogOutAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getSession().invalidate();
        return new ActionResult(WELCOME, true);
    }
}

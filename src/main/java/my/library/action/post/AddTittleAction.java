package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.service.ForumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.REFERER;
import static my.library.action.Constants.TITTLE;

public class AddTittleAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
       String tittle = req.getParameter(TITTLE);
        ForumService forumService = new ForumService();
        forumService.createTopic(tittle);

        return new ActionResult(req.getHeader(REFERER), true);
    }
}

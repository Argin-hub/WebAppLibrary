package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Comment;
import my.library.entity.Topic;
import my.library.service.ForumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static my.library.action.Constants.*;

public class TopicShowAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();

        String forumId = req.getParameter(TITTLE_ID);
        int id = Integer.parseInt(forumId);
        ForumService forumService = new ForumService();
        List<Comment> comments = forumService.showAllComments(id);
        session.setAttribute(FORUM_ID, forumId);
        req.setAttribute(COMMENTS, comments);
        return new ActionResult(COMMENT);
    }
}

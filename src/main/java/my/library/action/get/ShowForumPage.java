package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Topic;
import my.library.service.ForumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static my.library.action.Constants.FORUM;
import static my.library.action.Constants.TITTLES;

public class ShowForumPage implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ForumService forumService = new ForumService();
        HttpSession session = req.getSession();
        List<Topic> topics = forumService.showAllTopic();
        req.setAttribute(TITTLES, topics);
        return new ActionResult(FORUM);
    }
}

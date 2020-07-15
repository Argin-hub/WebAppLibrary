package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Comment;
import my.library.entity.User;
import my.library.service.ForumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static my.library.action.Constants.*;

public class CommentAddUser implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

       Integer userId = (Integer) session.getAttribute(ATT_USER_ID);
        if(userId==null){
        return new ActionResult(WELCOME, true);
        }

       int userNumb = userId;
       String commentFromJsp = req.getParameter(OPINION);
       String idForum = (String) session.getAttribute(FORUM_ID);
       int forumId = Integer.parseInt(idForum);
       ForumService forumService = new ForumService();
       Comment comment = new Comment();
       User user = new User();

        user.setId(userNumb);
        comment.setUser(user);
        comment.setMessage(commentFromJsp);
        comment.setId(forumId);

        forumService.createComment(comment);

        return new ActionResult(req.getHeader(REFERER), true);
    }
}

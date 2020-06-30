package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Comment;
import my.library.entity.Topic;
import my.library.entity.User;
import my.library.service.ForumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static my.library.action.Constants.*;

public class CommentAddUser implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
       Integer userId = (Integer) session.getAttribute(ATT_USER_ID);
        if(userId==null){
return new ActionResult(WELCOME, true);
        }
       int user_numb = userId;

       String comment = req.getParameter(OPINION);
       String id_forum = (String) session.getAttribute(FORUM_ID);
       int id_f = Integer.parseInt(id_forum);
        ForumService forumService = new ForumService();
        Comment comment1 = new Comment();
        User user = new User();
        user.setId(user_numb);
        comment1.setUser(user);
        comment1.setMessage(comment);
        comment1.setId(id_f);

        forumService.createComment(comment1);
        return new ActionResult(req.getHeader(REFERER), true);
    }
}

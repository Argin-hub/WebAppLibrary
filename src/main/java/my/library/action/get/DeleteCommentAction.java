package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Comment;
import my.library.service.ForumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.COMMENT_ID;
import static my.library.action.Constants.REFERER;

public class DeleteCommentAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ForumService forumService = new ForumService();
        String commentId = req.getParameter(COMMENT_ID);
        int deleteComId = Integer.parseInt(commentId);
        Comment comment = new Comment();
        comment.setId(deleteComId);
        forumService.deleteComment(comment);

        return new ActionResult(req.getHeader(REFERER), true);
    }
}

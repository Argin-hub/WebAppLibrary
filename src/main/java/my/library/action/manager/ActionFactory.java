package my.library.action.manager;

import my.library.action.get.*;
import my.library.action.post.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private Map<String, Action> actions;

    public void init() {
        actions = new HashMap<>();
        actions.put("GET/welcome", new ShowPageAction("welcome"));
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("GET/main", new ShowPageAction("main"));
        actions.put("GET/select-language", new SelectLanguageAction());
        actions.put("GET/readers", new PageReadersAction());
        actions.put("GET/books", new PageBooksAction());
        actions.put("GET/add-book", new PageAddBookAction());
        actions.put("GET/logout", new LogOutAction());
        actions.put("GET/about-order", new OrderShowAllStatus());
        actions.put("GET/create-author", new ShowPageAction("add_author"));
        actions.put("GET/basket", new PageBasketAction());
        actions.put("GET/add-to-basket", new AddToBasketAction());
        actions.put("GET/remove-book", new DeleteBookAction());
        actions.put("GET/taken", new ChangeStatusTaken());
        actions.put("GET/completed", new ChangeStatusCompleted());
        actions.put("GET/order-user", new ShowOrderUser());
        actions.put("GET/forum-show", new ShowForumPage());
        actions.put("GET/add-comment", new TopicShowAction());
        actions.put("GET/delete-comment", new DeleteCommentAction());

        actions.put("POST/login", new LoginAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/deleteProfile", new DeleteProfileAction());
        actions.put("POST/tittle-forum", new AddTittleAction());
        actions.put("POST/comment-user", new CommentAddUser());
        actions.put("POST/search-title", new SearchTittleBook());
        actions.put("POST/search-author", new SearchAuthorBook());
        actions.put("POST/create-order", new CreateOrderAction());
        actions.put("POST/create-book", new CreateBookAction());
        actions.put("POST/save-author", new CreateAuthorAction());
    }

    public Action getAction(HttpServletRequest request) {
        if (actions == null) {
            init();
        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}

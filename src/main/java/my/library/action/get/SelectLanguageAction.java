package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import static my.library.action.Constants.*;

public class SelectLanguageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String language = req.getParameter(LANG);
        Config.set(req.getSession(), Config.FMT_LOCALE, new Locale(language));

        Cookie cookie = new Cookie(LANG, language);
        cookie.setMaxAge(HOUR * MINUTE * SEC);
        resp.addCookie(cookie);
        try {
            req.setCharacterEncoding(CHARACTER_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new Exception("can't set character encoding", e);
        }
        return new ActionResult(req.getHeader(REFERER), true);

    }
}

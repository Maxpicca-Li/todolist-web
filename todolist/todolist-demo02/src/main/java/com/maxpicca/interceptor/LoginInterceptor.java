package com.maxpicca.interceptor;

import com.maxpicca.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Maxpicca
 * @Date 2021-02-24 21:54
 */
public class LoginInterceptor implements HandlerInterceptor {
    // TODO 后端拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String path = session.getServletContext().getContextPath();
        String[] requireAuthenticationPages = new String[]{"index",};
        String uri = request.getRequestURI();
        String topage = StringUtils.remove(uri,path+"/");
        if(beginWith(topage,requireAuthenticationPages)){
            User user= (User) session.getAttribute("user");
            if(user==null){
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean beginWith(String page, String[] requireAuthenticationPages) {
        boolean result = false;
        for (String requiredAuthPage : requireAuthenticationPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}

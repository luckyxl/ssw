package com.aas.ssw.common.security;

import com.aas.ssw.common.component.Constant;
import com.aas.ssw.common.component.Result;
import com.aas.ssw.common.util.RequestUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义security没有权限时的处理策略
 */
public class SswAccessDeniedHandler implements AccessDeniedHandler {
    private final String errorPage = "/accessDeny";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        boolean isAjax = RequestUtil.isAjaxRequest(request);
        //ajax请求返回json数据,非ajax请求跳转至自定义页面
        if (isAjax) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.append(JSONObject.toJSONString(Result.getResult(Constant.FAIL,"没有权限",null,null)));
                out.flush();
            } finally {
                if(out != null){
                    out.close();
                }
            }
        } else if (!response.isCommitted()) {
            if (errorPage != null) {
                // Put exception into request scope (perhaps of use to a view)
                request.setAttribute(WebAttributes.ACCESS_DENIED_403, e);
                // Set the 403 status code.
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // forward to error page.
                RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            }
        }
    }
}

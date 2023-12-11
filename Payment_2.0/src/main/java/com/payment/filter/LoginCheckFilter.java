//package com.payment.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.payment.common.BaseContext;
//import com.payment.common.Result;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.AntPathMatcher;
//
//
//import java.io.IOException;
//
//@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
//@Slf4j
//public class LoginCheckFilter implements Filter {
//    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String URI = request.getRequestURI();
////        log.info(URI);
//        String[] uris = new String[]{ //不拦截的页面
//                "/order",
//                "/item",
//                "/order/**",
//                "/item/**",
//                "/common/**",
//                "/*/**"
//        };
//        boolean check = check(uris, URI);
////        log.info(String.valueOf(check));
//        if(check){
//            filterChain.doFilter(request, response);
//            return;
//        }
//        if(request.getSession().getAttribute("employee")!=null){
//            Integer id = (Integer) request.getSession().getAttribute("employee");
//            BaseContext.setCurrentId(id);
//            filterChain.doFilter(request, response);
//            return;
//        }
//        if(request.getSession().getAttribute("user")!=null){
//            Integer id = (Integer) request.getSession().getAttribute("user");
//            BaseContext.setCurrentId(id);
//            filterChain.doFilter(request, response);
//            return;
//        }
//        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
//    }
//
//    public boolean check(String[] uris, String uri) {
//        for (String URI : uris) {
//            boolean match = PATH_MATCHER.match(URI,uri);
//            if (match)
//                return true;
//        }
//        return false;
//    }
//
//}

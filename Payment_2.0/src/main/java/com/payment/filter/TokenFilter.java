package com.payment.filter;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.payment.common.JwtUtil;
import com.payment.common.SpringUtil;
import com.payment.service.IManagerService;
import com.payment.vo.ResultM;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.io.PrintWriter;

@CrossOrigin
@WebFilter(filterName = "TokenFilter", urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {

    @Resource
    private IManagerService managerService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String method=((HttpServletRequest) servletRequest).getMethod();
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
            ResultM result = new ResultM<>();
            if (url != null) {
                if ("/admin/login".equals(url) || ((!url.contains("/admin"))&&(!url.contains("/recon"))) || (url.contains("/recon/api/test") || url.contains("/recon/api/testrecon"))) {//登录请求直接放行
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }else {//其他请求验证token
                    String token = httpServletRequest.getHeader("Authorization");
                    if (StringUtils.isNotBlank(token)) {
                        JwtUtil jwtUtil = new JwtUtil();
                        jwtUtil.managerService = managerService = SpringUtil.getBean(managerService.getClass());
                        int verifyToken = jwtUtil.verify(token);
                        if (verifyToken != 1) {
                            if (verifyToken == 2) {
                                result = ResultM.fail("登录过期，请重新登录", null);
                            } else if (verifyToken == 0) {
                                result = ResultM.fail("用户信息验证失败，请重新登录", null);
                            } else if (verifyToken == 3) {
                                result = ResultM.fail("token格式错误，请重新登陆", null);
                            }
                        } else if (verifyToken == 1) {
                            filterChain.doFilter(servletRequest, servletResponse);
                            return;
                        }
                    } else {
                        result = ResultM.fail("未登录，请先登录", null);
                    }
                }
                servletResponse.setContentType("application/json");//被拦截了，进行返回
                servletResponse.setCharacterEncoding("utf-8");
                PrintWriter pw = servletResponse.getWriter();
                pw.write(JSON.toJSONString(result));
                pw.flush();
                pw.close();
            }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

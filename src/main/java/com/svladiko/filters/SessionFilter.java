package com.svladiko.filters;

import com.svladiko.model.Client;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Filtering all requests in the application
 *
 * @author Vladislav Serhiychuk
 */
@WebFilter(filterName = "zep.family.test.filters.RequestLoggingFilter")
public class SessionFilter implements Filter {
    private List<String> admins;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/roles.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String allowedAdmins = properties.getProperty("role_admin");
        admins = new ArrayList(Arrays.asList(allowedAdmins.split(",")));
    }

    /**
     * Checking session, commands and logical to filter queries.
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String login = request.getParameter("login");
        String command = request.getParameter("command");

        if (session == null || session.getAttribute("client") == null) {
            if (!("authorization".equals(command) || "register".equals(command) || ("Register page".equals(command)))) {
                response.sendRedirect("login.jsp");
                return;
            }
        } else {

            Client client = (Client) session.getAttribute("client");

            if (admins.contains(client.getLogin())) {
                request.getRequestDispatcher("/adminController").forward(request, response);
                return;
            }
        }

        if (login != null) {
            if (admins.contains(login)) {
                request.getRequestDispatcher("/adminController").forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}


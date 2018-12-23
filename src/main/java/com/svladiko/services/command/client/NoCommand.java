package com.svladiko.services.command.client;

import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is invoked when the application receives an unknown command.
 *
 * @author Vladislav Serhiychuk
 */
public class NoCommand implements Command, CommonConstants {

    /**
     * Return in login page.
     *
     * @param request
     * @param response
     * @return PAGE_LOGIN
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // In the case of a direct appeal to the controller, redirected to the login PAGE_LOGIN input
        return PAGE_LOGIN;
    }
}

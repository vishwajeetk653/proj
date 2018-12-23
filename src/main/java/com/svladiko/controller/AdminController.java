package com.svladiko.controller;

import com.svladiko.services.command.MenuAdmin;
import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * For admin commands.
 * <p>
 * All admin commands implement the interface with a single method.
 * The method returns the page to which the transition will be.
 * To select the interface implementation uses a HashMap.
 * Using the key we liberam an implementation of the interface
 * </p>
 *
 * @author Vladislav Serhiychuk
 */
public class AdminController extends HttpServlet implements Servlet, CommonConstants {
    private final Logger LOGGER = Logger.getLogger(AdminController.class);
    private MenuAdmin adminCommand = MenuAdmin.getInstance();
    private String page = PAGE_ERROR;

    public AdminController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * The script processing the req
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Command command = adminCommand.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error("AdminController ", e);
            request.setAttribute(MESSAGE, ERROR_CONTROLLER);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

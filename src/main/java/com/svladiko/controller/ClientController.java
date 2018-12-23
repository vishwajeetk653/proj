package com.svladiko.controller;

import com.svladiko.services.command.MenuClient;
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
 * All client commands inherit the interface with a single method.
 * The method returns the page which will transition after processing the req.
 * To select the interface implementation uses a HashMap.
 * Using the key we choose an implementation of the interface.
 *
 * @author Vladislav Serhiychuk
 *         Created on 4/19/2015.
 */
public class ClientController extends HttpServlet implements Servlet,CommonConstants {
    private static final Logger LOGGER = Logger.getLogger(ClientController.class);
    private String page = PAGE_ERROR;

    // Object containing a list of available commands
    MenuClient requestHelper = MenuClient.getInstance();

    public ClientController() {
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
            Command command = requestHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error("ClientController ", e);
            request.setAttribute(MESSAGE, ERROR_CONTROLLER);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}


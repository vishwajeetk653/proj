package com.svladiko.services.command.client;

import com.svladiko.model.Client;
import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Exit on this application
 *
 * @author Vladislav Serhiychuk
 *         Created on 4/25/2015.
 */
public class Exit implements Command,CommonConstants {
    private final Logger LOG = Logger.getLogger(Exit.class);

    /**
     * Remove  session attribute.
     * Return in login.jsp.
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");
        session.removeAttribute("client");

        if (session.getAttribute("card") != null) {
            session.removeAttribute("card");
        }

        LOG.info(client.getLogin());
        return PAGE_LOGIN;
    }
}

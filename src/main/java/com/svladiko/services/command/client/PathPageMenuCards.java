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
 * Go to the many cards page sent via the controller for application security.
 *
 * @author Vladislav Serhiychuk
 */
public class PathPageMenuCards implements Command,CommonConstants {
    private final Logger LOG = Logger.getLogger(PathPageMenuCards.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Client client= (Client) session.getAttribute("client");
        LOG.info("client.login = " + client.getLogin());

        return MENU_CARDS_PAGE;
    }
}

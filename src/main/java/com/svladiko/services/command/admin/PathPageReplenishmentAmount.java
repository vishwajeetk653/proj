package com.svladiko.services.command.admin;

import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Go to the deposit page sent via the controller for application security.
 *
 * @author Vladislav Serhiychuk
 */
public class PathPageReplenishmentAmount implements Command, CommonConstants {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int account = Integer.parseInt(request.getParameter(ACCOUNT));
        request.setAttribute("card", CARD_DAO.getByAccount(account));
        return PAGE_REPLENISHMENT;
    }
}

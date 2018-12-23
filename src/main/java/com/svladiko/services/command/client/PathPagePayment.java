package com.svladiko.services.command.client;

import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Go to the payment page sent via the controller for application security.
 *
 * @author Vladislav Serhiychuk
 */
public class PathPagePayment implements Command, CommonConstants {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PAGE_PAYMENT;
    }
}

package com.svladiko.services.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This interface for all client commands and administrator.
 *
 * @author Vladislav Serhiychuk
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

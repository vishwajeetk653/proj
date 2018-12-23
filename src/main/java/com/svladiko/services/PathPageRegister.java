package com.svladiko.services;

import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Влад on 06.10.2015.
 */
public class PathPageRegister implements Command, CommonConstants {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PAGE_REGISTER;
    }
}

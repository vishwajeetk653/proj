package com.svladiko.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.http.HttpServlet;

/**
 * Created by ÀSUS on 08.06.2015.
 */
public class Log4jInitManeger   extends HttpServlet {
    private static final Logger LOGGER = Logger
            .getLogger(Log4jInitManeger.class);
    private static final long serialVersionUID = 1L;

    public void init() {
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-init-file");

        // if the log4j-init-file context parameter is not set, then no point in
        // trying
        if (file != null) {
            DOMConfigurator.configure(prefix + file);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Log4J Logging started: " + prefix + file);
            }
        } else {
            LOGGER.error("Log4J Is not configured for your Application: "
                    + prefix + file);
        }
    }
}
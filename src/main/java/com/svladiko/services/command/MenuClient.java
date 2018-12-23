package com.svladiko.services.command;

import com.svladiko.services.PathPageRegister;
import com.svladiko.services.command.admin.PathPageReplenishmentAmount;
import com.svladiko.services.command.client.*;
import com.svladiko.services.command.card.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * A list of client commands.
 *
 * @author Vladislav Serhiychuk
 */
public class MenuClient {
    private static volatile MenuClient instance = null;
    HashMap<String, Command> actions = new HashMap<String, Command>();

    private MenuClient() {
        actions.put("authorization", new Authorization());
        actions.put("register", new Registration());
        actions.put("use card", new GetCard());
        actions.put("block card", new Block());
        actions.put("unblock card", new RequestUnblock());
        actions.put("payment", new Payment());
        actions.put("history", new GetHistory());
        actions.put("exit", new Exit());
        actions.put("Register page", new PathPageRegister());

        actions.put("paymentPage", new PathPagePayment());
        actions.put("depositPage", new PathPageReplenishmentAmount());
        actions.put("menuCardsPage", new PathPageMenuCards());
    }

    /**
     * Team selection by key.
     *
     * @param request
     * @return Command
     */
    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = actions.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    /**
     * Create RequestHelper object
     *
     * @return
     */
    public static MenuClient getInstance() {
        if (instance == null) {
            synchronized (MenuClient.class) {
                if (instance == null) instance = new MenuClient();
            }
        }
        return instance;
    }
}



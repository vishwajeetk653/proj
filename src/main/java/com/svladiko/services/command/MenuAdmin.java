package com.svladiko.services.command;

import com.svladiko.services.command.admin.Authorization;
import com.svladiko.services.command.card.ReplenishmentAmounts;
import com.svladiko.services.command.card.Unblock;
import com.svladiko.services.command.client.Exit;
import com.svladiko.services.command.client.NoCommand;
import com.svladiko.services.command.admin.PathPageReplenishmentAmount;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * A list of admin commands.
 *
 * @author Vladislav Serhiychuk
 */
public class MenuAdmin {
    private static MenuAdmin instance = null;
    HashMap<String, Command> actions = new HashMap<>();

    private MenuAdmin() {
        actions.put("authorization", new Authorization());
        actions.put("ReplenishmentAmounts", new ReplenishmentAmounts());
        actions.put("ReplenishmentPage", new PathPageReplenishmentAmount());
        actions.put("Unlock card", new Unblock());
        actions.put("exit", new Exit());
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
     * Create AdminCommand object
     *
     * @return
     */
    public static MenuAdmin getInstance() {
        if (instance == null) {
            instance = new MenuAdmin();
        }
        return instance;
    }
}

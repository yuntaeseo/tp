package seedu.address.commons.core;

import java.util.ArrayList;

/**
 * Keep track of previous commands inputted by the user and
 * allow for traversal of previous commands.
 */
public class CommandTracker {
    private ArrayList<String> history;
    private int commandIndex;

    /**
     * Creates a {@code CommandTracker}, with empty command history.
     */
    public CommandTracker() {
        history = new ArrayList<>();
        commandIndex = 0;
    }

    /**
     * Returns the previous command inputted by the user,
     * assuming that user is nagivating their past commands.
     */
    public String getPreviousCommand() {
        if (history.isEmpty()) {
            return "";
        } else if (commandIndex == 0) {
            return history.get(0);
        } else {
            return history.get(--commandIndex);
        }
    }

    /**
     * Returns the next command inputted by the user,
     * assuming that users is navigating their past commands.
     */
    public String getNextCommand() {
        if (history.isEmpty() || commandIndex == history.size()) {
            return "";
        } else if (commandIndex == history.size() - 1) {
            commandIndex++;
            return "";
        } else {
            return history.get(++commandIndex);
        }
    }

    /**
     * Adds the command to the list of command history.
     */
    public void addToHistory(String command) {
        history.add(command);
        commandIndex = history.size();
    }
}

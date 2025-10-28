package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandTrackerTest {
    private CommandTracker tracker = new CommandTracker();

    @Test
    public void getPreviousAndNextCommand_withHistory() {
        tracker.addToHistory("my");
        tracker.addToHistory("name");
        tracker.addToHistory("is");
        tracker.addToHistory("minh");

        // previous
        assertEquals(tracker.getPreviousCommand(), "minh");
        assertEquals(tracker.getPreviousCommand(), "is");
        assertEquals(tracker.getPreviousCommand(), "name");
        assertEquals(tracker.getPreviousCommand(), "my");
        assertEquals(tracker.getPreviousCommand(), "my");
        assertEquals(tracker.getPreviousCommand(), "my");

        // next
        assertEquals(tracker.getNextCommand(), "name");
        assertEquals(tracker.getNextCommand(), "is");
        assertEquals(tracker.getNextCommand(), "minh");
        assertEquals(tracker.getNextCommand(), "");
        assertEquals(tracker.getNextCommand(), "");
    }

    @Test
    public void addToHistory_indexResetAfterAdding() {
        tracker.addToHistory("my");
        tracker.addToHistory("name");
        tracker.addToHistory("is");
        tracker.addToHistory("minh");
        tracker.getPreviousCommand();
        tracker.getPreviousCommand();
        tracker.getPreviousCommand();
        tracker.getNextCommand();

        tracker.addToHistory("hehe");
        assertEquals(tracker.getNextCommand(), "");
        assertEquals(tracker.getPreviousCommand(), "hehe");
    }

    @Test
    public void getPreviousCommand_withoutHistory() {
        assertEquals(tracker.getPreviousCommand(), "");
    }

    @Test
    public void getNextCommand_withoutHistory() {
        assertEquals(tracker.getNextCommand(), "");
    }
}

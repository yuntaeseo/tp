package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    @Test
    public void isShowTagList() {
        // CommandResult is list tag -> returns false
        CommandResult tagListResult = new CommandResult("Listed all tags");
        assertFalse(tagListResult.isShowPersonList());

        // CommandResult is list person -> returns false
        CommandResult personListResult = new CommandResult("Listed all persons");
        assertFalse(personListResult.isShowTagList());

        // CommandResult is different -> returns false
        CommandResult otherResult = new CommandResult("feedback");
        assertFalse(otherResult.isShowTagList());
    }

    @Test
    public void isShowRelationshipQueries() {
        CommandResult listQueryResult = new CommandResult("Listed all relationships involving person ID");
        assertTrue(listQueryResult.isShowRelationshipQueryList());
        assertFalse(listQueryResult.isShowRelationshipQueryLink());

        CommandResult linkQueryResult = new CommandResult("Listed link between person IDs");
        assertTrue(linkQueryResult.isShowRelationshipQueryLink());
        assertFalse(linkQueryResult.isShowRelationshipQueryList());

        CommandResult otherResult = new CommandResult("feedback");
        assertFalse(otherResult.isShowRelationshipQueryList());
        assertFalse(otherResult.isShowRelationshipQueryLink());
    }


    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}

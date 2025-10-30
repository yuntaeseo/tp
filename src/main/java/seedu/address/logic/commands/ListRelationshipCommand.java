package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_2;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.id.Id;

/**
 * Lists all relationships in the address book.
 * Can also show relationships connected to a person (1 ID) or a link between two persons (2 IDs).
 */
public class ListRelationshipCommand extends Command {

    public static final String COMMAND_WORD = "listrel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " p1/ID: Lists relationships related to a connection.\n"
            + "Usage:\n"
            + "  " + COMMAND_WORD + " " + PREFIX_PART_1 + "ID: lists all relationships involving that connection\n"
            + "  " + COMMAND_WORD + " " + PREFIX_PART_1 + "ID1 " + PREFIX_PART_2
            + "ID2: shows link between two persons\n"
            + "Examples:\n"
            + "  " + COMMAND_WORD + " " + PREFIX_PART_1 + "1\n"
            + "  " + COMMAND_WORD + " " + PREFIX_PART_1 + "1 " + PREFIX_PART_2 + "3";

    public static final String MESSAGE_SUCCESS_ONE = "Listed all relationships involving person ID " + "%1$s";
    public static final String MESSAGE_SUCCESS_TWO = "Listed link between person IDs " + "%1$s and %2$s";
    public static final String MESSAGE_INVALID_PERSON_ID = "One or both person IDs do not exist.";

    private final Id id1;
    private final Id id2;

    /** Constructor for one-ID (immediate relationship) query */
    public ListRelationshipCommand(Id id1) {
        this.id1 = id1;
        this.id2 = null;
    }

    /** Constructor for two-ID (link) query */
    public ListRelationshipCommand(Id id1, Id id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    /* Lists relationships depending on the inputs given (0 id, 1 id, 2ids)*/
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Case 1: one ID — queryImmediateRelationship
        if (id2 == null) {
            if (!model.hasPersonWithId(id1)) {
                throw new CommandException(MESSAGE_INVALID_PERSON_ID);
            }
            model.queryImmediateRelationship(id1);
            return new CommandResult(String.format(MESSAGE_SUCCESS_ONE, id1));
        }

        // Case 2: two IDs — queryLink
        if (!model.hasPersonWithId(id1) || !model.hasPersonWithId(id2)) {
            throw new CommandException(MESSAGE_INVALID_PERSON_ID);
        }

        model.queryLink(id1, id2); // Note that the method does not actually state whether it found a link or not
        return new CommandResult(String.format(MESSAGE_SUCCESS_TWO, id1, id2));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ListRelationshipCommand)) {
            return false;
        }

        ListRelationshipCommand otherCommand = (ListRelationshipCommand) other;
        return Objects.equals(id1, otherCommand.id1) && Objects.equals(id2, otherCommand.id2);
    }

    @Override
    public String toString() {
        if (id1 == null && id2 == null) {
            return COMMAND_WORD + " (all)";
        }
        if (id2 == null) {
            return COMMAND_WORD + " (id1=" + id1 + ")";
        }
        return COMMAND_WORD + " (id1=" + id1 + ", id2=" + id2 + ")";
    }
}

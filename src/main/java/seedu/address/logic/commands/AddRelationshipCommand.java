package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_2;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.relationship.Relationship;

/**
 * Adds a relationship to the address book.
 */
public class AddRelationshipCommand extends Command {

    public static final String COMMAND_WORD = "addrel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a relationship to the address book. "
            + "Parameters: "
            + PREFIX_PART_1 + "PART_1 "
            + PREFIX_PART_2 + "PART_2 "
            + PREFIX_DESC + "DESCRIPTION";

    public static final String MESSAGE_RELATIONSHIP_SUCCESS = "New relationship added: %1$s";
    public static final String MESSAGE_INVALID_PERSON_ID = "One or both person IDs do not exist.";
    public static final String MESSAGE_DUPLICATE_RELATIONSHIP = "This relationship already exists in the address book.";

    private final Relationship toAdd;

    /**
     * Creates an AddRelationshipCommand to add the specified {@code Relationship}
     */
    public AddRelationshipCommand(Relationship relationship) {
        requireNonNull(relationship);
        toAdd = relationship;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Validate that both persons exist
        if (!model.hasPersonWithId(toAdd.getPart1()) || !model.hasPersonWithId(toAdd.getPart2())) {
            throw new CommandException(MESSAGE_INVALID_PERSON_ID);
        }

        if (model.hasRelationship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RELATIONSHIP);
        }

        model.addRelationship(toAdd);
        return new CommandResult(String.format(MESSAGE_RELATIONSHIP_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddRelationshipCommand
                && toAdd.equals(((AddRelationshipCommand) other).toAdd));
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "{toAdd=" + toAdd + "}";
    }
}

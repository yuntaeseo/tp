package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_2;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.relationship.Relationship;

/**
 * Deletes a relationship between two persons identified by their IDs.
 */
public class DeleteRelationshipCommand extends Command {

    public static final String COMMAND_WORD = "deleterel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the relationship between two persons "
            + "identified by their IDs.\n"
            + "Parameters: "
            + PREFIX_PART_1 + "PERSON_ID_1 "
            + PREFIX_PART_2 + "PERSON_ID_2 "
            + "Example: " + COMMAND_WORD + " p1/1 p2/2";

    public static final String MESSAGE_DELETE_RELATIONSHIP_SUCCESS = "Deleted Relationship: %1$s";
    public static final String MESSAGE_RELATIONSHIP_NOT_FOUND =
            "No relationship found between the given person IDs.";
    public static final String MESSAGE_INVALID_PERSON_ID =
            "One or both person IDs do not exist in the address book.";

    private final Id part1;
    private final Id part2;

    /**
     * Creates a DeleteRelationshipCommand to delete the relationship between the given IDs.
     * @param part1 The ID of the first participant in the relationship.
     * @param part2 The ID of the second participant in the relationship.
     */
    public DeleteRelationshipCommand(Id part1, Id part2) {
        requireNonNull(part1);
        requireNonNull(part2);
        this.part1 = part1;
        this.part2 = part2;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check that both persons exist
        if (!model.hasPersonWithId(part1) || !model.hasPersonWithId(part2)) {
            throw new CommandException(MESSAGE_INVALID_PERSON_ID);
        }

        // Find the existing relationship
        List<Relationship> relationships = model.getFilteredRelationshipList();
        Optional<Relationship> match = relationships.stream()
                .filter(r -> r.isSameRelationship(new Relationship(part1, part2, new Description(""))))
                .findFirst();

        if (match.isEmpty()) {
            throw new CommandException(MESSAGE_RELATIONSHIP_NOT_FOUND);
        }

        Relationship relationshipToDelete = match.get();

        // Delete from model
        model.deleteRelationship(relationshipToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_RELATIONSHIP_SUCCESS, relationshipToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteRelationshipCommand
                && part1.equals(((DeleteRelationshipCommand) other).part1)
                && part2.equals(((DeleteRelationshipCommand) other).part2));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("part1", part1)
                .add("part2", part2)
                .toString();
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_2;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RELATIONSHIPS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.relationship.Relationship;

/**
 * Edits the description of an existing relationship between two persons in the address book.
 * Identifies the relationship by its two participant IDs.
 */
public class EditRelationshipCommand extends Command {

    public static final String COMMAND_WORD = "editrel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the description of the relationship "
            + "between two persons identified by their IDs.\n"
            + "Parameters: "
            + PREFIX_PART_1 + "PERSON_ID_1 "
            + PREFIX_PART_2 + "PERSON_ID_2 "
            + PREFIX_DESC + "DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " p1/1 p2/2 d/Former colleagues";

    public static final String MESSAGE_EDIT_RELATIONSHIP_SUCCESS = "Edited Relationship: %1$s";
    public static final String MESSAGE_RELATIONSHIP_NOT_FOUND = "No relationship found between the given person IDs.";
    public static final String MESSAGE_INVALID_PERSON_ID = "One or both person IDs do not exist in the address book.";
    public static final String MESSAGE_NOT_EDITED = "A new description must be provided.";

    private final Id part1;
    private final Id part2;
    private final Description newDescription;

    /**
     * Creates an EditRelationshipCommand to edit the relationship between the given IDs.
     * @param part1 The ID of the first participant in the relationship.
     * @param part2 The ID of the second participant in the relationship.
     * @param newDescription The new description for the relationship.
     */
    public EditRelationshipCommand(Id part1, Id part2, Description newDescription) {
        requireNonNull(part1);
        requireNonNull(part2);
        requireNonNull(newDescription);
        this.part1 = part1;
        this.part2 = part2;
        this.newDescription = newDescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check that both persons exist
        if (!model.hasPersonWithId(part1) || !model.hasPersonWithId(part2)) {
            throw new CommandException(MESSAGE_INVALID_PERSON_ID);
        }

        // Find the existing relationship between these two IDs
        List<Relationship> relationships = model.getFilteredRelationshipList();
        Optional<Relationship> match = relationships.stream()
                .filter(r -> r.isSameRelationship(new Relationship(part1, part2, new Description(""))))
                .findFirst();

        if (match.isEmpty()) {
            throw new CommandException(MESSAGE_RELATIONSHIP_NOT_FOUND);
        }

        Relationship existingRel = match.get();

        // Create a new relationship with updated description
        Relationship updatedRel = new Relationship(existingRel.getPart1(), existingRel.getPart2(), newDescription);

        // Replace in model
        model.setRelationship(existingRel, updatedRel);
        model.updateFilteredRelationshipList(PREDICATE_SHOW_ALL_RELATIONSHIPS);

        return new CommandResult(String.format(MESSAGE_EDIT_RELATIONSHIP_SUCCESS, updatedRel));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EditRelationshipCommand
                && part1.equals(((EditRelationshipCommand) other).part1)
                && part2.equals(((EditRelationshipCommand) other).part2)
                && newDescription.equals(((EditRelationshipCommand) other).newDescription));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("part1", part1)
                .add("part2", part2)
                .add("newDescription", newDescription)
                .toString();
    }
}

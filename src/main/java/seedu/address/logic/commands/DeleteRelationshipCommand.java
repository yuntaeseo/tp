package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.relationship.Relationship;

/**
 * Deletes a relationship identified using its displayed index from the relationship list.
 */
public class DeleteRelationshipCommand extends Command {

    public static final String COMMAND_WORD = "deleterel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the relationship identified by the index number used in the displayed relationship list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_RELATIONSHIP_SUCCESS = "Deleted Relationship: %1$s";

    private final Index targetIndex;

    public DeleteRelationshipCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Relationship> lastShownList = model.getFilteredRelationshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RELATIONSHIP_DISPLAYED_INDEX);
        }

        Relationship relationshipToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteRelationship(relationshipToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_RELATIONSHIP_SUCCESS, relationshipToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteRelationshipCommand
                && targetIndex.equals(((DeleteRelationshipCommand) other).targetIndex));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}

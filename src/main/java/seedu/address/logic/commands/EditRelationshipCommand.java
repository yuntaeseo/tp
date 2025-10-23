package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RELATIONSHIPS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.relationship.Relationship;

/**
 * Edits the details of an existing relationship in the address book.
 */
public class EditRelationshipCommand extends Command {

    public static final String COMMAND_WORD = "editrel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the relationship identified "
            + "by the index number used in the displayed relationship list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[p1/PERSON_ID_1] "
            + "[p2/PERSON_ID_2] "
            + "[d/DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 d/Former colleagues";

    public static final String MESSAGE_EDIT_RELATIONSHIP_SUCCESS = "Edited Relationship: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RELATIONSHIP = "This relationship already exists in the address book.";

    private final Index index;
    private final EditRelationshipDescriptor editRelationshipDescriptor;

    /**
     * @param index of the relationship in the filtered relationship list to edit
     * @param editRelationshipDescriptor details to edit the relationship with
     */
    public EditRelationshipCommand(Index index, EditRelationshipDescriptor editRelationshipDescriptor) {
        requireNonNull(index);
        requireNonNull(editRelationshipDescriptor);

        this.index = index;
        this.editRelationshipDescriptor = new EditRelationshipDescriptor(editRelationshipDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Relationship> lastShownList = model.getFilteredRelationshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RELATIONSHIP_DISPLAYED_INDEX);
        }

        Relationship relationshipToEdit = lastShownList.get(index.getZeroBased());
        Relationship editedRelationship = createEditedRelationship(relationshipToEdit, editRelationshipDescriptor);

        if (!model.hasPersonWithId(editedRelationship.getPart1())
                || !model.hasPersonWithId(editedRelationship.getPart2())) {
            throw new CommandException("One or both person IDs do not exist in the address book.");
        }

        if (!relationshipToEdit.isSameRelationship(editedRelationship)
                && model.hasRelationship(editedRelationship)) {
            throw new CommandException(MESSAGE_DUPLICATE_RELATIONSHIP);
        }

        model.setRelationship(relationshipToEdit, editedRelationship);
        model.updateFilteredRelationshipList(PREDICATE_SHOW_ALL_RELATIONSHIPS);
        return new CommandResult(String.format(MESSAGE_EDIT_RELATIONSHIP_SUCCESS, editedRelationship));
    }

    /**
     * Creates and returns a {@code Relationship} with the details of {@code relationshipToEdit}
     * edited with {@code editRelationshipDescriptor}.
     */
    private static Relationship createEditedRelationship(
            Relationship relationshipToEdit, EditRelationshipDescriptor editRelationshipDescriptor) {
        assert relationshipToEdit != null;

        Id updatedPart1 = editRelationshipDescriptor.getPart1().orElse(relationshipToEdit.getPart1());
        Id updatedPart2 = editRelationshipDescriptor.getPart2().orElse(relationshipToEdit.getPart2());
        Description updatedDescription =
                editRelationshipDescriptor.getDescription().orElse(relationshipToEdit.getDescription());

        return new Relationship(updatedPart1, updatedPart2, updatedDescription);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EditRelationshipCommand
                && index.equals(((EditRelationshipCommand) other).index)
                && editRelationshipDescriptor.equals(((EditRelationshipCommand) other).editRelationshipDescriptor));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editRelationshipDescriptor", editRelationshipDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the relationship with.
     * Each non-empty field value will replace the corresponding field value of the relationship.
     */
    public static class EditRelationshipDescriptor {
        private Id part1;
        private Id part2;
        private Description description;

        public EditRelationshipDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditRelationshipDescriptor(EditRelationshipDescriptor toCopy) {
            setPart1(toCopy.part1);
            setPart2(toCopy.part2);
            setDescription(toCopy.description);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(part1, part2, description);
        }

        public void setPart1(Id part1) {
            this.part1 = part1;
        }

        public Optional<Id> getPart1() {
            return Optional.ofNullable(part1);
        }

        public void setPart2(Id part2) {
            this.part2 = part2;
        }

        public Optional<Id> getPart2() {
            return Optional.ofNullable(part2);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof EditRelationshipDescriptor)) {
                return false;
            }

            EditRelationshipDescriptor e = (EditRelationshipDescriptor) other;
            return Objects.equals(part1, e.part1)
                    && Objects.equals(part2, e.part2)
                    && Objects.equals(description, e.description);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("part1", part1)
                    .add("part2", part2)
                    .add("description", description)
                    .toString();
        }
    }
}

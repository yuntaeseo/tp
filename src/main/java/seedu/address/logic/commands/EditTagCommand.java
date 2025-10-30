package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.id.Id;
import seedu.address.model.tag.Color;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;
/**
 * Edits the details of an existing tag in the address book.
 */
public class EditTagCommand extends Command {

    public static final String COMMAND_WORD = "edittag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a tag identified by its ID.\n"
            + "Parameters: ID "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DESC + "DESCRIPTION] "
            + "[" + PREFIX_COLOR + "COLOR]\n"
            + "Example: " + COMMAND_WORD + " 1 n/BestFriends c/RRGGBB";

    public static final String MESSAGE_EDIT_SUCCESS = "Edited Tag: %1$s";
    public static final String MESSAGE_TAG_NOT_FOUND = "No tag found with the specified ID.";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the address book.";

    private final Id idToEdit;
    private final TagName newName;
    private final TagDesc newDesc;
    private final Color newColor;
    /**
     * @param id of the tag to edit.
     * @param name of the edited tag.
     * @param desc of the edited tag.
     * @param color of the edited tag.
     */
    public EditTagCommand(Id id, TagName name, TagDesc desc, Color color) {
        this.idToEdit = id;
        this.newName = name;
        this.newDesc = desc;
        this.newColor = color;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (newName == null && newDesc == null && newColor == null) {
            throw new CommandException("At least one field to edit must be provided.");
        }

        ObservableList<Tag> tags = model.getFilteredTagList();
        Tag target = tags.stream()
                .filter(tag -> tag.getId().equals(idToEdit))
                .findFirst()
                .orElseThrow(() -> new CommandException(MESSAGE_TAG_NOT_FOUND));

        Tag edited = new Tag(idToEdit,
                newName != null ? newName : target.getName(),
                newDesc != null ? newDesc : target.getDesc(),
                newColor != null ? newColor : target.getColor());

        if (!target.isSameTag(edited) && model.hasTag(edited)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        model.setTag(target, edited);

        return new CommandResult(String.format(MESSAGE_EDIT_SUCCESS, edited));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EditTagCommand)) {
            return false;
        }
        EditTagCommand otherCommand = (EditTagCommand) other;
        return idToEdit.equals(otherCommand.idToEdit)
                && java.util.Objects.equals(newName, otherCommand.newName)
                && java.util.Objects.equals(newDesc, otherCommand.newDesc)
                && java.util.Objects.equals(newColor, otherCommand.newColor);
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName()
                + "{idToEdit=" + idToEdit
                + ", newName=" + newName
                + ", newDesc=" + newDesc
                + ", newColor=" + newColor + "}";
    }
}

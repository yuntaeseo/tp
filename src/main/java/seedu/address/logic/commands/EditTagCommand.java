package seedu.address.logic.commands;


import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

/**
 * Edits the details of an existing tag in the address book.
 */
public class EditTagCommand extends Command {
    public static final String COMMAND_WORD = "edittag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a tag identified by its ID.\n"
            + "Parameters: ID n/NAME [d/DESCRIPTION] [c/COLOR]\n"
            + "Example: " + COMMAND_WORD + " 1 n/BestFriends c/RRGGBB";

    private final int idToEdit;
    private final TagName newName;
    private final TagDesc newDesc;
    private final TagColor newColor;

    /**
     * @param id of the tag to edit.
     * @param name of the edited tag.
     * @param desc of the edited tag.
     * @param color of the edited tag.
     */
    public EditTagCommand(int id, TagName name, TagDesc desc, TagColor color) {
        this.idToEdit = id;
        this.newName = name;
        this.newDesc = desc;
        this.newColor = color;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Tag> tags = model.getFilteredTagList();
        Tag target = tags.stream()
                .filter(tag -> tag.getId() == idToEdit)
                .findFirst()
                .orElseThrow(() -> new CommandException("No tag found with ID: " + idToEdit));

        Tag edited = new Tag(idToEdit,
                             newName != null ? newName : target.getName(),
                             newDesc != null ? newDesc : target.getDesc(),
                             newColor != null ? newColor : target.getColor());

        model.deleteTag(target);
        model.addTag(edited);

        return new CommandResult(String.format("Edited Tag: %s", edited));
    }
}

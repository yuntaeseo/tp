package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label note;

    /**
     * Creates a {@code PersonCode} with the given {@code person}, {@code tagList} and index to display.
     */
    public PersonCard(Person person, ObservableList<Tag> tagList) {
        super(FXML);
        this.person = person;
        id.setText(person.getId() + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTagIds().stream().forEach(id -> {
            FilteredList<Tag> list = tagList.filtered(tag -> tag.getId().equals(id));
            if (list.size() == 0) {
                return;
            }

            Tag tag = list.get(0);
            Label label = new Label(tag.getName().value);
            label.setStyle(String.format("-fx-background-color: #%s; -fx-text-fill: #%s",
                    tag.getColor().getDisplayHex(), tag.getTextColor().value));
            tags.getChildren().add(label);
        });
        note.setText(person.getNote().value);
    }
}

package seedu.address.ui;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.id.Id;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String UNKNOWN_PERSON = "Unknown person";

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
    @FXML
    private VBox relationshipSection;
    @FXML
    private Label relationshipHeading;
    @FXML
    private FlowPane relationshipItems;
    @FXML
    private Label relationshipEmptyText;

    /**
     * Creates a {@code PersonCard} with the given data sources.
     */
    public PersonCard(Person person,
                      ObservableList<Tag> tagList,
                      ObservableList<Relationship> relationshipList,
                      ObservableList<Person> allPersons) {
        super(FXML);
        this.person = person;
        id.setText(person.getId() + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        populateTags(tagList);
        note.setText(person.getNote().value);
        populateRelationships(relationshipList, allPersons);
    }

    private void populateTags(ObservableList<Tag> tagList) {
        tags.getChildren().clear();
        person.getTagIds().forEach(tagId -> {
            FilteredList<Tag> list = tagList.filtered(tag -> tag.getId().equals(tagId));

            if (list.isEmpty()) {
                return;
            }

            Tag tag = list.get(0);
            Label label = new Label(tag.getName().value);
            label.setStyle(String.format("-fx-background-color: #%s; -fx-text-fill: #%s",
                    tag.getColor().getDisplayHex(), tag.getTextColor().value));
            tags.getChildren().add(label);
        });
    }

    private void populateRelationships(ObservableList<Relationship> relationshipList,
                                       ObservableList<Person> allPersons) {
        relationshipItems.getChildren().clear();
        // Returns a list of relationships involving this person, sorted by counterpart id
        List<Relationship> connections = relationshipList.stream()
                .filter(relationship -> involvesPerson(relationship, person.getId()))
                .sorted(Comparator.comparing(rel -> getCounterpartId(rel).value))
                .collect(Collectors.toList());

        boolean hasConnections = !connections.isEmpty();
        relationshipItems.setManaged(hasConnections);
        relationshipItems.setVisible(hasConnections);
        relationshipEmptyText.setManaged(!hasConnections);
        relationshipEmptyText.setVisible(!hasConnections);

        if (!hasConnections) {
            return;
        }

        connections.forEach(relationship -> {
            Id counterpartId = getCounterpartId(relationship);
            String counterpartName = findPersonById(allPersons, counterpartId)
                    .map(other -> other.getName().fullName)
                    .orElse(UNKNOWN_PERSON);

            Label idLabel = new Label(counterpartId.toString());
            idLabel.getStyleClass().add("relationship-entry-id");

            Label nameLabel = new Label(counterpartName);
            nameLabel.getStyleClass().add("relationship-entry-name");

            VBox chip = new VBox(idLabel, nameLabel);
            chip.setSpacing(2);
            chip.getStyleClass().add("relationship-entry");

            String description = relationship.getDescription().value.trim();
            if (!description.isEmpty()) {
                Tooltip.install(chip, new Tooltip(description));
            }

            relationshipItems.getChildren().add(chip);
        });
    }

    /**
     * Returns true if the relationship involves the person with the given id.
     *
     * @param relationship
     * @param id
     * @return boolean
     */
    private boolean involvesPerson(Relationship relationship, Id id) {
        return relationship.getPart1().equals(id) || relationship.getPart2().equals(id);
    }

    /**
     * Returns the counterpart id in the relationship involving this person.
     *
     * @param relationship
     * @return Id
     */
    private Id getCounterpartId(Relationship relationship) {
        return relationship.getPart1().equals(person.getId())
                ? relationship.getPart2()
                : relationship.getPart1();
    }

    /**
     * Finds a person by id from the entire list of persons.
     */
    private Optional<Person> findPersonById(List<Person> persons, Id id) {
        return persons.stream().filter(contact -> contact.getId().equals(id)).findFirst();
    }
}

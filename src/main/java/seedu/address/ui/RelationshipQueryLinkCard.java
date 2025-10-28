package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.id.Id;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;

/**
 * A UI component that displays a connection segment returned by {@code Model#queryLink}.
 */
public class RelationshipQueryLinkCard extends UiPart<Region> {

    private static final String FXML = "RelationshipQueryLinkCard.fxml";
    private static final String UNKNOWN_PERSON = "Unknown person";

    private final Pair<Person, Relationship> entry;
    private final ObservableList<Person> allPersons;

    @FXML
    private Label primaryId;
    @FXML
    private Label primaryName;
    @FXML
    private Label primaryPhone;
    @FXML
    private Label primaryAddress;
    @FXML
    private Label primaryEmail;
    @FXML
    private Label primaryNote;

    @FXML
    private Label counterpartId;
    @FXML
    private Label counterpartName;
    @FXML
    private Label counterpartPhone;
    @FXML
    private Label counterpartAddress;
    @FXML
    private Label counterpartEmail;
    @FXML
    private Label counterpartNote;

    @FXML
    private Label relationshipDescription;

    /**
     * Creates a {@code RelationshipQueryLinkCard}.
     */
    public RelationshipQueryLinkCard(Pair<Person, Relationship> entry, ObservableList<Person> allPersons) {
        super(FXML);
        this.entry = entry;
        this.allPersons = allPersons;

        renderCard();
    }

    private void renderCard() {
        Person primary = entry.getKey();
        Relationship relationship = entry.getValue();

        populatePrimary(primary);
        populateCounterpart(primary, relationship);
        populateDescription(relationship);
    }

    private void populatePrimary(Person person) {
        primaryId.setText(person.getId() + ". ");
        primaryName.setText(person.getName().fullName);
        primaryPhone.setText(person.getPhone().value);
        primaryAddress.setText(person.getAddress().value);
        primaryEmail.setText(person.getEmail().value);
        primaryNote.setText(person.getNote().value);
    }

    private void populateCounterpart(Person primary, Relationship relationship) {
        Id counterpartIdValue = getCounterpartId(relationship, primary.getId());
        Person counterpart = findPerson(counterpartIdValue);

        if (counterpart == null) {
            counterpartId.setText(counterpartIdValue + ". ");
            counterpartName.setText(UNKNOWN_PERSON);
            counterpartPhone.setText("-");
            counterpartAddress.setText("-");
            counterpartEmail.setText("-");
            counterpartNote.setText("-");
            return;
        }

        counterpartId.setText(counterpart.getId() + ". ");
        counterpartName.setText(counterpart.getName().fullName);
        counterpartPhone.setText(counterpart.getPhone().value);
        counterpartAddress.setText(counterpart.getAddress().value);
        counterpartEmail.setText(counterpart.getEmail().value);
        counterpartNote.setText(counterpart.getNote().value);
    }

    private void populateDescription(Relationship relationship) {
        String description = relationship.getDescription().value.trim();
        relationshipDescription.setText(description.isEmpty() ? "No description" : description);
    }

    private Person findPerson(Id personId) {
        return allPersons.stream()
                .filter(person -> person.getId().equals(personId))
                .findFirst()
                .orElse(null);
    }

    private Id getCounterpartId(Relationship relationship, Id primaryIdValue) {
        return relationship.getPart1().equals(primaryIdValue)
                ? relationship.getPart2()
                : relationship.getPart1();
    }
}

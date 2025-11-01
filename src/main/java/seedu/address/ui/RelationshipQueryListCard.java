package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;

/**
 * A UI component that displays a counterpart and the relationship description from
 * {@code Model#getRelationshipQuery()} when running the immediate relationship query.
 */
public class RelationshipQueryListCard extends UiPart<Region> {

    private static final String FXML = "RelationshipQueryListCard.fxml";

    private final Person counterpart;
    private final Relationship relationship;

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
     * Creates a {@code RelationshipQueryListCard} that shows the given {@code entry}.
     */
    public RelationshipQueryListCard(Pair<Person, Relationship> entry) {
        super(FXML);
        this.counterpart = entry.getKey();
        this.relationship = entry.getValue();

        populateCounterparts();
        populateRelationshipDescription();
    }

    /**
     * Populates counterparts with immediate relationship with the person.
     */
    private void populateCounterparts() {
        counterpartId.setText(counterpart.getId() + ". ");
        counterpartName.setText(counterpart.getName().fullName);
        counterpartPhone.setText(counterpart.getPhone().value);
        counterpartAddress.setText(counterpart.getAddress().value);
        counterpartEmail.setText(counterpart.getEmail().value);
        counterpartNote.setText(counterpart.getNote().value);
    }

    /**
     * Populates the relationship description between the person and the counterpart.
     */
    private void populateRelationshipDescription() {
        String description = relationship.getDescription().value.trim();
        relationshipDescription.setText(description.isEmpty() ? "No description" : description);
    }
}

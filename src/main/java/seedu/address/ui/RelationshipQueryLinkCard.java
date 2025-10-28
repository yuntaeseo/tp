package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;

/**
 * A UI component that displays a connection segment returned by {@code Model#queryLink}.
 */
public class RelationshipQueryLinkCard extends UiPart<Region> {

    private static final String FXML = "RelationshipQueryLinkCard.fxml";

    private final Pair<Person, Relationship> entry;

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
    private ImageView linkArrow;
    @FXML
    private Label relationshipDescription;

    /**
     * Creates a {@code RelationshipQueryLinkCard}.
     */
    public RelationshipQueryLinkCard(Pair<Person, Relationship> entry) {
        super(FXML);

        requireNonNull(entry.getKey());

        this.entry = entry;
        renderCard();
    }

    private void renderCard() {
        Person primary = entry.getKey();
        Relationship relationship = entry.getValue();

        populatePrimary(primary);

        if (relationship == null) {
            hideLinkingElements();
            return;
        }

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

    private void populateDescription(Relationship relationship) {
        String description = relationship.getDescription().value.trim();
        relationshipDescription.setText(description.isEmpty() ? "No description" : description);
    }

    private void hideLinkingElements() {
        linkArrow.setVisible(false);
        linkArrow.setManaged(false);

        Node descriptionContainer = relationshipDescription.getParent();
        if (descriptionContainer != null) {
            descriptionContainer.setVisible(false);
            descriptionContainer.setManaged(false);
        }

    }
}

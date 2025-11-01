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

    // NOTE: 'primary person' refers to the person from whom the relationship link starts
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

    /**
     * Renders the card with primary person and relationship details.
     */
    private void renderCard() {
        Person primary = entry.getKey();
        Relationship relationship = entry.getValue();

        populatePrimary(primary);

        if (relationship == null) {
            hideLinkingElements();
            return;
        }

        populateRelationshipDescription(relationship);
    }

    /**
     * Populates the primary person's details.
     */
    private void populatePrimary(Person person) {
        primaryId.setText(person.getId() + ". ");
        primaryName.setText(person.getName().fullName);
        primaryPhone.setText(person.getPhone().value);
        primaryAddress.setText(person.getAddress().value);
        primaryEmail.setText(person.getEmail().value);
        primaryNote.setText(person.getNote().value);
    }

    /**
     * Populates the relationship description between the primary person and the next person in the link.
     */
    private void populateRelationshipDescription(Relationship relationship) {
        String description = relationship.getDescription().value.trim();
        relationshipDescription.setText(description.isEmpty() ? "No description" : description);
    }

    /**
     * Hides the linking elements, which are arrows and description, when it's the end of the link.
     */
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

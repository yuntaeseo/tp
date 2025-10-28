package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;

/**
 * A UI component that displays a Tag in the same format as PersonListPanel.
 */
public class TagCard extends UiPart<Region> {
    private static final String FXML = "TagListCard.fxml";

    public final Tag tag;

    @FXML
    private Label id;

    @FXML
    private Label tagLabel;

    @FXML
    private Label description;

    /**
     * Creates a {@code TagCard} with the given {@code Tag} and index to display.
     */
    public TagCard(Tag tag) {
        super(FXML);
        this.tag = tag;

        /* The number shown will be the Tags ID */
        id.setText(tag.getId() + ". ");

        tagLabel.setText(tag.getName().toString());
        tagLabel.setStyle(String.format("-fx-background-color: #%s; -fx-text-fill: #%s",
                tag.getDisplayColor().value, tag.getTextColor().value));

        description.setText(tag.getDesc().toString());
    }
}

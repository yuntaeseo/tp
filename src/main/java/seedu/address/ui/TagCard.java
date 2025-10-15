package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;

/**
 * A UI component that displays a Tag in the same format as PersonListPanel.
 */
public class TagCard extends UiPart<Region> {
    private static final String FXML = "TagListCard.fxml";

    public final Tag tag;

    @FXML
    private Label id;

    @FXML
    private FlowPane tags;

    public TagCard(Tag tag, int displayedIndex) {
        super(FXML);
        this.tag = tag;

        id.setText(displayedIndex + ". ");

        TagName tagName = (TagName) tag.getName();
        Label label = new Label(tagName.toString());
        label.getStyleClass().add("tag");
        label.getStyleClass().add("cell_small_label");
        label.setStyle(String.format("-fx-background-color: #%s;", tag.getColor().value));
        tags.getChildren().setAll(label);
    }
}

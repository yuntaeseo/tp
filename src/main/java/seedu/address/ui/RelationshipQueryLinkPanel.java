package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;

/**
 * Panel containing the sequence of relationship link results.
 */
public class RelationshipQueryLinkPanel extends UiPart<Region> {

    private static final String FXML = "RelationshipQueryLinkPanel.fxml";

    private final ObservableList<Pair<Person, Relationship>> queryResults;

    @FXML
    private ListView<Pair<Person, Relationship>> relationshipQueryLinkListView;

    /**
     * Creates a {@code RelationshipQueryLinkPanel} with the given data sources.
     */
    public RelationshipQueryLinkPanel(ObservableList<Pair<Person, Relationship>> queryResults,
            ObservableList<Person> allPersons,
            ObservableList<Relationship> relationshipList) {
        super(FXML);

        requireAllNonNull(queryResults, allPersons, relationshipList);
        this.queryResults = queryResults;

        relationshipQueryLinkListView.setItems(queryResults);
        relationshipQueryLinkListView.setCellFactory(listView ->
                new RelationshipQueryLinkViewCell());

        queryResults.addListener((ListChangeListener<Pair<Person, Relationship>>) change ->
                Platform.runLater(relationshipQueryLinkListView::refresh));
        allPersons.addListener((ListChangeListener<Person>) change ->
                Platform.runLater(relationshipQueryLinkListView::refresh));
        relationshipList.addListener((ListChangeListener<Relationship>) change ->
                Platform.runLater(relationshipQueryLinkListView::refresh));
    }

    /**
     * Custom {@code ListCell} that displays {@code queryLink} entries.
     */
    class RelationshipQueryLinkViewCell extends ListCell<Pair<Person, Relationship>> {

        @Override
        protected void updateItem(Pair<Person, Relationship> entry, boolean empty) {
            super.updateItem(entry, empty);

            if (empty || entry == null) {
                setGraphic(null);
                setText(null);
                return;
            }

            setGraphic(new RelationshipQueryLinkCard(entry).getRoot());
        }
    }
}

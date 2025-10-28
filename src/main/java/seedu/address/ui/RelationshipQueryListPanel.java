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
 * Panel containing the list of relationship query results for immediate relationships.
 */
public class RelationshipQueryListPanel extends UiPart<Region> {

    private static final String FXML = "RelationshipQueryListPanel.fxml";

    private final ObservableList<Pair<Person, Relationship>> queryResults;

    @FXML
    private ListView<Pair<Person, Relationship>> relationshipQueryListView;

    /**
     * Creates a {@code RelationshipQueryListPanel} with the given data sources.
     */
    public RelationshipQueryListPanel(ObservableList<Pair<Person, Relationship>> queryResults,
            ObservableList<Person> allPersons,
            ObservableList<Relationship> relationshipList) {
        super(FXML);

        requireAllNonNull(queryResults, allPersons, relationshipList);

        this.queryResults = queryResults;

        relationshipQueryListView.setItems(queryResults);
        relationshipQueryListView.setCellFactory(listView -> new RelationshipQueryListViewCell());

        queryResults.addListener((ListChangeListener<Pair<Person, Relationship>>) change ->
                Platform.runLater(relationshipQueryListView::refresh));
        allPersons.addListener((ListChangeListener<Person>) change ->
                Platform.runLater(relationshipQueryListView::refresh));
        relationshipList.addListener((ListChangeListener<Relationship>) change ->
                Platform.runLater(relationshipQueryListView::refresh));
    }

    /**
     * Custom {@code ListCell} that displays an entry produced by {@code queryImmediateRelationship}.
     */
    class RelationshipQueryListViewCell extends ListCell<Pair<Person, Relationship>> {

        @Override
        protected void updateItem(Pair<Person, Relationship> entry, boolean empty) {
            super.updateItem(entry, empty);

            if (empty || entry == null) {
                setGraphic(null);
                setText(null);
                return;
            }

            setGraphic(new RelationshipQueryListCard(entry).getRoot());
        }
    }
}

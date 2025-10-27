package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given data sources.
     */
    public PersonListPanel(ObservableList<Person> personList,
            ObservableList<Tag> tagList,
            ObservableList<Relationship> relationshipList,
            ObservableList<Person> allPersons) {

        super(FXML);

        requireNonNull(personList);
        requireNonNull(tagList);
        requireNonNull(relationshipList);
        requireNonNull(allPersons);

        personListView.setItems(personList);
        personListView.setCellFactory(listView ->
                new PersonListViewCell(tagList, relationshipList, allPersons));
        // NOTE: refreshes person list when tags or relationships are updated
        tagList.addListener((ListChangeListener<Tag>) change ->
                Platform.runLater(personListView::refresh));
        relationshipList.addListener((ListChangeListener<Relationship>) change ->
                Platform.runLater(personListView::refresh));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        private final ObservableList<Tag> tagList;
        private final ObservableList<Relationship> relationshipList;
        private final ObservableList<Person> allPersons;

        PersonListViewCell(ObservableList<Tag> tagList,
                ObservableList<Relationship> relationshipList,
                ObservableList<Person> allPersons) {
            this.tagList = tagList;
            this.relationshipList = relationshipList;
            this.allPersons = allPersons;
        }

        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
                return;
            }
            // PersonCard is a UI component that displays a Person
            setGraphic(new PersonCard(person, tagList, relationshipList, allPersons).getRoot());
        }
    }

}

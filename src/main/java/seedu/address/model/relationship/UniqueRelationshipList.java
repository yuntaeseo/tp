package seedu.address.model.relationship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.relationship.exceptions.DuplicateRelationshipException;
import seedu.address.model.relationship.exceptions.RelationshipNotFoundException;

/**
 * A list of relationships that enforces uniqueness between its elements and does not allow nulls.
 * A relationship is considered unique by comparing using {@code Relationship#isSameRelationship(Relationship)}.
 * As such, adding and updating of relationships uses Relationship#isSameRelationship(Relationship) for equality so
 * as to ensure that the relationship being added or updated is unique in terms of identity in the
 * UniqueRelationshipList. However, the removal of a relationship uses Relationship#equals(Object) so
 * as to ensure that the relationship with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Relationship#isSameRelationship(Relationship)
 */
public class UniqueRelationshipList implements Iterable<Relationship> {
    private final ObservableList<Relationship> internalList = FXCollections.observableArrayList();
    private final ObservableList<Relationship> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent relationship as the given argument.
     */
    public boolean contains(Relationship toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRelationship);
    }

    /**
     * Adds a relationship to the list.
     * The relationship must not already exist in the list.
     */
    public void add(Relationship toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRelationshipException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the relationship {@code target} in the list with {@code editedRelationship}.
     * {@code target} must exist in the list.
     * The relationship identity of {@code editedRelationship} must not be the same as another existing
     * relationship in the list.
     */
    public void setRelationship(Relationship target, Relationship editedRelationship) {
        requireAllNonNull(target, editedRelationship);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RelationshipNotFoundException();
        }

        if (!target.isSameRelationship(editedRelationship) && contains(editedRelationship)) {
            throw new DuplicateRelationshipException();
        }

        internalList.set(index, editedRelationship);
    }

    /**
     * Removes the equivalent relationship from the list.
     * The relationship must exist in the list.
     */
    public void remove(Relationship toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RelationshipNotFoundException();
        }
    }

    public void setRelationships(UniqueRelationshipList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code relationships}.
     * {@code relationships} must not contain duplicate relationship.
     */
    public void setRelationships(List<Relationship> relationships) {
        requireAllNonNull(relationships);
        if (!relationshipsAreUnique(relationships)) {
            throw new DuplicateRelationshipException();
        }
        internalList.setAll(relationships);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}
     */
    public ObservableList<Relationship> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Relationship> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueRelationshipList)) {
            return false;
        }

        UniqueRelationshipList otherList = (UniqueRelationshipList) other;
        return internalList.equals(otherList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code relationships} contains only unique relationships.
     */
    private boolean relationshipsAreUnique(List<Relationship> relationships) {
        for (int i = 0; i < relationships.size() - 1; i++) {
            for (int j = i + 1; j < relationships.size(); j++) {
                if (relationships.get(i).isSameRelationship(relationships.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

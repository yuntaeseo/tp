package seedu.address.model.relationship.exceptions;

/**
 * Signals that the operation will result in duplicate Relationship
 * (Relationship are considered duplicates if they have the same 'to' and 'from' Person).
 */
public class DuplicateRelationshipException extends RuntimeException {
    public DuplicateRelationshipException() {
        super("Operation would result in duplicate relationship");
    }
}

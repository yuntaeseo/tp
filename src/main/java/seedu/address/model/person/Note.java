package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents the note of a Person in the address book.
 */
public class Note {
    public final String value;

    /**
     * Constructs a {@code Note}.
     *
     * @param value the note string.
     */
    public Note(String value) {
        requireNonNull(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return value.equals(otherNote.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

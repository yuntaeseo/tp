package seedu.address.model.id;

/**
 * Class to manage creation of auto-increment id, ensuring that unique id is always created.
 * Any class that contains an {@code Id} as a field should use exactly one {@code IdManager}.
 */
public class IdManager {
    /** The largest id that the class has encountered. */
    private Integer largest;

    /**
     * Constructs an {@code IdManager}.
     *
     * The first {@code Id} created will contain the value of 1.
     */
    public IdManager() {
        largest = 0;
    }

    /**
     * Create a new {@code Id}.
     */
    public Id getNewId() {
        return new Id(++largest);
    }

    /**
     * Set the largest encountered id, if {@code id} has a larger id.
     */
    public void setLargest(Id id) {
        largest = Math.max(largest, id.value);
    }
}

package seedu.address.model.id;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a positive 32-bit integer that is used as identification number
 * for objects of other classes.
 */
public class Id {
    /**
     * This is an invalid ID containing id value -1 that is mainly used for temporary entities.
     * Ensure no entity with {@code INVALID_ID} is added to the system.
     */
    public static final Id INVALID_ID = new Id(-1, true);

    public static final String MESSAGE_CONSTRAINTS =
            String.format("Id must be a positive integer not larger than %d", Integer.MAX_VALUE);

    public final Integer value;

    /**
     * Constructs a {@code Id}
     *
     * @param id A valid id.
     */
    public Id(Integer id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Constructs a {@code Id}
     *
     * @param id A valid id.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(id);
    }

    /**
     * Used to create an ID without validation, mainly for utility IDs with invalid values.
     * @param id any value.
     * @param skipValidation boolean to indicate whether to skip validation. Typically true if using this constructor.
     */
    private Id(Integer id, boolean skipValidation) {
        requireNonNull(id);
        if (!skipValidation) {
            checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        }
        this.value = id;
    }

    /**
     * Returns true if a given Integer is a valid Id.
     * Note that this does not check whether an object with that Id really exists.
     */
    public static boolean isValidId(Integer test) {
        requireNonNull(test);
        return 0 < test && test <= Integer.MAX_VALUE;
    }

    /**
     * Returns true if a given String is a valid Id.
     * Note that this does not check whether an object with that Id really exists.
     */
    public static boolean isValidId(String test) {
        requireNonNull(test);
        Integer testInteger;
        try {
            testInteger = Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return isValidId(testInteger);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Id)) {
            return false;
        }

        Id otherId = (Id) other;
        return this.value.equals(otherId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

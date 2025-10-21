package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.relationship.Relationship;

/**
 * A utility class containing a list of {@code Relationship} objects to be used in tests.
 */
public class TypicalRelationships {
    public static final Relationship ONE =
        new RelationshipBuilder().withFrom(1).withTo(2).withDesc("friends").build();
    public static final Relationship TWO =
        new RelationshipBuilder().withFrom(2).withTo(3).withDesc("not friends").build();
    public static final Relationship THREE =
        new RelationshipBuilder().withFrom(3).withTo(4).withDesc("bestie").build();

    private TypicalRelationships() {} // prevents instantiation

    public static List<Relationship> getTypicalRelationships() {
        return new ArrayList<>(Arrays.asList(ONE, TWO, THREE));
    }
}

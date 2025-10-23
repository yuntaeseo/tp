package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.relationship.Relationship;

/**
 * A utility class containing a list of {@code Relationship} objects to be used in tests.
 */
public class TypicalRelationships {
    public static final Relationship ONE_TWO =
        new RelationshipBuilder().withPart1(1).withPart2(2).withDesc("friends").build();
    public static final Relationship TWO_THREE =
        new RelationshipBuilder().withPart1(2).withPart2(3).withDesc("not friends").build();
    public static final Relationship THREE_FOUR =
        new RelationshipBuilder().withPart1(3).withPart2(4).withDesc("bestie").build();
    public static final Relationship ONE_THREE =
        new RelationshipBuilder().withPart1(1).withPart2(3).withDesc("enemies").build();
    public static final Relationship ONE_FOUR =
        new RelationshipBuilder().withPart1(1).withPart2(4).withDesc("family").build();

    private TypicalRelationships() {} // prevents instantiation

    public static List<Relationship> getTypicalRelationships() {
        return new ArrayList<>(Arrays.asList(ONE_TWO, TWO_THREE, THREE_FOUR, ONE_THREE, ONE_FOUR));
    }
}

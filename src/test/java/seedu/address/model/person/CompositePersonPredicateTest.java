package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_NAME_STUB;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_PHONE_STUB;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_TAGS_STUB;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CompositePersonPredicateTest {
    @Test
    public void test_andAcrossFields_allMustMatch() {
        FieldContainsKeywordsPredicate namePred = new FieldContainsKeywordsPredicate(GET_NAME_STUB, List.of("Alice"));
        FieldContainsKeywordsPredicate phonePred = new FieldContainsKeywordsPredicate(GET_PHONE_STUB, List.of("9123"));
        CompositePersonPredicate composite = new CompositePersonPredicate(List.of(namePred, phonePred));

        Person person1 = new PersonBuilder().withName("Alice Tan").withPhone("91234567").build();
        Person person2 = new PersonBuilder().withName("Alice Tan").withPhone("77777777").build();

        assertTrue(composite.test(person1)); // both match
        assertFalse(composite.test(person2)); // phone fails
    }

    @Test
    public void test_matchTags_matchExactString() {
        FieldContainsKeywordsPredicate tagPred = new FieldContainsKeywordsPredicate(GET_TAGS_STUB, List.of("1"), true);
        CompositePersonPredicate composite = new CompositePersonPredicate(List.of(tagPred));

        Person person1 = new PersonBuilder().withName("Alice Tan").withPhone("91234567").withTags(1, 2).build();
        Person person2 = new PersonBuilder().withName("Alice Tan").withPhone("12345678").withTags(2, 10).build();

        assertTrue(composite.test(person1)); // match tags
        assertFalse(composite.test(person2)); // does not exact match tags
    }

    @Test
    public void equals_true() {
        FieldContainsKeywordsPredicate p1 = new FieldContainsKeywordsPredicate(GET_NAME_STUB, List.of("Alice"));
        FieldContainsKeywordsPredicate p2 = new FieldContainsKeywordsPredicate(GET_PHONE_STUB, List.of("9123"));
        CompositePersonPredicate a = new CompositePersonPredicate(List.of(p1, p2));

        CompositePersonPredicate b = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_NAME_STUB, List.of("Alice")),
                new FieldContainsKeywordsPredicate(GET_PHONE_STUB, List.of("9123"))));

        assertEquals(a, a); // same object -> true
        assertEquals(a, b); // different objects of same parts and same order -> true
    }

    @Test
    public void equals_false() {
        FieldContainsKeywordsPredicate p1 = new FieldContainsKeywordsPredicate(GET_NAME_STUB, List.of("Alice"));
        FieldContainsKeywordsPredicate p2 = new FieldContainsKeywordsPredicate(GET_PHONE_STUB, List.of("9123"));

        CompositePersonPredicate a = new CompositePersonPredicate(List.of(p1, p2));
        CompositePersonPredicate b = new CompositePersonPredicate(List.of(p2, p1));

        CompositePersonPredicate c = new CompositePersonPredicate(List.of(p1));
        CompositePersonPredicate d = new CompositePersonPredicate(List.of(p2));

        assertNotEquals(a, b); // objects of same parts different order
        assertNotEquals(c, d); // contains different predicates
        assertNotEquals(a, new Object()); // different instances -> false
        assertNotEquals(a, null); // null -> false;
    }
}

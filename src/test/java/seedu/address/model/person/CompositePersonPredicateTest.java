package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_NAME;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_PHONE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CompositePersonPredicateTest {
    @Test
    void test_andAcrossFields_allMustMatch() {
        var namePred = new FieldContainsKeywordsPredicate(GET_NAME, List.of("Alice"));
        var phonePred = new FieldContainsKeywordsPredicate(GET_PHONE, List.of("9123"));
        var composite = new CompositePersonPredicate(List.of(namePred, phonePred));

        var person1 = new PersonBuilder().withName("Alice Tan").withPhone("91234567").build();
        var person2 = new PersonBuilder().withName("Alice Tan").withPhone("77777777").build();

        assertTrue(composite.test(person1));  // both match
        assertFalse(composite.test(person2)); // phone fails
    }

    @Test
    void equals_samePartsSameOrder_true() {
        var p1 = new FieldContainsKeywordsPredicate(GET_NAME, List.of("Alice"));
        var p2 = new FieldContainsKeywordsPredicate(GET_PHONE, List.of("9123"));
        var a = new CompositePersonPredicate(List.of(p1, p2));
        var b = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_NAME, List.of("Alice")),
                new FieldContainsKeywordsPredicate(GET_PHONE, List.of("9123"))));
        assertEquals(a, b);
    }

    @Test
    void equals_samePartsDifferentOrder_ifList_thenFalse() {
        var p1 = new FieldContainsKeywordsPredicate(GET_NAME, List.of("Alice"));
        var p2 = new FieldContainsKeywordsPredicate(GET_PHONE, List.of("9123"));
        var a = new CompositePersonPredicate(List.of(p1, p2));
        var b = new CompositePersonPredicate(List.of(p2, p1));
        assertNotEquals(a, b);
    }
}

package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_ADDRESS;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_EMAIL;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_NAME;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_PHONE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FieldContainsKeywordsPredicateTest {
    @Test
    void test_nameWholeWordMatch_true() {
        var p = new FieldContainsKeywordsPredicate(GET_NAME, List.of("Alice", "Zoe"));
        assertTrue(p.test(new PersonBuilder().withName("Alice Tan").build()));
    }

    @Test
    void test_nameCaseInsensitive_true() {
        var p = new FieldContainsKeywordsPredicate(GET_NAME, List.of("aLiCe"));
        assertTrue(p.test(new PersonBuilder().withName("ALICE TAN").build()));
    }

    @Test
    void test_addressNoMatch_false() {
        var p = new FieldContainsKeywordsPredicate(GET_ADDRESS, List.of("Clementi"));
        assertFalse(p.test(new PersonBuilder().withName("Little India").build()));
    }

    @Test
    void equals_sameExtractorSameKeywords_true() {
        var a = new FieldContainsKeywordsPredicate(GET_PHONE, List.of("9123"));
        var b = new FieldContainsKeywordsPredicate(GET_PHONE, List.of("9123"));
        assertEquals(a, b);
    }

    @Test
    void equals_differentExtractor_false() {
        var a = new FieldContainsKeywordsPredicate(GET_PHONE, List.of("9123"));
        var b = new FieldContainsKeywordsPredicate(GET_NAME, List.of("9123"));
        assertNotEquals(a, b);
    }

    @Test
    void equals_differentKeywords_false() {
        var a = new FieldContainsKeywordsPredicate(GET_EMAIL, List.of("ex.com"));
        var b = new FieldContainsKeywordsPredicate(GET_EMAIL, List.of("mail.com"));
        assertNotEquals(a, b);
    }
}

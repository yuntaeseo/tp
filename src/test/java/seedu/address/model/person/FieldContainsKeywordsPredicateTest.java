package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.PersonFieldExtractor.GET_ADDRESS;
import static seedu.address.logic.parser.PersonFieldExtractor.GET_EMAIL;
import static seedu.address.logic.parser.PersonFieldExtractor.GET_NAME;
import static seedu.address.logic.parser.PersonFieldExtractor.GET_PHONE;
import static seedu.address.logic.parser.PersonFieldExtractor.GET_TAGS;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_ADDRESS_STUB;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_EMAIL_STUB;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_NAME_STUB;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_PHONE_STUB;
import static seedu.address.testutil.PersonFieldExtractorsTestUtil.GET_TAGS_STUB;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FieldContainsKeywordsPredicateTest {
    @Test
    public void test_nameWholeWordMatch_true() {
        FieldContainsKeywordsPredicate p = new FieldContainsKeywordsPredicate(GET_NAME_STUB, List.of("Alice", "Zoe"));
        assertTrue(p.test(new PersonBuilder().withName("Alice Tan").build()));
    }

    @Test
    void test_nameCaseInsensitive_true() {
        FieldContainsKeywordsPredicate p = new FieldContainsKeywordsPredicate(GET_NAME_STUB, List.of("aLiCe"));
        assertTrue(p.test(new PersonBuilder().withName("ALICE TAN").build()));
    }

    @Test
    public void test_addressNoMatch_false() {
        FieldContainsKeywordsPredicate p = new FieldContainsKeywordsPredicate(GET_ADDRESS_STUB, List.of("Clementi"));
        assertFalse(p.test(new PersonBuilder().withAddress("Little India").build()));
    }

    @Test
    public void test_tagMatch_true() {
        FieldContainsKeywordsPredicate p = new FieldContainsKeywordsPredicate(GET_TAGS_STUB, List.of("1", "2"), true);
        assertTrue(p.test(new PersonBuilder().withTags(1, 10, 12).build()));
    }

    @Test
    public void test_tagNoMatch_false() {
        FieldContainsKeywordsPredicate p = new FieldContainsKeywordsPredicate(GET_TAGS_STUB, List.of("1", "2"), true);
        assertFalse(p.test(new PersonBuilder().withTags(10, 20, 12).build()));
    }

    @Test
    public void equals_sameExtractorSameKeywords_true() {
        FieldContainsKeywordsPredicate a = new FieldContainsKeywordsPredicate(GET_PHONE_STUB, List.of("9123"));
        FieldContainsKeywordsPredicate b = new FieldContainsKeywordsPredicate(GET_PHONE_STUB, List.of("9123"));
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertEquals(a, a); // same object -> true
    }

    @Test
    public void equals_differentExtractor_false() {
        FieldContainsKeywordsPredicate a = new FieldContainsKeywordsPredicate(GET_TAGS_STUB, List.of("9123"));
        FieldContainsKeywordsPredicate b = new FieldContainsKeywordsPredicate(GET_NAME_STUB, List.of("9123"));
        assertNotEquals(a, b);
        assertNotEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, new Object()); // different instance -> false
        assertNotEquals(a, null); // null -> false
    }

    @Test
    public void equals_differentKeywords_false() {
        FieldContainsKeywordsPredicate a = new FieldContainsKeywordsPredicate(GET_EMAIL_STUB, List.of("ex.com"));
        FieldContainsKeywordsPredicate b = new FieldContainsKeywordsPredicate(GET_EMAIL_STUB, List.of("mail.com"));
        assertNotEquals(a, b);
    }

    @Test
    public void toStringMethod_validValues_correctString() {
        List<String> nameList = List.of("Alice", "Zoe");
        List<String> phoneList = List.of("9123");
        List<String> emailList = List.of("mail.com");
        List<String> addressList = List.of("Clementi", "little india", "dover", "CHANGI");
        List<String> tagList = List.of("1", "2", "Some tag");

        FieldContainsKeywordsPredicate n = new FieldContainsKeywordsPredicate(GET_NAME, nameList);
        FieldContainsKeywordsPredicate p = new FieldContainsKeywordsPredicate(GET_PHONE, phoneList);
        FieldContainsKeywordsPredicate e = new FieldContainsKeywordsPredicate(GET_EMAIL, emailList);
        FieldContainsKeywordsPredicate a = new FieldContainsKeywordsPredicate(GET_ADDRESS, addressList);
        FieldContainsKeywordsPredicate t = new FieldContainsKeywordsPredicate(GET_TAGS, tagList);


        String nameExpected = FieldContainsKeywordsPredicate.class.getCanonicalName() +
                "{nameKeywords=" + nameList + "}";
        String phoneExpected = FieldContainsKeywordsPredicate.class.getCanonicalName() +
                "{phoneKeywords=" + phoneList + "}";
        String emailExpected = FieldContainsKeywordsPredicate.class.getCanonicalName() +
                "{emailKeywords=" + emailList + "}";
        String addressExpected = FieldContainsKeywordsPredicate.class.getCanonicalName() +
                "{addressKeywords=" + addressList + "}";
        String tagsExpected = FieldContainsKeywordsPredicate.class.getCanonicalName() +
                "{tagsKeywords=" + tagList + "}";

        assertEquals(nameExpected, n.toString());
        assertEquals(phoneExpected, p.toString());
        assertEquals(emailExpected, e.toString());
        assertEquals(addressExpected, a.toString());
        assertEquals(tagsExpected, t.toString());
    }

    @Test
    public void toStringMethod_unknownPredicate_correctUnkownString() {
        Function<Person, List<String>> unknownFunction = p -> List.of("");
        List<String> unknownList = Collections.emptyList();
        FieldContainsKeywordsPredicate unknown = new FieldContainsKeywordsPredicate(unknownFunction, unknownList);

        String unknownExpected = FieldContainsKeywordsPredicate.class.getCanonicalName() +
                "{unknownKeywords=" + unknownList + "}";

        assertEquals(unknownExpected, unknown.toString());
    }
}

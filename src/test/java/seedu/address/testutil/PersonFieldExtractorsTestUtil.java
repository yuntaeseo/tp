package seedu.address.testutil;

import java.util.List;
import java.util.function.Function;

import seedu.address.model.id.Id;
import seedu.address.model.person.Person;

/**
 * FOR TESTING PURPOSES
 * Provides reusable extractor functions that retrieve specific {@link Person} attributes
 * as lists of strings, for use in field-based predicate construction.
 * <p>
 * Each extractor converts a {@link Person} attribute into a list of string representations
 * to allow consistent matching logic across single-value fields (e.g. name, phone)
 * and multi-value fields (e.g. tags).
 * </p>
 *
 * <p>
 * These extractors are typically used by {@link seedu.address.model.person.FieldContainsKeywordsPredicate}
 * and {@link seedu.address.logic.parser.FindCommandParser} when building filter conditions
 * for the {@code find} command.
 * </p>
 */
public class PersonFieldExtractorsTestUtil {
    public static final Function<Person, List<String>> GET_NAME_STUB = p -> List.of(p.getName().toString());
    public static final Function<Person, List<String>> GET_PHONE_STUB = p -> List.of(p.getPhone().toString());
    public static final Function<Person, List<String>> GET_EMAIL_STUB = p -> List.of(p.getEmail().toString());
    public static final Function<Person, List<String>> GET_ADDRESS_STUB = p -> List.of(p.getAddress().toString());
    public static final Function<Person, List<String>> GET_TAGS_STUB = p ->
            p.getTagIds().stream().map(Id::toString).toList();
}

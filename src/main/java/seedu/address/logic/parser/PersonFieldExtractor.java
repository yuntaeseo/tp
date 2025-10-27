package seedu.address.logic.parser;

import java.util.List;
import java.util.function.Function;

import seedu.address.model.id.Id;
import seedu.address.model.person.Person;

/**
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
public final class PersonFieldExtractor {
    /** Extracts the person's name, phone, email, or address as a single-element list. */
    public static final Function<Person, List<String>> GET_NAME = p -> List.of(p.getName().toString());
    public static final Function<Person, List<String>> GET_PHONE = p -> List.of(p.getPhone().toString());
    public static final Function<Person, List<String>> GET_EMAIL = p -> List.of(p.getEmail().toString());
    public static final Function<Person, List<String>> GET_ADDRESS = p -> List.of(p.getAddress().toString());

    /**
     * Extracts all tag identifiers associated with the person as a list of strings.
     * Each tag ID is converted to its string representation using {@link Id#toString()}.
     */
    public static final Function<Person, List<String>> GET_TAGS = p ->
            p.getTagIds().stream().map(Id::toString).toList();

    // Private constructor to prevent instantiation.
    private PersonFieldExtractor() {}
}

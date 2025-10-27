package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.PersonFieldExtractor;

/**
 * Tests whether a specified field of a {@link Person} contains any of the provided keywords.
 * <p>
 * Each predicate instance corresponds to one field type (e.g. name, phone, email, address, or tag).
 * For tag-based matching, the predicate performs word-based matching using
 * {@link seedu.address.commons.util.StringUtil#containsWordIgnoreCase(String, String)}.
 * For all other fields, substring matching is used instead.
 * </p>
 */
public class FieldContainsKeywordsPredicate implements Predicate<Person> {
    private final Function<Person, List<String>> extractor;
    private final List<String> keywords;
    private boolean isTag = false;

    /**
     * Creates a predicate that checks if any of the given keywords
     * are contained within the specified field of a person.
     *
     * @param extractor Function extracting the field value(s) from a {@link Person}.
     * @param keywords  List of keywords to search for.
     */
    public FieldContainsKeywordsPredicate(Function<Person, List<String>> extractor, List<String> keywords) {
        this.extractor = extractor;
        this.keywords = keywords;
    }

    /**
     * Creates a predicate that checks if any of the given keywords
     * are contained within the specified field of a person, optionally treating it as a tag field.
     *
     * @param extractor Function extracting the field value(s) from a {@link Person}.
     * @param keywords  List of keywords to search for.
     * @param isTag     If true, enables word-based tag matching instead of substring matching.
     */
    public FieldContainsKeywordsPredicate(Function<Person, List<String>> extractor,
                                          List<String> keywords,
                                          boolean isTag) {
        this.extractor = extractor;
        this.keywords = keywords;
        this.isTag = isTag;
    }

    /**
     * Tests whether the extracted field values of the given person
     * contain any of the keywords specified in this predicate.
     *
     * @param person Person to test.
     * @return True if any keyword matches the field; false otherwise.
     */
    @Override
    public boolean test(Person person) {
        List<String> personFieldList = extractor.apply(person);
        BiPredicate<String, String> stringChecker = isTag
                ? StringUtil::containsWordIgnoreCase
                : StringUtil::containsSubstringIgnoreCase;

        return keywords.stream().anyMatch(keyword ->
                        personFieldList.stream().anyMatch(value ->
                                stringChecker.test(value, keyword))); // substring matching
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FieldContainsKeywordsPredicate otherPredicate)) {
            return false;
        }

        return keywords.equals(otherPredicate.keywords) && extractor.equals(otherPredicate.extractor);
    }

    @Override
    public String toString() {
        String field;
        if (this.extractor.equals(PersonFieldExtractor.GET_NAME)) {
            field = "name";
        } else if (this.extractor.equals(PersonFieldExtractor.GET_PHONE)) {
            field = "phone";
        } else if (this.extractor.equals(PersonFieldExtractor.GET_EMAIL)) {
            field = "email";
        } else if (this.extractor.equals(PersonFieldExtractor.GET_ADDRESS)) {
            field = "address";
        } else if (this.extractor.equals(PersonFieldExtractor.GET_TAGS)) {
            field = "tags";
        } else {
            field = "unknown";
        }
        return new ToStringBuilder(this).add(field + "Keywords", keywords.toString()).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(extractor, keywords);
    }
}

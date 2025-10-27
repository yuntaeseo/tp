package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.PersonFieldExtractor;

public class FieldContainsKeywordsPredicate implements Predicate<Person> {
    private final Function<Person, List<String>> extractor;
    private final List<String> keywords;
    private boolean isTag = false;

    public FieldContainsKeywordsPredicate(Function<Person, List<String>> extractor, List<String> keywords) {
        this.extractor = extractor;
        this.keywords = keywords;
    }

    public FieldContainsKeywordsPredicate(Function<Person, List<String>> extractor,
                                          List<String> keywords,
                                          boolean isTag) {
        this.extractor = extractor;
        this.keywords = keywords;
        this.isTag = isTag;
    }

    @Override
    public boolean test(Person person) {
        List<String> personFieldList = extractor.apply(person);
        BiPredicate<String, String> stringChecker = isTag
                ? StringUtil::containsWordIgnoreCase
                : StringUtil::containsSubstringIgnoreCase;

        return keywords.stream().anyMatch(keyword ->
                        personFieldList.stream().anyMatch(value ->
                                value != null && stringChecker.test(value, keyword))); // substring matching
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

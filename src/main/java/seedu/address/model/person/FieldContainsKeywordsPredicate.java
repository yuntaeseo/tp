package seedu.address.model.person;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class FieldContainsKeywordsPredicate implements Predicate<Person> {
    private final Function<Person, String> extractor;
    private final List<String> keywords;

    public FieldContainsKeywordsPredicate(Function<Person, String> extractor, List<String> keywords) {
        this.extractor = extractor;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String personField = extractor.apply(person);
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsSubstringIgnoreCase(personField, keyword)); // substring matching
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FieldContainsKeywordsPredicate)) {
            return false;
        }

        FieldContainsKeywordsPredicate otherPredicate = (FieldContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords) && extractor.equals(otherPredicate.extractor);
    }
}

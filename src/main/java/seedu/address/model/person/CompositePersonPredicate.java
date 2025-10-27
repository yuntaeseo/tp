package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * A composite predicate that combines multiple {@link FieldContainsKeywordsPredicate}s
 * into a single condition. A {@link Person} passes the test only if it satisfies
 * all contained field predicates.
 * <p>
 * This allows multi-field "AND" filtering such as:
 * <pre>
 * find n/Alice e/gmail
 * </pre>
 * which matches only persons whose name contains "Alice" and email contains "gmail".
 * </p>
 */
public class CompositePersonPredicate implements Predicate<Person> {
    private final List<FieldContainsKeywordsPredicate> predicates;

    /**
     * Constructs a composite predicate consisting of the given field predicates.
     *
     * @param predicates List of {@link FieldContainsKeywordsPredicate}s to combine.
     */
    public CompositePersonPredicate(List<FieldContainsKeywordsPredicate> predicates) {
        this.predicates = predicates;
    }

    /**
     * Tests whether a given person satisfies all predicates in this composite.
     *
     * @param person Person to test.
     * @return True if all predicates evaluate to true; false otherwise.
     */
    @Override
    public boolean test(Person person) {
        return predicates.stream().allMatch(predicate -> predicate.test(person));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompositePersonPredicate)) {
            return false;
        }

        CompositePersonPredicate otherCompositePersonPredicate = (CompositePersonPredicate) other;
        return predicates.equals(otherCompositePersonPredicate.predicates);
    }

    @Override
    public int hashCode() {
        return predicates.hashCode();
    }
}

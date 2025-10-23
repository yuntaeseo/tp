package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class CompositePersonPredicate implements Predicate<Person> {
    private final List<FieldContainsKeywordsPredicate> predicates;

    public CompositePersonPredicate(List<FieldContainsKeywordsPredicate> predicates) {
        this.predicates = predicates;
    }

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

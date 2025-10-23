package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class CompositePersonPredicate implements Predicate<Person> {
    private final List<Predicate<Person>> predicates;

    public CompositePersonPredicate(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean test(Person person) {
        return predicates.stream().allMatch(pred -> pred.test(person));
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
}

package seedu.address.logic.parser;

import java.util.List;
import java.util.function.Function;

import seedu.address.model.id.Id;
import seedu.address.model.person.Person;

public final class PersonFieldExtractor {
    public static final Function<Person, List<String>> GET_NAME = p -> List.of(p.getName().toString());
    public static final Function<Person, List<String>> GET_PHONE = p -> List.of(p.getPhone().toString());
    public static final Function<Person, List<String>> GET_EMAIL = p -> List.of(p.getEmail().toString());
    public static final Function<Person, List<String>> GET_ADDRESS = p -> List.of(p.getAddress().toString());
    public static final Function<Person, List<String>> GET_TAGS = p ->
            p.getTags().stream().map(Id::toString).toList();
}

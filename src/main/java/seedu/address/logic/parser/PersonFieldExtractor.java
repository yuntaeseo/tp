package seedu.address.logic.parser;

import java.util.function.Function;

import seedu.address.model.person.Person;

public final class PersonFieldExtractor {
    public static final Function<Person, String> GET_NAME = p -> p.getName().toString();
    public static final Function<Person, String> GET_PHONE = p -> p.getPhone().toString();
    public static final Function<Person, String> GET_EMAIL = p -> p.getEmail().toString();
    public static final Function<Person, String> GET_ADDRESS = p -> p.getAddress().toString();
}

package seedu.address.testutil;

import java.util.function.Function;

import seedu.address.model.person.Person;

public class PersonFieldExtractorsTestUtil {
    public static final Function<Person, String> GET_NAME_STUB = p -> p.getName().toString();
    public static final Function<Person, String> GET_PHONE_STUB = p -> p.getPhone().toString();
    public static final Function<Person, String> GET_EMAIL_STUB = p -> p.getEmail().toString();
    public static final Function<Person, String> GET_ADDRESS_STUB = p -> p.getAddress().toString();
}

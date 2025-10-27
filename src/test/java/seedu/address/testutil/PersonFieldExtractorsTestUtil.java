package seedu.address.testutil;

import java.util.List;
import java.util.function.Function;

import seedu.address.model.id.Id;
import seedu.address.model.person.Person;

public class PersonFieldExtractorsTestUtil {
    public static final Function<Person, List<String>> GET_NAME_STUB = p -> List.of(p.getName().toString());
    public static final Function<Person, List<String>> GET_PHONE_STUB = p -> List.of(p.getPhone().toString());
    public static final Function<Person, List<String>> GET_EMAIL_STUB = p -> List.of(p.getEmail().toString());
    public static final Function<Person, List<String>> GET_ADDRESS_STUB = p -> List.of(p.getAddress().toString());
    public static final Function<Person, List<String>> GET_TAGS_STUB = p ->
            p.getTags().stream().map(Id::toString).toList();
}

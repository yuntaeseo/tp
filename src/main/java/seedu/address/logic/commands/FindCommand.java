package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CompositePersonPredicate;

/**
 * Finds and lists all persons in the address book whose specified fields contain any of the given keywords.
 * Matching is case-insensitive and supports substring or word-based matching depending on the field.
 * <p>
 * The command can match across multiple fields (e.g. name, phone, email, address, tags).
 * When multiple field filters are provided, only persons satisfying all field filters are returned.
 * </p>
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose attributes contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [n/NAME_KEYWORDS]… [p/PHONE_KEYWORDS]… "
            + "[e/EMAIL_KEYWORDS]… [a/ADDRESS_KEYWORDS]… [t/TAG_ID]…\n"
            + "Example: " + COMMAND_WORD + " n/Ali e/gmail a/Clementi a/Bishan t/2 t/5 t/7\n"
            + "Will find ALL contacts with names containing 'Ali', emails containing 'gmail', addresses containing "
            + "EITHER 'Clementi' or 'Bishan', and have EITHER tag 2, 5, or 7.";

    private final CompositePersonPredicate predicates;

    /**
     * Creates a {@code FindCommand} with a composite predicate that combines all field-based filters.
     *
     * @param allPredicates A {@link CompositePersonPredicate} representing all field predicates to apply.
     */
    public FindCommand(CompositePersonPredicate allPredicates) {
        this.predicates = allPredicates;
    }

    /**
     * Executes the find command and updates the model’s filtered person list
     * to include only those persons who satisfy all provided predicates.
     *
     * @param model The model containing the address book data.
     * @return A {@link CommandResult} summarizing the number of persons listed.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicates);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicates.equals(otherFindCommand.predicates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicates)
                .toString();
    }

    @Override
    public int hashCode() {
        return predicates.hashCode();
    }
}

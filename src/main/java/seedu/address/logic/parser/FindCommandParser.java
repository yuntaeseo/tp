package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.StringUtil.toNonEmptyKeywords;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompositePersonPredicate;
import seedu.address.model.person.FieldContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new {@link FindCommand} object.
 * <p>
 * The parser tokenizes input by field prefixes (name, phone, email, address, tag),
 * extracts non-empty keyword lists for each field, and constructs
 * a {@link FieldContainsKeywordsPredicate} for every field provided by the user.
 * </p>
 * <p>
 * Multiple field predicates are combined into a single {@link CompositePersonPredicate}
 * using logical AND — a person must match all field conditions to be returned.
 * </p>
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String args} into a {@link FindCommand}.
     *
     * @param args The full user input string following the "find" command word.
     * @return A {@link FindCommand} ready for execution.
     * @throws ParseException If the input format is invalid or no valid fields are provided.
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.getPreamble().trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<FieldContainsKeywordsPredicate> predicates = new ArrayList<>();

        List<String> nameKeywords = toNonEmptyKeywords(argMultimap.getAllValues(PREFIX_NAME));
        List<String> phoneKeywords = toNonEmptyKeywords(argMultimap.getAllValues(PREFIX_PHONE));
        List<String> emailKeywords = toNonEmptyKeywords(argMultimap.getAllValues(PREFIX_EMAIL));
        List<String> addressKeywords = toNonEmptyKeywords(argMultimap.getAllValues(PREFIX_ADDRESS));
        List<String> tagKeywords = toNonEmptyKeywords(argMultimap.getAllValues(PREFIX_TAG));

        if (!nameKeywords.isEmpty()) {
            predicates.add(new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_NAME, nameKeywords));
        }
        if (!phoneKeywords.isEmpty()) {
            predicates.add(new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_PHONE, phoneKeywords));
        }
        if (!emailKeywords.isEmpty()) {
            predicates.add(new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_EMAIL, emailKeywords));
        }
        if (!addressKeywords.isEmpty()) {
            predicates.add(new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_ADDRESS, addressKeywords));
        }
        if (!tagKeywords.isEmpty()) {
            predicates.add(new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_TAGS, tagKeywords, true));
        }
        if (predicates.isEmpty()) {
            throw new ParseException("At least one field to find must be provided. "
                    + String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(new CompositePersonPredicate(predicates));
    }
}

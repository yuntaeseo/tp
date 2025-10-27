package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.StringUtil.toNonEmptyKeywords;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompositePersonPredicate;
import seedu.address.model.person.FieldContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

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
            throw new ParseException("At least one field to find must be provided. " +
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(new CompositePersonPredicate(predicates));
    }
}

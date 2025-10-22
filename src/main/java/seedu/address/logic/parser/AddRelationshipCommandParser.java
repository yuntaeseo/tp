package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_2;

import seedu.address.logic.commands.AddRelationshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.relationship.Relationship;

/**
 * Parses input arguments and creates a new AddRelationshipCommand object
 */
public class AddRelationshipCommandParser implements Parser<AddRelationshipCommand> {

    @Override
    public AddRelationshipCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PART_1, PREFIX_PART_2, PREFIX_DESC);

        if (!arePrefixesPresent(argMultimap, PREFIX_PART_1, PREFIX_PART_2, PREFIX_DESC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRelationshipCommand.MESSAGE_USAGE));
        }

        Id part1 = ParserUtil.parseId(argMultimap.getValue(PREFIX_PART_1).get());
        Id part2 = ParserUtil.parseId(argMultimap.getValue(PREFIX_PART_2).get());
        Description desc = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get());

        Relationship relationship = new Relationship(part1, part2, desc);
        return new AddRelationshipCommand(relationship);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return java.util.stream.Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

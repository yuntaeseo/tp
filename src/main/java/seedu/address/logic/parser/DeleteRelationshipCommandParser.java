package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_2;

import seedu.address.logic.commands.DeleteRelationshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.Id;

/**
 * Parses input arguments and creates a new {@link DeleteRelationshipCommand} object.
 */
public class DeleteRelationshipCommandParser implements Parser<DeleteRelationshipCommand> {

    @Override
    public DeleteRelationshipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PART_1, PREFIX_PART_2);

        if (!argMultimap.getValue(PREFIX_PART_1).isPresent()
                || !argMultimap.getValue(PREFIX_PART_2).isPresent()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteRelationshipCommand.MESSAGE_USAGE));
        }

        Id part1 = ParserUtil.parseId(argMultimap.getValue(PREFIX_PART_1).get());
        Id part2 = ParserUtil.parseId(argMultimap.getValue(PREFIX_PART_2).get());

        return new DeleteRelationshipCommand(part1, part2);
    }
}

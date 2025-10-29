package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_2;

import seedu.address.logic.commands.EditRelationshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;

/**
 * Parses input arguments and creates a new {@link EditRelationshipCommand} object.
 */
public class EditRelationshipCommandParser implements Parser<EditRelationshipCommand> {

    @Override
    public EditRelationshipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PART_1, PREFIX_PART_2, PREFIX_DESC);

        if (!argMultimap.getValue(PREFIX_PART_1).isPresent()
                || !argMultimap.getValue(PREFIX_PART_2).isPresent()
                || !argMultimap.getValue(PREFIX_DESC).isPresent()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRelationshipCommand.MESSAGE_USAGE));
        }

        Id part1 = ParserUtil.parseId(argMultimap.getValue(PREFIX_PART_1).get());
        Id part2 = ParserUtil.parseId(argMultimap.getValue(PREFIX_PART_2).get());
        Description desc = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get());

        return new EditRelationshipCommand(part1, part2, desc);
    }
}

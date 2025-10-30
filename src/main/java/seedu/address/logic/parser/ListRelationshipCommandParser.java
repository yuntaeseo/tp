package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_2;

import java.util.Optional;

import seedu.address.logic.commands.ListRelationshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.Id;

/**
 * Parses input arguments and creates a new {@link ListRelationshipCommand} object.
 */
public class ListRelationshipCommandParser implements Parser<ListRelationshipCommand> {

    @Override
    public ListRelationshipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PART_1, PREFIX_PART_2);

        Optional<String> part1 = argMultimap.getValue(PREFIX_PART_1);
        Optional<String> part2 = argMultimap.getValue(PREFIX_PART_2);

        // If no ID provided, invalid
        if (part1.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListRelationshipCommand.MESSAGE_USAGE));
        }

        // only one ID: queryImmediateRelationship
        if (part1.isPresent() && part2.isEmpty()) {
            Id id1 = ParserUtil.parseId(part1.get());
            return new ListRelationshipCommand(id1);
        }

        // two IDs: queryLink
        if (part1.isPresent() && part2.isPresent()) {
            Id id1 = ParserUtil.parseId(part1.get());
            Id id2 = ParserUtil.parseId(part2.get());
            return new ListRelationshipCommand(id1, id2);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRelationshipCommand.MESSAGE_USAGE));
    }
}

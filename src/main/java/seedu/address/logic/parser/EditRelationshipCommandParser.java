package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_2;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditRelationshipCommand;
import seedu.address.logic.commands.EditRelationshipCommand.EditRelationshipDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditRelationshipCommand object.
 */
public class EditRelationshipCommandParser implements Parser<EditRelationshipCommand> {

    @Override
    public EditRelationshipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PART_1, PREFIX_PART_2, PREFIX_DESC);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRelationshipCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PART_1, PREFIX_PART_2, PREFIX_DESC);

        EditRelationshipDescriptor editRelationshipDescriptor = new EditRelationshipDescriptor();

        if (argMultimap.getValue(PREFIX_PART_1).isPresent()) {
            editRelationshipDescriptor.setPart1(ParserUtil.parseId(argMultimap.getValue(PREFIX_PART_1).get()));
        }
        if (argMultimap.getValue(PREFIX_PART_2).isPresent()) {
            editRelationshipDescriptor.setPart2(ParserUtil.parseId(argMultimap.getValue(PREFIX_PART_2).get()));
        }
        if (argMultimap.getValue(PREFIX_DESC).isPresent()) {
            editRelationshipDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get()));
        }

        if (!editRelationshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRelationshipCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRelationshipCommand(index, editRelationshipDescriptor);
    }
}

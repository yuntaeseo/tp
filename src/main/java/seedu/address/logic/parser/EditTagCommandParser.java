package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.EditTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

/**
 * Parses input arguments and creates a new EditTagCommand object
 */
public class EditTagCommandParser implements Parser<EditTagCommand> {

    @Override
    public EditTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESC, PREFIX_COLOR);

        String preamble = argMultimap.getPreamble().trim();
        if (preamble.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
        }

        int id;
        try {
            id = Integer.parseInt(preamble);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DESC, PREFIX_COLOR);

        TagName name = null;
        TagDesc desc = null;
        TagColor color = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            name = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (argMultimap.getValue(PREFIX_DESC).isPresent()) {
            desc = ParserUtil.parseTagDesc(argMultimap.getValue(PREFIX_DESC).get());
        }
        if (argMultimap.getValue(PREFIX_COLOR).isPresent()) {
            color = ParserUtil.parseTagColor(argMultimap.getValue(PREFIX_COLOR).get());
        }

        if (name == null && desc == null && color == null) {
            throw new ParseException("At least one field to edit must be provided.");
        }

        return new EditTagCommand(id, name, desc, color);
    }
}

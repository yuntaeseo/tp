package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.Id;
import seedu.address.model.tag.Color;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

/**
 * Parses input arguments and creates a new AddTagCommand object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {

    /** Dummy ID with negative id value. */
    public static final Id DUMMY_ID = Id.INVALID_ID;

    @Override
    public AddTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESC, PREFIX_COLOR);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DESC, PREFIX_COLOR);

        TagName name = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_NAME).get());
        TagDesc desc = ParserUtil.parseTagDesc(argMultimap.getValue(PREFIX_DESC).orElse("No description"));
        Color color = ParserUtil.parseColor(argMultimap.getValue(PREFIX_COLOR).orElse("808080"));

        // dummy tag with dummy ID
        Tag dummyTag = new Tag(DUMMY_ID, name, desc, color);
        return new AddTagCommand(dummyTag);
    }

    /** Utility: checks all prefixes are present */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

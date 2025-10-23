package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_COLOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_COLOR_DESC_COWORKERS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_COLOR_DESC_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DESC_COWORKERS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DESC_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_NAME_DESC_COWORKERS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_NAME_DESC_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DESC_FRIENDS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTags.FRIENDS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.TagBuilder;

public class AddTagCommandParserTest {
    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tag expectedTag = new TagBuilder(FRIENDS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_NAME_DESC_FRIENDS + TAG_DESC_DESC_FRIENDS
                + TAG_COLOR_DESC_FRIENDS, new AddTagCommand(expectedTag));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedTagString = TAG_NAME_DESC_FRIENDS + TAG_DESC_DESC_FRIENDS + TAG_COLOR_DESC_FRIENDS;

        // multiple names
        assertParseFailure(parser, TAG_NAME_DESC_COWORKERS + validExpectedTagString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple descs
        assertParseFailure(parser, TAG_DESC_DESC_COWORKERS + validExpectedTagString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESC));

        // multiple color
        assertParseFailure(parser, TAG_COLOR_DESC_COWORKERS + validExpectedTagString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COLOR));


        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_TAG_NAME_DESC + validExpectedTagString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid color
        assertParseFailure(parser, INVALID_TAG_COLOR_DESC + validExpectedTagString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COLOR));


        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedTagString + INVALID_TAG_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid color
        assertParseFailure(parser, validExpectedTagString + INVALID_TAG_COLOR_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COLOR));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TAG_DESC_FRIENDS + TAG_DESC_DESC_FRIENDS + TAG_COLOR_DESC_FRIENDS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TAG_NAME_DESC + TAG_DESC_DESC_FRIENDS + TAG_COLOR_DESC_FRIENDS,
                TagName.MESSAGE_CONSTRAINTS);

        // invalid color
        assertParseFailure(parser, TAG_NAME_DESC_FRIENDS + TAG_DESC_DESC_FRIENDS + INVALID_TAG_COLOR_DESC,
                TagColor.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TAG_NAME_DESC_FRIENDS + TAG_DESC_DESC_FRIENDS
                + TAG_COLOR_DESC_FRIENDS, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));

    }
}

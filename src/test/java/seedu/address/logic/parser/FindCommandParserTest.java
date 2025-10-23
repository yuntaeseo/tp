package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.PersonFieldExtractor;
import seedu.address.model.person.CompositePersonPredicate;
import seedu.address.model.person.FieldContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        String inputNoWhiteSpace = "n/Ali n/bob";
        String inputMultipleWhitespaces = " n/ Alice \n \t n/\tBob  \t";

        CompositePersonPredicate expectedPredicates = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_NAME, List.of("Ali", "bob"))
                ));
        FindCommand expectedCommand = new FindCommand(expectedPredicates);

        assertParseSuccess(parser, inputNoWhiteSpace, expectedCommand);
        assertParseSuccess(parser, inputMultipleWhitespaces, expectedCommand);
    }

    @Test
    public void parse_multipleFields_returnsFindCommand() {
        String input = "n/Alice p/9123 e/@example.com a/little india a/#35";
        String inputDifferentOrder = "a/#35 a/little india   n/Alice \t e/@example.com \np/9123";

        CompositePersonPredicate expectedPredicates = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_NAME, List.of("Alice")),
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_PHONE, List.of("9123")),
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_EMAIL, List.of("@example.com")),
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_ADDRESS, List.of("little india", "#35"))
                ));
        FindCommand expectedCommand = new FindCommand(expectedPredicates);

        assertParseSuccess(parser, input, expectedCommand);
        assertParseSuccess(parser, inputDifferentOrder, expectedCommand);
    }
}

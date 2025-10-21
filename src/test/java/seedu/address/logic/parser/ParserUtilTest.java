package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.Id;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_TAG_NAME = "*";
    private static final String INVALID_TAG_COLOR = "AABBGG";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "123";
    private static final String VALID_TAG_2 = "456";
    private static final String VALID_NOTE = "some famous person";
    private static final String VALID_TAG_NAME = "friends";
    private static final String VALID_TAG_DESC = "close people";
    private static final String VALID_TAG_COLOR = "123456";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseId(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Id expectedId = new Id(VALID_TAG_1);
        assertEquals(expectedId, ParserUtil.parseId(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Id expectedTag = new Id(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseId(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIds(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIds(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseIds(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Id> actualTagSet = ParserUtil.parseIds(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Id> expectedTagSet =
                Set.of(new Id(Integer.parseInt(VALID_TAG_1)), new Id(Integer.parseInt(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote((String) null));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(VALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String noteWithWhitespace = WHITESPACE + VALID_NOTE + WHITESPACE;
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(noteWithWhitespace));
    }

    //  NOTE: TAG

    @Test
    public void parseTagName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTagName((String) null));
    }

    @Test
    public void parseTagName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagName(INVALID_TAG_NAME));
    }

    @Test
    public void parseTagName_validValueWithoutWhitespace_returnsTagName() throws Exception {
        TagName expectedName = new TagName(VALID_TAG_NAME);
        assertEquals(expectedName, ParserUtil.parseTagName(VALID_TAG_NAME));
    }

    @Test
    public void parseTagName_validValueWithWhitespace_returnsTrimmedTagName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_TAG_NAME + WHITESPACE;
        TagName expectedName = new TagName(VALID_TAG_NAME);
        assertEquals(expectedName, ParserUtil.parseTagName(nameWithWhitespace));
    }

    @Test
    public void parseTagDesc_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTagDesc((String) null));
    }

    @Test
    public void parseTagDesc_validValueWithoutWhitespace_returnsTagDesc() throws Exception {
        TagDesc expectedDesc = new TagDesc(VALID_TAG_DESC);
        assertEquals(expectedDesc, ParserUtil.parseTagDesc(VALID_TAG_DESC));
    }

    @Test
    public void parseTagDesc_validValueWithWhitespace_returnsTrimmedTagDesc() throws Exception {
        String descWithWhitespace = WHITESPACE + VALID_TAG_DESC + WHITESPACE;
        TagDesc expectedDesc = new TagDesc(VALID_TAG_DESC);
        assertEquals(expectedDesc, ParserUtil.parseTagDesc(descWithWhitespace));
    }

    @Test
    public void parseTagColor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTagColor((String) null));
    }

    @Test
    public void parseTagColor_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagColor(INVALID_TAG_COLOR));
    }

    @Test
    public void parseTagColor_validValueWithoutWhitespace_returnsTagColor() throws Exception {
        TagColor expectedColor = new TagColor(VALID_TAG_COLOR);
        assertEquals(expectedColor, ParserUtil.parseTagColor(VALID_TAG_COLOR));
    }

    @Test
    public void parseTagColor_validValueWithWhitespace_returnsTrimmedTagColor() throws Exception {
        String colorWithWhitespace = WHITESPACE + VALID_TAG_COLOR + WHITESPACE;
        TagColor expectedColor = new TagColor(VALID_TAG_COLOR);
        assertEquals(expectedColor, ParserUtil.parseTagColor(colorWithWhitespace));
    }
}

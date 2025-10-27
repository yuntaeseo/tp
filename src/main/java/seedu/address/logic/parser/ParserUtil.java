package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Color;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_ID = "ID is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String id} into an {@code Id}, the ID of some object.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(Integer.parseInt(trimmedId));
    }

    /**
     * Parses {@code Collection<String> ids} into a {@code Set<Id>}, a set of IDs.
     */
    public static Set<Id> parseIds(Collection<String> ids) throws ParseException {
        requireNonNull(ids);
        final Set<Id> idSet = new HashSet<>();
        for (String tagId : ids) {
            idSet.add(parseId(tagId));
        }
        return idSet;
    }

    /**
     * Parses a {@code String note} into an {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Note parseNote(String note) {
        requireNonNull(note);
        String trimmedNote = note.trim();

        // All notes are valid since they are just strings
        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Description parseDescription(String description) {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        return new Description(trimmedDescription);
    }

    // ===== TAG PARSERS =====

    /**
     * Parses a {@code String name} into a {@code TagName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TagName parseTagName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TagName.isValidTagName(trimmedName)) {
            throw new ParseException(TagName.MESSAGE_CONSTRAINTS);
        }
        return new TagName(trimmedName);
    }

    /**
     * Parses a {@code String desc} into a {@code TagDesc}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code desc} is invalid.
     */
    public static TagDesc parseTagDesc(String desc) throws ParseException {
        requireNonNull(desc);
        String trimmedDesc = desc.trim();
        return new TagDesc(trimmedDesc);
    }

    /**
     * Parses a {@code String color} into a {@code Color}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code color} is invalid.
     */
    public static Color parseColor(String color) throws ParseException {
        requireNonNull(color);
        String trimmedColor = color.trim();
        if (!Color.isValidColor(trimmedColor)) {
            throw new ParseException(Color.MESSAGE_CONSTRAINTS);
        }
        return new Color(trimmedColor);
    }
}

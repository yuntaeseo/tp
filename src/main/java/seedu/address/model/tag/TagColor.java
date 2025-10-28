package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag's color in HEX format.
 */
public class TagColor {
    public static final String MESSAGE_CONSTRAINTS = "Tags colors should be in HEX format (without the '#'): RRGGBB";

    /**
     * Regex for enforcing the HEX format (without the '#' at the beginning).
     */
    public static final String VALIDATION_REGEX = "^(?:[0-9a-fA-F]{6})$";

    /** 0: original colour, 1: white */
    private static final double WHITE_TINT_RATIO = 0.2;

    private static final int HEX_LENGTH = 6;
    private static final int MIN_CHANNEL_VALUE = 0;
    private static final int MAX_CHANNEL_VALUE = 255;

    public final String value;
    private final String displayValue;

    /**
     * Constructs a {@code TagColor}
     *
     * @param tagColor A valid tag color.
     */
    public TagColor(String tagColor) {
        requireNonNull(tagColor);
        checkArgument(isValidTagColor(tagColor), MESSAGE_CONSTRAINTS);
        value = tagColor;
        displayValue = soften(tagColor);
    }

    /**
     * Returns true if a given string is a valid tag color.
     */
    public static boolean isValidTagColor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a softened hex string that is white-tinted as a background color.
     */
    public String getDisplayHex() {
        return displayValue;
    }

    @Override
    public String toString() {
        return value.toUpperCase();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagColor)) {
            return false;
        }

        TagColor otherTagColor = (TagColor) other;
        return value.equals(otherTagColor.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Softens the given hex color by tinting it with white.
     * @param hex original hex color
     * @return softened hex color
     */
    private static String soften(String hex) {
        assert hex.length() == HEX_LENGTH : "Hex color should be 6 characters long.";
        int red = Integer.parseInt(hex.substring(0, 2), 16);
        int green = Integer.parseInt(hex.substring(2, 4), 16);
        int blue = Integer.parseInt(hex.substring(4, 6), 16);

        int softenedRed = tint(red);
        int softenedGreen = tint(green);
        int softenedBlue = tint(blue);

        return String.format("%02X%02X%02X", softenedRed, softenedGreen, softenedBlue);
    }


    /**
     * Tints a single color channel with white.
     */
    private static int tint(int channel) {
        assert channel >= MIN_CHANNEL_VALUE && channel <= MAX_CHANNEL_VALUE
                : "Channel should be within 0-255 range.";
        double tinted = channel * (1 - WHITE_TINT_RATIO) + MAX_CHANNEL_VALUE * (WHITE_TINT_RATIO);
        return (int) Math.round(Math.min(MAX_CHANNEL_VALUE, Math.max(MIN_CHANNEL_VALUE, tinted)));
    }
}

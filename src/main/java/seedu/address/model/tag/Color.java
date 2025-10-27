package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a color in HEX format.
 */
public class Color {
    public static final String MESSAGE_CONSTRAINTS = "Colors should be in HEX format (without the '#'): RRGGBB";

    /**
     * Regex for enforcing the HEX format (without the '#' at the beginning).
     */
    public static final String VALIDATION_REGEX = "^(?:[0-9a-fA-F]{6})$";

    /** 0: original colour, 1: white */
    private static final double WHITE_TINT_RATIO = 0.2;

    public final String value;
    private final String displayValue;

    /**
     * Constructs a {@code Color}
     *
     * @param tagColor A valid tag color.
     */
    public Color(String tagColor) {
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
        if (!(other instanceof Color)) {
            return false;
        }

        Color otherTagColor = (Color) other;
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
        double tinted = channel * (1 - WHITE_TINT_RATIO) + 255 * WHITE_TINT_RATIO;
        return (int) Math.round(Math.min(255, Math.max(0, tinted)));
    }
}

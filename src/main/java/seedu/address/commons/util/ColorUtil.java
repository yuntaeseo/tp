package seedu.address.commons.util;

import seedu.address.model.tag.Color;

/**
 * Provides utility functions related to colors.
 */
public class ColorUtil {

    public static final Color WHITE = new Color("FFFFFF");
    public static final Color BLACK = new Color("000000");

    // for luminance calculations
    private static final double RED_WEIGHT = 0.2126;
    private static final double GREEN_WEIGHT = 0.7152;
    private static final double BLUE_WEIGHT = 0.0722;

    // Number above which to switch to black text
    private static final double LUMINANCE_THRESHOLD = 0.18;

    /** 0: original colour, 1: white */
    private static final double WHITE_TINT_RATIO = 0.2;

    /**
     * Returns the text color based on the given {@code backgroundColor}.
     *
     * @param backgroundColor in RGB hex string format.
     * @return white/black color RGB hex code.
     */
    public static Color getTextColor(Color backgroundColor) {
        /*
         * Formula taken from:
         * https://stackoverflow.com/questions/596216/formula-to-determine-perceived-brightness-of-rgb-color
         */

        int red = backgroundColor.getRedChannel();
        int green = backgroundColor.getGreenChannel();
        int blue = backgroundColor.getBlueChannel();

        double redLinear = sRgbToLinear(red);
        double greenLinear = sRgbToLinear(green);
        double blueLinear = sRgbToLinear(blue);

        double luminance = (RED_WEIGHT * redLinear) + (GREEN_WEIGHT * greenLinear) + (BLUE_WEIGHT * blueLinear);
        return luminance > LUMINANCE_THRESHOLD ? BLACK : WHITE;
    }

    /**
     * Converts gamma-encoded R/G/B color channel to a linear value.
     *
     * @param colorChannel in the range of [0,255]
     * @return linearized value.
     */
    private static double sRgbToLinear(int colorChannel) {
        double normalized = colorChannel / 255.0; // Normalize color to be within [0,1] range

        if (normalized <= 0.04045) {
            return normalized / 12.92;
        } else {
            return Math.pow((normalized + 0.055) / 1.055, 2.4);
        }
    }

    /**
     * Softens the given color by tinting it with white.
     * @param color original color
     * @return softened color
     */
    public static Color soften(Color color) {
        int red = color.getRedChannel();
        int green = color.getGreenChannel();
        int blue = color.getBlueChannel();

        int softenedRed = tint(red);
        int softenedGreen = tint(green);
        int softenedBlue = tint(blue);

        return new Color(String.format("%02X%02X%02X", softenedRed, softenedGreen, softenedBlue));
    }

    /**
     * Tints a single color channel with white.
     */
    private static int tint(int channel) {
        double tinted = channel * (1 - WHITE_TINT_RATIO) + 255 * WHITE_TINT_RATIO;
        return (int) Math.round(Math.min(255, Math.max(0, tinted)));
    }
}

package seedu.address.commons.util;

/**
 * Provides utility functions related to colors.
 */
public class ColorUtil {

    public static final String WHITE = "FFFFFF";
    public static final String BLACK = "000000";

    // for luminance calculations
    private static final double RED_WEIGHT = 0.2126;
    private static final double GREEN_WEIGHT = 0.7152;
    private static final double BLUE_WEIGHT = 0.0722;

    // Number above which to switch to black text
    private static final double LUMINANCE_THRESHOLD = 0.18;

    /**
     * Returns the text color based on the given {@code backgroundColor}.
     *
     * @param backgroundColor in RGB hex string format.
     * @return white/black color RGB hex code.
     */
    public static String getTextColor(String backgroundColor) {
        /*
         * Formula taken from:
         * https://stackoverflow.com/questions/596216/formula-to-determine-perceived-brightness-of-rgb-color
         */

        int red = getRedChannel(backgroundColor);
        int green = getGreenChannel(backgroundColor);
        int blue = getBlueChannel(backgroundColor);

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
     * Returns red channel from RGB string.
     *
     * @param color in RRGGBB format.
     */
    private static int getRedChannel(String color) {
        return Integer.parseInt(color.substring(0, 2), 16);
    }

    /**
     * Returns green channel from RGB string.
     *
     * @param color in RRGGBB format.
     */
    private static int getGreenChannel(String color) {
        return Integer.parseInt(color.substring(2, 4), 16);
    }

    /**
     * Returns blue channel from RGB string.
     *
     * @param color in RRGGBB format.
     */
    private static int getBlueChannel(String color) {
        return Integer.parseInt(color.substring(4, 6), 16);
    }
}

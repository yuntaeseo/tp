package seedu.address.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorUtilTest {
    @Test
    public void getTextColor_whiteBackground_returnsBlack() {
        final String white = "FFFFFF";
        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(white));
    }

    @Test
    public void getTextColor_blackBackground_returnsWhite() {
        final String black = "000000";
        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(black));
    }

    @Test
    public void getTextColor_darkBackground_returnsWhite() {
        final String darkRed = "210403";
        final String darkBlue = "040359";
        final String olive = "3c3d01";
        final String darkGreen = "01360b";

        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(darkRed));
        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(darkBlue));
        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(olive));
        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(darkGreen));
    }

    @Test
    public void getTextColor_lightBackground_returnsBlack() {
        final String lightGreen = "05fc34";
        final String brightYellow = "f7f71e";
        final String lightBlue = "3afcfc";
        final String brightPink = "ff85fb";

        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(lightGreen));
        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(brightYellow));
        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(lightBlue));
        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(brightPink));
    }
}

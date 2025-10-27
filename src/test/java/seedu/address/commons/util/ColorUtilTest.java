package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Color;

public class ColorUtilTest {
    @Test
    public void getTextColor_whiteBackground_returnsBlack() {
        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(ColorUtil.WHITE));
    }

    @Test
    public void getTextColor_blackBackground_returnsWhite() {
        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(ColorUtil.BLACK));
    }

    @Test
    public void getTextColor_darkBackground_returnsWhite() {
        final Color darkRed = new Color("210403");
        final Color darkBlue = new Color("040359");
        final Color olive = new Color("3c3d01");
        final Color darkGreen = new Color("01360b");

        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(darkRed));
        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(darkBlue));
        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(olive));
        assertEquals(ColorUtil.WHITE, ColorUtil.getTextColor(darkGreen));
    }

    @Test
    public void getTextColor_lightBackground_returnsBlack() {
        final Color lightGreen = new Color("05fc34");
        final Color brightYellow = new Color("f7f71e");
        final Color lightBlue = new Color("3afcfc");
        final Color brightPink = new Color("ff85fb");

        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(lightGreen));
        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(brightYellow));
        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(lightBlue));
        assertEquals(ColorUtil.BLACK, ColorUtil.getTextColor(brightPink));
    }
}

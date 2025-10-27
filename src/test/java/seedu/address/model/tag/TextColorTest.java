package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ColorUtil;

public class TextColorTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TextColor(null));
    }

    @Test
    public void constructor_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TextColor("GGGGGG"));
        assertThrows(IllegalArgumentException.class, () -> new TextColor("0"));
    }

    @Test
    public void isValidTextColor() {
        // null -> returns false
        assertThrows(NullPointerException.class, () -> TextColor.isValidTextColor(null));

        // invalid colors -> returns false
        assertFalse(TextColor.isValidTextColor("")); // empty string
        assertFalse(TextColor.isValidTextColor("12")); // only 2 characters
        assertFalse(TextColor.isValidTextColor("12345")); // 5 characters
        assertFalse(TextColor.isValidTextColor("1")); // only 1 character
        assertFalse(TextColor.isValidTextColor("GGGGGG")); // contains non-hexadecimal characters

        // valid colors -> returns true
        assertTrue(TextColor.isValidTextColor("000")); // 3 characters, black
        assertTrue(TextColor.isValidTextColor("FFF")); // 3 characters, white
        assertTrue(TextColor.isValidTextColor("ABCABC")); // 6 characters
        assertTrue(TextColor.isValidTextColor("AB12B7")); // mix
    }

    @Test
    public void fromTagColor_darkTagColor_returnsWhiteText() {
        final Color black = new Color("000000");
        final Color darkRed = new Color("210403");
        final Color darkBlue = new Color("040359");
        final Color olive = new Color("3c3d01");
        final Color darkGreen = new Color("01360b");

        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(black));
        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(darkRed));
        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(darkBlue));
        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(olive));
        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(darkGreen));
    }

    @Test
    public void fromTagColor_lightTagColor_returnsBlackText() {
        final Color white = new Color("ffffff");
        final Color lightGreen = new Color("05fc34");
        final Color brightYellow = new Color("f7f71e");
        final Color lightBlue = new Color("3afcfc");
        final Color brightPink = new Color("ff85fb");

        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(white));
        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(lightGreen));
        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(brightYellow));
        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(lightBlue));
        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(brightPink));
    }

    @Test
    public void testToString() {
        assertEquals(new TextColor("FFFFFF").toString(), new TextColor("ffffff").toString());
        assertEquals(new TextColor("FFFFFF").toString(), new TextColor("FFFFFF").toString());
        assertEquals(new TextColor("123123").toString(), new TextColor("123123").toString());
        assertEquals(new TextColor("ABC123").toString(), new TextColor("AbC123").toString());
    }

    @Test
    public void equals() {
        TextColor textColor = new TextColor("121212");

        // same values -> returns true
        assertTrue(textColor.equals(new TextColor("121212")));

        // same object -> returns true
        assertTrue(textColor.equals(textColor));

        // null -> returns false
        assertFalse(textColor.equals(null));

        // different types -> returns false
        assertFalse(textColor.equals(5.0f));

        // different values -> returns false
        assertFalse(textColor.equals(new TextColor("BBCCDD")));
    }
}

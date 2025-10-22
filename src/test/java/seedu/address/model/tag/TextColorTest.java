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
        final TagColor black = new TagColor("000000");
        final TagColor darkRed = new TagColor("210403");
        final TagColor darkBlue = new TagColor("040359");
        final TagColor olive = new TagColor("3c3d01");
        final TagColor darkGreen = new TagColor("01360b");

        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(black).value);
        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(darkRed).value);
        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(darkBlue).value);
        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(olive).value);
        assertEquals(ColorUtil.WHITE, TextColor.fromTagColor(darkGreen).value);
    }

    @Test
    public void fromTagColor_lightTagColor_returnsBlackText() {
        final TagColor white = new TagColor("ffffff");
        final TagColor lightGreen = new TagColor("05fc34");
        final TagColor brightYellow = new TagColor("f7f71e");
        final TagColor lightBlue = new TagColor("3afcfc");
        final TagColor brightPink = new TagColor("ff85fb");

        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(white).value);
        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(lightGreen).value);
        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(brightYellow).value);
        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(lightBlue).value);
        assertEquals(ColorUtil.BLACK, TextColor.fromTagColor(brightPink).value);
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

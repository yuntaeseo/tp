package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTags.EX_GIRLFRIEND;
import static seedu.address.testutil.TypicalTags.FRIENDS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ColorUtil;
import seedu.address.testutil.TagBuilder;

public class TagTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null, null, null));
    }

    @Test
    public void constructor_tagColors_returnsCorrectTextColor() {
        Tag tag = new TagBuilder(FRIENDS).build();
        assertEquals(ColorUtil.getTextColor(FRIENDS.getDisplayColor()), tag.getTextColor());

        Tag whiteTag = new TagBuilder().withName("white").withColor("FFFFFF").build();
        assertEquals(ColorUtil.BLACK, whiteTag.getTextColor());

        Tag blackTag = new TagBuilder().withName("black").withColor("000000").build();
        assertEquals(ColorUtil.WHITE, blackTag.getTextColor());
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(FRIENDS.isSameTag(FRIENDS));

        // null -> returns false
        assertFalse(FRIENDS.isSameTag(null));

        // same name, all other attributes different -> returns true
        Tag editedFriends = new TagBuilder(FRIENDS).withDesc("bruh").withColor("696969").build();
        assertTrue(FRIENDS.isSameTag(editedFriends));

        // different name, all other attributes same -> returns false
        editedFriends = new TagBuilder(FRIENDS).withName("lmao").build();
        assertFalse(FRIENDS.isSameTag(editedFriends));

        String gfName = EX_GIRLFRIEND.getName().value;

        // name differs in case, all other attributes same -> returns false
        Tag editedGf = new TagBuilder(EX_GIRLFRIEND).withName(gfName.toUpperCase()).build();
        assertFalse(EX_GIRLFRIEND.isSameTag(editedGf));

        // name has trailing spaces, all other attributes same -> returns false
        editedGf = new TagBuilder(EX_GIRLFRIEND).withName(gfName + "   ").build();
        assertFalse(EX_GIRLFRIEND.isSameTag(editedGf));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Tag friendsCopy = new TagBuilder(FRIENDS).build();
        assertTrue(FRIENDS.equals(friendsCopy));

        // same object -> returns true
        assertTrue(FRIENDS.equals(FRIENDS));

        // null -> returns false
        assertFalse(FRIENDS.equals(null));

        // different type -> returns false
        assertFalse(FRIENDS.equals(0.5f));

        // different tag -> returns false
        assertFalse(FRIENDS.equals(EX_GIRLFRIEND));

        // different name -> returns false
        Tag editedFriends = new TagBuilder(FRIENDS).withName("bruh").build();
        assertFalse(FRIENDS.equals(editedFriends));

        // different desc -> returns false
        editedFriends = new TagBuilder(FRIENDS).withDesc("bruh").build();
        assertFalse(FRIENDS.equals(editedFriends));

        // different color -> returns false
        editedFriends = new TagBuilder(FRIENDS).withDesc("696969").build();
        assertFalse(FRIENDS.equals(editedFriends));
    }

    @Test
    public void toStringMethod() {
        String expected = Tag.class.getCanonicalName() + "{id=" + FRIENDS.getId() + ", name=" + FRIENDS.getName()
                + ", description=" + FRIENDS.getDesc() + ", color=" + FRIENDS.getColor() + "}";
        assertEquals(expected, FRIENDS.toString());
    }
}

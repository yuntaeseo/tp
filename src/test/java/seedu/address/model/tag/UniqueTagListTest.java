package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTags.EX_GIRLFRIEND;
import static seedu.address.testutil.TypicalTags.FRIENDS;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;
import seedu.address.testutil.TagBuilder;

public class UniqueTagListTest {
    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void contains_nullTag_throwsNullPoiinterException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(FRIENDS));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        // exactly the same tag
        uniqueTagList.add(FRIENDS);
        assertTrue(uniqueTagList.contains(FRIENDS));

        // name identity
        uniqueTagList.add(new TagBuilder(EX_GIRLFRIEND).withDesc("bruh").withColor("696969").build());
        assertTrue(uniqueTagList.contains(EX_GIRLFRIEND));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(FRIENDS);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(FRIENDS));
    }

    @Test
    public void setTag_nullTargetTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(null, FRIENDS));
    }

    @Test
    public void setTag_nullEditedTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(FRIENDS, null));
    }

    @Test
    public void setTag_targetTagNotInList_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.setTag(FRIENDS, FRIENDS));
    }

    @Test
    public void setTag_editedTagIsSameTag_success() {
        uniqueTagList.add(FRIENDS);
        uniqueTagList.setTag(FRIENDS, FRIENDS);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(FRIENDS);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasSameIdentity_success() {
        uniqueTagList.add(FRIENDS);
        Tag editedFriends = new TagBuilder(FRIENDS).withDesc("bruh").withColor("696969").build();
        uniqueTagList.setTag(FRIENDS, editedFriends);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(editedFriends);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasDifferentIdentity_success() {
        uniqueTagList.add(FRIENDS);
        uniqueTagList.setTag(FRIENDS, EX_GIRLFRIEND);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(EX_GIRLFRIEND);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasNonUniqueIdentity_throwsDuplicateTagException() {
        uniqueTagList.add(FRIENDS);
        uniqueTagList.add(EX_GIRLFRIEND);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTag(FRIENDS, EX_GIRLFRIEND));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.remove(null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.remove(FRIENDS));
    }

    @Test
    public void remove_existingTag_removesTag() {
        uniqueTagList.add(FRIENDS);
        uniqueTagList.remove(FRIENDS);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList) null));
    }

    @Test
    public void setTags_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(FRIENDS);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(EX_GIRLFRIEND);
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((List<Tag>) null));
    }
    
    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        uniqueTagList.add(FRIENDS);
        List<Tag> tagList = Collections.singletonList(EX_GIRLFRIEND);
        uniqueTagList.setTags(tagList);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(EX_GIRLFRIEND);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_listWithDuplicateTags_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(FRIENDS, FRIENDS);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicateTags));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTagList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator() {
        uniqueTagList.add(FRIENDS);
        uniqueTagList.add(EX_GIRLFRIEND);
        Iterator<Tag> i = uniqueTagList.iterator();
        assertTrue(i.next().equals(FRIENDS));
        assertTrue(i.next().equals(EX_GIRLFRIEND));
        assertFalse(i.hasNext());
    }

    @Test
    public void equals_sameObject() {
        assertTrue(uniqueTagList.equals(uniqueTagList));
    }

    @Test
    public void equals_differentType() {
        assertTrue(uniqueTagList.equals(0.5f));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueTagList.asUnmodifiableObservableList().toString(), uniqueTagList.toString());
    }
}

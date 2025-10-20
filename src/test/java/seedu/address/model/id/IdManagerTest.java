package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IdManagerTest {

    @Test
    public void constructor_valid() {
        new IdManager();
    }

    @Test
    public void getNewId_autoIncrement() {
        IdManager manager = new IdManager();
        assertEquals(manager.getNewId(), new Id(1));
        assertEquals(manager.getNewId(), new Id(2));
        assertEquals(manager.getNewId(), new Id(3));
    }

    @Test
    public void setLargest_autoIncrement() {
        IdManager manager = new IdManager();
        manager.setLargest(new Id(10));
        assertEquals(manager.getNewId(), new Id(11));
        manager.setLargest(new Id(103));
        assertEquals(manager.getNewId(), new Id(104));
        manager.setLargest(new Id(10374));
        assertEquals(manager.getNewId(), new Id(10375));
    }
}

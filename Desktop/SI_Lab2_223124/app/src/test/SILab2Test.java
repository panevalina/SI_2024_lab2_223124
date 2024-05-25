package app.src.test;
import app.src.main.Item;
import app.src.main.SILab2;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    public void everyBranch(){
        RuntimeException runtimeException;

        runtimeException = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertTrue(runtimeException.getMessage().contains("allItems list can't be null!"));

        Item item1 = new Item(null, "13579", 50, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item1), 50));

        Item item2 = new Item("", "24680", 660, 0.5f);
        assertTrue(SILab2.checkCart(List.of(item2), 600));

        Item item3 = new Item("Item3", null, 30, 0.2f);
        runtimeException = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(item3), 100));
        assertTrue(runtimeException.getMessage().contains("No barcode!"));

        Item item4 = new Item("Item4", "22*22", 44, 0);
        runtimeException = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(item4), 233));
        assertTrue(runtimeException.getMessage().contains("Invalid character in item barcode!"));

        Item item5 = new Item("Item5", "223124", 500, 0.5f);
        assertTrue(SILab2.checkCart(List.of(item5), 250));

        Item item6 = new Item("Item6", "223124", 500, 0);
        assertTrue(SILab2.checkCart(List.of(item6), 500));

        Item item7 = new Item("Item7", "012345", 301, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item7), 1));
        assertFalse(SILab2.checkCart(List.of(item7), 0));

        assertTrue(SILab2.checkCart(List.of(), 100));

        Item item8 = new Item("Item7", "97531", 300, 0);
        Item item9 = new Item("Item8", "08642", 300, 0);
        assertFalse(SILab2.checkCart(Arrays.asList(item8, item9), 599));
    }

    @Test
    public void multipleCondition() {
        // TTT (Item со цена над од 300, со попуст и баркод кој почнува со 0)
        Item item1 = new Item("Item1", "0223124", 400, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item1), 10));
        assertFalse(SILab2.checkCart(List.of(item1), 9));

        // TTF (Item со цена над од 300, со попуст и баркод кој не започнува со 0)
        Item item2 = new Item("Item2", "223124", 400, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item2), 41));
        assertFalse(SILab2.checkCart(List.of(item2), 10));

        // TFT (Item со цена над од 300, без попуст и баркод кој започнува со 0)
        Item item3 = new Item("Item3", "0223124", 400, 0);
        assertTrue(SILab2.checkCart(List.of(item3), 400));
        assertFalse(SILab2.checkCart(List.of(item3), 40));

        // FTT (Item со цена под 300, со попуст и баркод кој започнува со 0)
        Item item4 = new Item("Item4", "0223124", 299, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item4), 30));
        assertFalse(SILab2.checkCart(List.of(item4), 29));

        // FFT (Item со цена под 300, без попуст и баркод кој започнува со 0)
        Item item5 = new Item("Item5", "0223124", 200, 0);
        assertTrue(SILab2.checkCart(List.of(item5), 333));
        assertFalse(SILab2.checkCart(List.of(item5), 30));

        // FTF (Item со цена под 300, со попуст и баркод кој започнува со 0)
        Item item6 = new Item("Item6", "0223124", 298, 0.5f);
        assertTrue(SILab2.checkCart(List.of(item6), 150));
        assertFalse(SILab2.checkCart(List.of(item6), 148));

        // TFF (Item со цена над 300, без попуст и баркод кој не започнува со 0)
        Item item7 = new Item("Item7", "223124", 477, 0);
        assertTrue(SILab2.checkCart(List.of(item7), 1000));
        assertFalse(SILab2.checkCart(List.of(item7), 476));

        // FFF (Item со цена под 300, без попуст и баркод кој не започнува со 0)
        Item item8 = new Item("Item8", "223124", 150, 0);
        assertTrue(SILab2.checkCart(List.of(item8), 150));
        assertFalse(SILab2.checkCart(List.of(item8), 149));
    }
}
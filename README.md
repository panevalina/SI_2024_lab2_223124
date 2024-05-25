# Втора лабораториска вежба по Софтверско инженерство
### 1. Лина Панева 223124
### 2. Control Flow Graph
![223124 (3)](https://github.com/panevalina/SI_2024_lab2_223124/assets/164191019/b434de6c-79ac-4219-9a48-eed4ecbcfaf3)
### 3. Цикломатската комплексност 
P (предикатни јазли) + 1 = 9 + 1 = 10
### 4. Every Branch критериум
```
public class SILab2Test {

    // Тест случај за влезни параметри null
    @Test
    void testNullInput() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, 0);
        });
        assertTrue(exception.getMessage().contains("allItems list can't be null!"));
    }

    // Тест случај за влезна листа со еден елемент и цена 0
    @Test
    void testSingleItemWithPriceZero() {
        Item item1 = new Item("Item1", "13579", 0, 0.1f);
        assertTrue(SILab2.checkCart(Arrays.asList(item1), 0));
    }

    // Тест случај за влезна листа со еден елемент и цена над 0
    @Test
    void testSingleItemWithNonZeroPrice() {
        Item item2 = new Item("Item2", "24680", 660, 0.5f);
        assertTrue(SILab2.checkCart(Arrays.asList(item2), 600));
    }

    // Тест случај за влезна листа со еден елемент без баркод
    @Test
    void testSingleItemWithoutBarcode() {
        Item item3 = new Item("Item3", null, 30, 0.2f);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(Arrays.asList(item3), 100);
        });
        assertTrue(exception.getMessage().contains("No barcode!"));
    }

    // Тест случај за влезна листа со еден елемент со невалиден баркод
    @Test
    void testSingleItemWithInvalidBarcode() {
        Item item4 = new Item("Item4", "22*22", 44, 0);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(Arrays.asList(item4), 233);
        });
        assertTrue(exception.getMessage().contains("Invalid character in item barcode!"));
    }

    // Тест случај за влезна листа со еден елемент со попуст
    @Test
    void testSingleItemWithDiscount() {
        Item item5 = new Item("Item5", "223124", 500, 0.5f);
        assertTrue(SILab2.checkCart(Arrays.asList(item5), 250));
    }

    // Тест случај за влезна листа со еден елемент без попуст
    @Test
    void testSingleItemWithoutDiscount() {
        Item item6 = new Item("Item6", "223124", 500, 0);
        assertTrue(SILab2.checkCart(Arrays.asList(item6), 500));
    }

    // Тест случај за влезна листа со еден елемент со цена над 300, со попуст и баркод кој почнува со 0
    @Test
    void testSingleItemWithPriceAbove300AndDiscountAndBarcodeStartsWith0() {
        Item item7 = new Item("Item7", "012345", 301, 0.1f);
        assertTrue(SILab2.checkCart(Arrays.asList(item7), 1));
        assertFalse(SILab2.checkCart(Arrays.asList(item7), 0));
    }

    // Тест случај за влезна листа со два елементи и сумата помала од вкупната цена
    @Test
    void testTwoItemsWithTotalPriceGreaterThanPayment() {
        Item item8 = new Item("Item8", "97531", 300, 0);
        Item item9 = new Item("Item9", "08642", 300, 0);
        assertFalse(SILab2.checkCart(Arrays.asList(item8, item9), 599));
    }
}

```
### 5. Multiple Condition критериумот
```
public class SILab2Test {

    // TTT (Item со цена над од 300, со попуст и баркод кој започнува со 0)
    @Test
    void testItemPriceAbove300WithDiscountAndBarcodeStartsWith0() {
        Item item = new Item("Item1", "0223124", 400, 0.1f);
        assertTrue(SILab2.checkCart(Arrays.asList(item), 10));
    }

    // TTF (Item со цена над од 300, со попуст и баркод кој не започнува со 0)
    @Test
    void testItemPriceAbove300WithDiscountAndBarcodeNotStartingWith0() {
        Item item = new Item("Item2", "223124", 400, 0.1f);
        assertFalse(SILab2.checkCart(Arrays.asList(item), 10));
    }

    // TFT (Item со цена над од 300, без попуст и баркод кој започнува со 0)
    @Test
    void testItemPriceAbove300WithoutDiscountAndBarcodeStartingWith0() {
        Item item = new Item("Item3", "0223124", 400, 0);
        assertFalse(SILab2.checkCart(Arrays.asList(item), 40));
    }

    // FTT (Item со цена под 300, со попуст и баркод кој започнува со 0)
    @Test
    void testItemPriceBelow300WithDiscountAndBarcodeStartingWith0() {
        Item item = new Item("Item4", "0223124", 299, 0.1f);
        assertFalse(SILab2.checkCart(Arrays.asList(item), 29));
    }

    // FFT (Item со цена под 300, без попуст и баркод кој започнува со 0)
    @Test
    void testItemPriceBelow300WithoutDiscountAndBarcodeStartingWith0() {
        Item item = new Item("Item5", "0223124", 200, 0);
        assertFalse(SILab2.checkCart(Arrays.asList(item), 30));
    }

    // FTF (Item со цена под 300, со попуст и баркод кој започнува со 0)
    @Test
    void testItemPriceBelow300WithDiscountAndBarcodeStartingWith0() {
        Item item = new Item("Item6", "0223124", 298, 0.5f);
        assertFalse(SILab2.checkCart(Arrays.asList(item), 148));
    }

    // TFF (Item со цена над 300, без попуст и баркод кој не започнува со 0)
    @Test
    void testItemPriceAbove300WithoutDiscountAndBarcodeNotStartingWith0() {
        Item item = new Item("Item7", "223124", 477, 0);
        assertTrue(SILab2.checkCart(Arrays.asList(item), 1000));
    }

    // FFF (Item со цена под 300, без попуст и баркод кој не започнува со 0)
    @Test
    void testItemPriceBelow300WithoutDiscountAndBarcodeNotStartingWith0() {
        Item item = new Item("Item8", "223124", 150, 0);
        assertTrue(SILab2.checkCart(Arrays.asList(item), 150));
    }
}
```
### 6. Објаснување на напишаните unit tests
За критериумот Every Branch, тест случаите се дизајнирани да го поминат секој од можните branches во кодот. Со ова се проверува дали сите делови од функцијата се извршуваат правилно во можните случаеви наведени во коментар во кодот.

За критериумот Multiple Condition, тест случаите ги обработуваат сите можни комбинации на услови во функцијата од TTT до FFF како што е напишано во коментари во кодот. 

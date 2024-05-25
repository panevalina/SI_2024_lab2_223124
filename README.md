# Втора лабораториска вежба по Софтверско инженерство
### 1. Лина Панева 223124
### 2. Control Flow Graph
![223124 (3)](https://github.com/panevalina/SI_2024_lab2_223124/assets/164191019/b434de6c-79ac-4219-9a48-eed4ecbcfaf3)
### 3. Цикломатската комплексност 
P (предикатни јазли) + 1 = 9 + 1 = 10
### 4. Every Branch критериум
```
public class SILab2Test {
    @Test
    void everyBranch() {
        RuntimeException exception;

        // Тест случај за влезни параметри null
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertTrue(exception.getMessage().contains("allItems list can't be null!"));

        // Тест случај за влезна листа со еден елемент и цена 0
        Item item1 = new Item("Item1", "13579", 0, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item1), 0));

        // Тест случај за влезна листа со еден елемент и цена над 0
        Item item2 = new Item("Item2", "24680", 660, 0.5f);
        assertTrue(SILab2.checkCart(List.of(item2), 600));

        // Тест случај за влезна листа со еден елемент без баркод
        Item item3 = new Item("Item3", null, 30, 0.2f);
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(item3), 100));
        assertTrue(exception.getMessage().contains("No barcode!"));

        // Тест случај за влезна листа со еден елемент со невалиден баркод
        Item item4 = new Item("Item4", "22*22", 44, 0);
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(item4), 233));
        assertTrue(exception.getMessage().contains("Invalid character in item barcode!"));

        // Тест случај за влезна листа со еден елемент со попуст
        Item item5 = new Item("Item5", "223124", 500, 0.5f);
        assertTrue(SILab2.checkCart(List.of(item5), 250));

        // Тест случај за влезна листа со еден елемент без попуст
        Item item6 = new Item("Item6", "223124", 500, 0);
        assertTrue(SILab2.checkCart(List.of(item6), 500));

        // Тест случај за влезна листа со еден елемент со цена над 300, со попуст и баркод кој почнува со 0
        Item item7 = new Item("Item7", "012345", 301, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item7), 1));
        assertFalse(SILab2.checkCart(List.of(item7), 0));

        // Тест случај за влезна листа со два елементи и сумата помала од вкупната цена
        Item item8 = new Item("Item8", "97531", 300, 0);
        Item item9 = new Item("Item9", "08642", 300, 0);
        assertFalse(SILab2.checkCart(Arrays.asList(item8, item9), 599));
    }
}
```
### 5. Multiple Condition критериум
```
public class SILab2Test {
    @Test
    void everyConiditon() {
        // TTT (Item со цена над од 300, со попуст и баркод кој започнува со 0)
        Item item1 = new Item("Item1", "0223124", 400, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item1), 10));

        // TTF (Item со цена над од 300, со попуст и баркод кој не започнува со 0)
        Item item2 = new Item("Item2", "223124", 400, 0.1f);
        assertFalse(SILab2.checkCart(List.of(item2), 10));

        // TFT (Item со цена над од 300, без попуст и баркод кој започнува со 0)
        Item item3 = new Item("Item3", "0223124", 400, 0);
        assertFalse(SILab2.checkCart(List.of(item3), 40));

        // FTT (Item со цена под 300, со попуст и баркод кој започнува со 0)
        Item item4 = new Item("Item4", "0223124", 299, 0.1f);
        assertFalse(SILab2.checkCart(List.of(item4), 29));

        // FFT (Item со цена под 300, без попуст и баркод кој започнува со 0)
        Item item5 = new Item("Item5", "0223124", 200, 0);
        assertFalse(SILab2.checkCart(List.of(item5), 30));

        // FTF (Item со цена под 300, со попуст и баркод кој започнува со 0)
        Item item6 = new Item("Item6", "0223124", 298, 0.5f);
        assertFalse(SILab2.checkCart(List.of(item6), 148));

        // TFF (Item со цена над 300, без попуст и баркод кој не започнува со 0)
        Item item7 = new Item("Item7", "223124", 477, 0);
        assertTrue(SILab2.checkCart(List.of(item7), 1000));

        // FFF (Item со цена под 300, без попуст и баркод кој не започнува со 0)
        Item item8 = new Item("Item8", "223124", 150, 0);
        assertTrue(SILab2.checkCart(List.of(item8), 150));
    }
}
```
### 6. Објаснување на напишаните unit tests
За критериумот Every Branch, тест случаите ги поминуваат секој од можните branches во кодот. Со ова се проверува дали сите делови од функцијата се извршуваат правилно во можните случаеви наведени во коментарите над секој случај во кодот.

За критериумот Multiple Condition, тест случаите ги обработуваат сите можни комбинации на услови во условот if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0)== '0') како што е напишано над секоја комбинација во коментарите во кодот. 

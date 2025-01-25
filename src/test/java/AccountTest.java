import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class AccountTest {

    private final String name;
    private final boolean expectedValidity;

    public AccountTest(String name, boolean expectedValidity) {
        this.name = name;
        this.expectedValidity = expectedValidity;
    }

    @Parameterized.Parameters(name = "Имя и фамилия: {0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "", false },                          // 0
                { "Т", false },                         // 1
                { "Ти", false },                        // 2
                { "Т м", true },                        // 3
                { "Ти о", true },                       // 4
                { "Тимоти Шаламе", true },              // 13
                { "Тимоти Шаламеййййй", true },         // 18
                { "Тимоти Шаламейййййй", true },        // 19
                { "Тимоти Шаламеййййййй", false },      // 20
                { "Тимоти Шаламейййййййй", false },     // 21
                { "Тимоти Шаламеййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййй", false },// 100
                { " Тимоти Шаламе", false },            // Пробел в начале
                { "Тимоти Шаламе ", false },            // Пробел в конце
                { "ТимотиШаламе", false },              // Нет пробела
                { "Тимоти Шаламе Мих", false },         // Два пробела
                { "Тимоти   Шаламе", false },           // Много пробелов
        });
    }
    @DisplayName("Проверка имя и фамилия")
    @Test
    public void nameValidityTest() {
        Account account = new Account(name);
        assertEquals(expectedValidity, account.checkNameToEmboss());
    }
}

package guru.qa;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Класс с параметризованными тестами")
public class ParametrizeTests {

    @BeforeAll
    static void preconditions() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {
            "Джон",
            "Питер"})
    @ParameterizedTest(name = "Параметризованный тест для поля Full Name в text-box{0}")
    void loginInTexBox(String testName){
        open("text-box");
        $("#userName").setValue(testName);
        $("#userEmail").setValue("roman.mi@mail.ru");
        $("#currentAddress").setValue("Lenina street 105");
        $("#permanentAddress").setValue("No address");
        $("#submit").click();
//        Ожидаемый результат:
        $("[class='border col-md-12 col-sm-12']").shouldHave(
                text(testName),
                text("roman.mi@mail.ru"),
                text("Lenina street 105"),
                text ("No address"));

    }



}

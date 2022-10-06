
import com.codeborne.selenide.SelenideElement;
        import org.junit.jupiter.api.Test;
        import static com.codeborne.selenide.Condition.exactText;
        import static com.codeborne.selenide.Selenide.$;
        import static com.codeborne.selenide.Selenide.open;
        import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCardTest {


        @Test
        void shouldSubmitRequest() {
                open("http://localhost:9999");
                SelenideElement form = $("form");
                form.$("[data-test-id=name] input").setValue("Иван Николаев-Иванов");
                form.$("[data-test-id=phone] input").setValue("+79991234567");
                form.$("[data-test-id=agreement]").click();
                form.$(".button").click();
                $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        }

        @Test
        void incorrectNameField() {
                open("http://localhost:9999");
                SelenideElement form = $("form");
                form.$("[data-test-id=name] input").setValue("11");
                form.$(".button").click();
                $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
        void incorrectPhoneField() {
                open("http://localhost:9999");
                SelenideElement form = $("form");
                form.$("[data-test-id=name] input").setValue("Иван Николаев-Иванов");
                form.$("[data-test-id=phone] input").setValue("+7999");
                form.$(".button").click();
                $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void ifNameFieldEmpty() {
                open("http://localhost:9999");
                SelenideElement form = $("form");
                form.$("[data-test-id=phone] input").setValue("+79991234567");
                form.$(".button").click();
                $(".input_type_text .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        }

        @Test
        void ifPhoneFieldEmpty() {
                open("http://localhost:9999");
                SelenideElement form = $("form");
                form.$("[data-test-id=name] input").setValue("Иван Николаев-Иванов");
                form.$(".button").click();
                $(".input_type_tel .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        }

}

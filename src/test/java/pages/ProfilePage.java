package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private final SelenideElement booksTable = $(".ReactTable"),
            profileUserName = $("#userName-value");

    @Step("Open profile page")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Check user name")
    public ProfilePage checkUser(String userName) {
        profileUserName.shouldHave(text(userName));
        return this;
    }

    @Step("Verify whether the book is NOT in the collection")
    public ProfilePage checkTheBookNotInCollection(String bookName) {
        booksTable.shouldNotHave(text(bookName));
        return this;
    }

    @Step("Verify that the book is in the collection")
    public ProfilePage checkTheBookIsInCollection(String bookName) {
        booksTable.shouldHave(text(bookName));
        return this;
    }
}

package tests;

import api.authorization.AuthorizationApi;
import api.authorization.BooksApi;
import config.App;
import helpers.extensions.WithLogin;
import models.lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

public class CollectionTests extends TestBase {
    BooksApi booksApi = new BooksApi();
    AuthorizationApi authorizationApi = new AuthorizationApi();
    LoginResponseModel authorizationResponse = authorizationApi.userLogin(new LoginRequestModel(App.config.userLogin(), App.config.userPassword()));
    ProfilePage profilePage = new ProfilePage();
    IsbnModel isbn = new IsbnModel(TestData.BOOK_ISBN);
    List<IsbnModel> isbnList = new ArrayList<>();


    @Test
    @DisplayName("Add book test")
    @WithLogin
    public void addBookToCollectionTest() {
        step("Delete all books", () -> {
            booksApi.deleteAllBooks(authorizationResponse);
        });

        isbnList.add(isbn);

        step("Add a book to the collection", () -> {
            booksApi.addBook(authorizationResponse, new AddBookModel(authorizationResponse.getUserId(), isbnList));
        });

        profilePage.openPage()
                .checkUser(authorizationResponse.getUsername())
                .checkTheBookIsInCollection(TestData.BOOK_NAME);
    }

    @Test
    @DisplayName("Delete book test")
    @WithLogin
    public void deleteBookFromCollectionTest() {
        step("Delete all books", () -> {
            booksApi.deleteAllBooks(authorizationResponse);
        });

        isbnList.add(isbn);
        step("Add a book to the collection", () -> {
            booksApi.addBook(authorizationResponse, new AddBookModel(authorizationResponse.getUserId(), isbnList));
        });

        step("Delete the book from the collection", () -> {
            booksApi.deleteBook(authorizationResponse, new DeleteBookRequestModel(TestData.BOOK_ISBN, authorizationResponse.getUserId()));
        });

        profilePage.openPage()
                .checkUser(authorizationResponse.getUsername())
                .checkTheBookNotInCollection(TestData.BOOK_NAME);
    }

}

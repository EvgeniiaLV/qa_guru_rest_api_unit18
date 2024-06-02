package api.authorization;

import io.qameta.allure.Step;
import models.lombok.AddBookModel;
import models.lombok.DeleteBookRequestModel;
import models.lombok.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.DemoQASpec.*;

public class BooksApi {
    @Step("Delete all books")
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParams("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec204);
    }

    @Step("Add a book")
    public void addBook(LoginResponseModel loginResponse, AddBookModel addBookModel) {
        given(requestSpec)
                .body(addBookModel)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec201);
    }

    @Step("Delete a book")
    public void deleteBook(LoginResponseModel loginResponse, DeleteBookRequestModel deleteBookRequest) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(deleteBookRequest)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec204);
    }
}

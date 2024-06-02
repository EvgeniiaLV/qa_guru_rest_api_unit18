package models.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddBookModel {
    private String userId;
    private List<IsbnModel> collectionOfIsbns;
}

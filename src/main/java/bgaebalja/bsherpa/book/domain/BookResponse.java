package bgaebalja.bsherpa.book.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BookResponse {

  private static final String BOOK_ID_VALUE = "교재 ID";
  private static final String BOOK_ID_EXAMPLE = "1111";

  private static final String BOOK_NAME_VALUE = "교재명";
  private static final String NAME_EXAMPLE = "수학1-1";

  private static final String SUBJECT_CODE_VALUE = "과목 CODE";
  private static final String SUBJECT_CODE_EXAMPLE = "1111";

  private static final String SUBJECT_NAME_VALUE = "과목명";
  private static final String SUBJECT_NAME_EXAMPLE = "1111";

  @ApiModelProperty(value = BOOK_ID_VALUE, example = BOOK_ID_EXAMPLE)
  private String subjectId;

  @ApiModelProperty(value = BOOK_NAME_VALUE, example = NAME_EXAMPLE)
  private String bookName;

  private String author;

  @ApiModelProperty(value = SUBJECT_CODE_VALUE, example = SUBJECT_CODE_EXAMPLE)
  private String subjectCode;

  @ApiModelProperty(value = SUBJECT_NAME_VALUE, example = SUBJECT_NAME_EXAMPLE)
  private String subjectName;

  public BookResponse(Book book) {
    subjectId = book.getBookId();
    bookName = book.getName();
    author = book.getAuthor();
    subjectCode = book.getSubject().getCode();
    subjectName = book.getSubject().getName();
  }
}

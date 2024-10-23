package bgaebalja.bsherpa.book.domain;

import bgaebalja.bsherpa.subject.domain.Subject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BookResponse {

  private static final String BOOK_ID_VALUE = "교재 ID";
  private static final String BOOK_ID_EXAMPLE = "1111";

  private static final String BOOK_NAME_VALUE = "교재명";
  private static final String NAME_EXAMPLE = "수학1-1";

  private static final String SUBJECT_ID_VALUE = "과목 ID";
  private static final String SUBJECT_ID_EXAMPLE = "1111";

  @ApiModelProperty(value = BOOK_ID_VALUE, example = BOOK_ID_EXAMPLE)
  private String bookId;

  @ApiModelProperty(value = BOOK_NAME_VALUE, example = NAME_EXAMPLE)
  private String bookName;

  @ApiModelProperty(value = SUBJECT_ID_VALUE, example = SUBJECT_ID_EXAMPLE)
  private Long subjectId;

  public BookResponse(Book book) {
    this.bookId = book.getBookId();
    this.bookName = book.getName();
    this.subjectId = book.getSubject().getId();
  }
}

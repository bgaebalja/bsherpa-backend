package bgaebalja.bsherpa.book.controller;

import static org.springframework.http.HttpStatus.OK;

import bgaebalja.bsherpa.book.domain.BookResponse;
import bgaebalja.bsherpa.book.service.BookService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/books")
@RestController
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  private static final String GET_BOOKS = "교재 정보 조회";
  private static final String GET_BOOKS_DESCRIPTION = "교재 ID로 교재 정보를 조회할 수 있습니다.";


  @GetMapping()
  @ApiOperation(
      value = GET_BOOKS,
      notes = GET_BOOKS_DESCRIPTION,
      response = BookResponse.class
  )
  public ResponseEntity<?> getAllBooks() {
    bookService.getAllBooks();
    return ResponseEntity.status(OK).body(new Result<>(bookService.getAllBooks()));
  }

  @Data
  @AllArgsConstructor
  static class Result<T>{
    private T data;
  }

}

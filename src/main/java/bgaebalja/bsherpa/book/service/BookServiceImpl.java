package bgaebalja.bsherpa.book.service;

import bgaebalja.bsherpa.book.domain.BookResponse;
import bgaebalja.bsherpa.book.repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  public List<BookResponse> getAllBooks() {
   return bookRepository.findAll().stream().map(BookResponse::new)
        .collect(Collectors.toList());
  }
}

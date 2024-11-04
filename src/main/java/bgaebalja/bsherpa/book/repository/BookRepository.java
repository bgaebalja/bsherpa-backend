package bgaebalja.bsherpa.book.repository;

import bgaebalja.bsherpa.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByBookId(String bookId);

}

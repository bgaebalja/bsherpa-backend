package bgaebalja.bsherpa.book.repository;

import bgaebalja.bsherpa.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}

package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("ITBooks S.A.","Highway Street 52", "New York","New York","09-990");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Doman Driven Design","123123");

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book j2ee = new Book("J2EEE","234234");

        rod.getBooks().add(j2ee);
        j2ee.getAuthors().add(rod);

        j2ee.setPublisher(publisher);
        publisher.getBooks().add(j2ee);

        authorRepository.save(rod);
        bookRepository.save(j2ee);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number od Books: " + bookRepository.count() +
                            "\nNumber of Publishers: " + publisherRepository.count() +
                            "\nPublisher Number of Books: " + publisher.getBooks().size());

    }
}

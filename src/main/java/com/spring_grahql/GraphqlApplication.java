package com.spring_grahql;

import com.spring_grahql.model.Author;
import com.spring_grahql.model.Book;
import com.spring_grahql.repository.AuthorRepository;
import com.spring_grahql.repository.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
		return args -> {
			Author josh = authorRepository.save(
					Author.builder()
						.name("Josh")
						.age(30)
						.build()
			);
			Author mark = authorRepository.save(
					Author.builder()
							.name("Mark")
							.age(45)
							.build()
			);

			bookRepository.saveAll(List.of(
					Book.builder()
							.title("Reactive Spring")
							.author(josh)
							.build(),
					Book.builder()
							.title("Cloud Native Java")
							.author(mark)
							.build(),
					Book.builder()
							.title("Spring Boot Up & Reactive")
							.author(mark)
							.build()
			));
		};
	}
}

package com.example.reactive;

import java.time.Duration;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@SpringBootApplication
@RestController
public class ReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}
	
	@GetMapping(path = "documents", produces = "application/json")
	Flux<Document> fetchDocuments() {
		return Flux.interval(Duration.ofSeconds(1))
				.take(9)
				.map(i -> i.intValue() + 1)
				.map(Document::new);
	}
	
	@GetMapping(path = "streams", produces = "application/stream+json")
	Flux<Document> fetchDocumentsStream() {
		return Flux.interval(Duration.ofSeconds(1))
				.take(9)
				.map(i -> i.intValue() + 1)
				.map(Document::new);
	}
	
}

class Document {
	
	private int catalogNumber;
	private String content; 
	
	public Document(int number) {
		catalogNumber = number;
		content = UUID.randomUUID().toString();
	}
	
	public String getContent() {
		return content;
	}

	public int getCatalogNumber() {
		return catalogNumber;
	}
	
}

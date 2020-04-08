package com.example.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodolistApplication {

//	@Autowired
//	private CardService cardService;

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

//	public void setCardService(CardService cardService) {
//		this.cardService = cardService;
//	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Imprimindo testes em linha de comando");
//		System.out.println(cardService.listAllCards());
//	}

}

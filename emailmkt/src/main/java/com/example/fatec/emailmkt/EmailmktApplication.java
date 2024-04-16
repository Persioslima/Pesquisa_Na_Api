package com.example.fatec.emailmkt;

import com.example.fatec.emailmkt.services.ConsomeApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class EmailmktApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(EmailmktApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsomeApi lerApi = new ConsomeApi();

			String dados = lerApi.obterDados("https://api.escuelajs.co/api/v1/products/");
			System.out.println(dados);

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(dados);

		List<String> nomeList = new ArrayList<>();
		List<String> precoList = new ArrayList<>();
		jsonNode.forEach(node -> {
			double preco = node.get("price").asDouble();
			if (preco <= 30.0) {
				nomeList.add(node.get("title").asText());
				precoList.add(node.get("price").asText());
			}
		});

		for (int i = 0; i < nomeList.size(); i++) {
			System.out.println("Nome: " + nomeList.get(i) + ", Preço: " + precoList.get(i));
		}

	}
}

package com.text;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("./data/tickets/Билет 1.json");
        JsonNode jsonNode = mapper.readTree(file);

        for (JsonNode node : jsonNode) {
            System.out.println(node.get("question").asText());
            for (int i = 0; i < node.get("answers").size(); i++) {
                System.out.println(node.get("answers").get(i).get("answer_text"));
                System.out.println(node.get("answers").get(i).get("is_correct"));
            }
        }
    }
}


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
            String ticketNumber = node.get("ticket_number").asText();
            String title = node.get("title").asText();
            String questionText = node.get("question").asText();
            String imagePath = node.get("image").asText();
            String id = node.get("id").asText();
            String answerTip = node.get("answer_tip").asText();

            for (JsonNode topic : node.get("topic")) {
                System.out.println(topic.asText());
            }

            for (int i = 0; i < node.get("answers").size(); i++) {
                System.out.println(node.get("answers").get(i).get("answer_text").asText());
                System.out.println(node.get("answers").get(i).get("is_correct").asBoolean());
            }
        }
    }
}


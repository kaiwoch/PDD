package com.text;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("./data/tickets/Билет 1.json");
        JsonNode jsonNode = mapper.readTree(file);

        for (JsonNode node : jsonNode) {
            int ticketNumber = Integer.parseInt(node.get("ticket_number").asText().split(" ")[1]);
            int title = Integer.parseInt(node.get("title").asText().split(" ")[1]);
            String questionText = node.get("question").asText();
            String imagePath = node.get("image").asText();
            String id = node.get("id").asText();
            String answerTip = node.get("answer_tip").asText();

            List<String> topics = new ArrayList<>();

            for (JsonNode topic : node.get("topic")) {
                topics.add(topic.asText());
            }

            String topicsStr = String.join(", ", topics);

            for (int i = 0; i < node.get("answers").size(); i++) {
                System.out.println(node.get("answers").get(i).get("answer_text").asText());
                System.out.println(node.get("answers").get(i).get("is_correct").asBoolean());
            }
        }
    }
}


package com.text;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("./data/tickets/Билет 1.json");
        JsonNode jsonNode = mapper.readTree(file);

        for (JsonNode node : jsonNode) {
            short ticketNumber = Short.parseShort(node.get("ticket_number").asText().split(" ")[1]);
            short title = Short.parseShort(node.get("title").asText().split(" ")[1]);
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

    public static long insertQuestion(String questionText, String topics, String answerTip, short ticketNumber, short title, String imagePath, String sourceId) {
        String username = "postgres";
        String password = System.getenv("DB_PASSWORD");

        long questionId = -1l;

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdd_app", username, password)) {
            String sql = "INSERT INTO questions (question_text, category, difficulty, explanation, ticket_number, question_number, image_path, source_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n" +
                    "RETURNING id;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, questionText);
                statement.setString(2, topics);
                statement.setNull(3, Types.SMALLINT);
                statement.setString(4, answerTip);
                statement.setShort(5, ticketNumber);
                statement.setShort(6, title);
                statement.setString(7, imagePath);
                statement.setString(8, sourceId);

                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        questionId = result.getLong("id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questionId;
    }
}


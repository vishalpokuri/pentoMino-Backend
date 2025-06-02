package com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;

public class JettyCalendarServer extends AbstractHandler {
  public static void main(String[] args) throws Exception {
    String port = System.getenv("PORT");
    if (port == null) {
      port = "8080"; // Fallback for local dev
    }
    Server server = new Server(Integer.parseInt(port));

    server.setHandler(new JettyCalendarServer());
    server.start();
    System.out.println("Server started at http://localhost:8080/");
    server.join();
  }

  @Override
  public void handle(String target, Request baseRequest,
      HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_OK);
    baseRequest.setHandled(true);

    // Handle CORS
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type");

    if (request.getMethod().equals("OPTIONS")) {
      return; // Handle preflight requests
    }

    if (target.equals("/solve") && request.getMethod().equals("POST")) {
      try {
        // Read request body
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
          requestBody.append(line);
        }

        // Parse JSON
        JSONParser parser = new JSONParser();
        JSONObject requestJson = (JSONObject) parser.parse(requestBody.toString());

        // Create a new puzzle solver and solve the puzzle
        PuzzleSolver solver = new PuzzleSolver();
        JSONObject responseJson = solver.solve(requestJson);

        response.getWriter().println(responseJson.toJSONString());
      } catch (Exception e) {
        JSONObject errorJson = new JSONObject();
        errorJson.put("error", e.getMessage());
        errorJson.put("status", "error");
        response.getWriter().println(errorJson.toJSONString());
      }
    } else {
      // Also serve a simple HTML page at the root
      if (target.equals("/") && request.getMethod().equals("GET")) {
        response.setContentType("text/html");
        response.getWriter().println(
            "<!DOCTYPE html>" +
                "<html>" +
                "<head><title>Calendar Puzzle Solver</title></head>" +
                "<body>" +
                "<h1>Calendar Puzzle Solver</h1>" +
                "<p>Send a POST request to /solve with JSON data:</p>" +
                "<pre>{\"month\": \"Jan\", \"day\": \"1\", \"weekday\": \"Mon\"}</pre>" +
                "</body>" +
                "</html>");
      } else {
        response.getWriter().println("{\"error\": \"Invalid endpoint or method\"}");
      }
    }
  }
}
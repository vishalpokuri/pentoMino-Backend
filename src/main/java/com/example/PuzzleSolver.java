package com.example;

import java.util.*;
import org.json.simple.JSONObject;

public class PuzzleSolver {
  private int[][] board;
  private int[] used;
  private ArrayList<ArrayList<int[][]>> allOrientations;
  private String solution;

  public PuzzleSolver() {
    // Initialize core structures
    board = new int[6][9];
    used = new int[10];
    Arrays.fill(used, 0);
    allOrientations = PieceUtils.returnAllOrientations();
  }

  public JSONObject solve(JSONObject requestJson) {
    // Reset the board and used pieces for a new puzzle
    board = new int[6][9];
    Arrays.fill(used, 0);
    PieceUtils.initialiseBoard(board);

    // Get parameters from request
    String date = requestJson.get("day").toString();
    String day = requestJson.get("weekday").toString();
    String month = requestJson.get("month").toString();

    // Get positions from calendar map
    int[] idxDate = PieceUtils.findrevealMapIdx(date);
    int[] idxDay = PieceUtils.findrevealMapIdx(day);
    int[] idxMonth = PieceUtils.findrevealMapIdx(month);
    // System.out.println("Date received: " + date);
    // System.out.println("Day received: " + day);
    // System.out.println("Month received: " + month);

    // System.out
    // .println("Looking up date: " + date + ", found: " + (idxDate != null ?
    // Arrays.toString(idxDate) : "null"));
    // System.out.println("Looking up day: " + day + ", found: " + (idxDay != null ?
    // Arrays.toString(idxDay) : "null"));
    // System.out
    // .println("Looking up month: " + month + ", found: " + (idxMonth != null ?
    // Arrays.toString(idxMonth) : "null"));

    // Fix positions on board
    PieceUtils.fixPositions(idxMonth, board);
    PieceUtils.fixPositions(idxDate, board);
    PieceUtils.fixPositions(idxDay, board);

    // PieceUtils.display(board);
    // Solve the puzzle
    boolean solved = recursion(board);

    // Create and return the response
    JSONObject responseJson = new JSONObject();

    if (solved) {
      responseJson.put("status", "success");
      responseJson.put("solution", convertBoardToJson(board));
    } else {
      responseJson.put("status", "error");
      responseJson.put("message", "No solution found");
    }

    return responseJson;
  }

  private JSONObject convertBoardToJson(int[][] board) {
    JSONObject boardJson = new JSONObject();

    // Convert the board to a format that can be sent to the frontend
    List<List<Integer>> boardData = new ArrayList<>();

    for (int i = 0; i < board.length; i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < board[0].length; j++) {
        row.add(board[i][j]);
      }
      boardData.add(row);
    }

    boardJson.put("board", boardData);
    return boardJson;
  }

  private boolean recursion(int[][] board) {
    // Find the first empty cell
    int[] cell = PieceUtils.emptyCellonBoard(board);

    // Base case
    if (cell[0] == Integer.MIN_VALUE) {
      // Solution found!
      return true;
    }

    // Recursion
    for (int i = 0; i < 10; i++) {
      if (used[i] == 1)
        continue;

      ArrayList<int[][]> otnsOfi = allOrientations.get(i);
      for (int j = 0; j < otnsOfi.size(); j++) {
        int[][] selectedPiece = otnsOfi.get(j);

        if (PieceUtils.placePiece(board, selectedPiece, cell, i + 1)) {
          used[i] = 1;
          if (recursion(board)) {
            return true;
          }

          used[i] = 0;
          PieceUtils.removePiece(board, selectedPiece, cell);
        }
      }
    }

    return false;
  }
}
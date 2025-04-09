package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PieceUtils {

  public static final Map<String, int[]> revealMap = Map.ofEntries(
      Map.entry("Jan", new int[] { 0, 0 }),
      Map.entry("Feb", new int[] { 0, 1 }),
      Map.entry("Mar", new int[] { 0, 2 }),
      Map.entry("Apr", new int[] { 0, 3 }),
      Map.entry("1", new int[] { 0, 4 }),
      Map.entry("2", new int[] { 0, 5 }),
      Map.entry("3", new int[] { 0, 6 }),
      Map.entry("Mon", new int[] { 0, 7 }),
      Map.entry("Tue", new int[] { 0, 8 }),
      Map.entry("May", new int[] { 1, 0 }),
      Map.entry("4", new int[] { 1, 1 }),
      Map.entry("5", new int[] { 1, 2 }),
      Map.entry("6", new int[] { 1, 3 }),
      Map.entry("7", new int[] { 1, 4 }),
      Map.entry("8", new int[] { 1, 5 }),
      Map.entry("9", new int[] { 1, 6 }),
      Map.entry("Wed", new int[] { 1, 7 }),
      Map.entry("Jun", new int[] { 2, 0 }),
      Map.entry("10", new int[] { 2, 1 }),
      Map.entry("11", new int[] { 2, 2 }),
      Map.entry("12", new int[] { 2, 3 }),
      Map.entry("13", new int[] { 2, 4 }),
      Map.entry("31", new int[] { 2, 5 }),
      Map.entry("15", new int[] { 2, 6 }),
      Map.entry("Thu", new int[] { 2, 7 }),
      Map.entry("Jul", new int[] { 3, 0 }),
      Map.entry("16", new int[] { 3, 1 }),
      Map.entry("17", new int[] { 3, 2 }),
      Map.entry("18", new int[] { 3, 3 }),
      Map.entry("19", new int[] { 3, 4 }),
      Map.entry("20", new int[] { 3, 5 }),
      Map.entry("21", new int[] { 3, 6 }),
      Map.entry("Fri", new int[] { 3, 7 }),
      Map.entry("Sat", new int[] { 3, 8 }),
      Map.entry("Aug", new int[] { 4, 0 }),
      Map.entry("22", new int[] { 4, 1 }),
      Map.entry("23", new int[] { 4, 2 }),
      Map.entry("24", new int[] { 4, 3 }),
      Map.entry("25", new int[] { 4, 4 }),
      Map.entry("26", new int[] { 4, 5 }),
      Map.entry("27", new int[] { 4, 6 }),
      Map.entry("Sep", new int[] { 5, 0 }),
      Map.entry("Oct", new int[] { 5, 1 }),
      Map.entry("Nov", new int[] { 5, 2 }),
      Map.entry("Dec", new int[] { 5, 3 }),
      Map.entry("28", new int[] { 5, 4 }),
      Map.entry("29", new int[] { 5, 5 }),
      Map.entry("30", new int[] { 5, 6 }),
      Map.entry("14", new int[] { 5, 7 }),
      Map.entry("X", new int[] { 5, 8 }));

  public static ArrayList<ArrayList<int[][]>> returnAllOrientations() {

    ArrayList<ArrayList<int[][]>> allOrientations = new ArrayList<>();
    // pieces initialization
    int[][] pieceI = { { 1 }, { 1 }, { 1 }, { 1 }, { 1 } }; // 0
    int[][] pieceBaton = { { 1, 0 }, { 1, 0 }, { 1, 1 }, { 1, 0 } }; // 1
    int[][] pieceL2x4 = { { 1, 0 }, { 1, 0 }, { 1, 0 }, { 1, 1 } }; // 2
    int[][] pieceBend2x4 = { { 1, 0 }, { 1, 1 }, { 0, 1 }, { 0, 1 } }; // 3
    int[][] pieceBend3x3 = { { 1, 1, 0 }, { 0, 1, 0 }, { 0, 1, 1 } }; // 4
    int[][] pieceBuilding = { { 1, 0 }, { 1, 1 }, { 1, 1 } }; // 5
    int[][] pieceL3x3 = { { 1, 0, 0 }, { 1, 0, 0 }, { 1, 1, 1 } }; // 6
    int[][] pieceU = { { 1, 0, 1 }, { 1, 1, 1 } }; // 7
    int[][] pieceT = { { 1, 1, 1 }, { 0, 1, 0 }, { 0, 1, 0 } }; // 8
    int[][] pieceTap = { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 0, 1 } }; // 9

    // Create all orentations
    ArrayList<int[][]> orientsPieceTap = PieceUtils.getAllOrientations(pieceTap);
    ArrayList<int[][]> orientsPieceI = PieceUtils.getAllOrientations(pieceI);
    ArrayList<int[][]> orientsPieceBaton = PieceUtils.getAllOrientations(pieceBaton);
    ArrayList<int[][]> orientsPieceL2x4 = PieceUtils.getAllOrientations(pieceL2x4);
    ArrayList<int[][]> orientsPieceBend2x4 = PieceUtils.getAllOrientations(pieceBend2x4);
    ArrayList<int[][]> orientsPieceBend3x3 = PieceUtils.getAllOrientations(pieceBend3x3);
    ArrayList<int[][]> orientsPieceBuilding = PieceUtils.getAllOrientations(pieceBuilding);
    ArrayList<int[][]> orientsPieceL3x3 = PieceUtils.getAllOrientations(pieceL3x3);
    ArrayList<int[][]> orientsPieceU = PieceUtils.getAllOrientations(pieceU);
    ArrayList<int[][]> orientsPieceT = PieceUtils.getAllOrientations(pieceT);

    allOrientations.add(orientsPieceTap);
    allOrientations.add(orientsPieceI);
    allOrientations.add(orientsPieceBaton);
    allOrientations.add(orientsPieceL2x4);
    allOrientations.add(orientsPieceBend2x4);
    allOrientations.add(orientsPieceBend3x3);
    allOrientations.add(orientsPieceBuilding);
    allOrientations.add(orientsPieceL3x3);
    allOrientations.add(orientsPieceU);
    allOrientations.add(orientsPieceT);

    return allOrientations;

  }

  public static int[] findrevealMapIdx(String payload) {
    return revealMap.get(payload);
  }

  public static void fixPositions(int[] pos, int[][] board) {
    board[pos[0]][pos[1]] = -1;
  }

  public static void display(int[][] item) {
    for (var x : item) {
      for (var i : x) {
        if (i == -1) {
          System.out.print(i + " ");
        } else {
          System.out.print(" " + i + " ");
        }
      }
      System.out.println();
    }
  }

  public static void initialiseBoard(int[][] board) {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 9; j++) {
        if ((i == 5 && j == 8)) {
          board[i][j] = -1;
        } else {
          board[i][j] = 0;
        }
      }
    }
  }

  public static ArrayList<int[][]> getAllOrientations(int[][] piece) {
    ArrayList<int[][]> results = new ArrayList<>();
    Set<String> seen = new HashSet<>();

    // Logic to remove duplicates
    // Step -1: Use serialize function to convert into a string, check if it
    // not contains in the set, add or skip

    for (int i = 0; i < 4; i++) {
      piece = rotate(piece);
      if (!seen.contains(serialise(piece))) {
        results.add(piece);
        seen.add(serialise(piece));
      }
    }

    return results;
  }

  public static int[][] rotate(int[][] piece) {
    int row = piece.length;
    int col = piece[0].length;

    int[][] rotated = new int[col][row];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        rotated[j][row - 1 - i] = piece[i][j];
      }
    }
    return rotated;
  }

  public static String serialise(int[][] orientation) {
    StringBuilder sb = new StringBuilder();
    for (int[] row : orientation) {
      for (int item : row) {
        sb.append(item);
      }
      sb.append(";");
    }
    return sb.toString();
  }

  public static int[] emptyCellonBoard(int[][] board) {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == 0) {
          return new int[] { i, j };
        }
      }
    }
    return new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE };
  }

  // collision function, and piece start index picker
  public static int[] touchablePieceIndex(int[][] piece) {
    for (int i = 0; i < piece.length; i++) {
      for (int j = 0; j < piece[0].length; j++) {
        if (piece[i][j] == 1) {
          return new int[] { i, j };
        }
      }
    }
    return new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE };
  }

  public static boolean placePiece(int[][] board, int[][] piece, int[] cell, int pieceId) {
    int[] anchor = touchablePieceIndex(piece); // [row, col] in the piece
    int baseRow = cell[0] - anchor[0];
    int baseCol = cell[1] - anchor[1];

    if (!fits(board, piece, cell)) {
      return false;
    }

    for (int i = 0; i < piece.length; i++) {
      for (int j = 0; j < piece[0].length; j++) {
        if (piece[i][j] == 1) {
          int r = baseRow + i;
          int c = baseCol + j;
          board[r][c] = pieceId;
        }
      }
    }
    return true;
  }

  public static void removePiece(int[][] board, int[][] piece, int[] cell) {

    int[] anchor = touchablePieceIndex(piece); // [row, col] in the piece

    int baseRow = cell[0] - anchor[0];
    int baseCol = cell[1] - anchor[1];

    for (int i = 0; i < piece.length; i++) {
      for (int j = 0; j < piece[0].length; j++) {
        if (piece[i][j] == 1) {
          int r = baseRow + i;
          int c = baseCol + j;
          board[r][c] = 0; // Reset to empty
        }
      }
    }
  }

  public static boolean fits(int[][] board, int[][] piece, int[] cell) {

    int[] anchor = touchablePieceIndex(piece); // [row, col] in the piece

    int baseRow = cell[0] - anchor[0];
    int baseCol = cell[1] - anchor[1];

    for (int i = 0; i < piece.length; i++) {
      for (int j = 0; j < piece[0].length; j++) {
        if (piece[i][j] == 1) {
          int r = baseRow + i;
          int c = baseCol + j;
          // boundary check
          if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
            return false;
          }
          // collision check (if cell unavailable)
          if (board[r][c] != 0) {
            return false;
          }
        }
      }
    }

    return true;
  }

  public static void displayColorful(int[][] board) {
    // ANSI color codes for background colors
    String[] colors = {
        "\u001B[41m", // Red
        "\u001B[42m", // Green
        "\u001B[43m", // Yellow
        "\u001B[44m", // Blue
        "\u001B[45m", // Magenta
        "\u001B[46m", // Cyan
        "\u001B[47m", // White
        "\u001B[100m", // Bright Black
        "\u001B[101m", // Bright Red
        "\u001B[102m" // Bright Green
    };
    String reset = "\u001B[0m";

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == -1) {
          // Special character for date/month/day cells
          System.out.print("  ");
        } else if (board[i][j] == 0) {
          // Empty cell
          System.out.print("    ");
        } else {
          // The board already contains the piece ID
          int pieceId = board[i][j];
          System.out.print(colors[pieceId - 1] + "  " + reset);
        }
      }
      System.out.println();
    }
  }

}

package com.chess.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.File;
import common.Location;
import com.chess.piece.AbstractPiece;
import com.chess.piece.PieceColor;
import com.chess.piece.PieceFactory;
import com.chess.squares.Square;
import com.chess.squares.SquareColor;

public class Board {

    public static final Integer BOARD_LENGTH = 8;
    private final Map<Location, Square> locationSquareMap;

    Square[][] boardSquares = new Square[BOARD_LENGTH][BOARD_LENGTH];

    private final List<AbstractPiece> whitePieces = new ArrayList<>();
    private final List<AbstractPiece> blackPieces = new ArrayList<>();

    public Board() {
        locationSquareMap = new HashMap<>();
        Map<Location, AbstractPiece> pieces = PieceFactory.getPieces();
        for(int i = 0; i < boardSquares.length; i++) {
            int column = 0;
            SquareColor currentColor = (i % 2 == 0) ? SquareColor.WHITE : SquareColor.BLACK;
            for(File file : File.values()) {
                Square newSquare = new Square(currentColor, new Location(file,  BOARD_LENGTH - i));
                if (pieces.containsKey(newSquare.getLocation())) {
                    AbstractPiece piece = pieces.get(newSquare.getLocation());
                    newSquare.setCurrentPiece(piece);
                    newSquare.setOccupied(true);
                    piece.setCurrentSquare(newSquare);
                    if (piece.getPieceColor().equals(PieceColor.BLACK)) {
                        blackPieces.add(piece);
                    } else {
                        whitePieces.add(piece);
                    }
                }
                boardSquares[i][column] = newSquare;
                locationSquareMap.put(newSquare.getLocation(), newSquare);
                currentColor = (currentColor == SquareColor.BLACK) ? SquareColor.WHITE : SquareColor.BLACK;
                column++;
            }
        }
    }

    public Map<Location, Square> getLocationSquareMap() {
        return locationSquareMap;
    }

    public List<AbstractPiece> getWhitePieces() { return whitePieces; }

    public List<AbstractPiece> getBlackPieces() {
        return blackPieces;
    }

    public Square[][] getBoardSquares() {
        return boardSquares;
    }

    public void printBoard() {
        for(int i = 0; i < boardSquares.length; i++) {
            System.out.print(BOARD_LENGTH - i + " ");
            for(int j = 0; j < boardSquares[i].length; j++) {
                if (boardSquares[i][j].isOccupied()) {
                    AbstractPiece piece = boardSquares[i][j].getCurrentPiece();
                    System.out.print(piece.getPieceColor().toString().charAt(0) + "_" + piece.getName().charAt(0) + " ");
                } else {
                    // empty square
                    System.out.print(" -  ");
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for(File file : File.values()) {
            System.out.print(" " + file.name() + "  ");
        }
        System.out.println();
    }

}
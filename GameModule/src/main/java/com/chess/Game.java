package com.chess;

import java.util.*;

import com.chess.board.Board;
import common.Location;
import com.chess.piece.AbstractPiece;
import com.chess.squares.Square;
import com.chess.squares.SquareColor;

import static com.chess.board.Board.BOARD_LENGTH;

public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
        ArrayList<AbstractPiece> whitePieces = new ArrayList<>();
        ArrayList<AbstractPiece> blackPieces = new ArrayList<>();
        for(int i = 0; i < BOARD_LENGTH; i++) {
            for(int j = 0; j < 2; j++) {
                blackPieces.add(board.getBoardSquares()[j][i].getCurrentPiece());
                whitePieces.add(board.getBoardSquares()[BOARD_LENGTH - 1 - j][BOARD_LENGTH - 1 - i].getCurrentPiece());
            }
        }
        SquareColor currentPlayer = SquareColor.WHITE;
        boolean finish = false;
        int blackIterator = 0;
        int whiteIterator = 0;
        while(!whitePieces.isEmpty() && !blackPieces.isEmpty()) {
            Random rand = new Random();
            Location randomMove;
            AbstractPiece randomElement;
            List<Location> validMoves = new ArrayList<>();
            if(currentPlayer == SquareColor.WHITE) {
                if(whiteIterator >= whitePieces.size()) {
                    whiteIterator = 0;
                }
                randomElement = whitePieces.get(whiteIterator);
                validMoves.addAll(randomElement.getValidMoves(board));
                int counter = 0;
                while(validMoves.isEmpty()) {
                    counter++;
                    whiteIterator++;
                    if(counter >= whitePieces.size()){
                        finish = true;
                        break;
                    }
                    if(whiteIterator >= whitePieces.size()) {
                        whiteIterator = 0;
                    }
                    randomElement = whitePieces.get(whiteIterator);
                    validMoves.addAll(randomElement.getValidMoves(board));
                }
            } else {
                if(blackIterator >= blackPieces.size()) {
                    blackIterator = 0;
                }
                randomElement = blackPieces.get(blackIterator);
                validMoves.addAll(randomElement.getValidMoves(board));
                int counter = 0;
                while(validMoves.isEmpty()) {
                    counter++;
                    blackIterator++;
                    if(counter >= blackPieces.size()){
                        finish = true;
                        break;
                    }
                    if(blackIterator >= blackPieces.size()) {
                        blackIterator = 0;
                    }
                    randomElement = blackPieces.get(blackIterator);
                    validMoves.addAll(randomElement.getValidMoves(board));
                }
            }
            if(finish) break;
            randomMove = validMoves.get(rand.nextInt(validMoves.size()));


            Square fromSq = board.getLocationSquareMap().get(randomElement.getCurrentSquare().getLocation());
            Square toSq = board.getLocationSquareMap().get(randomMove);
            if(toSq.isOccupied()){
                whitePieces.remove(toSq.getCurrentPiece());
                blackPieces.remove(toSq.getCurrentPiece());
            }
            System.out.println(randomElement.getPieceColor() + " " + randomElement.getName() + " : " +
                    randomElement.getCurrentSquare().getLocation() + " -> " + randomMove);
            fromSq.getCurrentPiece().makeMove(toSq);
            fromSq.reset();

            board.printBoard();
            validMoves.clear();
            currentPlayer = (currentPlayer == SquareColor.WHITE) ? SquareColor.BLACK : SquareColor.WHITE;
        }
        if(whitePieces.isEmpty() || finish && currentPlayer == SquareColor.WHITE) {
            System.out.println("Black wins");
        }
        if(blackPieces.isEmpty() || finish && currentPlayer == SquareColor.BLACK) {
            System.out.println("White wins");
        }
    }
}
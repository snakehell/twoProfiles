package com.chess.piece;

import java.util.ArrayList;
import java.util.List;

import common.Location;
import com.chess.squares.Square;
import com.chess.board.Board;

public class Queen extends AbstractPiece implements ChessPieceInterface {

    private ChessPieceInterface bishop;
    private ChessPieceInterface rook;


    public Queen(PieceColor pieceColor) {
        this(pieceColor, new Bishop(pieceColor), new Rook(pieceColor));
    }

    public Queen(PieceColor pieceColor, ChessPieceInterface bishop, ChessPieceInterface rook) {
        super(pieceColor);
        this.name = "Queen";
        this.bishop = bishop;
        this.rook = rook;
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentSquare()));
        moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentSquare()));
        return moveCandidates;
    }

    @Override
    public List<Location> getValidMoves(Board board, Square square) {
        return null;
    }

    @Override
    public void makeMove(Square square) {
        this.getCurrentSquare().reset();
        this.setCurrentSquare(square);
        square.setCurrentPiece(this);
        square.setOccupied(true);
    }
}
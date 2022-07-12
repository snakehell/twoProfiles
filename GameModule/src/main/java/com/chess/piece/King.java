package com.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import common.Location;
import com.chess.squares.Square;
import com.chess.board.Board;

public class King extends AbstractPiece implements ChessPieceInterface {

    private final ChessPieceInterface rook;
    private final ChessPieceInterface bishop;

    public King(PieceColor pieceColor) {
        this(pieceColor, new Bishop(pieceColor), new Rook(pieceColor));
    }

    public King(PieceColor pieceColor, ChessPieceInterface bishop, ChessPieceInterface rook) {
        super(pieceColor);
        this.name = "King";
        this.bishop = bishop;
        this.rook = rook;
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentSquare()));
        moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentSquare()));
        Location current = this.getCurrentSquare().getLocation();
        return moveCandidates.stream()
                .filter(candidate -> (
                        Math.abs(candidate.getFile().ordinal() - current.getFile().ordinal()) == 1 && Math.abs(candidate.getRank() - current.getRank()) <= 1 ||
                                Math.abs(candidate.getFile().ordinal() - current.getFile().ordinal()) <= 1 && Math.abs(candidate.getRank() - current.getRank()) == 1))
                .collect(Collectors.toList());
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
package com.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import common.Location;
import com.chess.factory.LocationFactory;
import com.chess.squares.Square;
import com.chess.board.Board;

public class Pawn extends AbstractPiece implements ChessPieceInterface {

    private boolean isFirstMove = true;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "Pawn";
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        Location current = this.getCurrentSquare().getLocation();
        int sign = (pieceColor.equals(PieceColor.BLACK)) ? -1 : 1;
        moveCandidates.add(LocationFactory
                .build(current, 0, sign));
        if(isFirstMove) {
            moveCandidates.add(LocationFactory
                    .build(current, 0, 2 * sign));
        }

        moveCandidates.add(LocationFactory.build(current, 1, sign));
        moveCandidates.add(LocationFactory.build(current, -1, sign));
        Map<Location, Square> squareMap = board.getLocationSquareMap();
        List<Location> validMoves = moveCandidates.stream()
                .filter(squareMap::containsKey)
                .collect(Collectors.toList());

        return validMoves.stream().filter((candidate) -> {

            // occupied in front.
            if (squareMap.get(candidate).isOccupied() &&
                    candidate.getFile().equals(this.getCurrentSquare().getLocation().getFile())) {
                return false;
            }

            // occupied on diagonal with opposite color
            if (squareMap.get(candidate).isOccupied() &&
                    squareMap.get(candidate).getCurrentPiece().getPieceColor().equals(this.getPieceColor()) &&
                    candidate.getFile().equals(this.getCurrentSquare().getLocation().getFile())
            ) {
                return false;
            }

            if (!squareMap.get(candidate).isOccupied() &&
                    !candidate.getFile().equals(this.getCurrentSquare().getLocation().getFile())) {
                return false;
            }

            return true;
        }).collect(Collectors.toList());




    }

    @Override
    public List<Location> getValidMoves(Board board, Square square) {
        return null;
    }

    @Override
    public void makeMove(Square square) {
        if (isFirstMove) {
            isFirstMove = false;
        }
        this.getCurrentSquare().reset();
        this.setCurrentSquare(square);
        square.setCurrentPiece(this);
        square.setOccupied(true);
    }
}
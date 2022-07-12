package com.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.Location;
import com.chess.factory.LocationFactory;
import com.chess.squares.Square;
import com.chess.board.Board;

public class Knight extends AbstractPiece implements ChessPieceInterface {
    public Knight(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "Knight";
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        Map<Location, Square> squareMap = board.getLocationSquareMap();
        Location current = this.getCurrentSquare().getLocation();
        getMoves(moveCandidates, squareMap, current, 2, 1);
        getMoves(moveCandidates, squareMap, current, 2, -1);
        getMoves(moveCandidates, squareMap, current, -2, 1);
        getMoves(moveCandidates, squareMap, current, -2, -1);
        getMoves(moveCandidates, squareMap, current, 1, 2);
        getMoves(moveCandidates, squareMap, current, -1, -2);
        getMoves(moveCandidates, squareMap, current, 1, -2);
        getMoves(moveCandidates, squareMap, current, -1, 2);
        return moveCandidates;
    }


    private void getMoves(
            List<Location> candidates,
            Map<Location, Square> squareMap,
            Location current,
            int rankOffset,
            int fileOffset) {
        try {
            Location next = LocationFactory.build(current, fileOffset, rankOffset);
            if (squareMap.containsKey(next)) {
                if (squareMap.get(next).isOccupied()) {
                    if (squareMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) return;
                    candidates.add(next);
                    return;
                }
                candidates.add(next);
            }
        } catch (Exception e) { }
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
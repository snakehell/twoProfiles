package com.chess.piece;

import java.util.List;

import common.Location;
import com.chess.squares.Square;
import com.chess.board.Board;

public interface ChessPieceInterface {
    List<Location> getValidMoves(Board board);
    List<Location> getValidMoves(Board board, Square square);
    void makeMove(Square square);
}
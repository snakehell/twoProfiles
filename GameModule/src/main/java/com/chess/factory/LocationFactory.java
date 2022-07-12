package com.chess.factory;

import common.File;
import common.Location;

import static com.chess.board.Board.BOARD_LENGTH;

public class LocationFactory {
    private static final File[] files = File.values();
    public static Location build(Location current, Integer fileOffset, Integer rankOffset) {
        if(current.getFile().ordinal() + fileOffset >= BOARD_LENGTH || current.getRank() + rankOffset > BOARD_LENGTH ||
                current.getFile().ordinal() + fileOffset < 0 || current.getRank() + rankOffset <= 0) return null;
        return new Location(files[current.getFile().ordinal() + fileOffset], current.getRank() + rankOffset);
    }
}
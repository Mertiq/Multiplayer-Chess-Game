package main;

import java.util.LinkedList;

public class Piece {

    int indexX;
    int indexY;
    int screenPosX;
    int screenPosY;
    boolean isWhite;
    String name;
    LinkedList<Piece> pieces;

    public Piece(int indexX, int indexY, boolean isWhite, String name, LinkedList<Piece> pieces) {
        this.indexX = indexX;
        this.indexY = indexY;
        this.screenPosX = indexX * 64;
        this.screenPosY = indexY * 64;
        this.isWhite = isWhite;
        this.name = name;
        this.pieces = pieces;
        pieces.add(this);
    }

}

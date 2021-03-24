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

    public void move(int indexX, int indexY) {

        if (Game.getPiece(indexX * 64, indexY * 64) != null) {
            if (Game.getPiece(indexX * 64, indexY * 64).isWhite != isWhite) {
                Game.getPiece(indexX * 64, indexY * 64).kill();
            } else {
                screenPosX = this.indexX * 64;
                screenPosY = this.indexY * 64;
                return;
            }
        }
        this.indexX = indexX;
        this.indexY = indexY;
        screenPosX = indexX * 64;
        screenPosY = indexY * 64;

    }

    public void kill() {
        pieces.remove(this);
    }

}

package main;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class Piece implements Serializable {

    int indexX;
    int indexY;
    int screenPosX;
    int screenPosY;
    boolean isWhite;
    String name;
    public LinkedList<Piece> pieces;

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

    public void move(int indexX, int indexY,Game game) {

        if (Game.getPiece(indexX * 64, indexY * 64, game) != null)
            if (Objects.requireNonNull(Game.getPiece(indexX * 64, indexY * 64, game)).isWhite != isWhite) {
                Objects.requireNonNull(Game.getPiece(indexX * 64, indexY * 64, game)).kill();
            } else {
                screenPosX = this.indexX * 64;
                screenPosY = this.indexY * 64;
                return;
            }
        this.indexX = indexX;
        this.indexY = indexY;
        screenPosX = indexX * 64;
        screenPosY = indexY * 64;

    }

    public void kill() {

        System.out.println(this.name + " " + this.indexX + " " + this.indexY);
        pieces.remove(this);

    }
}

package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

enum PieceTypes{
    friend,
    enemy,
    empty
}

public class Moves {

    public static BufferedImage crossBufferedImage = null;
    public static Image crossImage = ReadImage(crossBufferedImage, "Multiplayer Chess Game/Multiplayer Chess Game/src/image/cross.png");

    public static void PawnMove(int x, int y, boolean isWhite, Graphics g, ImageObserver observer, LinkedList<Piece> pieces){
        if(isWhite){
            if(WhatsInThere(x-1, y-1, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(x-1);
                Game.movableLocationsY.add(y-1);
                g.drawImage(crossImage, (x-1) * 64, (y-1) * 64, observer);
            }
            if(WhatsInThere(x+1, y-1, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(x+1);
                Game.movableLocationsY.add(y-1);
                g.drawImage(crossImage, (x+1) * 64, (y-1) * 64, observer);
            }
            if(y == 6){
                for(int i = 0; i < 2 ;i++){
                    y--;
                    if( (x >= 0 && x <= 7) && (y >= 0 && y <= 7) ){
                        if(WhatsInThere(x, y, pieces, isWhite).toString().equals("empty"))
                        {
                            Game.movableLocationsX.add(x);
                            Game.movableLocationsY.add(y);
                            g.drawImage(crossImage, x * 64, y * 64, observer);
                        }
                        else
                        {
                            break;
                        }
                    }
                }
            }else{
                y--;
                if( (x >= 0 && x <= 7) && (y >= 0 && y <= 7) ){
                    if(WhatsInThere(x, y, pieces, isWhite).toString().equals("empty"))
                    {
                        Game.movableLocationsX.add(x);
                        Game.movableLocationsY.add(y);
                        g.drawImage(crossImage, x * 64, y * 64, observer);
                    }
                }
            }
        }else{
            if(WhatsInThere(x-1, y+1, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(x-1);
                Game.movableLocationsY.add(y+1);
                g.drawImage(crossImage, (x-1) * 64, (y+1) * 64, observer);
            }
            if(WhatsInThere(x+1, y+1, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(x+1);
                Game.movableLocationsY.add(y+1);
                g.drawImage(crossImage, (x+1) * 64, (y+1) * 64, observer);
            }
            if(y == 1){
                for(int i = 0; i < 2 ;i++){
                    y++;
                    if( (x >= 0 && x <= 7) && (y >= 0 && y <= 7) ){
                        if(WhatsInThere(x, y, pieces, isWhite).toString().equals("empty"))
                        {
                            Game.movableLocationsX.add(x);
                            Game.movableLocationsY.add(y);
                            g.drawImage(crossImage, x * 64, y * 64, observer);
                        }else {
                            break;
                        }
                    }
                }
            }else{
                y++;
                if( (x >= 0 && x <= 7) && (y >= 0 && y <= 7) ){
                    if(WhatsInThere(x, y, pieces, isWhite).toString().equals("empty"))
                    {
                        Game.movableLocationsX.add(x);
                        Game.movableLocationsY.add(y);
                        g.drawImage(crossImage, x * 64, y * 64, observer);
                    }
                }
            }
        }
    }

    public static void RookMove(int x, int y, boolean isWhite, Graphics g, ImageObserver observer, LinkedList<Piece> pieces){

        int tempX;
        int tempY;

        //forward
        tempX = x;
        tempY = y - 1;

        while (tempY >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempY--;
        }

        //backward
        tempX = x;
        tempY = y + 1;

        while (tempY <= 7){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempY++;
        }

        //left
        tempX = x - 1;
        tempY = y;

        while (tempX >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX--;
        }

        //right
        tempX = x + 1;
        tempY = y;

        while (tempX <= 7){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX++;
        }

    }

    public static void KnightMove(int x, int y, boolean isWhite, Graphics g, ImageObserver observer, LinkedList<Piece> pieces){

        int tempX = x - 2;
        int tempY = y - 1;

        for(int i = 0 ; i < 4;i++){

            if(i == 2){
                tempY = y - 1;
                tempX += 4;
            }
            if(tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7){

                if(!WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("friend"))
                {
                    Game.movableLocationsX.add(tempX);
                    Game.movableLocationsY.add(tempY);
                    g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                }

            }

            tempY += 2;
        }

    tempX = x - 1;
    tempY = y - 2;

    for(int i = 0 ; i < 4;i++){

        if(i == 2){
            tempX = x - 1;
            tempY += 4;
        }

        if(tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7){

            if(!WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("friend"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }

        }

        tempX += 2;
    }


    }

    public static void BishopMove(int x, int y, boolean isWhite, Graphics g, ImageObserver observer, LinkedList<Piece> pieces){
        
        int tempX;
        int tempY;

        //LEFT TOP
        tempX = x - 1;
        tempY = y - 1;

        while (tempX >= 0 && tempY >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX--;
            tempY--;
        }

        //right bottom
        tempX = x + 1;
        tempY = y + 1;

        while (tempX <= 7 && tempY <= 7){
            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX++;
            tempY++;
        }

        //right top
        tempX = x + 1;
        tempY = y - 1;

        while (tempX <= 7 && tempY >= 0){
            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX++;
            tempY--;
        }

        //left bottom
        tempX = x - 1;
        tempY = y + 1;

        while (tempX >= 0 && tempY <= 7){
            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX--;
            tempY++;
        }

        
    }

    public static void QueenMove(int x, int y, boolean isWhite, Graphics g, ImageObserver observer, LinkedList<Piece> pieces){

        int tempX;
        int tempY;

        //left top
        tempX = x - 1;
        tempY = y - 1;

        while (tempX >= 0 && tempY >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX--;
            tempY--;
        }

        //right bottom
        tempX = x + 1;
        tempY = y + 1;

        while (tempX <= 7 && tempY <= 7){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX++;
            tempY++;
        }

        //up
        tempX = x;
        tempY = y - 1;

        while (tempY >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempY--;
        }

        //down
        tempX = x;
        tempY = y + 1;

        while (tempY <= 7){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempY++;
        }

        //left
        tempX = x - 1;
        tempY = y;

        while (tempX >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX--;
        }

        //right
        tempX = x + 1;
        tempY = y;

        while (tempX <= 7){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX++;
        }

        //right top
        tempX = x + 1;
        tempY = y - 1;

        while (tempX <= 7 && tempY >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX++;
            tempY--;
        }

        //left bottom
        tempX = x - 1;
        tempY = y + 1;

        while (tempX >= 0 && tempY <= 7){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                Game.movableLocationsX.add(tempX);
                Game.movableLocationsY.add(tempY);
                g.drawImage(crossImage, tempX * 64, tempY * 64, observer);
                break;
            }
            else{break;}

            tempX--;
            tempY++;
        }

    }

    public static void KingMove(int x, int y, boolean isWhite, Graphics g, ImageObserver observer, LinkedList<Piece> pieces){

        for(int i = x - 1;i <= x+1;i++){

            for(int j = y -1;j <= y +1;j++){

                if( !(i == x && j == y) && ( i <= 7 && j <= 7) && ( 0 <= i && 0 <= j) ){

                    if(!WhatsInThere(i, j, pieces, isWhite).toString().equals("friend"))
                    {
                        Game.movableLocationsX.add(i);
                        Game.movableLocationsY.add(j);
                        g.drawImage(crossImage, i * 64, j * 64, observer);
                    }

                }

            }
        }
    }

    public static PieceTypes WhatsInThere(int x, int y, LinkedList<Piece> pieces, boolean isWhite){

        for (Piece p: pieces) {
            if(p.indexX == x && p.indexY == y){
                if(p.isWhite == isWhite){
                    return PieceTypes.friend;
                }
                else{
                    return PieceTypes.enemy;
                }
            }
        }
        return PieceTypes.empty;
    }

    public static BufferedImage ReadImage(BufferedImage fullPiecesImage, String filePath) {
        try {
            fullPiecesImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fullPiecesImage;
    }

}

package main;

import java.util.LinkedList;

enum PieceTypes{
    friend,
    enemy,
    empty
}

public class Moves {

    public static void PawnMove(int x, int y, boolean isWhite, LinkedList<Piece> pieces, Game game){
        if(isWhite){
            if(WhatsInThere(x-1, y-1, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(x-1);
                game.movableLocationsY.add(y-1);
            }
            if(WhatsInThere(x+1, y-1, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(x+1);
                game.movableLocationsY.add(y-1);
            }
            if(y == 6){
                for(int i = 0; i < 2 ;i++){
                    y--;
                    if( (x >= 0 && x <= 7) && (y >= 0 && y <= 7) ){
                        if(WhatsInThere(x, y, pieces, isWhite).toString().equals("empty"))
                        {
                            game.movableLocationsX.add(x);
                            game.movableLocationsY.add(y);
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
                        game.movableLocationsX.add(x);
                        game.movableLocationsY.add(y);
                    }
                }
            }
        }else{
            if(WhatsInThere(x-1, y+1, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(x-1);
                game.movableLocationsY.add(y+1);
            }
            if(WhatsInThere(x+1, y+1, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(x+1);
                game.movableLocationsY.add(y+1);
            }
            if(y == 1){
                for(int i = 0; i < 2 ;i++){
                    y++;
                    if( (x >= 0 && x <= 7) && (y >= 0 && y <= 7) ){
                        if(WhatsInThere(x, y, pieces, isWhite).toString().equals("empty"))
                        {
                            game.movableLocationsX.add(x);
                            game.movableLocationsY.add(y);
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
                        game.movableLocationsX.add(x);
                        game.movableLocationsY.add(y);
                    }
                }
            }
        }
    }

    public static void RookMove(int x, int y, boolean isWhite, LinkedList<Piece> pieces, Game game){

        int tempX;
        int tempY;

        //forward
        tempX = x;
        tempY = y - 1;

        while (tempY >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
                break;
            }
            else{break;}

            tempX++;
        }

    }

    public static void KnightMove(int x, int y, boolean isWhite, LinkedList<Piece> pieces, Game game){

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
                    game.movableLocationsX.add(tempX);
                    game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }

        }

        tempX += 2;
    }


    }

    public static void BishopMove(int x, int y, boolean isWhite, LinkedList<Piece> pieces, Game game){
        
        int tempX;
        int tempY;

        //LEFT TOP
        tempX = x - 1;
        tempY = y - 1;

        while (tempX >= 0 && tempY >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
                break;
            }
            else{break;}

            tempX--;
            tempY++;
        }

        
    }

    public static void QueenMove(int x, int y, boolean isWhite, LinkedList<Piece> pieces, Game game){

        int tempX;
        int tempY;

        //left top
        tempX = x - 1;
        tempY = y - 1;

        while (tempX >= 0 && tempY >= 0){

            if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("empty"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
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
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
            }
            else if(WhatsInThere(tempX, tempY, pieces, isWhite).toString().equals("enemy"))
            {
                game.movableLocationsX.add(tempX);
                game.movableLocationsY.add(tempY);
                break;
            }
            else{break;}

            tempX--;
            tempY++;
        }

    }

    public static void KingMove(int x, int y, boolean isWhite, LinkedList<Piece> pieces, Game game){

        for(int i = x - 1;i <= x+1;i++){

            for(int j = y -1;j <= y +1;j++){

                if( !(i == x && j == y) && ( i <= 7 && j <= 7) && ( 0 <= i && 0 <= j) ){

                    if(!WhatsInThere(i, j, pieces, isWhite).toString().equals("friend"))
                    {
                        game.movableLocationsX.add(i);
                        game.movableLocationsY.add(j);
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

}

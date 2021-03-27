package main;

import java.util.LinkedList;

public class Check {

    public static void Control(int x, int y, LinkedList<Piece> pieces, Piece king){
        Game.isChecked = false;
        CheckPawn(x,y, pieces, king);
        CheckKnight(x,y, pieces, king);
        CheckBishopAndQueen(x,y, pieces, king);
        CheckRookAndQueen(x,y, pieces, king);
    }


    public static void CheckPawn(int x, int y, LinkedList<Piece> pieces, Piece king){

        if(king.isWhite){
            for(int i = x - 1;i <= x+1;i++){

                int j=y-1;

                if( (i != x) && ( i <= 7 && j <= 7) && ( 0 <= i && 0 <= j) ){

                    for (Piece p:pieces) {

                        if(p.isWhite)
                            continue;
                        if (i == p.indexX && j == p.indexY && p.name.equals("pawn")) {
                            Game.isChecked = true;
                            break;
                        }

                    }

                }
            }
        }else{
            for(int i = x - 1;i <= x+1;i++){

                int j=y+1;

                if( (i != x) && ( i <= 7 && j <= 7) && ( 0 <= i && 0 <= j) ){

                    for (Piece p:pieces) {

                        if(!p.isWhite)
                            continue;
                        if (i == p.indexX && j == p.indexY && p.name.equals("pawn")) {
                            Game.isChecked = true;
                            break;
                        }

                    }

                }

            }
        }

    }

    public static void CheckKnight(int x, int y, LinkedList<Piece> pieces, Piece king){

        int tempX = x - 2;
        int tempY = y - 1;

        for(int i = 0 ; i < 4;i++){

            if(i == 2){
                tempY = y - 1;
                tempX += 4;
            }
            if(tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7){

                for (Piece p:pieces) {

                    if (tempX == p.indexX && tempY == p.indexY && p.name.equals("knight") && p.isWhite != king.isWhite) {
                        Game.isChecked = true;
                        break;
                    }
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

                for (Piece p:pieces) {

                    if (tempX == p.indexX && tempY == p.indexY && p.name.equals("knight") && p.isWhite != king.isWhite) {
                        Game.isChecked = true;
                        break;
                    }

                }

            }

            tempX += 2;
        }


    }

    public static void CheckBishopAndQueen(int x, int y, LinkedList<Piece> pieces, Piece king){


        int tempX;
        int tempY;

        //LEFT TOP
        tempX = x - 1;
        tempY = y - 1;

        a:
        while (tempX >= 0 && tempY >= 0){

            for (Piece p:pieces) {

                if(tempX==p.indexX && tempY==p.indexY ){
                    if(p.isWhite==king.isWhite){
                        break a;
                    }

                    if((p.name.equals("bishop") || p.name.equals("queen"))){
                        Game.isChecked = true;
                    }
                }


            }

            tempX--;
            tempY--;
        }

        //right bottom
        tempX = x + 1;
        tempY = y + 1;

        a:
        while (tempX <= 7 && tempY <= 7){

            for (Piece p:pieces) {

                if(tempX==p.indexX && tempY==p.indexY ){
                    if(p.isWhite==king.isWhite){
                        break a;
                    }

                    if((p.name.equals("bishop") || p.name.equals("queen")) ){
                        Game.isChecked = true;
                    }
                }


            }

            tempX++;
            tempY++;
        }

        //right top
        tempX = x + 1;
        tempY = y - 1;

        a:
        while (tempX <= 7 && tempY >= 0){

            for (Piece p:pieces) {

                if(tempX==p.indexX && tempY==p.indexY ){
                    if(p.isWhite==king.isWhite){
                        break a;
                    }

                    if((p.name.equals("bishop") || p.name.equals("queen"))){
                        Game.isChecked = true;
                    }
                }


            }

            tempX++;
            tempY--;
        }

        //left bottom
        tempX = x - 1;
        tempY = y + 1;

        a:
        while (tempX >= 0 && tempY <= 7){

            for (Piece p:pieces) {

                if(tempX==p.indexX && tempY==p.indexY ){
                    if(p.isWhite==king.isWhite){
                        break a;
                    }

                    if((p.name.equals("bishop") || p.name.equals("queen"))){
                        Game.isChecked = true;
                    }
                }
            }

            tempX--;
            tempY++;
        }


    }

    public static void CheckRookAndQueen(int x, int y, LinkedList<Piece> pieces, Piece king){
        int tempX;
        int tempY;

        //forward
        tempX = x;
        tempY = y - 1;

        while (tempY >= 0){

            for (Piece p:pieces) {

                if(tempX==p.indexX && tempY==p.indexY ){
                    if(p.isWhite==king.isWhite)
                        break;
                    if( (p.name.equals("rook") || p.name.equals("queen"))){
                        Game.isChecked = true;
                    }
                }

            }

            tempY--;
        }

        //backward
        tempX = x;
        tempY = y + 1;

        while (tempY <= 7){

            for (Piece p:pieces) {

                if(tempX==p.indexX && tempY==p.indexY ){
                    if(p.isWhite==king.isWhite)
                        break;
                    if( (p.name.equals("rook") || p.name.equals("queen")) ){
                        Game.isChecked = true;
                    }
                }

            }

            tempY++;
        }

        //left
        tempX = x - 1;
        tempY = y;

        while (tempX >= 0){

            for (Piece p:pieces) {

                if(tempX==p.indexX && tempY==p.indexY ){
                    if(p.isWhite==king.isWhite)
                        break;
                    if( (p.name.equals("rook") || p.name.equals("queen")) ){
                        Game.isChecked = true;
                    }
                }
            }

            tempX--;
        }

        //right
        tempX = x + 1;
        tempY = y;

        while (tempX <= 7){

            for (Piece p:pieces) {

                if(tempX==p.indexX && tempY==p.indexY ){
                    if(p.isWhite==king.isWhite)
                        break;
                    if( (p.name.equals("rook") || p.name.equals("queen")) ){
                        Game.isChecked = true;
                    }
                }
            }

            tempX++;
        }

    }


}


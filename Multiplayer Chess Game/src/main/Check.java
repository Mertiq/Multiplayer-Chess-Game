package main;

import java.util.LinkedList;

public class Check {

    public static void Control(int x, int y, LinkedList<Piece> pieces, Piece king){
        Game.isChecked = false;
        CheckPawn(x,y, pieces, king);
        if(!Game.isChecked)
            CheckKnight(x,y, pieces, king);
        if(!Game.isChecked)
            CheckBishopAndQueen(x,y, pieces, king);
        if(!Game.isChecked)
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
                            Game.checkerPiece = p;
                            Game.goOnCheckLocationsX.add(p.indexX);
                            Game.goOnCheckLocationsY.add(p.indexY);
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
                            Game.checkerPiece = p;
                            Game.goOnCheckLocationsX.add(p.indexX);
                            Game.goOnCheckLocationsY.add(p.indexY);
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
                        Game.checkerPiece = p;
                        Game.goOnCheckLocationsX.add(p.indexX);
                        Game.goOnCheckLocationsY.add(p.indexY);
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
                        Game.checkerPiece = p;
                        Game.goOnCheckLocationsX.add(p.indexX);
                        Game.goOnCheckLocationsY.add(p.indexY);
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
                        Game.checkerPiece = p;
                        break;
                    }
                }
            }

            int a = tempX;
            int b = tempY;
            if(Game.isChecked){
                while(a != x && b != y){
                    Game.goOnCheckLocationsX.add(a);
                    Game.goOnCheckLocationsY.add(b);
                    a++;
                    b++;
                }
                break;
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
                        Game.checkerPiece = p;
                    }
                }


            }

            int a = tempX;
            int b = tempY;
            if(Game.isChecked){
                while(a != x && b != y){
                    Game.goOnCheckLocationsX.add(a);
                    Game.goOnCheckLocationsY.add(b);
                    a--;
                    b--;
                }
                break;
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
                        Game.checkerPiece = p;
                    }
                }
            }

            int a = tempX;
            int b = tempY;
            if(Game.isChecked){
                while(a != x && b != y){
                    Game.goOnCheckLocationsX.add(a);
                    Game.goOnCheckLocationsY.add(b);
                    a--;
                    b++;
                }
                break;
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
                        Game.checkerPiece = p;
                        break;
                    }
                }
            }

            int a = tempX;
            int b = tempY;
            if(Game.isChecked){
                while(a != x && b != y){
                    Game.goOnCheckLocationsX.add(a);
                    Game.goOnCheckLocationsY.add(b);
                    a++;
                    b--;
                }
                break;
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
                        Game.checkerPiece = p;
                    }
                }
            }

            int b = tempY;
            if(Game.isChecked){
                while(b != y){
                    Game.goOnCheckLocationsX.add(tempX);
                    Game.goOnCheckLocationsY.add(b);
                    b++;
                }
                break;
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
                        Game.checkerPiece = p;
                    }
                }

            }

            int b = tempY;
            if(Game.isChecked){
                while(b != y){
                    Game.goOnCheckLocationsX.add(tempX);
                    Game.goOnCheckLocationsY.add(b);
                    b--;
                }
                break;
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
                        Game.checkerPiece = p;
                    }
                }
            }

            int b = tempX;
            if(Game.isChecked){
                while(b != x){
                    Game.goOnCheckLocationsX.add(b);
                    Game.goOnCheckLocationsY.add(tempY);
                    b++;
                }
                break;
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
                        Game.checkerPiece = p;
                    }
                }
            }

            int b = tempX;
            if(Game.isChecked){
                while(b != y){
                    Game.goOnCheckLocationsX.add(b);
                    Game.goOnCheckLocationsY.add(tempY);
                    b--;
                }
                break;
            }

            tempX++;
        }

    }


}


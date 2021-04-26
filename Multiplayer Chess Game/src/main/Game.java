/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Client.Client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

import Message.Message;

public class Game {

    public LinkedList<Integer> movableLocationsX = new LinkedList<>();
    public LinkedList<Integer> movableLocationsY = new LinkedList<>();
    public LinkedList<Piece> pieces = new LinkedList<>();
    public Piece selectedPiece = null;
    public Piece checkerPiece = null;
    public boolean mouseSelected = false;
    public boolean isChecked = false;


    public Image crossImage = ReadImage("Multiplayer Chess Game/Multiplayer Chess Game/src/image/cross.png");

    public boolean isTurnWhite = false;
    public boolean isSideWhite = false;

    public JFrame jFrame = new JFrame();

    public static void main(String[] args) {

        Game game = new Game();

        Client c = new Client(game);
        c.Start("127.0.0.1", 5000);

        Message msg = new Message(Message.Message_Type.SideChoose);
        msg.content = game.isSideWhite;
        Client.Send(msg);


        BufferedImage fullPiecesImage = null;
        String filePath = "Multiplayer Chess Game/Multiplayer Chess Game/src/image/chess.png";

        Image[] eachPiecesImage = new Image[12];
        SplitPiecesImage(ReadImage(filePath), eachPiecesImage);
        CreateAllPieces(game.pieces);


        game.jFrame.setBounds(10, 10, 530, 555);
        JPanel jPanel = new JPanel(){
            @Override
            public void paint(Graphics g) {

                DrawBoard(g);

                for (Piece p : game.pieces) {
                    g.drawImage(eachPiecesImage[GetPieceIndex(p)], p.screenPosX, p.screenPosY, this);
                }

                if(game.mouseSelected){
                    game.movableLocationsX.clear();
                    game.movableLocationsY.clear();
                    FindMovableLocations(game.selectedPiece,game);
                    DrawMovableLocations(g,this,game, game.crossImage);
                }
            }
        };

        game.jFrame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //while drag move the piece with mouse
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
            }
        });

        game.jFrame.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                int getInputX = Math.max(e.getX() - 18, 0);
                int getInputY = Math.max(e.getY() - 43, 0);

                if(getPiece(getInputX, getInputY,game) != null){ // if there is a piece at the mouse pressed position
                    if(!game.mouseSelected){ // if mouse didn't select before that
                        if(getPiece(getInputX, getInputY,game).isWhite == game.isSideWhite){
                            game.selectedPiece = getPiece(getInputX, getInputY,game); // selectedPiece selected
                            game.mouseSelected = true; // mouse selected
                        }
                    }else{ // if mouse selected before that
                        if(getPiece(getInputX, getInputY,game).isWhite == game.selectedPiece.isWhite){ // if the color of the piece which is at mouse pressed position is same with the color of selectedPiece
                            if(getPiece(getInputX, getInputY,game).isWhite == game.isSideWhite){
                                game.selectedPiece = getPiece(getInputX, getInputY,game); // selectedPiece selected
                                game.mouseSelected = true; // mouse selected
                            }
                        }else{ //  if the color of the piece which is at mouse pressed position isn't same with the color of selectedPiece
                            for (int i = 0; i < game.movableLocationsX.size(); i++) {
                                if((game.movableLocationsX.get(i) == (getInputX / 64)) && (game.movableLocationsY.get(i) == (getInputY / 64))){ // selectedPiece can move the mouse pressed position
                                    if(game.selectedPiece.isWhite == game.isSideWhite){
                                        game.MoveServerSend(new Point(game.selectedPiece.indexX, game.selectedPiece.indexY),new Point(getInputX / 64, getInputY / 64),game);
                                        game.selectedPiece.move(getInputX / 64, getInputY / 64, game); // move selectedPiece to the mouse pressed position
                                        game.mouseSelected=false; // mouse didn't select

                                        // after movement check control
                                        for (Piece p: game.pieces) {
                                            if(p.name.equals("king")){
                                                Check.Control(p.indexX, p.indexY, game.pieces, p,game);
                                                if(game.isChecked)
                                                    break;
                                            }
                                        }

                                        game.isTurnWhite = !game.isTurnWhite;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }else{ // if there isn't any piece at the mouse pressed position
                    for (int i = 0; i < game.movableLocationsX.size(); i++) {
                        if((game.movableLocationsX.get(i) == (getInputX / 64)) && (game.movableLocationsY.get(i) == (getInputY / 64))){ // selectedPiece can move the mouse pressed position
                            if(game.selectedPiece.isWhite == game.isSideWhite){
                                game.MoveServerSend(new Point(game.selectedPiece.indexX, game.selectedPiece.indexY),new Point(getInputX / 64, getInputY / 64),game);
                                game.selectedPiece.move(getInputX / 64, getInputY / 64, game); // move selectedPiece to the mouse pressed position
                                game.mouseSelected=false; // mouse didn't select

                                // after movement check control
                                for (Piece p: game.pieces) {
                                    if(p.name.equals("king")){
                                        Check.Control(p.indexX, p.indexY, game.pieces, p,game);
                                        if(game.isChecked)
                                            break;
                                    }
                                }

                                game.isTurnWhite = !game.isTurnWhite;
                                break;
                            }
                        }
                    }
                }
                if(game.checkerPiece != null)
                    System.out.println(Objects.requireNonNull(game.checkerPiece).isWhite + " king "+ game.isChecked);

                if(game.isChecked) {
                    if(game.checkerPiece.isWhite == game.isTurnWhite){
                        System.out.println("mate");
                    }
                }

                game.jFrame.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });

        game.jFrame.add(jPanel);
        game.jFrame.setVisible(true);
    }

    public void MoveServerSend(Point start, Point end, Game game){
        ArrayList<Point> points = new ArrayList<>();
        points.add(start);
        points.add(end);
        Message msg = new Message(Message.Message_Type.Move);

        ArrayList<Object> send = new ArrayList<>();
        send.add(points);
        send.add(game.pieces);
        msg.content = send;
        Client.Send(msg);


    }

    public void MoveServer(ArrayList<Object> objects,Game game){
        game.pieces = (LinkedList<Piece>) objects.get(1);
        ArrayList<Point> points = (ArrayList<Point>) objects.get(0);
        for (Piece p2: pieces) {
            if(p2.indexX == points.get(0).x && p2.indexY == points.get(0).y){
                System.out.println("if");
                System.out.println(p2.name);
                p2.move(points.get(1).x, points.get(1).y, game);
                System.out.println("Rakip Hareket etti");
                break;
            }
        }

        game.isTurnWhite = !game.isTurnWhite;
        game.jFrame.repaint();
    }

    public static void DrawBoard(Graphics g) {
        boolean isWhite = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isWhite) {
                    g.setColor(new Color(235, 235, 208));
                } else {
                    g.setColor(new Color(119, 148, 85));
                }
                g.fillRect(i * 64, j * 64, 64, 64);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
    }

    public static void CreateAllPieces(LinkedList<Piece> pieces) {


        Piece blackLeftRook = new Piece(0, 0, false, "rook", pieces);
        Piece blackRightRook = new Piece(7, 0, false, "rook", pieces);
        Piece blackLeftKnight = new Piece(1, 0, false, "knight", pieces);
        Piece blackRightKnight = new Piece(6, 0, false, "knight", pieces);
        Piece blackLeftBishop = new Piece(2, 0, false, "bishop", pieces);
        Piece blackRightBishop = new Piece(5, 0, false, "bishop", pieces);
        Piece blackQueen = new Piece(3, 0, false, "queen", pieces);
        Piece blackKing = new Piece(4, 0, false, "king", pieces);
        Piece blackPawn0 = new Piece(0, 1, false, "pawn", pieces);
        Piece blackPawn1 = new Piece(1, 1, false, "pawn", pieces);
        Piece blackPawn2 = new Piece(2, 1, false, "pawn", pieces);
        Piece blackPawn3 = new Piece(3, 1, false, "pawn", pieces);
        Piece blackPawn4 = new Piece(4, 1, false, "pawn", pieces);
        Piece blackPawn5 = new Piece(5, 1, false, "pawn", pieces);
        Piece blackPawn6 = new Piece(6, 1, false, "pawn", pieces);
        Piece blackPawn7 = new Piece(7, 1, false, "pawn", pieces);

        //---

        Piece whiteLeftRook = new Piece(0, 7, true, "rook", pieces);
        Piece whiteRightRook = new Piece(7, 7, true, "rook", pieces);
        Piece whiteLeftKnight = new Piece(1, 7, true, "knight", pieces);
        Piece whiteRightKnight = new Piece(6, 7, true, "knight", pieces);
        Piece whiteLeftBishop = new Piece(2, 7, true, "bishop", pieces);
        Piece whiteRightBishop = new Piece(5, 7, true, "bishop", pieces);
        Piece whiteQueen = new Piece(3, 7, true, "queen", pieces);
        Piece whiteKing = new Piece(4, 7, true, "king", pieces);
        Piece whitePawn0 = new Piece(0, 6, true, "pawn", pieces);
        Piece whitePawn1 = new Piece(1, 6, true, "pawn", pieces);
        Piece whitePawn2 = new Piece(2, 6, true, "pawn", pieces);
        Piece whitePawn3 = new Piece(3, 6, true, "pawn", pieces);
        Piece whitePawn4 = new Piece(4, 6, true, "pawn", pieces);
        Piece whitePawn5 = new Piece(5, 6, true, "pawn", pieces);
        Piece whitePawn6 = new Piece(6, 6, true, "pawn", pieces);
        Piece whitePawn7 = new Piece(7, 6, true, "pawn", pieces);


    }

    public static BufferedImage ReadImage(String filePath) {
        BufferedImage crossBufferedImage = null;
        try {
            crossBufferedImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return crossBufferedImage;
    }

    public static void SplitPiecesImage(BufferedImage fullPiecesImage, Image[] eachPiecesImage) {
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                eachPiecesImage[ind] = fullPiecesImage.getSubimage(x, y, 200, 200).getScaledInstance(64, 64,
                        BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
    }

    public static int GetPieceIndex(Piece p) {

        int index = 0;

        switch (p.name) {

        case "king":
            index = 0;
            break;
        case "queen":
            index = 1;
            break;
        case "bishop":
            index = 2;
            break;
        case "knight":
            index = 3;
            break;
        case "rook":
            index = 4;
            break;
        case "pawn":
            index = 5;
            break;
        }
        if (!p.isWhite)
            index += 6;

        return index;
    }

    public static Piece getPiece(int x, int y,Game game) {

        int xp=x/64;
        int yp=y/64;
        for(Piece p: game.pieces){
            if(p.indexX==xp&&p.indexY==yp){
                return p;
            }
        }
        return null;
    }

    public static void FindMovableLocations(Piece piece, Game game) {
        switch (piece.name) {
            case "king":
                Moves.KingMove(piece.indexX, piece.indexY, piece.isWhite, game.pieces,game);
                break;
            case "queen":
                Moves.QueenMove(piece.indexX, piece.indexY, piece.isWhite, game.pieces,game);
                break;
            case "bishop":
                Moves.BishopMove(piece.indexX, piece.indexY, piece.isWhite, game.pieces,game);
                break;
            case "knight":
                Moves.KnightMove(piece.indexX, piece.indexY, piece.isWhite, game.pieces,game);
                break;
            case "rook":
                Moves.RookMove(piece.indexX, piece.indexY, piece.isWhite, game.pieces,game);
                break;
            case "pawn":
                Moves.PawnMove(piece.indexX, piece.indexY, piece.isWhite, game.pieces,game);
                break;
        }
    }

    public static void DrawMovableLocations(Graphics g, ImageObserver observer, Game game, Image crossImage){
        int y=0;
        for (Integer locationsX : game.movableLocationsX) {
            g.drawImage(crossImage, locationsX * 64, game.movableLocationsY.get(y) * 64, observer);
            y++;
        }
    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 *
 * @author Mert
 */
public class Game {

    public static LinkedList<Integer> movableLocationsX = new LinkedList<>();
    public static LinkedList<Integer> movableLocationsY = new LinkedList<>();
    public static LinkedList<Piece> pieces = new LinkedList<>();
    public static Piece selectedPiece = null;
    public static boolean mouseSelected = false;
    public static boolean isChecked = false;

    public static BufferedImage crossBufferedImage = null;
    public static Image crossImage = ReadImage(crossBufferedImage, "Multiplayer Chess Game/Multiplayer Chess Game/src/image/cross.png");


    public static void main(String[] args) {

        BufferedImage fullPiecesImage = null;
        String filePath = "Multiplayer Chess Game/Multiplayer Chess Game/src/image/chess.png";

        Image[] eachPiecesImage = new Image[12];
        SplitPiecesImage(ReadImage(fullPiecesImage, filePath), eachPiecesImage);
        CreateAllPieces(pieces);

        JFrame jFrame = new JFrame();
        jFrame.setBounds(10, 10, 512, 512);
        jFrame.setUndecorated(true);
        JPanel jPanel = new JPanel(){
            @Override
            public void paint(Graphics g) {

                DrawBoard(g);

                for (Piece p : pieces) {
                    g.drawImage(eachPiecesImage[GetPieceIndex(p)], p.indexX * 64, p.indexY * 64, this);
                }

                if(mouseSelected){
                    movableLocationsX.clear();
                    movableLocationsY.clear();
                    FindMovableLocations(selectedPiece);
                    DrawMovableLocations(g,this);
                }

            }
        };

        jFrame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //while drag move the piece with mouse
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });

        jFrame.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {

                if(!mouseSelected){
                    if(getPiece(e.getX(), e.getY()) != null){
                        selectedPiece = getPiece(e.getX(), e.getY());
                        mouseSelected = true;
                    }
                }else{
                    if(getPiece(e.getX(), e.getY()) != null){
                        if(Objects.requireNonNull(getPiece(e.getX(), e.getY())).isWhite == selectedPiece.isWhite){
                            selectedPiece = getPiece(e.getX(), e.getY());
                            mouseSelected = true;
                        }else{
                            if(!isChecked){
                                for (int i = 0; i < movableLocationsX.size(); i++) {
                                    if((movableLocationsX.get(i) == (e.getX() / 64)) && (movableLocationsY.get(i) == (e.getY() / 64))){
                                        selectedPiece.move(e.getX() / 64, e.getY() / 64);
                                        mouseSelected=false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                jFrame.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if(!isChecked){
                    for (int i = 0; i < movableLocationsX.size(); i++) {
                        if((movableLocationsX.get(i) == (e.getX() / 64)) && (movableLocationsY.get(i) == (e.getY() / 64))){
                            selectedPiece.move(e.getX() / 64, e.getY() / 64);
                            mouseSelected=false;
                            jFrame.repaint();
                            break;
                        }
                    }
                }else{
                    if(selectedPiece.name.equals("king")){
                        int tempX = e.getX() / 64;
                        int tempY = e.getY() / 64;

                        Check.Control(tempX,tempY,pieces,selectedPiece);
                        if(!isChecked){
                            selectedPiece.move(e.getX() / 64, e.getY() / 64);
                            mouseSelected=false;
                            jFrame.repaint();
                        }
                    }else {

                       //----burası
                    }

                }

                for (Piece p: pieces) {

                    if(p.name.equals("king")){
                        Check.Control(p.indexX, p.indexY, pieces, p);
                        //System.out.println(p.isWhite ? "beyaz" +" "+ p.indexY : "siyah" +" "+ p.indexY );
                        if(isChecked)
                            break;
                    }

                }

                System.out.println(isChecked ? "şah" : "");

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

        jFrame.add(jPanel);
        jFrame.setVisible(true);
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

    public static BufferedImage ReadImage(BufferedImage fullPiecesImage, String filePath) {
        try {
            fullPiecesImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fullPiecesImage;
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

    public static Piece getPiece(int x, int y) {

        int xp=x/64;
        int yp=y/64;
        for(Piece p: pieces){
            if(p.indexX==xp&&p.indexY==yp){
                return p;
            }
        }
        return null;
    }

    public static void FindMovableLocations(Piece piece) {
        switch (piece.name) {
            case "king":
                Moves.KingMove(piece.indexX, piece.indexY, piece.isWhite, pieces);
                break;
            case "queen":
                Moves.QueenMove(piece.indexX, piece.indexY, piece.isWhite, pieces);
                break;
            case "bishop":
                Moves.BishopMove(piece.indexX, piece.indexY, piece.isWhite, pieces);
                break;
            case "knight":
                Moves.KnightMove(piece.indexX, piece.indexY, piece.isWhite, pieces);
                break;
            case "rook":
                Moves.RookMove(piece.indexX, piece.indexY, piece.isWhite, pieces);
                break;
            case "pawn":
                Moves.PawnMove(piece.indexX, piece.indexY, piece.isWhite, pieces);
                break;
        }
    }

    public static void DrawMovableLocations(Graphics g, ImageObserver observer){
        int y=0;
        for (Integer locationsX : movableLocationsX) {
            g.drawImage(crossImage, locationsX * 64, movableLocationsY.get(y) * 64, observer);
            y++;
        }
    }


}

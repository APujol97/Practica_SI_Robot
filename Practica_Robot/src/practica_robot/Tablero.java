/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_robot;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author pujol
 */
public class Tablero extends JPanel{
    
    private Robot robot;
    private static int elementos; //número de casillas
    private static final int dimension = 600; //dimensión de un lado del tablero
    private Casilla tablero[][];
    private static int lado;
    private boolean borrar;
    private boolean muro = true;
    
    public Tablero(int n){
        initComponents(n);
    }
    
    public void initComponents(int n){
        tablero = new Casilla[n][n];
        elementos = n;
        lado = dimension/elementos;
        borrar = false;
        
        int eje_y = 0;
        for(int i = 0; i < n; i++){
            int eje_x = 0;
            for(int j = 0; j < n; j++){
                Rectangle2D.Float r = new Rectangle2D.Float(eje_x, eje_y, 
                    lado,lado);
                tablero[i][j] = new Casilla(r);
                eje_x += lado;
            }
            eje_y += lado;
        }
    }
    
    public void Colorear(int x, int y) { //método para colorear una casilla
        x = x / lado; //calculamos la columna de la casilla clickeada
        y = y / lado; //calculamos la fila de la casilla clickeada
        if(muro){
            tablero[y][x].ColorearCasilla(borrar);
        }else{
            ponerRobot(x,y);
        }
    }
    
    public void ponerRobot(int x, int y) {
        if(!tablero[y][x].hayMuro()){
            if(!tablero[y][x].hayRobot()){
                if(robot != null){
                    tablero[robot.getY()][robot.getX()].pintaRobot();
                }
                robot = new Robot(y, x);
                tablero[y][x].pintaRobot();
            }else{
                robot = null;
                tablero[y][x].pintaRobot();
            }
            
        }
    }
    
    public void resetTablero(){
        initComponents(elementos);
        robot = null;
        repaint();
    }
    
    @Override
    public Dimension getPreferredSize() { //método que devuelve las dimensiones del tablero
        return new Dimension(dimension, dimension);
    }

    @Override
    public void paintComponent(Graphics g) { //método que pinta el tablero en la ventana
        for (int i = 0; i < elementos; i++) {
            for (int j = 0; j < elementos; j++) {
                tablero[i][j].paintComponent(g);
            }
        }
    }

    void setMuro(boolean b) {
        this.muro = b;
    }
    
}

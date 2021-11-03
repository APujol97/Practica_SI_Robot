/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_robot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author pujol
 */
public class Main extends JFrame implements MouseListener {

    /**
     * @param args the command line arguments
     */
    
    static Tablero tablero; //instancia de la clase tablero
    
    static int n = 20;
    
    public Main() { //constructor de la clase TallerPictograma
        this.setTitle("Robot");
        initComponents();
        this.setDefaultCloseOperation(Main.EXIT_ON_CLOSE);
    }
    
    public void initComponents() { //se inicializan los componentes de la ventana
        //se inicializa el tablero
        tablero = new Tablero(n);
        tablero.addMouseListener(this);
        tablero.setLocation(50, 50);
        
        //se especifican las caracterísitcas de la ventana
        this.getContentPane().add(new Controlador(), BorderLayout.NORTH);
        this.getContentPane().add(tablero, BorderLayout.CENTER);
        this.setResizable(false);
        this.setSize(new Dimension(650, 650));
        this.pack();
        this.setLocationRelativeTo(null);

    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().setVisible(true);
    }
    
    public static void reset() {
        tablero.resetTablero();
    }
    
    public static void muro(){
        tablero.setMuro(true);
    }
    
    public static void robot(){
        tablero.setMuro(false);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            tablero.Colorear(me.getX(), me.getY()); //se colorea la casilla clickeada
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_robot;

import java.awt.BorderLayout;
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
    
    Tablero tablero; //instancia de la clase tablero
    JMenu menu;
    JMenuBar barra;
    JMenuItem inicia;
    JMenuItem para;
    
    int n = 20;
    
    public Main() { //constructor de la clase TallerPictograma
        this.setTitle("Robot");
        initComponents();
        this.setDefaultCloseOperation(Main.EXIT_ON_CLOSE);
    }
    
    public void initComponents() { //se inicializan los componentes de la ventana
        //se inicializa el tablero
        tablero = new Tablero(n);
        tablero.addMouseListener(this);
        
        barra = new JMenuBar();
        menu = new JMenu();
        inicia = new JMenuItem();
        para = new JMenuItem();
        
        barra.add(menu);
        menu.setText("MENÚ");
        menu.add(inicia);
        menu.add(para);
        
        inicia.setText("INICIA");
        inicia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                repaint();
            }
        });
        para.setText("PARA");
        para.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                repaint();
            }
        });
        
        //se especifican las caracterísitcas de la ventana
        this.getContentPane().add(tablero, BorderLayout.CENTER);
        this.setJMenuBar(barra);
        this.setResizable(false);
        this.setSize(tablero.getPreferredSize());
        this.pack();
        this.setLocationRelativeTo(null);

    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().setVisible(true);
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

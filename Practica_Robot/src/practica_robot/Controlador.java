/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_robot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author pujol
 */
public class Controlador extends JPanel {
    
    private JButton iniciar;
    private JButton reset;
    private JButton rapido;
    private JButton lento;
    private JButton muro;
    private JButton robot;
    
    public Controlador(){
        iniciar = new JButton("iniciar");
        iniciar.setBounds(20, 20, 40, 40);
        
        reset = new JButton("reset");
        reset.setBounds(20, 50, 40, 70);
        
        rapido = new JButton("rapido");
        rapido.setBounds(20, 80, 40, 100);
        
        lento = new JButton("lento");
        lento.setBounds(20, 110, 40, 130);
        
        muro = new JButton("muro");
        muro.setBounds(20, 140, 40, 160);
        
        robot = new JButton("robot");
        robot.setBounds(20, 170, 40, 190);
        
        initComponents();
    }
    
    public void initComponents(){
        
        iniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.reset();
            }
        });
        rapido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        lento.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        muro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.muro();
            }
        });
        robot.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.robot();
            }
        });
        
        this.add(iniciar);
        this.add(reset);
        this.add(rapido);
        this.add(lento);
        this.add(muro);
        this.add(robot);
    }
}

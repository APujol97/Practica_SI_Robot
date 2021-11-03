/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_robot;

/**
 *
 * @author pujol
 */
public class Robot {
    
    private int x;
    private int y;
    
    private int velocidad = 500; // espera en milisegundos
    
    public Robot(int y, int x){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    
    public void setVelocidad(int v) {
        this.velocidad = v;
    }
    
    public int getVelocidad() {
        return velocidad;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_robot;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pujol
 */
public class Robot extends Thread{
    
    private int x;
    private int y;
    
    static int velocidad = 500; // espera en milisegundos
    private boolean moverse = false;
    
    private int[] percepciones = {0,0,0,0,0,0,0,0};
    private Direccion mov_previo= null;
    private Direccion adyacencia = null;
    private Direccion adyacencia_prev = null;
    private int[] caracteristicas = new int[4];
    
    public Robot(int y, int x){
        this.x = x;
        this.y = y;
        this.start();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    
    @Override
    public void run(){
        while(true){
            System.out.println("espero");
            while(moverse){
                percibe();
                avanzar();
                try {
                    Thread.sleep(velocidad);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    
    public void setMovimiento(boolean moverse){
        this.moverse = moverse;
    }
    
    public boolean getMovimiento() {
        return this.moverse;
    }
    
    public void setVelocidad(int v) {
        this.velocidad = v;
    }
    
    public int getVelocidad() {
        return velocidad;
    }
    
    public void percibir(int[] percepciones){
        this.percepciones = percepciones;
        
    }
    
    public void percibe(){
        Main.percepciones();
    }
    
    public void mover(Direccion dir){
        switch(dir){
            case NORTE:
                this.y -= 1;
                break;
            case SUR:
                this.y += 1;
                break;
            case ESTE:
                this.x += 1;
                break;
            case OESTE:
                this.x -= 1;
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        Main.moverRobot(this.y, this.x, dir);
    }
    
    public void evaluar(){
        int[] carac = new int[4];
        
        if(percepciones[1] == 1 || percepciones[2] == 1){
            carac[0] = 1;
        }
        
        if(percepciones[3] == 1 || percepciones[4] == 1){
            carac[1] = 1;
        }
        
        if(percepciones[5] == 1 || percepciones[6] == 1){
            carac[2] = 1;
        }
        
        if(percepciones[7] == 1 || percepciones[0] == 1){
            carac[3] = 1;
        }
        
        this.caracteristicas = carac;
    }
    
    public void avanzar(){
        this.evaluar();
        //adyacencia_prev = adyacencia;
        if(adyacencia == null){
            if(caracteristicas[0] == 1 && caracteristicas[1] == 0){
                adyacencia = Direccion.NORTE;
                mov_previo = Direccion.ESTE;
                mover(Direccion.ESTE);
            } else if(caracteristicas[1] == 1 && caracteristicas[2] == 0){
                adyacencia = Direccion.ESTE;
                mov_previo = Direccion.SUR;
                mover(Direccion.SUR);
            } else if(caracteristicas[2] == 1 && caracteristicas[3] == 0){
                adyacencia = Direccion.SUR;
                mov_previo = Direccion.OESTE;
                mover(Direccion.OESTE);
            } else if(caracteristicas[3] == 1 && caracteristicas[0] == 0){
                adyacencia = Direccion.OESTE;
                mov_previo = Direccion.NORTE;
                mover(Direccion.NORTE);
            } else if(percepciones[1] != 1){
                mov_previo = Direccion.NORTE;
                mover(Direccion.NORTE);
            }
        } else {
            switch(mov_previo){
                case NORTE:
                    if(adyacencia == Direccion.ESTE){
                        if(percepciones[1] == 0){
                            if(percepciones[3] == 0){
                                adyacencia = Direccion.SUR;
                                mov_previo = Direccion.ESTE;
                                mover(Direccion.ESTE);
                            } else {
                                mov_previo = Direccion.NORTE;
                                mover(Direccion.NORTE);
                            }
                        } else if(percepciones[3] == 0){
                            adyacencia = Direccion.SUR;
                            mov_previo = Direccion.ESTE;
                            mover(Direccion.ESTE);
                        } else if(percepciones[7] == 0){
                            adyacencia = Direccion.NORTE;
                            mov_previo = Direccion.OESTE;
                            mover(Direccion.OESTE);
                        } else {
                            adyacencia = Direccion.OESTE;
                            mov_previo = Direccion.SUR;
                            mover(Direccion.SUR);
                        }
                    } else if(adyacencia == Direccion.OESTE){
                        if(percepciones[1] == 0){
                            if(percepciones[7] == 0){
                                adyacencia = Direccion.SUR;
                                mov_previo = Direccion.OESTE;
                                mover(Direccion.OESTE);
                            } else {
                                mov_previo = Direccion.NORTE;
                                mover(Direccion.NORTE);
                            }
                        } else if(percepciones[7] == 0){
                            adyacencia = Direccion.SUR;
                            mov_previo = Direccion.OESTE;
                            mover(Direccion.OESTE);
                        } else if(percepciones[3] == 0){
                            adyacencia = Direccion.NORTE;
                            mov_previo = Direccion.ESTE;
                            mover(Direccion.ESTE);
                        } else {
                            adyacencia = Direccion.ESTE;
                            mov_previo = Direccion.SUR;
                            mover(Direccion.SUR);
                        }
                    }
                    break;
                case SUR:
                    if(adyacencia == Direccion.ESTE){
                        if(percepciones[5] == 0){
                            if(percepciones[3] == 0){
                                adyacencia = Direccion.NORTE;
                                mov_previo = Direccion.ESTE;
                                mover(Direccion.ESTE);
                            } else {
                                mov_previo = Direccion.SUR;
                                mover(Direccion.SUR);
                            }
                        } else if(percepciones[3] == 0){
                            adyacencia = Direccion.NORTE;
                            mov_previo = Direccion.ESTE;
                            mover(Direccion.ESTE);
                        } else if(percepciones[7] == 0){
                            adyacencia = Direccion.SUR;
                            mov_previo = Direccion.OESTE;
                            mover(Direccion.OESTE);
                        } else {
                            adyacencia = Direccion.OESTE;
                            mov_previo = Direccion.NORTE;
                            mover(Direccion.NORTE);
                        }
                    } else if(adyacencia == Direccion.OESTE){
                        if(percepciones[5] == 0){
                            if(percepciones[7] == 0){
                                adyacencia = Direccion.NORTE;
                                mov_previo = Direccion.OESTE;
                                mover(Direccion.OESTE);
                            } else {
                                mov_previo = Direccion.SUR;
                                mover(Direccion.SUR);
                            }
                        } else if(percepciones[7] == 0){
                            adyacencia = Direccion.NORTE;
                            mov_previo = Direccion.OESTE;
                            mover(Direccion.OESTE);
                        } else if(percepciones[3] == 0){
                            adyacencia = Direccion.SUR;
                            mov_previo = Direccion.ESTE;
                            mover(Direccion.ESTE);
                        } else {
                            adyacencia = Direccion.ESTE;
                            mov_previo = Direccion.NORTE;
                            mover(Direccion.NORTE);
                        }
                    }
                    break;
                case ESTE:
                    if(adyacencia == Direccion.NORTE){
                        if(percepciones[3] == 0){
                            if(percepciones[1] == 0){
                                adyacencia = Direccion.OESTE;
                                mov_previo = Direccion.NORTE;
                                mover(Direccion.NORTE);
                            } else {
                                mov_previo = Direccion.ESTE;
                                mover(Direccion.ESTE);
                            }
                        } else if(percepciones[1] == 0){
                            adyacencia = Direccion.OESTE;
                            mov_previo = Direccion.NORTE;
                            mover(Direccion.NORTE);
                        } else if(percepciones[5] == 0){
                            adyacencia = Direccion.ESTE;
                            mov_previo = Direccion.SUR;
                            mover(Direccion.SUR);
                        } else {
                            adyacencia = Direccion.SUR;
                            mov_previo = Direccion.OESTE;
                            mover(Direccion.OESTE);
                        }
                    } else if(adyacencia == Direccion.SUR){
                        if(percepciones[3] == 0){
                            if(percepciones[5] == 0){
                                adyacencia = Direccion.OESTE;
                                mov_previo = Direccion.SUR;
                                mover(Direccion.SUR);
                            } else {
                                mov_previo = Direccion.ESTE;
                                mover(Direccion.ESTE);
                            }
                        } else if(percepciones[5] == 0){
                            adyacencia = Direccion.OESTE;
                            mov_previo = Direccion.SUR;
                            mover(Direccion.SUR);
                        } else if(percepciones[1] == 0){
                            adyacencia = Direccion.ESTE;
                            mov_previo = Direccion.NORTE;
                            mover(Direccion.NORTE);
                        } else {
                            adyacencia = Direccion.NORTE;
                            mov_previo = Direccion.OESTE;
                            mover(Direccion.OESTE);
                        }
                    }
                    break;
                case OESTE:
                    if(adyacencia == Direccion.NORTE){
                        if(percepciones[7] == 0){
                            if(percepciones[1] == 0){
                                adyacencia = Direccion.ESTE;
                                mov_previo = Direccion.NORTE;
                                mover(Direccion.NORTE);
                            } else {
                                mov_previo = Direccion.OESTE;
                                mover(Direccion.OESTE);
                            }
                        } else if(percepciones[1] == 0){
                            adyacencia = Direccion.ESTE;
                            mov_previo = Direccion.NORTE;
                            mover(Direccion.NORTE);
                        } else if(percepciones[5] == 0){
                            adyacencia = Direccion.OESTE;
                            mov_previo = Direccion.SUR;
                            mover(Direccion.SUR);
                        } else {
                            adyacencia = Direccion.SUR;
                            mov_previo = Direccion.ESTE;
                            mover(Direccion.ESTE);
                        }
                    } else if(adyacencia == Direccion.SUR){
                        if(percepciones[7] == 0){
                            if(percepciones[5] == 0){
                                adyacencia = Direccion.ESTE;
                                mov_previo = Direccion.SUR;
                                mover(Direccion.SUR);
                            } else {
                                mov_previo = Direccion.OESTE;
                                mover(Direccion.OESTE);
                            }
                        } else if(percepciones[5] == 0){
                            adyacencia = Direccion.ESTE;
                            mov_previo = Direccion.SUR;
                            mover(Direccion.SUR);
                        } else if(percepciones[1] == 0){
                            adyacencia = Direccion.OESTE;
                            mov_previo = Direccion.NORTE;
                            mover(Direccion.NORTE);
                        } else {
                            adyacencia = Direccion.NORTE;
                            mov_previo = Direccion.ESTE;
                            mover(Direccion.ESTE);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        
        
    }
}

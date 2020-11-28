package TrioPuntos;

import org.intellij.lang.annotations.Flow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class TrioPuntosGUI {
    public static void main(String[] args) {
        MarcoTrio marco = new MarcoTrio();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoTrio extends JFrame{
    public MarcoTrio(){
        setVisible(true);
        setBounds(800, 400, 700, 500);
        setTitle("Trio de Puntos");
        LaminaTrio lamina = new LaminaTrio();
        //lamina.setLayout(new BorderLayout());
        //add(lamina, BorderLayout.EAST);
        add(lamina);
    }

    public void paint() {
        paint();
    }

    public void paint(Graphics g){
        super.paint(g);
        Cloud nube = new Cloud();
        nube.fillCloud(10);
        nube.showCloud();

    }
}

class LaminaTrio extends JPanel {

    JButton botonExhaustivo = new JButton("Exhaustivo");
    JButton botonDyV = new JButton("DyV");

    public LaminaTrio(){
        add(botonExhaustivo);
        add(botonDyV);

        VentanaNueva ventanaOyente = new VentanaNueva();

        botonExhaustivo.addActionListener(ventanaOyente);
        botonDyV.addActionListener(ventanaOyente);
    }



    private class VentanaNueva implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //MarcoEmergenteEx marcoex = new MarcoEmergenteEx();
            //marcoex.setVisible(true);
            Object botonPulsado = actionEvent.getSource();
            if(botonPulsado == botonExhaustivo){
                //Ejecutamos metodo Exhaustivo
                MarcoEmergenteEx marcoex = new MarcoEmergenteEx();
                marcoex.setVisible(true);
                System.out.println("Boton exhaustivo pulsado");

            }
            else if(botonPulsado == botonDyV){
                //Ejecutamos metodo DyV
                MarcoEmergenteDyV marcodyv = new MarcoEmergenteDyV();
                marcodyv.setVisible(true);
                System.out.println("Boton DyV");
            }
        }
    }
}

class LaminaPuntos extends JPanel{
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
    }
}

class MarcoEmergenteEx extends JFrame{
    public MarcoEmergenteEx(){
        setTitle("Ventana nueva Exhaustivo");
        setBounds(100, 50, 700, 500);
    }
}

class MarcoEmergenteDyV extends JFrame{
    public MarcoEmergenteDyV(){
        setTitle("Ventana nueva DyV");
        setBounds(100, 550, 700, 500);
    }
}
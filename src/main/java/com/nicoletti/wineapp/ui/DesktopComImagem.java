package com.nicoletti.wineapp.ui;

import javax.swing.*;
import java.awt.*;

public class DesktopComImagem extends JDesktopPane {

    private Image imageFundo;

    public DesktopComImagem(String caminhoImagem) {
        this.imageFundo = new ImageIcon(caminhoImagem).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageFundo, 0, 0, getWidth(), getHeight(), this);
    }
}

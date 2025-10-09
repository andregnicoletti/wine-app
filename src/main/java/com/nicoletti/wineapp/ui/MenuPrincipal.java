package com.nicoletti.wineapp.ui;

import javax.swing.*;

public class MenuPrincipal extends JFrame {

    private JDesktopPane desktopPane;

    public MenuPrincipal() {
        setTitle("Sistema para controle de Vinhos");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

//        desktopPane = new JDesktopPane();
        desktopPane = new DesktopComImagem("wine.jpeg");
        setContentPane(desktopPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuSistema = new JMenu("Sistema");
        JMenuItem itemUsuario = new JMenuItem("Usuario");
        menuSistema.add(itemUsuario);

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem itemPaises = new JMenuItem("Paises");
        JMenuItem itemRegioes = new JMenuItem("Regioes");
        JMenuItem itemViniculas = new JMenuItem("Viniculas");
        JMenuItem itemUvas = new JMenuItem("Uvas");
        JMenuItem itemVinhos = new JMenuItem("Vinhos");
        menuCadastro.add(itemPaises);
        menuCadastro.add(itemRegioes);
        menuCadastro.add(itemViniculas);
        menuCadastro.add(itemUvas);
        menuCadastro.add(itemVinhos);

        JMenu menuRelatorio = new JMenu("Relatorios");
        JMenuItem itemRelatorios = new JMenuItem("Relatorio de venda");
        menuRelatorio.add(itemRelatorios);

        menuBar.add(menuSistema);
        menuBar.add(menuCadastro);
        menuBar.add(menuRelatorio);
        setJMenuBar(menuBar);

    }

//    static class DesktopComFundo extends JDesktopPane {
//
//        private Image imageFundo;
//
//        public DesktopComFundo(String caminhoImagem) {
//            this.imageFundo = new ImageIcon(caminhoImagem).getImage();
//        }
//
//        @Override
//        public void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            g.drawImage(imageFundo, 0, 0, getWidth(), getHeight(), this);
//        }
//
//    }

}


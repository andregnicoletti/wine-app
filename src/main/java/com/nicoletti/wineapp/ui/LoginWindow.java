package com.nicoletti.wineapp.ui;

import com.nicoletti.wineapp.actions.LoginAction;
import com.nicoletti.wineapp.service.api.AuthService;
import com.nicoletti.wineapp.service.impl.AuthServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

    private final AuthService authService;

    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JButton btnCancelar;

    public LoginWindow(AuthService authService) {
        this.authService = authService;

        setTitle("Tela de Login");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        componentesCriar();

        setVisible(true);
    }

    private void componentesCriar() {

        Font fontTitle = new Font(Font.MONOSPACED, Font.BOLD, 26);
        Font fontLabel = new Font(Font.MONOSPACED, Font.PLAIN, 16);

        lblTitulo = new JLabel("Sistema Vinhos");
        lblTitulo.setBounds(35, 20, 280, 30);
        lblTitulo.setFont(fontTitle);
        getContentPane().add(lblTitulo);

        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(20, 70, 90, 30);
        lblUsuario.setFont(fontLabel);
        getContentPane().add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 70, 160, 30);
        txtUsuario.setFont(fontLabel);
        getContentPane().add(txtUsuario);

        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 110, 160, 30);
        lblSenha.setFont(fontLabel);
        getContentPane().add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 110, 160, 30);
        txtSenha.setFont(fontLabel);
        getContentPane().add(txtSenha);

        btnEntrar = new JButton(new LoginAction(this, txtUsuario, txtSenha, authService));
        btnEntrar.setBounds(100, 160, 160, 30);
        btnEntrar.setFont(fontLabel);
        getContentPane().add(btnEntrar);
    }


    public static void main(String[] args) {


        JWindow splashScreen = new JWindow();

        JLabel label = new JLabel("Carregando sistema de vinhos...", SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        splashScreen.getContentPane().add(label);

        splashScreen.setSize(300, 100);
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setVisible(true);

        // Mantém a UI responsiva
        new Thread(() -> {
            try {
                Thread.sleep(1000); // tempo de splash
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                splashScreen.dispose();
                AuthServiceImpl authService = new AuthServiceImpl();
                new LoginWindow(authService);
            });
        }).start();

    }

}

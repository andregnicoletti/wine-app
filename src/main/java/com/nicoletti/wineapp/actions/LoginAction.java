package com.nicoletti.wineapp.actions;

import com.nicoletti.wineapp.service.api.AuthService;
import com.nicoletti.wineapp.ui.MenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class LoginAction extends AbstractAction {

    private final Component parent;
    private final JTextField txtUsuario;
    private final JPasswordField txtSenha;
    private final AuthService authService;

    public LoginAction(Component parent,
                       JTextField txtUsuario,
                       JPasswordField txtSenha,
                       AuthService authService) {

        super("Login");
        this.parent = parent;
        this.txtUsuario = txtUsuario;
        this.txtSenha = txtSenha;
        this.authService = authService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String usuario = txtUsuario.getText();
        char[] senha = txtSenha.getPassword();

        if (usuario == null || usuario.isBlank()) {
            JOptionPane.showMessageDialog(parent, "Campo <Usuário> é obrigatório.");
            txtUsuario.requestFocus();
            return;
        }
        if (senha.length == 0) {
            JOptionPane.showMessageDialog(parent, "Campo <Senha> é obrigatório.");
            txtSenha.requestFocus();
            return;
        }

        boolean ok = authService.authenticate(usuario, Arrays.copyOf(senha, senha.length));
        if (ok) {
            JOptionPane.showMessageDialog(parent, "Logado com sucesso!");
            //TODO - navegar para a próxima tela
            new MenuPrincipal().setVisible(true);
            //TODO - dispose da tela anterior


        } else {
            JOptionPane.showMessageDialog(parent, "Usuário ou senha inválidos.");
            txtSenha.setText("");
            txtSenha.requestFocus();
        }
    }
}

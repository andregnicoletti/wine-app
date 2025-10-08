package com.nicoletti.wineapp.ui;

import com.nicoletti.wineapp.model.Wine;
import com.nicoletti.wineapp.service.api.WineService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class MainWindow extends JFrame {
    private final WineService service;
    private final WineTableModel tableModel;
    private final JTable table;

    public MainWindow() {
        super("Wine App");
        this.service = new WineService();
        this.tableModel = new WineTableModel(service.findAll());
        this.table = new JTable(tableModel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        setJMenuBar(createMenuBar());
        add(createToolbar(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createStatusBar(), BorderLayout.SOUTH);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        file.add(exit);

        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Wine App\nJava Swing + Gradle\n© 2025",
                        "About", JOptionPane.INFORMATION_MESSAGE));
        help.add(about);

        bar.add(file);
        bar.add(help);

        return bar;
    }

    private JToolBar createToolbar() {
        JToolBar tb = new JToolBar();
        tb.setFloatable(false);

        JButton add = new JButton("Add");
        add.addActionListener(e -> onAdd());
        JButton remove = new JButton("Remove");
        remove.addActionListener(e -> onRemove());
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> onRefresh());

        tb.add(add);
        tb.add(remove);
        tb.addSeparator();
        tb.add(refresh);
        return tb;
    }

    private JPanel createStatusBar() {
        JPanel status = new JPanel(new BorderLayout());
        status.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        JLabel lbl = new JLabel("Ready");
        status.add(lbl, BorderLayout.WEST);
        return status;
    }

    private void onAdd() {
        JTextField name = new JTextField();
        JTextField grape = new JTextField();
        JTextField year = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1, 6, 6));
        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Grape:"));
        panel.add(grape);
        panel.add(new JLabel("Year:"));
        panel.add(year);

        int res = JOptionPane.showConfirmDialog(this, panel,
                "Add Wine", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                int y = Integer.parseInt(year.getText().trim());
                service.add(new Wine(name.getText().trim(), grape.getText().trim(), y));
                onRefresh();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid year", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onRemove() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Wine wine = tableModel.getAt(row);
            int res = JOptionPane.showConfirmDialog(this,
                    "Remove '" + wine.name() + "'?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                service.remove(wine);
                onRefresh();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a row first", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void onRefresh() {
        tableModel.setData(service.findAll());
    }

    // Table model simples
    private static class WineTableModel extends AbstractTableModel {
        private final String[] cols = {"Name", "Grape", "Year"};
        private java.util.List<Wine> data;

        public WineTableModel(List<Wine> data) {
            this.data = data;
        }

        public void setData(List<Wine> data) {
            this.data = data;
            fireTableDataChanged();
        }

        public Wine getAt(int row) {
            return data.get(row);
        }

        @Override public int getRowCount() { return data.size(); }
        @Override public int getColumnCount() { return cols.length; }
        @Override public String getColumnName(int c) { return cols[c]; }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Wine w = data.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> w.name();
                case 1 -> w.grape();
                case 2 -> w.year();
                default -> "";
            };
        }

        @Override
        public boolean isCellEditable(int row, int col) { return false; }
    }
}

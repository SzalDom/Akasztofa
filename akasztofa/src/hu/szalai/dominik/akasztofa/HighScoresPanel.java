package hu.szalai.dominik.akasztofa;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class HighScoresPanel extends JPanel {

	private DefaultTableModel model;

    public HighScoresPanel() {
        setLayout(new BorderLayout());

        // Táblázat modell létrehozása
        model = new DefaultTableModel();
        model.addColumn("#");
        model.addColumn("NÉV");
        model.addColumn("PONT");
        model.addColumn("MIKOR");

        // Táblázat létrehozása a modellből
        JTable table = new JTable(model);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Set custom font
                rendererComponent.setFont(new Font("Arial", Font.PLAIN, 30));
                return rendererComponent;
            }
        };
        
        table.setDefaultRenderer(Object.class, centerRenderer);
        
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        // Set column width
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(680);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(470);

        
        // Set row height
        table.setRowHeight(52); // Set the desired row height here
        
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 30));

        // Táblázat beállítása görgethetővé
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Adatok frissítése az adatbázis alapján
    public void frissit() {
        // Táblázatban lévő összes sor törlése
        model.setRowCount(0);

        // Adatok lekérdezése az adatbázisból
        List<AdatbazisBejegyzes> bejegyzesek = Adatbazis.kerPontok();
        for (int i = 0; i < bejegyzesek.size(); i++) {
            AdatbazisBejegyzes bejegyzes = bejegyzesek.get(i);
            model.addRow(new Object[]{i + 1, bejegyzes.getUsername(), bejegyzes.getScore(), bejegyzes.getTimestamp()});
        }
    }

}

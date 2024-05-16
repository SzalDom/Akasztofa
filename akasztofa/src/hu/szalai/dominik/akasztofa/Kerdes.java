package hu.szalai.dominik.akasztofa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public abstract class Kerdes extends Dialogus{

    public Kerdes(String cim, String szoveg, String[] gombNevek) {

        Runnable folyamat = new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    final JDialog dialog = new JDialog((JFrame) null, cim, true);
                    dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    dialog.setLayout(new BorderLayout());
                    dialog.setSize(400, 300);
                    dialog.setLocationRelativeTo(null);  // Középre helyezés
                    
                    // Szöveges rész hozzáadása
                    JLabel label = new JLabel("<html>" + szoveg.replaceAll("\n", "<br>") + "</html>", SwingConstants.CENTER);
                    dialog.add(label, BorderLayout.CENTER);

                    // Gombok hozzáadása
                    JPanel buttonPanel = new JPanel(new FlowLayout());
                    for (String gombNev : gombNevek) {
                        JButton gomb = new JButton(gombNev);
                        gomb.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                                kattintottak(gomb.getText());
                            }
                        });
                        buttonPanel.add(gomb);
                    }
                    dialog.add(buttonPanel, BorderLayout.SOUTH);

                    dialog.setVisible(true);
                });
            }
        };
        Thread szal = new Thread(folyamat);
        szal.start();
    }

}

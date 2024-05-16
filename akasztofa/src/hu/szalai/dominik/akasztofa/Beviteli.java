package hu.szalai.dominik.akasztofa;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Beviteli extends JDialog {
    private JTextField nevMezo;
    private JButton okGomb;

    public Beviteli(Frame parent, String cim, int pont) {
    	super(parent, cim, true); // true felelős azért, hogy modális legyen
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle(cim);
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(parent); // Középre helyezi az ablakot
        setLayout(new BorderLayout());

        // Felirat és beviteli mező létrehozása
        JPanel feliratPanel = new JPanel();
        JLabel feliratLabel = new JLabel("Név: ");
        nevMezo = new JTextField(15);
        feliratPanel.add(feliratLabel);
        feliratPanel.add(nevMezo);

        // OK gomb létrehozása és eseménykezelő hozzáadása
        okGomb = new JButton("OK");
        okGomb.addActionListener(new ActionListener() {
        	 @Override
             public void actionPerformed(ActionEvent e) {
        		String nev = nevMezo.getText();
        		if(nev!=null && !nev.equals("") && nev.length()<50) {
        			Adatbazis.beszur(nevMezo.getText(), pont);
        			dispose();
        		}else {
        			new Dialogus("Figyelem!","Kérem ne hagyja üresen a név mezőt!","Vettem") {
						
						@Override
						public void kattintottak(String gomb_neve) {

						}
					};
        		}
            }
        });

        // Alsó panel hozzáadása az OK gombbal
        JPanel alsoPanel = new JPanel();
        alsoPanel.add(okGomb);

        // Frame-hez hozzáadás
        add(feliratPanel, BorderLayout.CENTER);
        add(alsoPanel, BorderLayout.SOUTH);
        setVisible(true);

    }

}

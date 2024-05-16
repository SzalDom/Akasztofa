package hu.szalai.dominik.akasztofa;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Gombsor extends JPanel{
		
	// Az absztrakt metódus, amely visszaad egy boolean-t, hogy helyes volt-e a válasz
	public abstract boolean gombotNyomtak(char betu);
	
	private boolean tiltva;
	
	public Gombsor() {
        setLayout(new GridLayout(7, 5, 10, 10)); // 5x7 grid, távolságok beállítása
        createGombok();
        tiltva=false;
        setBackground(Color.BLACK);
    }

    private void createGombok() {
        char[] betuk = {'A', 'Á', 'B', 'C', 'D', 'E', 'É', 'F', 'G', 'H',
                'I', 'Í', 'J', 'K', 'L', 'M', 'N', 'O', 'Ó', 'Ö',
                'Ő', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'Ű',
                'V', 'W', 'X', 'Y', 'Z'};

		for (char c : betuk) {
		    JButton gomb = new JButton(String.valueOf(c));
		    gomb.setFont(new Font("Arial", Font.PLAIN, 46));
		    gomb.setFocusable(false);
		    gomb.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	if(!tiltva) { // Csak akkor vizsgáljuk meg a gombnyomást, ha a panel nincs tiltva
			            JButton clickedButton = (JButton) e.getSource(); // A megnyomott gomb lekérése
			            boolean helyes = gombotNyomtak(clickedButton.getText().charAt(0)); // A gombon lévő szöveg első karakterével hívjuk meg
			            clickedButton.setEnabled(false); // Gomb inaktiválása
			            if (helyes) {
			                clickedButton.setBackground(new Color(0, 100, 0)); // Sötétzöld, ha helyes
			            } else {
			                clickedButton.setBackground(Color.RED); // Piros, ha helytelen
			            }
		        	}
		        }
		    });
		    add(gomb);
		}
	}

	public void tilt(boolean tiltva) {
		this.tiltva=tiltva;
	}
	
	public void helyreallit() {
		this.tiltva=false;
		for (Component comp : this.getComponents()) {
            comp.setEnabled(true);
            comp.setBackground(Color.BLACK); // Narancssárga RGB

        }

	}

}

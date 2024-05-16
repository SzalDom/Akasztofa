package hu.szalai.dominik.akasztofa;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class MegfejtesPanel extends JPanel{
	
	private List<JButton> betuMezok;  // Inaktív gombok a betűknek
    private String aktualisSzo;
    private int hianyzoBetukSzama;
    
    private static Color narancs = new Color(255, 165, 0);
    
    public MegfejtesPanel() {
        betuMezok = new ArrayList<>();
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
    }
    
    public void inicializal(String szo) {
        this.aktualisSzo = szo;
        hianyzoBetukSzama = szo.length();  // Kezdetben minden betű hiányzik
        betuMezok.clear();  // Előző állapot törlése, ha volt
        this.removeAll();   // Minden korábbi komponenst eltávolítunk a panelről
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.setBackground(Color.BLACK);

        for (int i = 0; i < szo.length(); i++) {
            JButton gomb = new JButton(" ");  // Üres gombok létrehozása
            gomb.setContentAreaFilled(false);     
            gomb.setFocusable(false);// Inaktív gombok
            gomb.setPreferredSize(new Dimension(60, 60));  // Gombok mérete
            gomb.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, narancs));  // Alsó border
            gomb.setBackground(Color.BLACK);
            gomb.setForeground(narancs);
            gomb.setFont(new Font("Arial", Font.PLAIN, 46));
            betuMezok.add(gomb);
            panel.add(gomb);
        }
        
        this.add(panel,BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();
    }
    
    public int talalt(char betu) {

        for (int i = 0; i < aktualisSzo.length(); i++) {
            if (aktualisSzo.charAt(i) == betu) {
                JButton betuGomb = betuMezok.get(i);
                if (betuGomb.getText().equals(" ")) {  // Csak akkor csökkentjük, ha még nem volt felfedve
                    betuGomb.setText(String.valueOf(betu));  // Betű beírása a megfelelő helyre
                    hianyzoBetukSzama--;
                    
                }
            }
        }


        return hianyzoBetukSzama;  // Hány betű hiányzik még
    }

	public void felfed(boolean sikerult) {
		for (int i = 0; i < aktualisSzo.length(); i++) {
            JButton betuGomb = betuMezok.get(i);
            if (betuGomb.getText().equals(" ")) {  // Csak akkor csökkentjük, ha még nem volt felfedve
                betuGomb.setText(String.valueOf(aktualisSzo.charAt(i)));  // Betű beírása a megfelelő helyre
            }
            betuGomb.setForeground((sikerult)?Color.GREEN:Color.RED);
        }
		
	}

}

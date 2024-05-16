package hu.szalai.dominik.akasztofa;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JatekAblak extends Ablak{
	
	private int hatralevo_probalkozas=11;
	private int aktualis_pont=0;
	
	private ImageIcon[] kepek = new ImageIcon[12];
	
	private JLabel imageLabel = new JLabel();; // Képet megjelenítő JLabel
	
	private String aktualis_szo = "";
	
	private MegfejtesPanel megfejtesPanel;
	
	private Gombsor betukJPanel;
	
	private JLabel hatralevo_felirat;
	
	
	
	private void inicializal() {
		hatralevo_probalkozas=11;
		frissitAbrat(hatralevo_probalkozas);
		aktualis_szo=Szotar.kerVeletlen();
		megfejtesPanel.inicializal(aktualis_szo);
		betukJPanel.helyreallit();
		hatralevo_felirat.setText(""+hatralevo_probalkozas);
	}
	
	private void loadImages() {
        for (int i = 0; i <= 11; i++) {
            ImageIcon image = new ImageIcon(i + ".png"); // Kép betöltése
            kepek[i]=image; // Kép hozzáadása a listához
        }
    }
	
	private void frissitAbrat(int allapot) {
		if(allapot>=0 && allapot<=11) {
			imageLabel.setIcon(kepek[allapot]);
			repaint(); // JPanel frissítése a változások megjelenítéséhez
	        revalidate(); // A layout menedzser újraszámolja a komponens elrendezését
		}
	}
	
	public JatekAblak() {
		super();
		
		

		setLayout(new BorderLayout()); // BorderLayout használata
		

        // Fejléc JPanel létrehozása és hozzáadása
        JPanel fejlecJPanel = new JPanel(new BorderLayout());
        fejlecJPanel.setPreferredSize(new Dimension(1024, 100)); // Méret beállítása
        
        add(fejlecJPanel, BorderLayout.NORTH); // Hozzáadás a felső részhez
        
        megfejtesPanel = new MegfejtesPanel();
        
        JPanel hatralevo_panel = new JPanel(new BorderLayout());
        hatralevo_panel.setPreferredSize(new Dimension(250,100));
        
        JLabel hatralevo_felirat_label = new JLabel("Lehetőség:",SwingConstants.CENTER);
        hatralevo_felirat_label.setFont(new Font("Arial", Font.PLAIN,30));
        
        hatralevo_felirat = new JLabel(""+hatralevo_probalkozas,SwingConstants.CENTER);
        hatralevo_felirat.setFont(new Font("Arial", Font.PLAIN,30));
        
        hatralevo_panel.add(hatralevo_felirat_label,BorderLayout.NORTH);
        hatralevo_panel.add(hatralevo_felirat,BorderLayout.CENTER);
        
        fejlecJPanel.add(hatralevo_panel,BorderLayout.WEST);
        
        fejlecJPanel.add(megfejtesPanel,BorderLayout.CENTER);
        
        JPanel pontom_panel = new JPanel(new BorderLayout());
        pontom_panel.setPreferredSize(new Dimension(250,100));
        
        JLabel pontom_felirat_label = new JLabel("Pont:",SwingConstants.CENTER);
        pontom_felirat_label.setFont(new Font("Arial", Font.PLAIN,30));
        
        JLabel pontom_felirat = new JLabel(""+aktualis_pont,SwingConstants.CENTER);
        pontom_felirat.setFont(new Font("Arial", Font.PLAIN,30));
        
        //JPanel seged = new JPanel();
        
        //seged.add(pontom_felirat_label);
        
        pontom_panel.add(pontom_felirat_label,BorderLayout.NORTH);
        pontom_panel.add(pontom_felirat,BorderLayout.CENTER);
        
        fejlecJPanel.add(pontom_panel,BorderLayout.EAST);
        
        //beallitSzineket(fejlecJPanel);

        // Abra JPanel létrehozása és hozzáadása
        JPanel abraJPanel = new JPanel();
        abraJPanel.setPreferredSize(new Dimension(640, 800)); // Méret beállítása
        
        abraJPanel.add(imageLabel);
        
        
        
        JPanel kozepso_panel =new JPanel(new GridLayout(1,2));
        
        kozepso_panel.add(abraJPanel); // Hozzáadás a bal oldali részhez
        
        

        // Betűk JPanel létrehozása és hozzáadása
        betukJPanel = new Gombsor() {

			@Override
			public boolean gombotNyomtak(char betu) {
				
				//if aktualis_szo contains `betu` give true, else false.
				
				boolean helyes = aktualis_szo.contains(String.valueOf(betu));
				aktualis_pont+=Pontrendszer.szamol(helyes,betu,hatralevo_probalkozas);
				pontom_felirat.setText(""+aktualis_pont);
				if(helyes) {
					int hatralevo = megfejtesPanel.talalt(betu);
					if(hatralevo==0) {
						this.tilt(true);
						megfejtesPanel.felfed(true);

						Dialogus dialogus = new Dialogus("Győzelem!","Sikeres megfejtés! :)\nAktuális pontod: "+aktualis_pont,"Következő kihívás") {

							@Override
							public void kattintottak(String gomb_neve) {
								inicializal();
							}
							
						};
					}
					return true;
				}else {
					if(rossz_probalkozas()) { // Ha true az eredmény, akkor elfogyott az összes próbálkozás.
						
						this.tilt(true); // Gombsor.tilt(true);
						megfejtesPanel.felfed(false);
						
						Dialogus dialogus = new Dialogus("Vesztettél!","Sajnos nincs több próbálkozásod! :(\nAktuális pontod: "+aktualis_pont,"Tovább a szégyenfalra") {

							@Override
							public void kattintottak(String gomb_neve) {
								if(esemenyKezelo!=null) {
									Beviteli beviteli = new Beviteli(JatekAblak.this,"Írd be a neved!",aktualis_pont); // Blokkolja az ablakot, amíg nem záródik be a beviteli dialog
									esemenyKezelo.esemeny("VEGE");
								}
							}
							
						};
					}
					hatralevo_felirat.setText(""+hatralevo_probalkozas);
					return false;
				}
				
			}
        	
        };
        betukJPanel.setPreferredSize(new Dimension(640, 800)); // Méret beállítása
        kozepso_panel.add(betukJPanel); // Hozzáadás a jobb oldali részhez
        
        add(kozepso_panel,BorderLayout.CENTER);
        
        beallitSzineket(this);
        
    	loadImages();
        
        inicializal();
        
        setVisible(true); // Teszi láthatóvá az ablakot

    }
	
	private boolean rossz_probalkozas() {
		hatralevo_probalkozas--;
		frissitAbrat(hatralevo_probalkozas);
		
		if(hatralevo_probalkozas==0) {
			return true;
		}
		return false;
	}
}

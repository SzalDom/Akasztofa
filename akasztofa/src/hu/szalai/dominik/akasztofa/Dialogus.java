package hu.szalai.dominik.akasztofa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public abstract class Dialogus {

	public abstract void kattintottak(String gomb_neve);
	
	protected Thread szal;
	
	protected Runnable folyamat;
	
	public Dialogus() {}
	
	public Dialogus(String cim, String szoveg, String gombNeve) { // Külön szálon kell indítani, mert blokolja a fő szálat, és a gombpanel nem tud helyreállni, mert nem megy végbe az eseménykezelője.
		
	        Runnable folyamat = new Runnable() {
	            @Override
	            public void run() {
	                // GUI komponensekkel kapcsolatos kódokat az EDT-n (Event Dispatch Thread) futtatjuk
	                SwingUtilities.invokeLater(() -> {
	                    final JDialog dialog = new JDialog((JFrame) null, cim, true);
	                    dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	                    dialog.setLayout(new BorderLayout());
	                    dialog.setSize(400, 200);
	                    dialog.setLocationRelativeTo(null);  // Középre helyezés
	                    
	                    // Szöveges rész hozzáadása a dialógushoz
	                    JLabel label = new JLabel("<html>" + szoveg.replaceAll("\n", "<br>") + "</html>", SwingConstants.CENTER);
	                    dialog.add(label, BorderLayout.CENTER);
	                    
	                    // Gomb hozzáadása
	                    JButton tovabbGomb = new JButton(gombNeve);
	                    tovabbGomb.addActionListener(new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            dialog.dispose();  // Dialógus bezárása
	                            kattintottak(tovabbGomb.getText());  // Függvény meghívása a "Tovább" gomb lenyomására
	                        }
	                    });
	                    dialog.add(tovabbGomb, BorderLayout.SOUTH);
	                    
	                    dialog.setVisible(true);  // Dialógus megjelenítése
	                });
	            }
	        };
	        Thread szal = new Thread(folyamat);
	        szal.start();
	    }

}

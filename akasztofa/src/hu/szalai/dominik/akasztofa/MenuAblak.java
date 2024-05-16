package hu.szalai.dominik.akasztofa;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuAblak extends Ablak{
	
	private ArrayList<AdatbazisBejegyzes> bejegyzesek = new ArrayList<>();
		
	public MenuAblak() {
		super();

		setLayout(new BorderLayout()); // BorderLayout használata

		JPanel fejlec = new JPanel(new BorderLayout());

		JPanel cimPanel = new JPanel(); // Panel a cím feliratnak
		JPanel pontlistaPanel = new JPanel(); // Panel a pontlista feliratnak

		JLabel cim_felirat = new JLabel("AKASZTÓFA",SwingConstants.CENTER);
		cim_felirat.setFont(new Font("Arial", Font.PLAIN,40));
		JLabel pontlista_felirat = new JLabel("HIGH SCORES",SwingConstants.CENTER);
		pontlista_felirat.setFont(new Font("Arial", Font.PLAIN,20));

		cimPanel.add(cim_felirat); // Cím felirat hozzáadása a cím panelhez
		pontlistaPanel.add(pontlista_felirat); // Pontlista felirat hozzáadása a pontlista panelhez

		fejlec.add(cimPanel, BorderLayout.NORTH); // Cím panel hozzáadása a fejléc panelhez, NORTH pozícióba
		fejlec.add(pontlistaPanel, BorderLayout.CENTER); // Pontlista panel hozzáadása a fejléc panelhez, CENTER pozícióba

		add(fejlec, BorderLayout.NORTH); // Fejléc panel hozzáadása a fő panelhez, NORTH pozícióba
		
		
		HighScoresPanel pontlista_panel = new HighScoresPanel();
		
		add(pontlista_panel,BorderLayout.CENTER);
		
		JPanel lablec = new JPanel(new BorderLayout());
		
		JButton indito_gomb = new JButton("KEZDŐTHET A JÁTÉK");
		indito_gomb.setFocusable(false);
		indito_gomb.setFont(new Font("Arial", Font.PLAIN,30));
		
		indito_gomb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(esemenyKezelo!=null) {
					esemenyKezelo.esemeny("INDIT");
				}
            }
        });
		
		lablec.add(indito_gomb,BorderLayout.CENTER);
		add(lablec,BorderLayout.SOUTH);

        beallitSzineket(this);
        
        pontlista_panel.frissit();
        
        setVisible(true); // Teszi láthatóvá az ablakot

    }
	
	
}

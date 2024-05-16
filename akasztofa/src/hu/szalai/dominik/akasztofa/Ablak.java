package hu.szalai.dominik.akasztofa;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Ablak  extends JFrame {
	
	
	private final String gomb_kilepes = "Léptem";
	
	protected EsemenyKezelo esemenyKezelo;
	
	public void beallitEsemenyKezelo(EsemenyKezelo esemenyKezelo) {
		this.esemenyKezelo=esemenyKezelo;
	}

	protected static void beallitSzineket(Container container) {
	    container.setBackground(Color.BLACK);
	    container.setForeground(new Color(255, 165, 0)); // Narancssárga RGB
	    
	    if (container instanceof JPanel) {
	        Border orangeBorder = BorderFactory.createLineBorder(new Color(255, 165, 0), 2); // Narancssárga keret
	        ((JPanel) container).setBorder(orangeBorder);
	    }
	    
	    for (Component comp : container.getComponents()) {
	        if (comp instanceof Container) {
	            beallitSzineket((Container) comp); // Rekurzív hívás a gyermekkomponensekre
	        } else {
	            comp.setBackground(Color.BLACK);
	            comp.setForeground(new Color(255, 165, 0)); // Narancssárga RGB
	        }
	    }
	}

	// JFrame paraméterrel
	protected static void beallitSzineket(JFrame frame) {
	    beallitSzineket(frame.getContentPane()); // JFrame tartalma (content pane) az elején
	}


	
	public Ablak() {
		setTitle("Akasztófa Játék");
		setSize(1280, 1024); // Állítsd be az ablak méretét szükség szerint
		setResizable(false);
		

        setLocationRelativeTo(null); // Középre helyezi az ablakot
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	String[] gombok = {gomb_kilepes,"Meggondoltam magam"};
            	Kerdes kerdes = new Kerdes("Kilépés?","Biztos, hogy ki szeretnél lépni?\nAz összegyűjtött pontok így végleg elvesznek!",gombok) {

					@Override
					public void kattintottak(String gomb_neve) {
						if(gomb_neve.equals(gomb_kilepes)) {
							dispose();
						}
						
					}
            		
            	};
            }
        });
        
        setBackground(Color.BLACK);
        
	}

}

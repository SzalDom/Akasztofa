package hu.szalai.dominik.akasztofa;

import java.util.HashMap;
import java.util.Map;

public class Pontrendszer {
	
	private static Map<Character, Integer> letterPoints = new HashMap<>();
	private static boolean inicializalva=false;
	
	private static int legritkabb = 15;
	private static int ritka = 10;
	private static int gyakori = 5;
	
	public static int szamol(boolean jo_valasz, char betu, int hatralevo_probalkozasok) {
		if(!inicializalva) {
			inicializal();
		}
		
		if(jo_valasz) {
			return letterPoints.getOrDefault(betu, legritkabb); // Ha ritka betű, és nincs a listába
		}else {
			// Rossz válasz esetén az elején -1, majd -2 végül -3 pontot adunk.
			if(hatralevo_probalkozasok>7) {
				return -1;
			}else if(hatralevo_probalkozasok>4){
				return -2;
			}else return -3;
		}

	}
	
	private static void inicializal() {
		
		// Minden más betűt legrittkábbnak számítunk, nem rakjuk külön a Mapba.
		
        // Közepesen ritka betűk
        int moderatelyRarePoints = ritka;
        for (char c : new char[] {'H', 'V', 'B', 'D', 'G', 'M', 'É', 'Z', 'Á', 'O', 'I', 'S', 'R'}) {
            letterPoints.put(c, moderatelyRarePoints);
        }
        
        // Nagyon gyakori betűk
        int veryCommonPoints = gyakori;
        for (char c : new char[] {'N', 'K', 'L', 'A', 'T', 'E'}) {
            letterPoints.put(c, veryCommonPoints);
        }
		inicializalva=true;
	}

}

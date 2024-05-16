package hu.szalai.dominik.akasztofa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Szotar {
	private static List<String> szavak = new ArrayList<>();
    private static boolean inicializalva = false;
    private static final Random random = new Random();

    public static void inicializal() {
        try (BufferedReader reader = new BufferedReader(new FileReader("szotar.txt"))) {
            String sor;
            while ((sor = reader.readLine()) != null) {
                szavak.add(sor.trim().toUpperCase()); // Minden szót nagybetűvel adunk hozzá
            }
            inicializalva = true; // Beállítjuk, hogy sikeresen inicializálva lettünk
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String kerVeletlen() {
        if (!inicializalva) {
        	inicializal();
        }
        if(szavak.size()>0) {
        	int index = random.nextInt(szavak.size()); // Véletlen index a lista mérete alapján
        	return szavak.get(index);
        }
        return "";
    }
	
}

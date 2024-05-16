package hu.szalai.dominik.akasztofa;

public class Main {
	
	private static JatekAblak jatekAblak = null;
	private static MenuAblak menuAblak = null;
	

	public static void main(String[] args) {
		
		menuAblak = new MenuAblak();
		
		EsemenyKezelo esemenyKezelo = new EsemenyKezelo() {
			
			@Override
			public void esemeny(String azonosito) {
				
				switch (azonosito) {
				case "INDIT":
					menuAblak.dispose();
					jatekAblak = new JatekAblak();
					jatekAblak.beallitEsemenyKezelo(this); // Ezt az esemény kezelőt állítjuk be ide is
					break;

				case "VEGE":
					if(jatekAblak!=null) {
						jatekAblak.dispose();
						menuAblak = new MenuAblak();
						menuAblak.beallitEsemenyKezelo(this); // Ezt az esemény kezelőt állítjuk be ide is
					}
					break;
				}
				
			}
		};
		
		
		menuAblak.beallitEsemenyKezelo(esemenyKezelo);

	}

}

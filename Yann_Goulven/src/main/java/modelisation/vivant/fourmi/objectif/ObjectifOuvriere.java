package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public class ObjectifOuvriere implements Objectif {

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return true;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		if (aFaim(adulte.getTempsSansManger(), adulte.TEMPS_POUR_MANGER)) {
			return new MangerRecherche(this);
		} else if (this.veuxChasser()){
			return new ChasseRecherche(this);
		} else {
			return new NourrirPrendreNourriture(this);
		}
	}

	private boolean veuxChasser() {
		int random = (int)(Math.random() * 100);
		return random < 60;
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		// TODO Auto-generated method stub
		
	}

	
	private boolean aFaim(int tempsSansManger, int tempsMax) {
		int random = (int)(Math.random() * 100);
		if (tempsSansManger < tempsMax / 2) {
			return random < 10;
		} else if (tempsSansManger < tempsMax / 3) {
			return random < 20;
		} else if (tempsSansManger < tempsMax / 4) {
			return random < 50;
		} else if (tempsSansManger < tempsMax / 5) {
			return random < 80;
		} else if (tempsSansManger < tempsMax / 10) {
			return true;	
		} else {
			return false;
		}
	}
}

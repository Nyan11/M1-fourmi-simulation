package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public class ChasseAttaque implements Objectif {

	
private Objectif objectifRetour;
	
	public ChasseAttaque(Objectif objectifRetour) {
		this.objectifRetour = objectifRetour;
	}
	
	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return adulte.transporteNourriture() || !terrain.contientProiePosition(adulte.getPosition());
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		if (adulte.transporteNourriture()) {
			return new ChasseRamener(objectifRetour);
		} else {
			return new ChasseRecherche(objectifRetour);
		}
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		adulte.attaque(terrain);
		
	}

}

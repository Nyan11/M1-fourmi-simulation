package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.Comportement;
import modelisation.vivant.fourmi.etat.Adulte;

public class ChasseRamener implements Objectif {

	
	private Objectif objectifRetour;
	
	public ChasseRamener(Objectif objectifRetour) {
		this.objectifRetour = objectifRetour;
	}
	
	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return !adulte.transporteNonNull();
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return objectifRetour;
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		Position prochaine = adulte.getDeplacement().deplacementGardeManger(terrain);
		adulte.setProchainePosition(prochaine);
		adulte.lacherPheromone(terrain);
		if(adulte.presentSurLeGardeManger()) {
			adulte.deposerNourriture(terrain);
		}
	}

}

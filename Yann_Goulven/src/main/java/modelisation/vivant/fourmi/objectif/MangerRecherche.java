package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.Comportement;
import modelisation.vivant.fourmi.etat.Adulte;

public class MangerRecherche implements Objectif {
	
	private Objectif objectifRetour;

	public MangerRecherche(Objectif objectifRetour) {
		this.objectifRetour = objectifRetour;
	}

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return adulte.presentSurLeGardeManger();
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return new MangerConsommer(objectifRetour);
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		Position prochaine = adulte.getDeplacement().deplacementGardeManger(terrain);
		adulte.setProchainePosition(prochaine);		
	}

}

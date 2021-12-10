package modelisation.vivant;

import modelisation.GardeManger;
import modelisation.Terrain;
import modelisation.utils.Position;

public interface EtreVivant {
	
	public void deplacer(Terrain terrain);

	public void ajouterAuGardeManger(GardeManger gardeManger);

	public boolean estNourriture();

	public void step(Terrain terrain);

	public Position getPosition();

	public EtreVivant estManger(float f);

}

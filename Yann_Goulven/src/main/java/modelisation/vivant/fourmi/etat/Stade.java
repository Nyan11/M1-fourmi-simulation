package modelisation.vivant.fourmi.etat;

import modelisation.Fourmiliere;
import modelisation.Terrain;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;

public interface Stade {

	public Stade grandir(int age, Fourmi fourmi, Terrain terrain);

	public void changerPopulation(Fourmiliere fourmiliere);

	public void step(Terrain terrain);

	public Stade changerPopulationMort(Fourmiliere fourmiliere, Terrain terrain);

	public boolean epuiser();

	public EtreVivant manger(EtreVivant nourriture);

	public boolean estMale();

	public boolean estFemelle();

	public boolean estReine();

	public boolean estSoldat();

	public boolean estMort();

}

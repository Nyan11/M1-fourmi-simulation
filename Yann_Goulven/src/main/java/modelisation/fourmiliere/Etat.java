package modelisation.fourmiliere;

import modelisation.Fourmiliere;

public interface Etat {
	public void step(Fourmiliere fourmiliere);
	public Etat changerEtat(Fourmiliere fourmiliere);
}

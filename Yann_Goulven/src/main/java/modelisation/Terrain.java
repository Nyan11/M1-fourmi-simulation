package modelisation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import modelisation.pheromone.Pheromone;
import modelisation.pheromone.PheromoneRecherche;
import modelisation.utils.Position;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.fourmi.etat.Adulte;
import modelisation.vivant.proie.Proie;

public class Terrain implements Runnable {

	public final int TERRAIN_LARGEUR = 1200;
	public final int TERRAIN_LONGUEUR = 1200;

	public final int TAILLE_CASE = 30;
	public final int TEMPS_ETAPE = 10;

	protected Map<Position, List<Fourmi>> fourmis;
	protected Map<Position, List<Pheromone>> pheromones;
	protected Map<Position, List<EtreVivant>> proies;

	public List<Fourmiliere> fourmilieres;
	private int date;


	public Terrain() {
		this.fourmis = new ConcurrentHashMap<Position, List<Fourmi>>();
		this.pheromones = new ConcurrentHashMap<Position, List<Pheromone>>();
		this.proies = new ConcurrentHashMap<Position, List<EtreVivant>>();
		this.fourmilieres = new ArrayList<Fourmiliere>();
		this.date = 0;
	}

	public Terrain(Fourmi reine) {
		this();
		List<Fourmi> list = new ArrayList<Fourmi>();
		list.add(reine);
		this.fourmis.put(reine.getPosition(), list);
	}

	/**Main loop de la simulation.
	 * Met à jour l'état des différents objets de la simulation avec l'ordre suivant :
	 * - fourmis
	 * - pheromones
	 * - proies
	 */
	public void stepTerrain() {
		this.stepFourmis();
		this.stepPheromones();
		this.stepProies();
		this.stepFourmilieres();
		this.date++;
		this.date = this.date % 3600;
	}


	public void stepFourmilieres() {
		for (Fourmiliere fourmiliere : this.fourmilieres) {
			fourmiliere.step(this);
		}
	}

	public void stepFourmis() {
		Collection<Fourmi> allFourmis = this.getFourmis();
		for (Fourmi fourmi : allFourmis) {
			fourmi.step(this);		}
	}

	public void stepPheromones() {
		Collection<Pheromone> allPheromones = this.getPheromones();
		for (Pheromone pheromone : allPheromones) {
			pheromone.step(this);
		}
	}

	public void stepProies() {
		Collection<EtreVivant> allProies = this.getProies();
		for (EtreVivant proie : allProies) {
			proie.step(this);
		}
	}

	public Map<Position, List<Fourmi>> getMapFourmis() {
		return this.fourmis;
	}

	public Map<Position, List<Pheromone>> getMapPheromones() {
		return this.pheromones;
	}

	public Map<Position, List<EtreVivant>> getMapProie() {
		return this.proies;
	}

	public Collection<Fourmi> getFourmis() {
		Collection<Fourmi> allFourmis = new LinkedList<Fourmi>();
		for (Collection<Fourmi> list : this.fourmis.values()) {
			for (Fourmi fourmi : list) {
				allFourmis.add(fourmi);
			}
		}
		return allFourmis;
	}

	public Collection<Pheromone> getPheromones() {
		Collection<Pheromone> allPheromone = new LinkedList<Pheromone>();
		for (Collection<Pheromone> list : this.pheromones.values()) {
			for (Pheromone phero : list) {
				allPheromone.add(phero);
			}
		}
		return allPheromone;
	}

	@Override
	public void run() {

	}

	public Collection<EtreVivant> getProies() {
		Collection<EtreVivant> allProies = new LinkedList<EtreVivant>();
		for (Collection<EtreVivant> list : this.proies.values()) {
			for (EtreVivant proie : list) {
				allProies.add(proie);
			}
		}
		return allProies;
	}

	public void supprimerProie(Position position, EtreVivant proie) {
		List<EtreVivant> list = this.proies.get(position);
		if (list != null) {
			list.remove(proie);
			if (list.size() == 0) {
				this.proies.remove(position);
			}
		}
	}

	public void ajouteFourmi(Fourmi fourmi) {
		Position position = fourmi.getPosition();
		List<Fourmi> list = this.fourmis.get(position);
		if (list == null) {
			this.fourmis.put(position, new ArrayList<Fourmi>());
			list = this.fourmis.get(position);
		}
		list.add(fourmi);
	}

	public void rajouterProie(Position position, EtreVivant proie) {
		List<EtreVivant> list = this.proies.get(position);
		if (list == null) {
			this.proies.put(position, new ArrayList<EtreVivant>());
			list = this.proies.get(position);
		}
		list.add(proie);
	}

	public boolean contientProiePosition(Position position) {
		return this.proies.get(position) != null;
	}

	public boolean proiePretPostion(Position position) {
		if (proies.get(position) != null) return true;
		if (proies.get(position.nord()) != null) return true;
		if (proies.get(position.est()) != null) return true;
		if (proies.get(position.sud()) != null) return true;
		if (proies.get(position.ouest()) != null) return true;
		return false;

	}

	public void supprimerFourmi(Position position, Fourmi fourmi) {
		if(this.fourmis.get(position) != null) {
			this.fourmis.get(position).remove(fourmi);
			if (this.fourmis.get(position).size() == 0) {
				this.fourmis.remove(position);
			}
		} else {
			for (Fourmiliere fourmiliere : this.fourmilieres) {
				fourmiliere.nid.retireEnfant(fourmi);
			}
		}
	}

	public boolean periodeDeReproduction() {
		return this.date < 2700;
	}

	public boolean positionLibre(Position position, int size, Fourmi fourmi) {
		int x = position.x() - size / 2;
		int y = position.y() - size / 2;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(!this.positionLibre(new Position(x+i, y+j), fourmi)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean positionLibre(Position position, Fourmi fourmi) {
		if (this.positionLibre(position)) return true;
		if (this.fourmis.get(position) == null) return true;
		if (this.fourmis.get(position).size() > 1) return false;
		return this.fourmis.get(position).get(0).equals(fourmi);
	}
	
	public boolean positionLibre(Position position) {
		if (this.proies.get(position) != null) return false;
		if (this.pheromones.get(position) != null) return false;
		if (this.fourmis.get(position) != null) return false;
		return true;
	}

	public void creerFourmiliere(Adulte adulte) {
		this.fourmilieres.add(new Fourmiliere(adulte));
		this.supprimerFourmi(adulte.getPosition(), adulte.getFourmi());
	}

	public boolean periodePonte() {
		return 1800 < this.date && this.date < 2700;
	}

	public List<Fourmiliere> getFourmilieres() {
		return this.fourmilieres;
	}

	public boolean malePosition(Position position) {
		List<Fourmi> list = this.fourmis.get(position);
		if (list == null) return false;
		for (Fourmi fourmi : list) {
			if (fourmi.estMale()) return true;
		}
		return false;
	}
	
	public boolean femellePosition(Position position) {
		List<Fourmi> list = this.fourmis.get(position);
		if (list == null) return false;
		for (Fourmi fourmi : list) {
			if (fourmi.estFemelle()) return true;
		}
		return false;
	}

	public void ajoutePheromone(Pheromone pheromone) {
		List<Pheromone> list = this.getMapPheromones().get(pheromone.getPosition());
		if (list == null) {
			list = new ArrayList<Pheromone>();
			this.getMapPheromones().put(pheromone.getPosition(), list);
		}
		list.add(new PheromoneRecherche(pheromone.getPosition()));
	}
	
	private void ajoutProiesAleatoire(int nb, int x, int y, int size) {
		for (int i = 0; i < nb; i++) {
			Position position = new Position(
					(int)(Math.random() * size - x),
					(int)(Math.random() * size - y)
				);
			List<EtreVivant> list = this.getMapProie().get(position);
			if (list == null) {
				list = new ArrayList<EtreVivant>();
				this.getMapProie().put(position, list);
			}
			list.add(new Proie((int)(Math.random() * 50 + 60), position));
		}
	}
}

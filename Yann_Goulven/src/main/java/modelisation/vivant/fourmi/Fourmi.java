package modelisation.vivant.fourmi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelisation.Fourmiliere;
import modelisation.GardeManger;
import modelisation.Terrain;
import modelisation.pheromone.PheromoneRecherche;
import modelisation.pheromone.Pheromone;
import modelisation.utils.Position;
import modelisation.utils.Simulable;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.etat.Adulte;
import modelisation.vivant.fourmi.etat.Larve;
import modelisation.vivant.fourmi.etat.Mort;
import modelisation.vivant.fourmi.etat.Nymphe;
import modelisation.vivant.fourmi.etat.Oeuf;
import modelisation.vivant.fourmi.etat.Stade;
import modelisation.vivant.proie.Proie;

public class Fourmi implements EtreVivant, Simulable {

	private Position position;
	private Fourmiliere fourmiliere;

	private Stade stade;
	private int age;

	public Fourmi(Position position, Fourmiliere fourmiliere) {
		this.position = position;
		this.fourmiliere = fourmiliere;
		this.stade = new Oeuf();
		this.age = 0;
		this.stade.changerPopulation(this.fourmiliere);
		
	}
	
	public Fourmi(Position position, Fourmiliere fourmiliere, boolean isAdulte) {
		this(position, fourmiliere);
		this.stade = new Adulte(this);
	}

	public Fourmi(Position position, boolean estReine) {
		this.position = position;
		Adulte stadeReine = new Adulte(this);
		stadeReine.devenirReine();
		this.stade = stadeReine;
	}

	@Override
	public void step(Terrain terrain) {
		this.changerStade(terrain);
		this.stade.step(terrain);
	}

	private void changerStade(Terrain terrain) {
		if (!this.epuiser(terrain)) {
			this.age++;
			Stade newStade = this.stade.grandir(this.age, this, terrain);
			if (newStade != this.stade) {
				this.stade = newStade;
				this.stade.changerPopulation(this.fourmiliere);
				this.age = 0;
			}
		}
	}

	private boolean epuiser(Terrain terrain) {
		if (this.stade.epuiser()) {
			this.stade = this.stade.changerPopulationMort(fourmiliere, terrain);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deplacer(Terrain terrain) {}

	public Fourmiliere getFourmiliere() {
		return this.fourmiliere;
	}

	public Stade getStade() {
		return this.stade;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position prochaineCasePosition) {
		this.position = prochaineCasePosition;
	}

	public void updatePositionFourmi(Terrain terrain, Position prochaineCasePosition) {
		List<Fourmi> list;
		Map<Position, List<Fourmi>> mapFourmis = terrain.getMapFourmis();
		list = mapFourmis.get(this.getPosition());
		if (list != null) {
			list.remove(this);
			if (list.isEmpty()) {
				mapFourmis.remove(this.getPosition());
			}
		}
		list = mapFourmis.get(prochaineCasePosition);
		if (list == null) {
			mapFourmis.put(prochaineCasePosition, new ArrayList<Fourmi>());
			list = mapFourmis.get(prochaineCasePosition);
		}
		list.add(this);
		this.setPosition(prochaineCasePosition);
	}

	@Override
	public void ajouterAuGardeManger(GardeManger gardeManger) {}

	@Override
	public boolean estNourriture() {
		return false;
	}

	public EtreVivant nourrir(EtreVivant nourriture) {
		return this.stade.manger(nourriture);
	}

	@Override
	public EtreVivant estManger(float f) {
		return null;
	}

	public void setFourmiliere(Fourmiliere fourmiliere) {
		this.fourmiliere = fourmiliere;
	}

	@Override
	public String toString() {
		return "Fourmi [position=" + position + ", fourmiliere=" + fourmiliere + ", age=" + age
				+ "]";
	}

	public boolean estMale() {
		return this.stade.estMale();
	}

	public boolean estFemelle() {
		return this.stade.estFemelle();
	}
	
	
}

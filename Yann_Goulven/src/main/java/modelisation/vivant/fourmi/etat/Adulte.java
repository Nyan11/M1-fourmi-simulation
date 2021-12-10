package modelisation.vivant.fourmi.etat;

import java.util.ArrayList;
import java.util.List;

import modelisation.Fourmiliere;
import modelisation.GardeManger;
import modelisation.Terrain;
import modelisation.pheromone.PheromoneRecherche;
import modelisation.pheromone.PheromoneReproduction;
import modelisation.pheromone.Pheromone;
import modelisation.utils.Position;
import modelisation.utils.Simulable;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Comportement;
import modelisation.vivant.fourmi.Deplacement;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.fourmi.objectif.Objectif;
import modelisation.vivant.fourmi.objectif.ObjectifFemelle;
import modelisation.vivant.fourmi.objectif.ObjectifMale;
import modelisation.vivant.fourmi.objectif.ObjectifOuvriere;
import modelisation.vivant.fourmi.objectif.ObjectifReine;
import modelisation.vivant.fourmi.objectif.ObjectifSoldat;
import modelisation.vivant.fourmi.role.Role;

public class Adulte implements Stade, Simulable {
	
	public int TEMPS_POUR_MANGER = 900;
	
	private EtreVivant transporte;
	private Role role;
	private Fourmi fourmi;
	private int tempsSansManger;
	private float poids;
	private Objectif objectif;
	private Deplacement deplacement;
	private Position prochaineCasePosition;
	private int esperanceDeVie;
	
	
	public Adulte(Fourmi fourmi) {
		this.fourmi = fourmi;
		this.tempsSansManger = this.TEMPS_POUR_MANGER;
		this.poids = (float) ((Math.random()*0.5) + 1.5);
		this.deplacement = new Deplacement(this);
		this.choixRole();
		this.prochaineCasePosition = this.getPosition();
		this.esperanceDeVie = 5000 + (int) (Math.random() * 2000);
	}
	
	/**
	 * Prochaine étape de la simulation. A faire dans l'ordre:
	 * - choisir un comportement
	 * - faire un déplacement
	 * - mettre à jour sa position sur le terrain
	 */
	@Override
	public void step(Terrain terrain) {
		this.tempsSansManger--;
		if (this.objectif.aValiderObjectif(terrain, this)) {
			this.objectif = this.objectif.objectifSuivant(terrain, this);
		} else {
			this.objectif.step(terrain, this);
		}
		this.fourmi.updatePositionFourmi(terrain, prochaineCasePosition);
	}
	
	@Override
	public Stade grandir(int age, Fourmi fourmi, Terrain terrain) {
		if (age > this.esperanceDeVie) {
			/*
			if (this.role == Role.REINE) {
				System.out.println("La reine est morte de veillesse ):\n" + this);
			}
			*/
			return new Mort();
		} else {
			return this; 
		}
	}
	
	@Override
	public void changerPopulation(Fourmiliere fourmiliere) {
		fourmiliere.nbNymphes--;
		fourmiliere.nbAdultes++;
	}

	@Override
	public Stade changerPopulationMort(Fourmiliere fourmiliere, Terrain terrain) {
		fourmiliere.nbAdultes--;
		fourmiliere.nbMorts++;
		this.lacherTransporte(terrain);
		if (this.role == Role.REINE) {
			System.out.println("La reine est morte de faim ):\n" + this);
		}
		return new Mort();
	}
	
	private void choixRole() {
		int valeur = (int) (Math.random() * 100);
		if (valeur < 80) {
			this.role = Role.OUVRIERE;
			this.objectif = new ObjectifOuvriere();
		} else if (valeur < 90) {
			this.role = Role.SOLDAT;
			this.objectif = new ObjectifSoldat();
		} else if (valeur < 95){
			this.role = Role.MALE;
			this.objectif = new ObjectifMale();
		} else {
			this.role = Role.FEMELLE;
			this.objectif = new ObjectifFemelle();
		}
	}
	
	public void devenirReine() {
		this.role = Role.REINE;
		this.esperanceDeVie = 18000 + (int) (Math.random() * 21000);
		this.objectif = new ObjectifReine();
		this.TEMPS_POUR_MANGER = 1800;
		this.tempsSansManger = this.TEMPS_POUR_MANGER;
	}

	public void deposerNourriture(Terrain terrain) {
		this.transporte.ajouterAuGardeManger(this.fourmi.getFourmiliere().getGardeManger());
		this.transporte = null;
	}
	
	public boolean presentSurFourmiliere() {
		return this.fourmi.getPosition().equals(this.fourmi.getFourmiliere().getPosition());
	}

	public boolean presentSurLeGardeManger() {
		return this.fourmi.getPosition().equals(this.fourmi.getFourmiliere().getGardeManger().position);
	}
	
	public boolean proieVisible(Terrain terrain) {
		return terrain.proiePretPostion(this.getPosition());
	}
	
	public boolean postionVisible(Position position) {
		int distance = 10;
		int valeurX = this.fourmi.getPosition().x() - position.x();
		int valeurY = this.fourmi.getPosition().y() - position.y();
		return -distance < valeurX && 
				valeurX < distance && 
				-distance < valeurY && 
				valeurY < distance;
	}

	private EtreVivant getProie(Terrain terrain) {
		List<EtreVivant> list = terrain.getMapProie().get(this.fourmi.getPosition());
		return list.get(0);
	}

	private void lacherTransporte(Terrain terrain) {
		if (this.transporte != null) {
		terrain.rajouterProie(this.fourmi.getPosition(), this.transporte);
		this.transporte = null;
		}
	}
	
	public boolean transporteNonNull() {
		return this.transporte != null;
	}

	@Override
	public boolean epuiser() {
		return this.tempsSansManger < 0;
	}
	
	public Position getPosition() {
		return this.fourmi.getPosition();
	}

	public int getTempsSansManger() {
		return this.tempsSansManger;
	}

	public void prendreNourriture(Terrain terrain) {
		this.transporte = this.fourmi.getFourmiliere().getGardeManger().prendreAuGardeManger();
	}

	public boolean gardeMangerEstVide() {
		return this.fourmi.getFourmiliere().getGardeManger().estVide();
	}

	public void donnerAManger(Terrain terrain) {
		this.transporte = this.fourmi.getFourmiliere().nourrirLarves(this.transporte);
	}

	@Override
	public EtreVivant manger(EtreVivant nourriture) {
		this.tempsSansManger = this.TEMPS_POUR_MANGER;
		return nourriture.estManger(this.poids / 3);
	}
	
	public boolean manger() {
		GardeManger gardeManger = this.fourmi.getFourmiliere().getGardeManger();
		if(gardeManger.possedeNourriture(this.poids / 3)) {
			gardeManger.retirerNourriture(this.poids / 3);
			this.tempsSansManger = this.TEMPS_POUR_MANGER;
			return true;
		} else {
			return false;
		}
	}

	public boolean voirEnnemi() {
		return false;
	}
	
	public boolean transporteNourriture() {
		return this.transporte != null && this.transporte.estNourriture();
	}
	
	public void attaque(Terrain terrain) {
		EtreVivant proie = getProie(terrain);
		terrain.supprimerProie(this.fourmi.getPosition(), proie);
		this.transporte = proie;
	}

	public Fourmiliere getFourmiliere() {
		return this.fourmi.getFourmiliere();
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public Deplacement getDeplacement() {
		return this.deplacement;
	}

	public void setProchainePosition(Position position) {
		this.prochaineCasePosition = position;
	}

	public Fourmi getFourmi() {
		return this.fourmi;
	}

	public void setFourmiliere(Fourmiliere fourmiliere) {
		this.fourmi.setFourmiliere(fourmiliere);
	}

	@Override
	public String toString() {
		return "Adulte [transporte=" + transporte + ", role=" + role + ", fourmi="
				+ fourmi + ", tempsSansManger=" + tempsSansManger + ", poids=" + poids + ", objectif=" + objectif
				+ ", esperanceDeVie=" + this.esperanceDeVie + "]";
	}

	public void lacherPheromone(Terrain terrain) {
		terrain.ajoutePheromone(new PheromoneRecherche(this.getPosition()));
	}
	
	public void lacherPheromoneReproduction(Terrain terrain) {
		terrain.ajoutePheromone(new PheromoneReproduction(this.getPosition()));
	}

	public void mourrir() {
		this.esperanceDeVie = 0;
	}

	@Override
	public boolean estMale() {
		return this.role == Role.MALE;
	}

	@Override
	public boolean estFemelle() {
		return this.role == Role.FEMELLE;
	}
	
	@Override
	public boolean estReine() {
		return this.role == Role.REINE;
	}

	@Override
	public boolean estSoldat() {
		return this.role == Role.SOLDAT;
	}

	@Override
	public boolean estMort() {
		return false;
	}
	
	
}

package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Timer;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import modelisation.Fourmiliere;
import modelisation.Terrain;
import modelisation.pheromone.Pheromone;
import modelisation.utils.Position;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.proie.Proie;
import nicellipse.component.NiRectangle;

public class TerrainVue implements ActionListener {
	public final int graphicAnimationDelay = 50;
	
	public Terrain terrain;
	public NiRectangle vue;
	public Set<FourmiVue> fourmies;
	public Set<PositionVue> fourmisVues;
	public Set<PositionVue> pheromonesVues;
	public Set<PositionVue> proiesVues;
	public Set<PositionVue> batimentsVues;

	public TerrainVue(Terrain terrain) {
		this.terrain = terrain;
		this.vue = new NiRectangle();
		this.vue.setSize(new Dimension(1000, 1000));
		this.vue.setLocation(0, 0);
		this.vue.setBackground(Color.BLACK);
		
		this.fourmies = new HashSet<FourmiVue>();
		
		this.fourmisVues = new HashSet<PositionVue>();
		//this.updatePositionVue(fourmisVues, terrain.getMapFourmis().keySet(), 1, Color.BLUE);
		
		this.pheromonesVues = new HashSet<PositionVue>();
		//this.updatePositionVue(pheromonesVues, terrain.getMapPheromones().keySet(), 2, Color.YELLOW);
		
		this.proiesVues = new HashSet<PositionVue>();
		//this.updatePositionVue(proiesVues, terrain.getMapProie().keySet(), 1, Color.GREEN);
				
		this.batimentsVues = new HashSet<PositionVue>();
		//this.updateBatimentVue(1, Color.white);
	}
/*
	private void updatePositionVue(Collection<PositionVue> vues, Collection<Position> positions, int size, Color couleur) {
		Collection<PositionVue> aRetirer = new LinkedList<PositionVue>();
		for (Position pos : positions) {
			PositionVue positionVue = new PositionVue(pos, size, couleur);
			if (vues.add(positionVue)) {
				this.vue.add(positionVue.vue);
			}
		}
		for (PositionVue positionVue : vues) {
			if(!positions.contains(positionVue.position)) {
				aRetirer.add(positionVue);
				this.vue.remove(positionVue.vue);
			}
		}
		vues.removeAll(aRetirer);
	}
*/
	public void update() {
		/*
		this.updateFourmis();
		for (FourmiVue fourmi: this.fourmies) {
			fourmi.update();
		}
		*/
		//Position[] copyPositionFourmis = terrain.getMapFourmis().keySet().toArray(new Position[terrain.getMapFourmis().size()]);
		
		//Collection<Position> copyFourmis = new ArrayList<Position>();
		//for (Position pos : copyPositionFourmis) copyFourmis.add(pos);
		//Collection<Position> copyPheromones = new ArrayList<Position>(terrain.getMapPheromones().keySet());
		//Collection<Position> copyProies = new ArrayList<Position>(terrain.getMapProie().keySet());
		this.updateFourmis();
		this.updatePheromones();
		//this.updatePositionVue(pheromonesVues, copyPheromones, 2, Color.YELLOW);
		//this.updatePositionVue(proiesVues, copyProies, 1, Color.GREEN);
		//this.updateBatimentVue(1, Color.white);
		this.vue.updateUI();
	}

	private void updatePheromones() {
		Collection<PositionVue> aRetirer = new LinkedList<PositionVue>();
		for (Entry<Position, List<Pheromone>> entrie : terrain.getMapPheromones().entrySet()) {
			PositionVue positionVue = new PheromoneVue(entrie.getKey(), List.copyOf(entrie.getValue()));
			if (this.fourmisVues.add(positionVue)) {
				this.vue.add(positionVue.vue);
			}
		}
		for (PositionVue positionVue : this.fourmisVues) {
			if(!this.terrain.getMapFourmis().keySet().contains(positionVue.position)) {
				aRetirer.add(positionVue);
				this.vue.remove(positionVue.vue);
			}
		}
		this.fourmisVues.removeAll(aRetirer);
	}
/*
	private void updateBatimentVue(int size, Color couleur) {
		if (this.batimentsVues.size() != this.terrain.getFourmilieres().size()) {
			for (PositionVue batiment : this.batimentsVues) {
				this.vue.remove(batiment.vue);
			}
			this.batimentsVues.clear();
			this.terrain.getFourmilieres().stream().forEach(fourmiliere -> this.batimentsVues.add(new PositionVue(fourmiliere.getPosition(), size, couleur)));
		}
		for (PositionVue batiment : this.batimentsVues) {
			this.vue.add(batiment.vue);
		}
	}
*/
	private void updateFourmis() {
		Collection<PositionVue> aRetirer = new LinkedList<PositionVue>();
		for (Entry<Position, List<Fourmi>> entrie : terrain.getMapFourmis().entrySet()) {
			PositionVue positionVue = new FourmiVue(entrie.getKey(), entrie.getValue());
			if (this.fourmisVues.add(positionVue)) {
				this.vue.add(positionVue.vue);
			}
		}
		for (PositionVue positionVue : this.fourmisVues) {
			if(!this.terrain.getMapFourmis().keySet().contains(positionVue.position)) {
				aRetirer.add(positionVue);
				this.vue.remove(positionVue.vue);
			}
		}
		this.fourmisVues.removeAll(aRetirer);
	}
	
	private void updateVue() {
		Collection<PositionVue> aRetirer = new LinkedList<PositionVue>();
		for (Entry<Position, List<Fourmi>> entrie : terrain.getMapFourmis().entrySet()) {
			PositionVue positionVue = new FourmiVue(entrie.getKey(), entrie.getValue());
			if (this.fourmisVues.add(positionVue)) {
				this.vue.add(positionVue.vue);
			}
		}
		for (PositionVue positionVue : this.fourmisVues) {
			if(!this.terrain.getMapFourmis().keySet().contains(positionVue.position)) {
				aRetirer.add(positionVue);
				this.vue.remove(positionVue.vue);
			}
		}
		this.fourmisVues.removeAll(aRetirer);
	}
	
	public void start() {
		Timer animation = new Timer(10, this);
		animation.setDelay(this.graphicAnimationDelay);
		animation.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.update();
	}
}

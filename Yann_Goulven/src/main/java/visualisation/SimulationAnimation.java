package visualisation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import nicellipse.component.NiRectangle;

public class SimulationAnimation implements ActionListener {
	
	final int graphicAnimationDelay = 10;
	TerrainVue terrainVue;
	
	public SimulationAnimation(TerrainVue terrainVue) {
		this.terrainVue = terrainVue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		Component[] views =  this.home.getComponents();
		for (int i = 0; i < views.length; i++) {
			SomethingView next = (SomethingView) views[i];
			next.thingHasChanged();
		}
		*/
	}
	
	public void start() {
		Timer animation = new Timer(0, this);
		animation.setDelay(this.graphicAnimationDelay);
		animation.start();
	}
}

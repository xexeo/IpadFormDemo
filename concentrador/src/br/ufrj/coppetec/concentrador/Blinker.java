/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author ludes
 */
public class Blinker implements ActionListener{
	int counter = 0;
	JTextField txtField;
	Color color;
	
	public Blinker(JTextField txtField){
		super();
		this.txtField = txtField;
		this.color = txtField.getBackground();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (counter % 2 == 0){
			txtField.setBackground(Color.ORANGE);
		} else {
			txtField.setBackground(color);
		}
		counter++;
		if (counter>10) counter = 0;
	}
	
	
}

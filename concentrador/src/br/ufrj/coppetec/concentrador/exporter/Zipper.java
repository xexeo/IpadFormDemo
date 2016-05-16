/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador.exporter;

import java.io.File;

/**
 *
 * @author mangeli
 */
public class Zipper {
	private File jsonFile;
	private File zipFile;
	
	public Zipper(File f, File z){
		jsonFile = f;
		zipFile = z;
	}
	
	
}

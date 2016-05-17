/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador.exporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
	
	public void zipIt() throws Exception{
		byte[] buffer = new byte[1024];
		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		ZipEntry ze= new ZipEntry(zipFile.getName().split(Pattern.quote("."))[0]+".json");
		zos.putNextEntry(ze);
		FileInputStream in = new FileInputStream(jsonFile);
		int len;
		while ((len = in.read(buffer)) > 0) {
			zos.write(buffer, 0, len);
		}
 		in.close();
		zos.closeEntry();
    	//remember close it
    	zos.close();

	}
	
	
}

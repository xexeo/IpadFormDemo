package br.ufrj.coppetec.concentrador.exporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Compacta um arquivo com dados exportados para o formato zip.
 * 
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public class Zipper {
	private File jsonFile;	///< Arquivo com dados exportados no formato JSON
	private File zipFile;	///< Arquivo compactado no formato ZIP

	/**
	 * Constrói e configura um objeto para compactar um arquivo.
	 * @param f	referência para arquivo com os dados exportados no formato JSON
	 * @param z referência para arquivo compactado no formato ZIP
	 */
	public Zipper(File f, File z) {
		jsonFile = f;
		zipFile = z;
	}

	/**
	 * Executa a compactação com base nas configurações recebidas pelo construtor do objeto
	 * @throws Exception 
	 */
	public void zipIt() throws Exception {
		byte[] buffer = new byte[1024];
		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		ZipEntry ze = new ZipEntry(zipFile.getName().split(Pattern.quote("."))[0] + ".json");
		zos.putNextEntry(ze);
		FileInputStream in = new FileInputStream(jsonFile);
		int len;
		while ((len = in.read(buffer)) > 0) {
			zos.write(buffer, 0, len);
		}
		in.close();
		zos.closeEntry();
		// remember close it
		zos.close();

	}

}

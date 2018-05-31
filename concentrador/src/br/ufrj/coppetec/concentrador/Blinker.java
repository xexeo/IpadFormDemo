package br.ufrj.coppetec.concentrador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *  Faz um JTextField piscar.
 *
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 *
 */
public class Blinker implements ActionListener{
	int counter = 0; ///<  conta quantos blinks até 10
	JTextField txtField; ///< Mantém o link para o pai
	Color color; ///< cor atual

	/**
	*  Construtor precisa receber um JTextField
	*
	* @param txtField um JTextField que vai piscar
	*/
	public Blinker(JTextField txtField){
		super();
		this.txtField = txtField;
		this.color = txtField.getBackground();
	}


	/**
	* Executa o piscar trocando o background de normal para laranja.
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (counter % 2 == 0){
			txtField.setBackground(Color.ORANGE);
		} else {
			txtField.setBackground(color);
		}
		counter++;
		if (counter>9) counter = 0; // para não explodir
	}


}

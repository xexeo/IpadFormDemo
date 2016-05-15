/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador.exporter;

import java.sql.ResultSet;

/**
 *
 * @author mangeli
 */
public interface JSONBuilder {
	public String build(ResultSet result) throws Exception;
}

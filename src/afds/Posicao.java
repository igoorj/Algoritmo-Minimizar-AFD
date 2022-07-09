/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author igor
 */
public class Posicao {
    
    
    private Set elementos = new LinkedHashSet();
    
    
    public Posicao() { }
    
    public boolean vazio() {

		if (elementos.size() == 0) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * Limpa o conjunto de simbolos
	 *
	 */
	public void limpar(){
		elementos.clear();
	}	

	/**
	 * Inclui um elemento no final do conjunto de s�mbolos
	 * 
	 * @param elemento
	 *            Simbolo a ser inserido no conjunto de s�mbolos
	 */
	public void inclui(ConjuntoEstados elem) {
		elementos.add(elem);
	}

	/**
	 * Verfica se um Simbolo pertence a um dado conjunto de s�mbolos
	 * 
	 * @param elemento
	 *            Simbolo a ser verificado
	 * @return true se o Simbolo pertence ao conjunto de s�mbolos ou false caso
	 *         contr�rio
	 */
	public boolean pertence(ConjuntoEstados elemento) {

		for (Iterator iter = elementos.iterator(); iter.hasNext();) {

		}

		return false;

	}
        
       
        public Iterator iterator(){
		return elementos.iterator();
	}
    public Set getElementos() {
		return elementos; 
	}
	
	/**
	 * Retorna o tamanho do Conjunto
	 * @return tamanho do conjunto
	 */
	public int size(){
		return elementos.size();
	}
	
	
	/**
	 * Remove um elemento do conjunto
	 * 
	 */
	public void removerElemento(Estado e){
		elementos.remove(e);
	}
   
}

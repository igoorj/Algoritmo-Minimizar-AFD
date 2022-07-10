/**
 * Classe para a criacao de um automato finito deterministico
 *
 * @author Fabio Moreira Campos, Rafael Sachetto Oliveira, Tiago Jose Melquiades
 * @author Claudio Soares Junior, Gildo
 */
package afds;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AFD {

    private ConjuntoSimbolo simbolos;
    private ConjuntoEstados estados;
    private ConjuntoEstados estadosFinais;
    private ConjuntoTransicaoD funcaoPrograma;
    private Estado estadoInicial;

    /**
     * Metodo construtor de um Automato finito deterministico
     *
     * @param simbolos ConjuntoSimbolo que representa o alfabeto do automato
     * finito deterministico
     * @param estados ConjuntoEstados que representa o conjunto de estados do
     * automato finito deterministico
     * @param funcaoPrograma ConjuntoTransicaoD que representa a funcao programa
     * do automato finito deterministico
     * @param estadoInicial Estado que representa o estado inicial do automato
     * finito deterministico
     * @param estadosFinais ConjuntoEstados que representa o conjunto de estados
     * finais do automato finito deterministico
     */
    public AFD(ConjuntoSimbolo simbolos, ConjuntoEstados estados,
            ConjuntoTransicaoD funcaoPrograma, Estado estadoInicial,
            ConjuntoEstados estadosFinais) {
        this.simbolos = simbolos.clonar();
        this.estados = estados.clonar();
        this.funcaoPrograma = funcaoPrograma.clonar();
        this.estadoInicial = estadoInicial.clonar();
        this.estadosFinais = estadosFinais.clonar();
    }

    public AFD() {
        simbolos = new ConjuntoSimbolo();
        estados = new ConjuntoEstados();
        estadosFinais = new ConjuntoEstados();
        funcaoPrograma = new ConjuntoTransicaoD();
    }

    /**
     * Obt�m o estado inicial do aut�mato finito determin�stico
     *
     * @return estadoInicial - Estado que representa o estado inicial do
     * aut�mato finito determin�stico
     */
    public Estado getEstadoInicial() {
        return this.estadoInicial.clonar();
    }

    /**
     * Ajusta o estado inicial do aut�mato finito determin�stico para o valor
     * passado como par�metro
     *
     * @param estadoInicial um Estado a ser definido como estado inicial do
     * aut�mato finito determin�stico
     */
    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial.clonar();
    }

    /**
     * Obt�m o conjunto de estados do aut�mato finito determin�stico
     *
     * @return estados - ConjuntoEstados que representa o conjunto de estados do
     * aut�mato finito determin�stico
     */
    public ConjuntoEstados getEstados() {
        return this.estados.clonar();
    }

    /**
     * Ajusta o conjunto de estados do aut�mato finito determin�stico para o
     * valor passado como par�metro
     *
     * @param estados um ConjuntoEstados a ser definido como o conjunto de
     * estados do aut�mato finito determin�stico
     */
    public void setEstados(ConjuntoEstados estados) {
        this.estados = estados.clonar();
    }

    /**
     * Obt�m o conjunto de estados finais do aut�mato finito determin�stico
     *
     * @return estadosFinais - ConjuntoEstados que representa o conjunto de
     * estados finais do aut�mato finito determin�stico
     */
    public ConjuntoEstados getEstadosFinais() {
        return this.estadosFinais.clonar();
    }

    /**
     * Ajusta o conjunto de estados finais do aut�mato finito determin�stico
     * para o valor passado como par�metro
     *
     * @param estadosFinais um ConjuntoEstados a ser definido como o conjunto de
     * estados finais do aut�mato finito determin�stico
     */
    public void setEstadosFinais(ConjuntoEstados estadosFinais) {
        this.estadosFinais = estadosFinais.clonar();
    }

    /**
     * Obt�m a fun��o programa do aut�mato finito determin�stico
     *
     * @return funcaoPrograma - ConjuntoTransicaoD que representa a fun��o
     * programa do aut�mato finito determin�stico
     */
    public ConjuntoTransicaoD getFuncaoPrograma() {
        return this.funcaoPrograma.clonar();
    }

    /**
     * Ajusta a fun��o programa do aut�mato finito determin�stico para o valor
     * passado como par�metro
     *
     * @param funcaoPrograma um ConjuntoTransicaoD a ser definido como a fun��o
     * programa do aut�mato finito determin�stico
     */
    public void setFuncaoPrograma(ConjuntoTransicaoD funcaoPrograma) {
        this.funcaoPrograma = funcaoPrograma.clonar();
    }

    /**
     * Obt�m o alfabeto do aut�mato finito determin�stico
     *
     * @return simbolos - ConjuntoSimbolo que representa o alfabeto do aut�mato
     * finito determin�stico
     */
    public ConjuntoSimbolo getSimbolos() {
        return this.simbolos.clonar();
    }

    /**
     * Ajusta o alfabeto do aut�mato finito determin�stico para o valor passado
     * como par�metro
     *
     * @param simbolos um ConjuntoSimbolo a ser definido como o alfabeto do
     * aut�mato finito determin�stico
     */
    public void setSimbolos(ConjuntoSimbolo simbolos) {
        this.simbolos = simbolos.clonar();
    }

    /**
     * Cria e retorna uma c�pia do objeto AFD
     *
     * @return um clone desse AFD
     */
    public AFD clonar() {
        return new AFD(simbolos, estados, funcaoPrograma, estadoInicial,
                estadosFinais);
    }

    public String toString() {
        String s = new String();
        s += "(";
        s += simbolos.toString();
        s += ",";
        s += estados.toString();
        s += ",";
        s += this.getFuncaoPrograma().toString();
        s += ",";
        s += estadoInicial.toString();
        s += ",";
        s += estadosFinais.toString();
        s += ")";
        return s;
    }

    /**
     * Le as informaoces de um AFN em um arquivo XML passado como parametro
     *
     * @param pathArquivo define o arquivo de onde será lido as informacoes do
     * automato
     * @return retorna o automato lido
     */
    public void ler(String pathArquivo) throws Exception {
        String xmlPathname = pathArquivo;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlPathname);

        Element elem = doc.getDocumentElement();
        NodeList nl0 = elem.getElementsByTagName("simbolos");
        NodeList nl1 = elem.getElementsByTagName("estados");
        NodeList nl2 = elem.getElementsByTagName("estadosFinais");
        NodeList nl3 = elem.getElementsByTagName("funcaoPrograma");
        NodeList nl4 = elem.getElementsByTagName("estadoInicial");

        getChildTagValue(0, (Element) nl0.item(0), "elemento");
        getChildTagValue(1, (Element) nl1.item(0), "elemento");
        getChildTagValue(2, (Element) nl2.item(0), "elemento");
        Element eI = (Element) nl4.item(0);
        estadoInicial = new Estado(eI.getAttribute("valor"));

        getChildTagValue((Element) nl3.item(0), "elemento");

    }

    private void getChildTagValue(int tipo, Element elem, String tagName)
            throws Exception {
        NodeList children = elem.getElementsByTagName(tagName);

        if (children != null) {
            for (int i = 0; i < children.getLength(); i++) {
                Element child = (Element) children.item(i);

                if (child != null) {
                    switch (tipo) {
                        case 0:
                            char[] c = child.getAttribute("valor").toCharArray();
                            simbolos.inclui(new Simbolo(c[0]));
                            break;
                        case 1:
                            estados.inclui(new Estado(child.getAttribute("valor")));
                            break;
                        case 2:
                            estadosFinais.inclui(new Estado(child
                                    .getAttribute("valor")));
                            break;
                    }
                }
            }
        }
    }

    private void getChildTagValue(Element elem, String tagName)
            throws Exception {
        TransicaoD transD = new TransicaoD();
        NodeList children = elem.getElementsByTagName(tagName);
        if (children != null) {
            for (int i = 0; i < children.getLength(); i++) {
                Element child = (Element) children.item(i);
                if (child != null) {
                    transD.setOrigem(new Estado(child.getAttribute("origem")));
                    transD.setDestino(new Estado(child.getAttribute("destino")));
                    char[] c = child.getAttribute("simbolo").toCharArray();
                    transD.setSimbolo(new Simbolo(c[0]));
                    //int a =  Integer.parseInt(child.getAttribute("acao"));                                       
                    funcaoPrograma.inclui(transD);
                }
            }
        }
    }
    // Limpa a estrutura de dados do AFD

    private void limpa() {
        // limpa Alfabeto
        simbolos.limpar();
        // limpa conjunto de estados
        estados.limpar();
        // limpa Funcao Programa
        funcaoPrograma.limpar();
        // Limpa estados finais
        estadosFinais.limpar();
    }

    /**
     * Fun��o Programa
     *
     * @return estado alcan�avel depois de processar o Simbolo s a partir de
     * estados e
     * @param Estado estado onde iniciar� o processamento
     * @param Simbolo simbolo a ser processado
     */
    public Estado p(Estado e, Simbolo s) {
        ConjuntoTransicaoD fp;
        TransicaoD t;
        fp = getFuncaoPrograma();
        for (Iterator iter = fp.getElementos().iterator(); iter.hasNext();) {
            t = (TransicaoD) iter.next();
            if (t.getOrigem().igual(e) && t.getSimbolo().igual(s)) {
                return t.getDestino();
            }
        }

        return null;
    }

    /**
     * Fun��o Programa Estendida
     *
     * @return estado alcan�avel depois de processar a palavra p a partir de um
     * estados e
     * @param Estado Estado onde iniciar� o processamento
     * @param String palavra a ser processada
     */
    public Estado pe(Estado e, String p) {
        Estado eAtual = e;
        Simbolo s;
        int i = 0;
        while (i < p.length()) {
            s = new Simbolo(p.charAt(i));
            eAtual = p(eAtual, s);
            if (eAtual == null) {
                return null;
            }
            i++;
        }
        return eAtual;
    }

    /**
     * Retorna se uma palavra � aceita ou n�o por determinado AFD
     *
     * @return true caso a palavra � aceita; false caso contr�rio
     * @param String palavra a ser avaliada
     */
    public boolean Aceita(String p) {
        return getEstadosFinais().pertence(pe(getEstadoInicial(), p));
    }

    /*
	 * Cria arquivo XML do AFD com nome de filename.xml
	 * 
	 * @param filename Nome do arquivo XML que será criado sem a extensão.
     */
    public void toXML(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename + ".xml");
        PrintWriter saida = new PrintWriter(writer);

        saida.println("<AFD>");
        saida.println();

        saida.println("\t<simbolos>");
        for (Object s : this.getSimbolos().getElementos()) {
            saida.println("\t\t<elemento valor= \"" + s.toString() + "\"/>");
        }
        saida.println("\t</simbolos>");
        saida.println();

        saida.println("\t<estados>");
        for (Object s : this.getEstados().getElementos()) {
            saida.println("\t\t<elemento valor= \"" + s.toString() + "\"/>");
        }
        saida.println("\t</estados>");
        saida.println();

        saida.println("\t<estadosFinais>");
        for (Object s : this.getEstadosFinais().getElementos()) {
            saida.println("\t\t<elemento valor= \"" + s.toString() + "\"/>");
        }
        saida.println("\t</estadosFinais>");
        saida.println();

        saida.println("\t<funcaoPrograma>");
        for (Iterator iter = this.getFuncaoPrograma().getElementos().iterator(); iter.hasNext();) {
            TransicaoD element = (TransicaoD) iter.next();
            saida.println("\t\t<elemento origem= \""
                    + element.getOrigem().toString() + "\" destino= \""
                    + element.getDestino().toString() + "\" simbolo= \""
                    + element.getSimbolo().toString() + "\"/>");
        }
        saida.println("\t</funcaoPrograma>");
        saida.println();

        saida.println("\t<estadoInicial valor= \"" + this.getEstadoInicial().toString() + "\"/>");
        saida.println();

        saida.println("</AFD>");

        saida.close();
        writer.close();
    }

    public boolean otimizar() {
        /*
            Pré-requisitos do algoritmo de minimização
                a) O autômato deve ser determinístico;
                b) Não podem haver estados inatingíveis, a partir do estado inicial;
                c) A função P do AFD deve ser total.
         */

        // Não podem haver estados inatingíveis, a partir do estado inicial
        ConjuntoEstados conjuntoEstados = getEstados();
        int totalEstados = conjuntoEstados.size();

        // Transicoes
        Estado estado1;
        Estado estado2;
        ConjuntoEstados estadosAlcancaveis = new ConjuntoEstados();

        for (Iterator iter = conjuntoEstados.getElementos().iterator(); iter.hasNext();) {

            estado1 = (Estado) iter.next();
            if (!estadosAlcancaveis.pertence(estado1)) {
                estadosAlcancaveis.inclui(estado1);
            }

        }

        // Verifica se todos os estados são alcaçaveis
        if (!this.estados.igual(estadosAlcancaveis)) {
            return false;
        }

        // A função P do AFD deve ser total
        // to-do
        
        
        int estadosNaoEquivalentes[][] = new int[totalEstados][totalEstados];
        ConjuntoConjuntoEstados matrizPosicoes[][] = new ConjuntoConjuntoEstados[totalEstados][totalEstados];

        /**
         * Marcando os pares trivialmente diferentes
         */
        for (Iterator iter = conjuntoEstados.getElementos().iterator(); iter.hasNext();) {

            estado1 = (Estado) iter.next();

            for (Iterator iter2 = conjuntoEstados.getElementos().iterator(); iter2.hasNext();) {

                estado2 = (Estado) iter2.next();

                if ((this.estadosFinais.pertence(estado1)) && !(this.estadosFinais.pertence(estado2))
                        || ((this.estadosFinais.pertence(estado2)) && !(this.estadosFinais.pertence(estado1)))) {
                    estadosNaoEquivalentes[estado1.getIndex()][estado2.getIndex()] = 1;

                }
            }
        }

        /**
         * Marcação dos estados não-equivalentes;
             *
         */
        Estado p1;
        Estado p2;

        for (Iterator iter = conjuntoEstados.getElementos().iterator(); iter.hasNext();) {

            estado1 = (Estado) iter.next();
            for (Iterator iter2 = conjuntoEstados.getElementos().iterator(); iter2.hasNext();) {

                estado2 = (Estado) iter2.next();

                ConjuntoSimbolo novoCsi = this.getSimbolos().clonar();
                Simbolo s;
                boolean programa = true;

                for (Iterator iterSi = novoCsi.iterator(); iterSi.hasNext();) {
                    s = (Simbolo) iterSi.next();

                    p1 = this.p(estado1, s);
                    p2 = this.p(estado2, s);

                    if (p1.igual(p2)) {
                        programa = false;
                        break;
                    }
                }

                if (programa) {

                    for (Iterator iterSi = novoCsi.iterator(); iterSi.hasNext();) {
                        s = (Simbolo) iterSi.next();

                        p1 = this.p(estado1, s);
                        p2 = this.p(estado2, s);

                        // Verificando se a Posição já está marcada 
                        if (estadosNaoEquivalentes[p1.getIndex()][p2.getIndex()] == 1) {

                            estadosNaoEquivalentes[estado1.getIndex()][estado2.getIndex()] = 1;
                            estadosNaoEquivalentes = funcaoDeMarcar(matrizPosicoes, estadosNaoEquivalentes, estado1.getIndex(), estado2.getIndex());

                        } else {
                            // Criando o conjunto de estados que pertence a uma posição
                            ConjuntoEstados conjunto = new ConjuntoEstados();
                            // Adicionando o par de estados que pertencee a um Conjunto de estados
                            conjunto.inclui(estado1);
                            conjunto.inclui(estado2);
                            // Adicionando o conjunto de estados na lista da posição
                            matrizPosicoes[p1.getIndex()][p2.getIndex()].inclui(conjunto);
                            // incluir o par {qu,qv} numa lista relacionada a {pu,pv} para posterior análise;
                        }

                    }
                }

            }
        }

        ConjuntoEstados estados = new ConjuntoEstados();
        ConjuntoEstados estadosFinais = new ConjuntoEstados();
        Estado estadoInicial = new Estado();

       for (Iterator iter = conjuntoEstados.getElementos().iterator(); iter.hasNext();) {

            estado1 = (Estado) iter.next();
            for (Iterator iter2 = conjuntoEstados.getElementos().iterator(); iter2.hasNext();) {

                estado2 = (Estado) iter2.next();

                // Verifica se são equivalentes na matriz
                if (estadosNaoEquivalentes[estado1.getIndex()][estado2.getIndex()] == 0) {

                    // Pares de estados equivalentes não-finais são unificados como um estado não-final.
                    if (this.estadosFinais.pertence(estado1) && this.estadosFinais.pertence(estado2)) {
                        // Unificar os estados como finais
                        estadosFinais.inclui(estado1);
                    } else {
                        // Unificar os estados como não finais
                    }

                    if (this.estadoInicial.igual(estado1) || this.estadoInicial.igual(estado2)) {
                        // Se algum estado unificado é inicial, então, o estado resultante é inicial.
                        estadoInicial = estado1;
                    }
                    
                    if (!estados.pertence(estado1)) {
                        estados.inclui(estado1);
                    }

                } else {
                    
                    if (!estados.pertence(estado1)) {
                        estados.inclui(estado1);
                    }
                    if (!estados.pertence(estado2)) {
                        estados.inclui(estado2);
                    }
                    
                }

            }
        }
        
        this.estados = estados;
        this.estadosFinais = estadosFinais;
        this.estadoInicial = estadoInicial;
        
        return true;
    }

    /* Recebe a posição com todos os elementos a serem marcados */
    private int[][] funcaoDeMarcar(ConjuntoConjuntoEstados matrizPosicao[][], int matriz[][], int index1, int index2) {

        ConjuntoConjuntoEstados posicao = matrizPosicao[index1][index2];

        for (Iterator iter = posicao.getElementos().iterator(); iter.hasNext();) {

            // Recuperando o conjunto de estados da Posição
            ConjuntoEstados conjunto = (ConjuntoEstados) iter.next();

            // Recuperando todos os estados do conjunto de estados (2)
            List<Estado> estados = new ArrayList<>();
            for (Iterator iter2 = conjunto.getElementos().iterator(); iter.hasNext();) {
                estados.add((Estado) iter2.next());
            }

            // marcando a posição na matriz
            matriz[estados.get(0).getIndex()][estados.get(1).getIndex()] = 1;
            matriz = funcaoDeMarcar(matrizPosicao, matriz, estados.get(0).getIndex(), estados.get(1).getIndex());
        }
        return matriz;
    }
}

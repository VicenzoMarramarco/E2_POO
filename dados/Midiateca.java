package dados;

import java.util.ArrayList;

public class Midiateca implements Iterador {

	private int contador = 0;

	private ArrayList<Midia>midia;

	public Midiateca() {
		midia = new ArrayList<>();
		
	}

	public boolean cadastraMidia(Midia jogo) {
		return  midia.add(jogo);
	}


	public Midia consultaPorCodigo(int codigo) {
		 for (Midia m : midia) {
			if(m.getCodigo()==codigo){
				return m;
			}
		 }
		return null;
	}
	public ArrayList<Midia> consultaPorCategoria(Categoria categoria) {
		ArrayList<Midia> mi = new ArrayList<Midia>();
		for (Midia midiaa : midia) {
			if (midiaa.getCategoria() != null && midiaa.getCategoria().equals(categoria)) {
				mi.add(midiaa);
			}
		}
		return mi;
	}

	public ArrayList<Midia> consultaPorQualidade(int qualidade) {
		ArrayList<Midia> videosComQualidade = new ArrayList<>();
    for (Midia m : midia) {
        if (m instanceof Video && ((Video) m).getQualidade() == qualidade) {
            videosComQualidade.add(m);
        }
    }
    return videosComQualidade;
	}
	


	public Musica maiorduração() {
		double maiorDuracao = Double.MIN_VALUE;
		Musica musicaMaiorDuracao = null;
		for (Midia midia : midia) {
			if (midia instanceof Musica) {
				Musica musica = (Musica) midia;
				if (musica.getDuracao() > maiorDuracao) {
					maiorDuracao = musica.getDuracao();
					musicaMaiorDuracao = musica;
				}
			}
		}
		return musicaMaiorDuracao;

	}

	public boolean removeMidia(int codigo) {
		for (int i = 0; i < midia.size(); i++) {
			Midia m = midia.get(i);
			if (m.getCodigo() == codigo) {
				midia.remove(i);
				reset();
				return true;
			}
		}
		return false;
	}
	
	public double calcularSomatorioLocacoes() {
        double somatorio = 0.0;
        for (Midia midia : midia) {
            somatorio += midia.calculaLocacao();
        }
        return somatorio;
    }
	
	
	/**
	 * @see dados.Iterador#reset()
	 */
	public void reset() {
		this.contador =0;
	}


	/**
	 * @see dados.Iterador#hasNext()
	 */
	public boolean hasNext() {
		return contador < midia.size();
	}


	/**
	 * @see dados.Iterador#next()
	 */
	public Object next() {
		if (hasNext()) {
			return midia.get(contador++);
		}
		return null;	
	}

	public ArrayList<Midia> getMU(){
		return midia;
	}

	public void setMidia(ArrayList<Midia> midia) {
		this.midia = midia;
	}
	
	public double calMediaMusica() {
		double somaLocacoes = 0;
		int quantidadeMusicas = 0;
		for (Midia m : midia) {
			if (m instanceof Musica) {
				somaLocacoes += m.calculaLocacao();
				quantidadeMusicas++;
			}
		}
		if (quantidadeMusicas == 0) {
			return 0;
		}
		return somaLocacoes / quantidadeMusicas;
	}

	public Midia maisNovo(){
	
		Midia midiaMaisNova = midia.get(0);
		for (Midia m : midia) {
			if (m.getAno() > midiaMaisNova.getAno()) {
				midiaMaisNova = m;
			}
		}
		
		return midiaMaisNova;
	}
	


}

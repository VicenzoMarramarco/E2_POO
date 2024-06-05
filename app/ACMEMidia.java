package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


import dados.Categoria;
import dados.Midia;
import dados.Midiateca;
import dados.Musica;
import dados.Video;

public class ACMEMidia {
   
   private Scanner entrada = null;
   private PrintStream streamSaida;  
   private final String nomeArquivoEntrada = "entrada.txt";  
   private final String nomeArquivoSaida = "saida.txt";  


	private Midiateca midiateca = new Midiateca();

	public ACMEMidia() {
		try {
          BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
          entrada = new Scanner(streamEntrada);   
          streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
          System.setOut(streamSaida);  

          } catch (Exception e) {
          System.out.println(e);

        } 
		    Locale.setDefault(Locale.ENGLISH);   
            entrada.useLocale(Locale.ENGLISH);

	}

	public void executa() {
		CadastraVideos();
		CadastraMusicas();
		MostrarDadosDeDeterminadaMídia();
		mostrarDadosMidiasDeDeterminadaCategoria();
		MostrarDadosVídeoDeterminadaQualidade();
		MostrarDadosMúsicaDeMaiorDuração();
		RemoverMídia();
		MostrarSomatórioMidias();
		MostrarMúsicaMédia();
		MaisNovo();
	}

	public void CadastraVideos(){
		int qualidade;
		int codigo;
		String titulo;
		int ano;
		Video v;
		String categoriaNome;
		Categoria categoria;
	
		codigo = Integer.parseInt(entrada.nextLine());
		while(codigo != -1){
			titulo = entrada.nextLine();
			ano = Integer.parseInt(entrada.nextLine());
			categoriaNome = entrada.nextLine();
			qualidade = Integer.parseInt(entrada.nextLine());
			try {
				categoria = Categoria.valueOf(categoriaNome.toUpperCase());
			} catch (IllegalArgumentException e) {
				categoria = null; 
			}
			v = new Video(codigo, titulo, ano, categoria, qualidade);
			midiateca.cadastraMidia(v);
			streamSaida.println("Qualidade:"  +  v.getQualidade()   + "  Codigo:"+  v.getCodigo()    + "  Titulo:"+ v.getTitulo()   + "   Ano:" +  v.getAno()   + "  Categoria:" + categoria.getNome());
			codigo = Integer.parseInt(entrada.nextLine());
		}
	} 



	public void CadastraMusicas(){
		int codigo; 
		String titulo;
		int ano; 
		double duracao;
		String categoriaNome2;
		Categoria categoria2;
		Musica musica;

		codigo = Integer.parseInt(entrada.nextLine());
		while (codigo != -1) {
			titulo = entrada.nextLine();
			ano = Integer.parseInt(entrada.nextLine());
			categoriaNome2 = entrada.nextLine();
			duracao = Double.parseDouble(entrada.nextLine());
			try{
				categoria2 = Categoria.valueOf(categoriaNome2.toUpperCase());
			}catch(IllegalArgumentException e2){
				categoria2 = null;
				streamSaida.println("Erro-musica com codigo repetido:");
			}
			musica = new Musica(codigo, titulo, ano, categoria2, duracao);
			midiateca.cadastraMidia(musica);
			streamSaida.println("Duração:"  +  musica.getDuracao()   + "  Codigo:"+  musica.getCodigo()    + "  Titulo:"+ musica.getTitulo()   + "   Ano:" +  musica.getAno()   + "  Categoria:" + categoria2.getNome());
			codigo = Integer.parseInt(entrada.nextLine());
		}
	}

	public void MostrarDadosDeDeterminadaMídia() {
		int codigo = Integer.parseInt(entrada.nextLine());
        Midia midia = midiateca.consultaPorCodigo(codigo);

        if (midia == null) {
            streamSaida.println("Codigo inexistente");
        } else {
            streamSaida.println("Codigo:"+  midia.getCodigo()    + "  Titulo:"+ midia.getTitulo()   + "   Ano:" +  midia.getAno()   +"  ValorLocação:" + midia.getcalculaLocacao());
        }
	  
    }
	

	public void mostrarDadosMidiasDeDeterminadaCategoria() {
		String cat = entrada.nextLine();
		try {
			Categoria categoria = Categoria.valueOf(cat.toUpperCase());
			if (categoria != null) {
				ArrayList<Midia> midias = midiateca.consultaPorCategoria(categoria);
				if (!midias.isEmpty()) {
					for (Midia midia : midias) {
						streamSaida.println("Codigo:" + midia.getCodigo() + "  Titulo:" + midia.getTitulo() + "   Ano:" + midia.getAno() + "  Categoria:" + categoria.getNome() + "  ValorLocação:" + midia.getcalculaLocacao());
					}
				} else {
					streamSaida.println("Nenhuma mídia encontrada para a categoria: " + categoria.getNome());
				}
			} else {
				streamSaida.println("Categoria inválida!");
			}
		} catch (IllegalArgumentException e) {
			streamSaida.println("Erro na leitura da categoria: " + e.getMessage());
		}
	}

	public void MostrarDadosVídeoDeterminadaQualidade() {
		int qualidade = Integer.parseInt(entrada.nextLine());
		ArrayList<Midia> v = midiateca.consultaPorQualidade(qualidade);

		if (v.size() > 0) {
			for (Midia video : v) {
				streamSaida.println("Codigo:" + video.getCodigo() + "   Ano:"+ video.getAno()  + "  Titulo:"+ video.getTitulo()+ "  Categoria:" + video.getCategoria()+ "  ValorLocação:" + video.calculaLocacao());
			}
		} else {
			streamSaida.println("Qualidade não encontrada!!");
		}
	}

	public void MostrarDadosMúsicaDeMaiorDuração() {
		Musica muu = midiateca.maiorduração();
		if (muu != null) {
			streamSaida.println("Titulo: " + muu.getTitulo() + "   Duracão: " + muu.getDuracao() + "  ValorLocação:" + muu.calculaLocacao());
		} else {
			streamSaida.println("Nenhuma música encontrada!!");
		}
	}


	public void RemoverMídia(){
		if (entrada.hasNextLine()) {
        String linha = entrada.nextLine();
        try {
            int codigo = Integer.parseInt(linha);
            Midia midia = midiateca.consultaPorCodigo(codigo);

            if (midia != null) {
                streamSaida.println("Codigo:" + midia.getCodigo() + "   Ano: "+ midia.getAno() + "  Titulo:" + midia.getTitulo() + "  ValorLocação:" + midia.getcalculaLocacao() );
                midiateca.removeMidia(codigo);
            } else {
                streamSaida.println("Código Inexistente!!!");
            }
        } catch (NumberFormatException e) {
            streamSaida.println("Entrada inválida! Por favor, insira um número válido.");
        }
    } else {
        streamSaida.println("Linha não corresponde!");
    }
	}

	public void MostrarSomatórioMidias() {
		ArrayList<Midia> listaDeMidias = midiateca.getMU();
		if (listaDeMidias.isEmpty()) {
			streamSaida.println("Nenhuma mídia encontrada!!!");
		} else {
			double somatorio = midiateca.calcularSomatorioLocacoes();
			streamSaida.println("Somatorio:" + somatorio);
		}
	}

	public void MostrarMúsicaMédia(){
		ArrayList<Midia> listaDeMidias = midiateca.getMU();
		double mediaLocacoes = midiateca.calMediaMusica();

		if (mediaLocacoes == 0) {
			streamSaida.println("9:Nenhuma música encontrada.");
			return;
		}
	
		Musica musicaMaisProxima = null;
		double diferencaMinima = Double.MAX_VALUE;
		for (Midia m : listaDeMidias) {
			if (m instanceof Musica) {
				double diferenca = Math.abs(m.calculaLocacao() - mediaLocacoes);
				if (diferenca < diferencaMinima) {
					diferencaMinima = diferenca;
					musicaMaisProxima = (Musica) m;
				}
			}
		}
		if (musicaMaisProxima != null) {
			streamSaida.println("Média: " + String.format("%.2f", mediaLocacoes) + ", CodigoMaisDaMusicaProx:" + musicaMaisProxima.getCodigo() + ", Valor:" + musicaMaisProxima.calculaLocacao());
		} else {
			streamSaida.println("9:Nenhuma música encontrada.");
		}
	}

	public void MaisNovo(){
		Midia m = midiateca.maisNovo();
		if (m == null) {
			streamSaida.println("9:Nenhuma midia encontrada.");
		}else{
			streamSaida.println("Primeira midia: "+ " Ano: " + m.getAno() + ", Titulo: " + m.getTitulo() + ", Codigo: " + m.getCodigo());

		}
	}

}
package dados;


public class Musica extends Midia {

	private double duracao;
	private Categoria categoria;
	
	public Musica(int codigo, String titulo, int ano, Categoria categoria, double duracao) {
		super(codigo, titulo, ano, categoria);
		this.categoria = categoria;
		this.duracao = duracao;
	}

	@Override
	public double calculaLocacao() {
		double valorPorMinutoOuvido;
        
        switch (categoria) {
            case ACA:
                valorPorMinutoOuvido = 0.90;
                break;
            case DRA:
			    valorPorMinutoOuvido = 0.70;
                break;
            case FIC:
			     valorPorMinutoOuvido = 0.50;
                break;
            case ROM:
			     valorPorMinutoOuvido = 0.30;
                break;
            default:
			     valorPorMinutoOuvido = 0.0;
                break;
        }
        
        return duracao * valorPorMinutoOuvido;
    }
		
	

	public double getDuracao() {return duracao;}
	public void setDuracao(double duracao) {this.duracao = duracao;}
	public Categoria getCategoria() {return categoria;}
	public void setCategoria(Categoria categoria) {this.categoria = categoria;}
	

	



}

package dados;


public class Video extends Midia {

	private int qualidade;
	private Categoria categoria;

	public Video(int codigo, String titulo, int ano, Categoria categoria, int qualidade) {
		super(codigo, titulo, ano, categoria);
		this.categoria = categoria;
		this.qualidade = qualidade;
	}
	
	@Override
	public double calculaLocacao() {
		if(getAno() == 2024){
			return 20.0;
		}else if (getAno()<= 2023 && getAno()>=2000) {
			return 15.0;	
		}else if (getAno() < 2000) {
			return 10.0;
		}else{
			return 0.0;
		}
		
	}


	public int getQualidade() {return qualidade;}
	public void setQualidade(int qualidade) {this.qualidade = qualidade;}
	public Categoria getCategoria() {return categoria;}
	public void setCategoria(Categoria categoria) {this.categoria = categoria;}
	
	

}

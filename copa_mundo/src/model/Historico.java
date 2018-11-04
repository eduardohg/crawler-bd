package model;

public class Historico {
	private int titulos;
	private int participacoes;
	private int total_cartoes;
	private int cartoes_amarelos;
	private int cartoes_vermelhos;
	private int partidas;
	private int pontuacao;
	private int vitorias;
	private int derrotas;
	private int empates;
	private String nome_selecao;
	private double aproveitamento;
	private double media_cartoes;
	private double porc_part;
	
	public double getPorc_part() {
		return porc_part;
	}
	public void setPorc_part(double porc_part) {
		this.porc_part = porc_part;
	}
	public double getMedia_cartoes() {
		return media_cartoes;
	}
	public void setMedia_cartoes(double media_cartoes) {
		this.media_cartoes = media_cartoes;
	}
	private double porcPart;
	

	public double getPorcPart() {
		return porcPart;
	}
	public void setPorcPart(double porcPart) {
		this.porcPart = porcPart;
	}
	public double getAproveitamento() {
		return aproveitamento;
	}
	public void setAproveitamento(double aproveitamento) {
		this.aproveitamento = aproveitamento;
	}
	public int getTitulos() {
		return titulos;
	}
	public void setTitulos(int titulos) {
		this.titulos = titulos;
	}
	public int getParticipacoes() {
		return participacoes;
	}
	public void setParticipacoes(int participacoes) {
		this.participacoes = participacoes;
	}
	public int getTotal_cartoes() {
		return total_cartoes;
	}
	public void setTotal_cartoes(int total_cartoes) {
		this.total_cartoes = total_cartoes;
	}
	public int getCartoes_amarelos() {
		return cartoes_amarelos;
	}
	public void setCartoes_amarelos(int cartoes_amarelos) {
		this.cartoes_amarelos = cartoes_amarelos;
	}
	public int getCartoes_vermelhos() {
		return cartoes_vermelhos;
	}
	public void setCartoes_vermelhos(int cartoes_vermelhos) {
		this.cartoes_vermelhos = cartoes_vermelhos;
	}
	public int getPartidas() {
		return partidas;
	}
	public void setPartidas(int partidas) {
		this.partidas = partidas;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	public int getEmpates() {
		return empates;
	}
	public void setEmpates(int empates) {
		this.empates = empates;
	}
	public String getNome_selecao() {
		return nome_selecao;
	}
	public void setNome_selecao(String nome_selecao) {
		this.nome_selecao = nome_selecao;
	}
}

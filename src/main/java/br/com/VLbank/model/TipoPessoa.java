package br.com.VLbank.model;

public enum TipoPessoa {
	VAZIO("", "", "", TipoVazio.class),
	JURIDICA("JURIDICA", "CNPJ", "00.000.000/0000-00", CnpjGroups.class),
	FISICA("FISICA", "CPF", "000.000.000-00", CpfGroups.class);
	
	private final String descricao;
	private final String documento;
	private final String mascara;
	private final Class<?> group;
	
	private TipoPessoa(String descricao, String documento, String mascara, Class<?> group) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.group = group;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}

	public Class<?> getGroup() {
		return group;
	}
	
}

package com.spring.reserva.domain.enuns;

public enum Estado {

	DISPONIVEL(0, "Dísponivel"), MANUTENCAO(1, "Manutenção");

	private Integer cod;
	private String descricao;

	private Estado(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Estado toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Estado s : Estado.values()) {
			if (cod.equals(s.getCod())) {
				return s;
			}
		}

		throw new IllegalArgumentException("Situção inválida! " + cod);
	}

}

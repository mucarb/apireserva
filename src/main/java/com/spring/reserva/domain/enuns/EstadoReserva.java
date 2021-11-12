package com.spring.reserva.domain.enuns;

public enum EstadoReserva {

	PROVISORIA(0, "Provisória"), CONFIRMADA(1, "Confirmada"), CANCELADA(2, "Cancelada");

	private Integer cod;
	private String descricao;
	
	private EstadoReserva(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoReserva toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (EstadoReserva e : EstadoReserva.values()) {
			if (cod.equals(e.getCod())) {
				return e;
			}
		}

		throw new IllegalArgumentException("Estado inválido! " + cod);
	}
	
}

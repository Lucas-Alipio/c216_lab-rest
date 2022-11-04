package br.inatel.labs.labrest.server.model;

public class Curso {
	
	private Long id;
	private String descricao;
	private Integer cargaHoraria;
	
	
	
	public Curso() {
		super();
	}

	//constructor com ctrl + 3 > gcuf
	public Curso(Long id, String descricao, Integer cargaHoraria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.cargaHoraria = cargaHoraria;
	}
	
	// getters and setters com ctrl + 3 > ggas
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
}

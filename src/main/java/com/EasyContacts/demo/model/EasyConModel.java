package com.EasyContacts.demo.model;

import com.EasyContacts.demo.dto.*;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contatos")
public class EasyConModel {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String nome;
	private String numero;
	private String descricao;
	private String link;

	public EasyConModel() {
	}

	public EasyConModel(EasyConDto dto) {
		this.nome = dto.nome();
		this.numero = dto.numero();
		this.descricao = dto.descricao();
		this.link = dto.link();
	}

	public EasyConModel(UUID id, String nome, String numero, String descricao, String link) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.descricao = descricao;
		this.link = link;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}


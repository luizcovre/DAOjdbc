package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer wId;
	private String wNome;
	
	public Departamento() {
	}
	
	public Departamento(Integer wId, String wNome) {
		this.wId = wId;
		this.wNome = wNome;
	}

	public Integer getwId() {
		return wId;
	}

	public void setwId(Integer wId) {
		this.wId = wId;
	}

	public String getwNome() {
		return wNome;
	}

	public void setwNome(String wNome) {
		this.wNome = wNome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(wId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(wId, other.wId);
	}

	@Override
	public String toString() {
		return "Departamento [wId=" + wId + ", wNome=" + wNome + "]";
	}
	
	
}
package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Vendedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer wId;
	private String wNome;
	private String wEmail;
	private Date wDataNasc;
	private Double wSalBase;
	
	private Departamento wDepartamento;
	
	public Vendedor() {
	}

	public Vendedor(Integer wId, String wNome, String wEmail, Date wDataNasc, Double wSalBase, Departamento wDepartamento) {
		this.wId = wId;
		this.wNome = wNome;
		this.wEmail = wEmail;
		this.wDataNasc = wDataNasc;
		this.wSalBase = wSalBase;
		this.wDepartamento = wDepartamento;
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

	public String getwEmail() {
		return wEmail;
	}

	public void setwEmail(String wEmail) {
		this.wEmail = wEmail;
	}

	public Date getwDataNasc() {
		return wDataNasc;
	}

	public void setwDataNasc(Date wDataNasc) {
		this.wDataNasc = wDataNasc;
	}

	public Double getwSalBase() {
		return wSalBase;
	}

	public void setwSalBase(Double wSalBase) {
		this.wSalBase = wSalBase;
	}

	public Departamento getwDepartamento() {
		return wDepartamento;
	}

	public void setwDepartamento(Departamento wDepartamento) {
		this.wDepartamento = wDepartamento;
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
		Vendedor other = (Vendedor) obj;
		return Objects.equals(wId, other.wId);
	}

	@Override
	public String toString() {
		return "Vendedor [wId=" + wId + ", wNome=" + wNome + ", wEmail=" + wEmail + ", wDataNasc=" + wDataNasc
				+ ", wSalBase=" + wSalBase + ", wDepartamento=" + wDepartamento + "]";
	}
		
}
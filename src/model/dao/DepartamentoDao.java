package model.dao;

import java.util.List;

import model.entities.Departamento;

public interface DepartamentoDao {
	
	void insert(Departamento prObj);
	void update(Departamento prObj);
	void deleteById(Integer prId);
	Departamento findById(Integer prId);
	List<Departamento> findAll();
}
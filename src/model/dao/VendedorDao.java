package model.dao;

import java.util.List;

import model.entities.Vendedor;

public interface VendedorDao {

	void insert(Vendedor prObj);
	void update(Vendedor prObj);
	void deleteById(Integer prId);
	Vendedor findById(Integer prId);
	List<Vendedor> findAll();
}
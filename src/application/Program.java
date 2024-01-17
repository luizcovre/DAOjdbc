package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		
		Vendedor vendedor = vendedorDao.findById(3);		
		System.out.println(vendedor);
		
		Departamento depart = new Departamento(2, null);
		List<Vendedor> list = vendedorDao.findByDepartamento(depart);
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
		
		list = vendedorDao.findAll();
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
	}

}

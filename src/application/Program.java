package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		
		Vendedor vendedor = vendedorDao.findById(3);		
		/*System.out.println(vendedor);*/
		
		Departamento depart = new Departamento(2, null);
		/*List<Vendedor> list = vendedorDao.findByDepartamento(depart);
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
		
		list = vendedorDao.findAll();
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
		
		Vendedor newVendedor = new Vendedor(null, "TESTE", "TESTE@EMAIL.COM", new Date(), 1234.56, depart);
		vendedorDao.insert(newVendedor);
		System.out.println("Inserido com o ID = " + newVendedor.getwId());
		
		vendedor =  vendedorDao.findById(1);
		vendedor.setwNome("REPOLHO");
		vendedorDao.update(vendedor);*/
		
		System.out.print("Quer deletar qual Id? ");
		int Id = sc.nextInt();
		vendedorDao.deleteById(Id);
		System.out.println("Deletou!!!");
		
		sc.close();
	}

}

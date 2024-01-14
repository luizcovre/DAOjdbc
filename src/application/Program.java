package application;

import java.util.Date;

import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1,"Livros");
		
		Vendedor vendedor = new Vendedor(1, "Luiz", "@gmail", new Date(), 2345.67, obj);
		
		System.out.println(vendedor);
	}

}

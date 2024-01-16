package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao{

	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Vendedor prObj) {
	}

	@Override
	public void update(Vendedor prObj) {
	}

	@Override
	public void deleteById(Integer prId) {
	}

	@Override
	public Vendedor findById(Integer prId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select X.*, Y.Name as DepName "
			      + "from seller as X "
				  + "join department Y on (Y.Id = X.DepartmentId) "
				  + "where X.Id = ?");
			
			st.setInt(1, prId);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento depart = instanciaDepartamento(rs);
				Vendedor obj = instanciaVendedor(rs, depart);
				return obj;
			}
			return null; 
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Vendedor instanciaVendedor(ResultSet rs, Departamento depart) throws SQLException {
		Vendedor obj = new Vendedor();
		obj.setwId(rs.getInt("Id"));
		obj.setwNome(rs.getString("Name"));
		obj.setwEmail(rs.getString("Email"));
		obj.setwSalBase(rs.getDouble("BaseSalary"));
		obj.setwDataNasc(rs.getDate("BirthDate"));
		obj.setwDepartamento(depart);
		return obj;
	}

	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
		Departamento depart = new Departamento();
		depart.setwId(rs.getInt("DepartmentId"));
		depart.setwNome(rs.getString("DepName"));
		return depart;
	}

	@Override
	public List<Vendedor> findAll() {
		return null;
	}

	@Override
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select X.*, Y.Name as DepName "
			      + "from seller as X "
				  + "join department Y on (Y.Id = X.DepartmentId) "
				  + "where X.DepartmentId = ? "
				  + "ORDER BY Y.Name");
			
			st.setInt(1, departamento.getwId());
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento depart = map.get(rs.getInt("DepartmentId"));
				
				if (depart == null) {
					depart = instanciaDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), depart);
				}
				
				Vendedor obj = instanciaVendedor(rs, depart);
				list.add(obj);
			}
			return list; 
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
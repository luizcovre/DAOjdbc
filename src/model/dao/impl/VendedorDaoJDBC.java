package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO seller " +
					"(Name, Email, BirthDate, BaseSalary, DepartmentId) "+
					"VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, prObj.getwNome());
			st.setString(2, prObj.getwEmail());
			st.setDate(3, new java.sql.Date(prObj.getwDataNasc().getTime()));
			st.setDouble(4, prObj.getwSalBase());
			st.setInt(5, prObj.getwDepartamento().getwId());
			
			int linhasAfetadas = st.executeUpdate();
			
			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					prObj.setwId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro não esperado, nenhuma linha afetada?");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Vendedor prObj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE seller " +
					"Set Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "+
					"WHERE Id = ?");
			
			st.setString(1, prObj.getwNome());
			st.setString(2, prObj.getwEmail());
			st.setDate(3, new java.sql.Date(prObj.getwDataNasc().getTime()));
			st.setDouble(4, prObj.getwSalBase());
			st.setInt(5, prObj.getwDepartamento().getwId());
			st.setInt(6, prObj.getwId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer prId) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			
			st.setInt(1, prId);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
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
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select X.*, Y.Name as DepName "
			      + "from seller as X "
				  + "join department Y on (Y.Id = X.DepartmentId) "
				  + "ORDER BY X.Name");
						
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
				  + "ORDER BY X.Name");
			
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
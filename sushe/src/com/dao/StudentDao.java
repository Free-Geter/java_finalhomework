package com.dao;

import com.db.DBHelper;
import com.bean.StudentBean;

import java.util.*;
import java.sql.*;

public class StudentDao {
	
	//验证登录
	public String CheckLogin(String username, String password){
		String id = null;
		String sql="select * from Student where Student_Username='"+username+"' and Student_Password='"+password+"' and Student_State='在读，已入住'";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				id = rs.getString("Student_ID");
			}
		}
		catch(SQLException ex){}
		return id;
	}
	// 获取姓名
	public String CheckLogin2(String username, String password){
		String name = null;
		String sql="select * from Student where Student_Username='"+username+"' and Student_Password='"+password+"' and Student_State='在读，已入住'";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				name = rs.getString("Student_Name");
			}
		}
		catch(SQLException ex){}
		return name;
	}
	//验证密码
	public boolean CheckPassword(String id, String password){
		boolean ps = false;
		String sql="select * from Student where Student_ID='"+id+"' and Student_Password='"+password+"'";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				ps=true;
			}
		}
		catch(SQLException ex){}
		return ps;
	}
	//获取所有列表
	public List<StudentBean> GetAllList(String strwhere,String strorder){
		String sql="select * from Student,Class where Student_ClassID = Class_ID and ";
		if(!(isInvalid(strwhere)))
		{
			sql+=strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
//		System.out.println(sql);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<StudentBean> list=new ArrayList<StudentBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				StudentBean cnbean=new StudentBean();
				cnbean.setStudent_ID(rs.getInt("Student_ID"));
				cnbean.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
				cnbean.setStudent_Username(rs.getString("Student_Username"));
				cnbean.setStudent_Password(rs.getString("Student_Password"));
				cnbean.setStudent_Name(rs.getString("Student_Name"));
				cnbean.setStudent_Sex(rs.getString("Student_Sex"));
				cnbean.setStudent_State(rs.getString("Student_State"));
				cnbean.setStudent_ClassID(rs.getInt("Student_ClassID"));
				cnbean.setStudent_ClassName(rs.getString("Class_Name"));
				list.add(cnbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//获取列表
	public List<StudentBean> GetList(String strwhere,String strorder){
		String sql="select * from Student,Domitory,Building,Class where Student_ClassID=Class_ID and Student_DomitoryID=Domitory_ID and Domitory_BuildingID=Building_ID";
		if(!(isInvalid(strwhere)))
		{
			sql+=" and "+strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
//		System.out.println(sql);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<StudentBean> list=new ArrayList<StudentBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				StudentBean cnbean=new StudentBean();
				cnbean.setStudent_ID(rs.getInt("Student_ID"));
				cnbean.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
				cnbean.setStudent_Username(rs.getString("Student_Username"));
				cnbean.setStudent_Password(rs.getString("Student_Password"));
				cnbean.setStudent_Name(rs.getString("Student_Name"));
				cnbean.setStudent_Sex(rs.getString("Student_Sex"));
				cnbean.setStudent_ClassID(rs.getInt("Student_ClassID"));
				cnbean.setStudent_ClassName(rs.getString("Class_Name"));
				cnbean.setStudent_State(rs.getString("Student_State"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
				cnbean.setDomitory_Type(rs.getString("Domitory_Type"));
				cnbean.setDomitory_Number(rs.getString("Domitory_Number"));
				list.add(cnbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//获取指定ID的实体Bean
	public StudentBean GetAllFirstBean(String strwhere){
		String sql="select * from Student where "+strwhere;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		StudentBean cnbean=new StudentBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()){
				cnbean.setStudent_ID(rs.getInt("Student_ID"));
				cnbean.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
				cnbean.setStudent_Username(rs.getString("Student_Username"));
				cnbean.setStudent_Password(rs.getString("Student_Password"));
				cnbean.setStudent_Name(rs.getString("Student_Name"));
				cnbean.setStudent_Sex(rs.getString("Student_Sex"));
				cnbean.setStudent_State(rs.getString("Student_State"));	
				cnbean.setStudent_ClassID(rs.getInt("Student_ClassID"));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnbean;
	}
	//获取指定ID的实体Bean
	public StudentBean GetFirstBean(String strwhere){
		String sql="select * from Student,Domitory,Building,Class where  Student_ClassID=Class_ID and Student_DomitoryID=Domitory_ID and Domitory_BuildingID=Building_ID and "+strwhere;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		StudentBean cnbean=new StudentBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()){
				cnbean.setStudent_ID(rs.getInt("Student_ID"));
				cnbean.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
				cnbean.setStudent_Username(rs.getString("Student_Username"));
				cnbean.setStudent_Password(rs.getString("Student_Password"));
				cnbean.setStudent_Name(rs.getString("Student_Name"));
				cnbean.setStudent_Sex(rs.getString("Student_Sex"));
				cnbean.setStudent_ClassID(rs.getInt("Student_ClassID"));
				cnbean.setStudent_ClassName(rs.getString("Class_Name"));
				cnbean.setStudent_State(rs.getString("Student_State"));	
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnbean;
	}
	//获取指定ID的实体Bean
	public StudentBean GetAllBean(int id){
		String sql="select * from Student where Student_ID="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		StudentBean cnbean=new StudentBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setStudent_ID(rs.getInt("Student_ID"));
				cnbean.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
				cnbean.setStudent_Username(rs.getString("Student_Username"));
				cnbean.setStudent_Password(rs.getString("Student_Password"));
				cnbean.setStudent_Name(rs.getString("Student_Name"));
				cnbean.setStudent_Sex(rs.getString("Student_Sex"));
				cnbean.setStudent_ClassID(rs.getInt("Student_ClassID"));
				cnbean.setStudent_State(rs.getString("Student_State"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnbean;
	}
	//获取指定ID的实体Bean
	public StudentBean GetBean(int id){
		String sql="select * from Student,Domitory,Building,Class where Student_ClassID=Class_ID and Student_DomitoryID=Domitory_ID and Domitory_BuildingID=Building_ID and Student_ID="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		StudentBean cnbean=new StudentBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setStudent_ID(rs.getInt("Student_ID"));
				cnbean.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
				cnbean.setStudent_Username(rs.getString("Student_Username"));
				cnbean.setStudent_Password(rs.getString("Student_Password"));
				cnbean.setStudent_Name(rs.getString("Student_Name"));
				cnbean.setStudent_Sex(rs.getString("Student_Sex"));
				cnbean.setStudent_ClassID(rs.getInt("Student_ClassID"));
				cnbean.setStudent_ClassName(rs.getString("Class_Name"));
				cnbean.setStudent_State(rs.getString("Student_State"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnbean;
	}
	
	//添加
	public void Add(StudentBean cnbean){
		String sql="insert into Student (";
		sql+="Student_DomitoryID,Student_Username,Student_Password,Student_Name,Student_Sex,Student_ClassID,Student_State";
		sql+=") values(";
		sql+="'"+cnbean.getStudent_DomitoryID()+"','"+cnbean.getStudent_Username()+"','"+cnbean.getStudent_Password()+"','"+cnbean.getStudent_Name()+"','"+cnbean.getStudent_Sex()+"','"+cnbean.getStudent_ClassID()+"','"+cnbean.getStudent_State()+"'";
		sql+=")";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//修改
	public void Update(StudentBean cnbean){
		String sql="update Student set ";
		sql+="Student_DomitoryID='"+cnbean.getStudent_DomitoryID()+"',";
		sql+="Student_Username='"+cnbean.getStudent_Username()+"',";
		sql+="Student_Password='"+cnbean.getStudent_Password()+"',";
		sql+="Student_Name='"+cnbean.getStudent_Name()+"',";
		sql+="Student_Sex='"+cnbean.getStudent_Sex()+"',";
		sql+="Student_ClassID='"+cnbean.getStudent_ClassID()+"',";
		sql+="Student_State='"+cnbean.getStudent_State()+"'";
		
		sql+=" where Student_ID='"+cnbean.getStudent_ID()+"'";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//删除
	public void Delete(String strwhere){
		String sql="delete from Student where ";
		sql+=strwhere;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println("");
	}
	
}


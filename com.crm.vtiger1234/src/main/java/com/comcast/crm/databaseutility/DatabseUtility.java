package com.comcast.crm.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabseUtility {
	Connection con;
	public void getConnection(String url,String username,String password) throws SQLException {
		try {
		Driver div = new Driver();
		DriverManager.registerDriver(div);
		con=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
		}
	}
	public void getConnection() throws SQLException {
		try {
		Driver div = new Driver();
		DriverManager.registerDriver(div);
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger","root","root");
		}
		catch(Exception e) {
		}
	}
	public void closeDbconnection() throws SQLException {
		try {
		con.close();
		}
		catch(Exception e) {
		}
	}
	public ResultSet excecuteSelectQuery(String query) {
		ResultSet res = null;
		try {
			Statement st=con.createStatement();
		    res=st.executeQuery(query);
		}
		catch(Exception e) {
		}
		return res;
	}
	public int executeNonSelectQuery(String query) {
		int res=0;   
		try {
			Statement st=con.createStatement();
		    res=st.executeUpdate(query);
		} 
		catch(Exception e) {
		}
		return res;
	}
}

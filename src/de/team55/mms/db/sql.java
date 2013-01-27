package de.team55.mms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import de.team55.mms.function.User;


public class sql {
  
	String url = "com.mysql.jdbc.Driver";
	ResultSet rs = null;
	private Connection con = null;
	
	 // Hostname
    private static String dbHost = "localhost";
 
    // Port -- Standard: 3306
    private String dbPort = "3306";
 
    // Datenbankname
    private String database = "mms";
 
    // Datenbankuser
    private String dbUser = "root";
 
    // Datenbankpasswort
    private String dbPassword = "";
    
    public void connect(){
		try{
			Class.forName(url);
			this.con = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+dbPort+"/"+database+"?"+"user="+dbUser+"&"+"password="+dbPassword);
			this.con.setAutoCommit(false);
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate(
						"CREATE TABLE IF NOT EXISTS user" +
						"(" +
						"id int NOT NULL AUTO_INCREMENT, " +
						"email varchar(255) NOT NULL, " +
						"vorname varchar(255) , " +
						"namen varchar(255) , " +
						"password varchar(255)" +			
						"); " +
						"CREATE TABLE IF NOT EXISTS rights" +
						"(" +
						"id int NOT NULL, " +
						"userchange BOOLEAN NOT NULL, " +
						"modcreate BOOLEAN NOT NULL, " +
						"modacc BOOLEAN NOT NULL, " +
						"modread BOOLEAN" +				
						"); "
						
					);
			this.con.commit();
			stmt.close();
			
		}catch(SQLException e){
			//TODO fehler fenster aufrufen
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			//TODO fehler fenster aufrufen
			e.printStackTrace();
		} 
	}
	
	public void disconnect(){
		try{
			this.con.commit();
			this.con.setAutoCommit(true);
			this.con.close();
		}catch(SQLException e){
			//TODO fehler fenster aufrufen
			System.out.print(e.getMessage());
		}
		
	}
	
	public void usersave(User user){
		connect();
		Statement state = null;
		ResultSet res = null;
		int id = -1;
		try{
			state = con.createStatement();
			state.executeUpdate("INSERT INTO user (email,vorname,name,password) VALUES ('"+user.geteMail()+"','"+user.getVorname()+"','"+user.getNachname()+"','"+user.getPassword()+"');");
		}catch(SQLException e){
			//TODO fehler fenster aufrufen
			System.out.print(e.getMessage());
		}
		finally{
			try{
				con.commit();
			}catch(Exception e){}
			try{
				state.close();
			}catch(Exception e){}
		}
		try{
			state = this.con.createStatement();
			res = state.executeQuery("SELECT id FROM user WHERE email='"+user.geteMail()+"';");
			if(res.first());
			id = res.getInt("id");
			res.close();
			state.close();
		}catch(SQLException e){
			//TODO fehler fenster aufrufen
			e.printStackTrace();
		}
		if(id != -1){
			try{
				state = con.createStatement();
				state.executeUpdate("INSERT INTO rights (id,userchange,modcreate,modacc,modread) VALUES ("+id+",'"+user.getManageUsers()+"','"+user.getCreateModule()+"','"+user.getAcceptModule()+"','"+user.getReadModule()+"');");
			}catch(SQLException e){
				//TODO fehler fenster aufrufen
				System.out.print(e.getMessage());
			}
			finally{
				try{
					con.commit();
				}catch(Exception e){}
				try{
					state.close();
				}catch(Exception e){}
			}
		}else{
			System.out.print("Failed to write rights!");
		}
		disconnect();
	}
	
	public void userupdate(User user){
		connect();
		Statement state = null;
		ResultSet res = null;
		int id = -1;
		try{
			state = this.con.createStatement();
			state.executeUpdate("UPDATE user SET vorname = '"+user.getVorname()+"', name = '"+user.getNachname()+"', password = '"+user.getPassword()+"' WHERE email = '"+user.geteMail()+"' ;");
		}catch(SQLException e){
			//TODO fehler fenster aufrufen
			System.out.print(e.getMessage());
		}
		finally{
			try{
				this.con.commit();
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				state.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{
			state = this.con.createStatement();
			res = state.executeQuery("SELECT id FROM user WHERE email='"+user.geteMail()+"';");
			if(res.first());
			id = res.getInt("id");
			res.close();
			state.close();
		}catch(SQLException e){
			//TODO fehler fenster aufrufen
			e.printStackTrace();
		}
		if(id != -1){
			try{
				state = this.con.createStatement();
				state.executeUpdate("UPDATE rights SET userchange = '"+user.getManageUsers()+"', modcreate = '"+user.getCreateModule()+"', modacc = '"+user.getAcceptModule()+"', modread = '"+user.getReadModule()+"' WHERE id = "+id+" ;");
			}catch(SQLException e){
				//TODO fehler fenster aufrufen
				System.out.print(e.getMessage());
			}
			finally{
				try{
					this.con.commit();
				}catch(Exception e){
					e.printStackTrace();
				}
				try{
					state.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}else{
			System.out.print("Failed to write rights!");
		}
		disconnect();
	}
	
	public LinkedList<User> userload(){
		User zws = null;
		ResultSet res = null;
		Statement state = null;
		int i = 0;
		int j = 0;
		LinkedList<User> list = new LinkedList<>();
		connect();
		try{
			state = this.con.createStatement();
			res = state.executeQuery("SELECT * FROM user ;");
			res.first();
			zws = new User(res.getString("vorname"), res.getString("name"), res.getString("email"), res.getString("password"), false, false, false, false);
			list.add(zws);
			while(res.next()){
				zws = new User(res.getString("vorname"), res.getString("name"), res.getString("email"), res.getString("password"), false, false, false, false);
				list.add(zws);
				i++;
			}
			res.close();
			state.close();
		}catch(SQLException e){
			//TODO fehler fenster aufrufen
			e.printStackTrace();
		}
		try{
			state = this.con.createStatement();
			res = state.executeQuery("SELECT userchange, modcreate, modacc, modread FROM rights ;");
			res.first();
			j = list.size();
			list.getFirst().setManageUsers(res.getBoolean("userchange"));
			list.getFirst().setCreateModule(res.getBoolean("modcreate"));
			list.getFirst().setAcceptModule(res.getBoolean("modacc"));
			list.getFirst().setReadModule(res.getBoolean("modread"));
			for(int k = 0; k < j; k++){
				res.next();
				list.get(k).setManageUsers(res.getBoolean("userchange"));
				list.get(k).setCreateModule(res.getBoolean("modcreate"));
				list.get(k).setAcceptModule(res.getBoolean("modacc"));
				list.get(k).setReadModule(res.getBoolean("modread"));
			}
			res.close();
			state.close();
		}catch(SQLException e){
			//TODO fehler fenster aufrufen
			e.printStackTrace();
		}
		disconnect();
			
		return list;
		
	}
	
//	public User userload(String email){
//		User zws = null;
//		ResultSet res = null;
//		Statement state = null;
//		int id = -1;
//		connect();
//		try{
//			state = this.con.createStatement();
//			res = state.executeQuery("SELECT * FROM user WHERE position='"+email+"';");
//			res.first();
//			zws.set;
//			res.close();
//			state.close();
//		}catch(SQLException e){
//			//TODO fehler fenster aufrufen
//			e.printStackTrace();
//		}
//		try{
//			state = this.con.createStatement();
//			res = state.executeQuery("SELECT * FROM rights WHERE id='"+id+"';");
//			if(res.first());
//			zws.set;
//			res.close();
//			state.close();
//		}catch(SQLException e){
//			//TODO fehler fenster aufrufen
//			e.printStackTrace();
//		}
//		disconnect();
//			
//		return zws;
//		
//	}
	
}

package Main;

import java.sql.*;

import Database.Database;

public class ManageDataMain {

	public static void main(String[] args) throws SQLException {
		//Fer la mitja de la nota de cada user
		//Fer la mitja per a cada restaurant
		//Fer la mitja de la mitja de la nota per a cada user
		//Quants users hi ha 
		//Quants restaurants hi ha
		//Quantes relacions hi ha
		//Classificar users segons nota mitja
		final String allGrades="SELECT COUNT(grade) FROM Relation";
		final String allUsers="SELECT COUNT(DISTINCT id_user) FROM Relation";
		final String allRest="SELECT COUNT(DISTINCT id_rest) FROM Relation";
		final String avgAllGrades="SELECT AVG(grade) FROM Relation";
		int numGrades=0;
		int numUs=0;
		int numRes=0;
		float avgGrade=0;
		float[] avgUsGrade=null;
		float[] avgResGrade=null;
		ResultSet rs=null;
		Database db = new Database();
		db.connectBD();
		
		//numGrades=num grades we have in the DB
		rs=db.querryExecute(allGrades);
		if(rs.next()) 
			numGrades=rs.getInt(1);
		
		//numRes=num restaurants we have in the DB
		rs=db.querryExecute(allRest);
		if(rs.next()) 
			numRes=rs.getInt(1);
		
		//numUs=num users we have in the DB
		rs=db.querryExecute(allUsers);
		if(rs.next()) 
			numUs=rs.getInt(1);
		
		//avgGrade=average of grades we have in the DB
		rs=db.querryExecute(avgAllGrades);
		if(rs.next()) 
			avgGrade=rs.getFloat(1);
		
		avgUsGrade=avgUser(db);
		avgResGrade=avgRest(db);
		
		System.out.println("Number of grades in DB is:"+numGrades);
		System.out.println("Number of diferent users in DB is:"+numUs);
		System.out.println("Number of diferent restaurants in DB is:"+numRes);
		System.out.println("Average of grades in DB is:"+avgGrade);
	}
	
	public static float[] avgRest(Database db) throws SQLException {
		float avgRest[] = null;
		for(int i=1; i<101; i++) {
			String sql="SELECT CAST(AVG(R.grade) AS DECIMAL (10,2)) AS avg_grade FROM Relation R WHERE R.id_rest = 'Restaurant"+i+"' ;";
			ResultSet resu = db.querryExecute(sql);
			avgRest[i] = resu.getFloat(0);
		}
		return avgRest;	
	}

	public static float[] avgUser(Database db) throws SQLException {
		float avgUser[] = null;
		for(int i=1; i<74322; i++) {
			String sql="SELECT CAST(AVG(R.grade) AS DECIMAL (10,2)) AS avg_grade FROM Relation R WHERE R.id_user = 'User"+i+"' ;";
			ResultSet resu = db.querryExecute(sql);
			avgUser[i] = resu.getFloat(0);
		}
		return avgUser;	
	}
	

	
}

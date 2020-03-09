package Main;

import java.sql.PreparedStatement;

import Database.Database;

public class ManageDataMain {

	public static void main(String[] args) {
		//Fer la mitja de la nota de cada user
		//Fer la mitja per a cada restaurant
		//Fer la mitja de la mitja de la nota per a cada user
		//Quants users hi ha 
		//Quants restaurants hi ha
		//Quantes relacions hi ha
		//Classificar users segons nota mitja
		
		Database db = new Database();
		db.connectBD();
		
		final String allUsers="SELECT * FROM User";
		final String allRest="SELECT * FROM Restaurant";
		final String avgAllGrades="SELECT AVG(grade) AS avg_grades FROM Relation";
		
		
		
		
	}
	
	public float avgRest() {
		
		for(int i=1; i<101; i++) {
			String avgRest="SELECT AVG(R.grade) AS avg_grade FROM relation R WHERE R.id_rest = 'Restaurant"+i+"' ;";
			
		}
		
		
		
	}

	
}

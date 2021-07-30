package application;
import javafx.stage.*;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainPage extends Application {
	
	public static String foundpageid = "foundscene";
	public static String foundpageres = "/application/found.fxml";
	
	public static String loginpageid = "loginscene";
	public static String loginpageres = "/application/myscence.fxml";
	
	public static String mainpageid = "mainscene";
	public static String mainpageres = "/application/mainscene.fxml";
	
	public static String personpageid = "personscene";
	public static String personpageres = "/application/personscene.fxml";
	
	public static String Albumpageid = "albumpage";
	public static String Albumpageres = "Albumpage.fxml";
	
	public static String Admin1id = "admin1";
	public static String Admin1Res = "admin2.fxml";
	
	public static String f3id = "f3";
	public static String f3Res = "fun3.fxml";
	
	private StageController sc;
	
	public void start(Stage p1) {
		
		sc = new StageController();
		
		sc.setp1Stage("primarystage", p1);
		
		sc.loadStage(Admin1id, Admin1Res);
		sc.loadStage(loginpageid, loginpageres);
		sc.loadStage(f3id,f3Res);
		
//		sc.loadStage(foundpageid, foundpageres);
//		
//		sc.loadStage(mainpageid, mainpageres);
//		sc.loadStage(personpageid, personpageres);
//		sc.loadStage(Albumpageid, Albumpageres);
		
		sc.setStage(loginpageid);
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}

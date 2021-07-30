package application;
import java.net.URL;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageController {
	private HashMap<String , Stage> stages = new HashMap<String,Stage>();
	//
	private HashMap<String , Scene> scenes = new HashMap<String,Scene>();
	
	public void addScene(String name, Scene scene) {
		scenes.put(name,scene);
	}
	
	public Scene getScene(String name) {
		return scenes.get(name);
	}
	//
	public void addStage(String name, Stage stage) {
		stages.put(name,stage);
	}
	
	public Stage getStage(String name) {
		return stages.get(name);
	}
	
	public void setp1Stage(String p1name,Stage p1) {
		this.addStage(p1name, p1);
	}
	
	public boolean loadStage(String name,String resources) {
		try {
			
			URL location = getClass().getResource(resources);
			FXMLLoader l = new FXMLLoader();
			l.setLocation(location);
			l.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root = l.load();
			ControlledStage cs = (ControlledStage)l.getController();
			cs.setStageController(this);
			Scene tempScene = new Scene(root);
			Stage tempStage = new Stage();
			tempStage.setScene(tempScene);
			tempStage.setTitle(name);
			if(name.equals(mainPage.mainpageid)) {
				tempStage.initStyle(StageStyle.UNDECORATED);
				tempStage.setAlwaysOnTop(true);
				tempStage.setX(Maincontroller.screenx);
				tempStage.setY(Maincontroller.screeny);
			}
			
			this.addScene(name, tempScene);
			this.addStage(name, tempStage);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setStage(String name) {
		this.getStage(name).show();
		return true;
	}
	
	public boolean setStage(String show,String close) {
		getStage(close).close();
		setStage(show);
		return true;
	}
	
	public boolean switchScene(String sceneid) {
		getStage(mainPage.foundpageid).setScene(getScene(sceneid));
		return true;
	}
	
	
	public boolean unloadStage(String name) {
		if(stages.remove(name)==null) {
			System.out.print("cuowo shu ru chuangkouing");
			return false;
		}else {
			return true;
		}
	}
	
	public boolean updateStageAndScene(String name,String Resources) {
		unloadStage(name);
		unloadScene(name);
		loadStage(name,Resources);
		return true;
	}
	
		public boolean unloadScene(String name) {
			if(scenes.remove(name)==null) {
				System.out.print("cuowo shu ru chuangkouing");
				return false;
			}else {
				return true;
			}
		}

//	public boolean updateScene(String name,String Resources) {
//		unloadScene(name);
//		loadScene(name,Resources);
//		return true;
//	}
	
}

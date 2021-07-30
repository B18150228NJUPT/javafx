package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import connection.Albumdemo;
import connection.userdemo;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import user.Album;
import user.User;
import user.userinformation;

public class personController implements ControlledStage,Initializable{
	
	@FXML
    private Button found;

    @FXML
    private AnchorPane Collections;

    @FXML
    private Button person;

    @FXML
    private AnchorPane root;

    @FXML
    private Button main;

    @FXML
    private Label username;

	public void msg() {
		System.out.print("点击成功");
	}
	
	private StageController s;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setcontent();
	}

	@Override
	public void setStageController(StageController s1) {
		this.s = s1;
	}
	
	void setcontent() {
		userdemo ud = new userdemo();
		User u = ud.getUserById(userinformation.id);
		username.setText(u.getName());
		
		Collections.setOnMouseClicked((new EventHandler<MouseEvent>() { 
	        public void handle(MouseEvent event) { 
	        	collectionPage();
	        } 
	      }));
	}
	
	void collectionPage()
	{
		AnchorPane ap = new AnchorPane();
		VBox vb = new VBox(10);
		ArrayList<Album> a = new ArrayList<Album>();
		TranslateTransition tt = new TranslateTransition();
		tt.setDuration(Duration.millis(350));
		ap.setPrefWidth(400.0);
		ap.setPrefHeight(615.0);
		ap.setStyle("-fx-background-color: white;");
		AnchorPane.setTopAnchor(ap,700.0);
	  	AnchorPane.setLeftAnchor(ap, 0.0);
	  	root.getChildren().add(ap);
	  	Label l3 = new Label(">");
	  	l3.setStyle("-fx-font-family: 'KaiTi';-fx-font-size: 30;");
	  	AnchorPane.setTopAnchor(l3,0.0);
	  	AnchorPane.setLeftAnchor(l3, 350.0);
	  	ap.getChildren().add(l3);
	  	l3.setOnMouseClicked((new EventHandler<MouseEvent>() { 
	         public void handle(MouseEvent event) { 
	        	 root.getChildren().remove(ap);
	         } 
	      }));
	  	ScrollPane scrollp = new ScrollPane();
	  	scrollp.setPrefHeight(605);
	  	scrollp.setPrefWidth(400.0);
	  	AnchorPane.setTopAnchor(scrollp,30.0);
	  	AnchorPane.setLeftAnchor(scrollp, 0.0);
	  	ap.getChildren().add(scrollp);
	  	VBox vbx = new VBox();
	  	scrollp.setContent(vbx);
	  	userdemo ud = new userdemo();
	  	User u1 = ud.getUserById(userinformation.id);
	  	
	  	Albumdemo albumd = new Albumdemo();
	  	a = albumd.getAlbumbyIds(u1.getCollection());
	  	Album ab = null;
	  	for(int i=0; i<a.size(); i++)
	  	{
	  		ab = a.get(i);
	  		int ab_id = ab.getId();
		  	AnchorPane a1 = new AnchorPane();
		  	a1.setPrefWidth(400.0);
			a1.setPrefHeight(50.0);
		  	String album_image_path = ab.getImagepath();
			String s1 = new File(System.getProperty("user.dir") + album_image_path).getAbsoluteFile().toURI().toString();
	  		Image ig = new Image(s1,50, 50, false, false);
	  		ImageView iv = new ImageView(ig);
			Label l1 = new Label(ab.getName());
			Label l2 = new Label(ab.getAuthor());
			AnchorPane.setTopAnchor(iv,0.0);
		  	AnchorPane.setLeftAnchor(iv, 10.0);
		  	AnchorPane.setTopAnchor(l1,20.0);
		  	AnchorPane.setLeftAnchor(l1, 70.0);
		  	AnchorPane.setTopAnchor(l2,20.0);
		  	AnchorPane.setLeftAnchor(l2, 250.0);
		  	a1.getChildren().addAll(iv,l1,l2);
		  	vbx.getChildren().add(a1);
		  	a1.setOnMouseEntered((new EventHandler<MouseEvent>() { 
		         public void handle(MouseEvent event) { 
		        	 a1.setStyle("-fx-background-color: gray;");
			         } 
			      }));
		  	a1.setOnMouseExited((new EventHandler<MouseEvent>() { 
		         public void handle(MouseEvent event) { 
		        	 a1.setStyle("-fx-background-color: white;");
			         } 
			      }));
		  	a1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		         public void handle(MouseEvent event) { 
			     	  	root.getChildren().remove(ap);
		        	 	AlbumpageController.Ablum_id = ab_id;
		 	        	s.updateStageAndScene(application.mainPage.Albumpageid,application.mainPage.Albumpageres);
		  	        	s.switchScene(application.mainPage.Albumpageid);
		         } 
			      }));
	  	}
		tt.setNode(ap);
	  	tt.setByY(-615);
	  	tt.setCycleCount(1);
	  	tt.setAutoReverse(false);
	  	tt.play();
	
	}
	
	@FXML
    void toFound(ActionEvent event) {
		s.switchScene(mainPage.foundpageid);
    }

    @FXML
    void toMain(ActionEvent event) {
    	s.setStage(mainPage.mainpageid);
    }

    @FXML
    void toPerson(ActionEvent event) {
    	s.switchScene(mainPage.personpageid);
    }
    
    
}

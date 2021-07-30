package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import connection.Albumdemo;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import junit.framework.Test;
import javafx.scene.Parent;
import javafx.scene.Scene;
import user.Album;

public class foundController  implements ControlledStage,Initializable {
	
	@FXML
    private TextField searchtext;
	@FXML
    private AnchorPane root;
	@FXML
    private ScrollPane scrollPane;
    @FXML
    private Button found;
    @FXML
    private Text searchHit;
    @FXML
    private Button person;

    @FXML
    private Button searchbutton;

    @FXML
    private Button main;
    
    private StageController s;
//    private SceneController sc;
    
    final VBox vb = new VBox();
	private boolean isSearchPageExist = false;
	private String oldsearchContent = null ;
	
  
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createScrollpaneContent();
//    	atestForURL();
	}
	
   
    
	public void setStageController(StageController s1) {
		this.s = s1;
		
	}
	
	 public void atestForURL(){
	    	Albumdemo ad = new Albumdemo();
		  	Album a = ad.getAlbumbyid(1);
		  	String album_image_path = a.getImagepath();
		  	File sourceImage = new File(album_image_path);
		  	String f = null;
//			f = System.getProperty("user.dir")+ sourceImage;
//			System.out.print(f);
			try {
				f = sourceImage.toURI().toURL().toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			String s1 = new File(System.getProperty("user.dir") + album_image_path).getAbsoluteFile().toURI().toString();
			System.out.print(s1);
	    }
	
	public void createScrollpaneContent() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> hboxindex = new ArrayList<Integer>();
		for(int i=1; i<6; i++) {
			a.add(i);
		}
		for(int z=0; z<2; z++) {
			Random r = new Random();
			for(int j=0; j<2; j++) {
				int ran1 = r.nextInt(a.size());
				hboxindex.add(a.get(ran1));
				a.remove(ran1);
			}
			vb.getChildren().add(createHBox(hboxindex.get(0),hboxindex.get(1)));
			
			hboxindex.removeAll(hboxindex);
		}
		
		scrollPane.setContent(vb);	
	}
	
	public void clearScrollpaneContent() {
		vb.getChildren().clear();
	}
	
	public AnchorPane createAlbumUnit_byId(int canshu) {
		//msuci collectin unit
		AnchorPane ap = new AnchorPane();
	  	Button Album_b = new Button();
	  	
	  	ImageInput ii = new ImageInput(); 
	  	
	  	Albumdemo ad = new Albumdemo();
	  	Album a = ad.getAlbumbyid(canshu);
	  	String album_image_path = a.getImagepath();
		String s1 = new File(System.getProperty("user.dir") + album_image_path).getAbsoluteFile().toURI().toString();
  		Image i = new Image(s1,170, 170, false, false);
  		ii.setSource(i);
  		Album_b.setEffect(ii);
  		Album_b.setOnMouseClicked((new EventHandler<MouseEvent>() { 
  	         public void handle(MouseEvent event) { 
  	        	AlbumpageController.Ablum_id = canshu;
 	        	s.updateStageAndScene(application.mainPage.Albumpageid,application.mainPage.Albumpageres);
  	        	s.switchScene(application.mainPage.Albumpageid);
  	         } 
  	      }));
  		
	  	AnchorPane.setTopAnchor(Album_b,5.0);
	  	AnchorPane.setLeftAnchor(Album_b, 5.0);
	  	
	  	//bottom
	  	Label text_b = new Label(a.getName()+"\r\n"+a.getAuthor());
	  	AnchorPane.setTopAnchor(text_b,175.0);
	  	AnchorPane.setLeftAnchor(text_b, 5.0);
	  	ap.getChildren().addAll(Album_b,text_b);
	  	return ap;
	} 
	
	public HBox createHBox(int b1,int b2) {
		HBox hb = new HBox(55);
		hb.getChildren().addAll(createAlbumUnit_byId(b1),createAlbumUnit_byId(b2));
		return hb;
	}
	
	@FXML
    void search(ActionEvent event) {
		String newsearchContent = searchtext.getText();
		if(newsearchContent.isEmpty())
		{
			return;
		}
		else if(isSearchPageExist && oldsearchContent.equals(newsearchContent)) {
			Thread t = new Thread() {
				public void run() {
					searchHit.setVisible(true);
					try {
						sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					searchHit.setVisible(false);
				}
			};
			t.start();
		}
		else {
			oldsearchContent = newsearchContent;
			isSearchPageExist = true;
			AnchorPane ap = new AnchorPane();
			VBox vb = new VBox(10);
			ArrayList<Album> a = new ArrayList<Album>();
			TranslateTransition tt = new TranslateTransition();
			tt.setDuration(Duration.millis(350));
			ap.setPrefWidth(400.0);
			ap.setPrefHeight(615.0);
			ap.setStyle("-fx-background-color: white;");
			AnchorPane.setTopAnchor(ap,85.0);
		  	AnchorPane.setLeftAnchor(ap, 400.0);
		  	root.getChildren().add(ap);
		  	Label l3 = new Label(">");
		  	l3.setStyle("-fx-font-family: 'KaiTi';-fx-font-size: 30;");
		  	AnchorPane.setTopAnchor(l3,0.0);
		  	AnchorPane.setLeftAnchor(l3, 350.0);
		  	ap.getChildren().add(l3);
		  	l3.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		         public void handle(MouseEvent event) { 
		        	TranslateTransition t2 = new TranslateTransition();
		     		t2.setDuration(Duration.millis(250));
		     		t2.setNode(ap);
		     	  	t2.setByX(400);
		     	  	t2.setCycleCount(1);
		     	  	t2.setAutoReverse(false);
		     	  	t2.play();
		     	  	isSearchPageExist = false;
		     	  	//TOrepair
		     	  	if(!isSearchPageExist) {root.getChildren().remove(ap);}
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
		  	Albumdemo albumd = new Albumdemo();
		  	a = albumd.getAlbumbyName(oldsearchContent);
//		  	System.out.print("  albumlist:"+a);
		  	Album ab = null;
//			for msuic and album 	  	
//		  	StackPane stackp = new StackPane();
		  	// example
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
			        	 	isSearchPageExist = false;
				     	  	root.getChildren().remove(ap);
			        	 	AlbumpageController.Ablum_id = ab_id;
			 	        	s.updateStageAndScene(application.mainPage.Albumpageid,application.mainPage.Albumpageres);
			  	        	s.switchScene(application.mainPage.Albumpageid);
			         } 
				      }));
		  	}
			//
			tt.setNode(ap);
		  	tt.setByX(-400);
		  	tt.setCycleCount(1);
		  	tt.setAutoReverse(false);
		  	tt.play();
		}
    }
	  
	@FXML
	public void toFoundPage(ActionEvent event) {
		clearScrollpaneContent();
		s.switchScene(mainPage.foundpageid);
		createScrollpaneContent();
    }

    @FXML
    public void toMainPage(ActionEvent event) {
    		s.setStage(mainPage.mainpageid);
    }
    
    @FXML
    public void toPersonPage(ActionEvent event) {
    	s.updateStageAndScene(mainPage.personpageid, mainPage.personpageres);
    	s.switchScene(mainPage.personpageid);
    }
}

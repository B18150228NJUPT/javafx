package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import connection.Albumdemo;
import connection.Musicdemo;
import connection.Previewdemo;
import connection.userdemo;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import user.Album;
import user.Music;
import user.Preview;
import user.User;
import user.userinformation;

public class AlbumpageController implements ControlledStage,Initializable{
	
	static int Ablum_id = 1;
	
	@FXML
    private Button preview;
    
    @FXML
    private Label score;

    @FXML
    private Button rate;
    
    @FXML
    private AnchorPane ac;
    
    @FXML
    private Button back;

    @FXML
    private Text rateHit;

    @FXML
    private CheckBox favorite;

    @FXML
    private Button musiclist;
    
    @FXML
    private ScrollPane scrollpane;

    private StageController s;
    
    private static boolean isRatePageExist = false;
    private static boolean isPreveiwPageExist = false;
    boolean a= false;
    VBox vb1 = new VBox(10);
    VBox vb2 = new VBox(10);
    StackPane sp = new StackPane();
    
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	setpagecontent();
	}
	
	public void setStageController(StageController s1) {
		this.s = s1;
		
	}
	
	public void setAblum_id(int canshu) {
		canshu = this.Ablum_id;
	}
	
	
	public static int getAblum_id() {
		return Ablum_id;
	}

	public  void setpagecontent() {
		//
		scrollpane.setContent(sp);
		//image
	  	Albumdemo ad = new Albumdemo();
	  	Album a = ad.getAlbumbyid(Ablum_id);
//	  	System.out.print(Ablum_id);
	  	String album_image_path = a.getImagepath();
		String s1 = new File(System.getProperty("user.dir") + album_image_path).getAbsoluteFile().toURI().toString();
  		Image i = new Image(s1,200, 200, false, false);
  		ImageView image = new ImageView(i);
  		AnchorPane.setTopAnchor(image,70.0);
	  	AnchorPane.setLeftAnchor(image, 30.0);
	  	ac.getChildren().addAll(image);
  		score.setText(Float.valueOf(a.getScore()).toString());
  		Font font = new Font("仿宋", 30);
  		Font font1 = new Font(15);
        score.setFont(font);
        Label text_b = new Label(a.getName()+"\r\n"+a.getAuthor());
        text_b.setFont(font1);
        AnchorPane.setTopAnchor(text_b,270.0);
	  	AnchorPane.setLeftAnchor(text_b, 30.0);
	  	ac.getChildren().addAll(text_b);
	  	
	  	createAlbumListcontent();
	  	setCollectionCheckBox();
	}
	
	void setCollectionCheckBox() {
		userdemo u = new userdemo();
		User u1 = u.getUserById(userinformation.id);
		String c1 = u1.getCollection();
		String [] c2 = c1.split("/");
		for(int i=0;i<c2.length-1; i++) {
			if(c2[i].equals(Ablum_id+"")) {
				favorite.setSelected(true);
			}
		}
		favorite.setOnMouseClicked((new EventHandler<MouseEvent>() { 
 	         public void handle(MouseEvent event) { 
 	        	if(favorite.isSelected()) {
 	        		String s = ""+Ablum_id+"/";
 	        		
 	        		for(int i=0; i<c2.length-1; i++)
 	        		{
 	        				s+=c2[i]+"/";
 	        		}
 	        		s+="*";
 	        		u.updateUserCollectionByid(s);
 	        	}
 	        	else {
 	        		String s = "";
 	        		for(int i=0; i<c2.length-1; i++)
 	        		{
 	        			if(!c2[i].equals(Ablum_id+"")) {
 	        				s+=c2[i]+"/";
 	        			}
 	        		}
 	        		s+="*";
 	        		u.updateUserCollectionByid(s);
 	        	}	
 	         } 
 	      }));
		
		
	}
	
	void createAlbumListcontent()
	{
		
//			System.out.print(isMusicListPageExist);
			Musicdemo md = new Musicdemo();
//	    	ArrayList<Music> a = md.getMusicList(Ablum_id);
			
			//1  == aid
	    	ArrayList<Music> a = md.getMusicList(1);
	    	
	    	//music example
	    	for(int i=0; i<a.size(); i++) {
	    		AnchorPane ap = new AnchorPane();
	    		ap.setOnMouseEntered((new EventHandler<MouseEvent>() { 
	     	         public void handle(MouseEvent event) { 
	     	        	 InnerShadow is = new InnerShadow();
	     	        	 is.setColor(Color.GRAY);
	     	        	 ap.setEffect(is);
	     	   	         } 
	     	   	      }));
	    		ap.setOnMouseExited((new EventHandler<MouseEvent>() { 
	     	         public void handle(MouseEvent event) { 
	     	        	 InnerShadow is = new InnerShadow();
	     	        	 is.setColor(Color.BLACK);
	     	        	 ap.setEffect(is);
	     	   	         } 
	     	   	      }));
		    	Music m = new Music();
		    	m=a.get(i);
		    	Label morder = new Label(m.getOrder()+"");
		    	Label mname = new Label(m.getMusicname()+"");
		    	Label mauthor = new Label(m.getAuthor()+"");
		    	Label mduration = new Label(m.getDuration()+"");
		    	AnchorPane.setTopAnchor(morder,5.0);
			  	AnchorPane.setLeftAnchor(morder, 10.0);
			  	AnchorPane.setTopAnchor(mname,5.0);
			  	AnchorPane.setLeftAnchor(mname, 50.0);
			  	AnchorPane.setTopAnchor(mauthor,5.0);
			  	AnchorPane.setLeftAnchor(mauthor, 200.0);
			  	AnchorPane.setTopAnchor(mduration,5.0);
			  	AnchorPane.setLeftAnchor(mduration, 300.0);
		    	ap.getChildren().addAll(morder,mname,mauthor,mduration);
		    	vb1.getChildren().add(ap);
	    		
	    	}
	    	
	    	sp.getChildren().add(vb1);
	    	switchPage(vb2,vb1);
		
	}
	
	void createRatePage()
	{
		if(isRatePageExist)
		{
			Thread t = new Thread() {
				public void run() {
//					System.out.print(true);
					rateHit.setVisible(true);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					rateHit.setVisible(false);
				}
			};
			t.start();
		}
		else {
			isRatePageExist = true;
			AnchorPane ap = new AnchorPane();
			VBox vb = new VBox(10);
			TranslateTransition tt = new TranslateTransition();
			tt.setDuration(Duration.millis(350));
			ap.setPrefWidth(400.0);
			ap.setPrefHeight(500.0);
			ap.setStyle("-fx-background-color: white;");
			Label l1 = new Label("你的评分：");
//			AnchorPane.setTopAnchor(l1,5.0);
//		  	AnchorPane.setLeftAnchor(l1, 30.0);
			ChoiceBox cb = new ChoiceBox();
			cb.getItems().addAll("1","2","3","4","5");
			cb.setValue("1");
//			AnchorPane.setTopAnchor(cb,5.0);
//		  	AnchorPane.setLeftAnchor(cb, 30.0);
		  	Label l2 = new Label("发表评论：");
//			AnchorPane.setTopAnchor(l2,5.0);
//		  	AnchorPane.setLeftAnchor(l2, 30.0);
		  	TextArea ta = new TextArea();
		  	ta.setPrefHeight(150);
		  	ta.setPrefWidth(350);
		  	Button b1 = new Button("发表");
		  	vb.setMargin(b1, new Insets(20, 20, 150, 150));
		  	Button b2 = new Button("×");
		  	
		  	vb.setMargin(b2, new Insets(10, 0, 0, 310));
//		  	AnchorPane.setLeftAnchor(b1, 180.0);
//		  	System.out.print(true);
		  	vb.getChildren().addAll(b2,l1,cb,l2,ta,b1);
		  	AnchorPane.setTopAnchor(vb,0.0);
		  	AnchorPane.setLeftAnchor(vb, 20.0);
		  	AnchorPane.setTopAnchor(ap,700.0);
		  	AnchorPane.setLeftAnchor(ap, 0.0);
		  	ap.getChildren().add(vb);
		  	ac.getChildren().add(ap);
		  	tt.setNode(ap);
		  	tt.setByY(-390);
		  	tt.setCycleCount(1);
		  	tt.setAutoReverse(false);
		  	tt.play();
		  	b2.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		         public void handle(MouseEvent event) { 
		       //     System.out.println("Hello World");
		        	TranslateTransition t2 = new TranslateTransition();
		     		t2.setDuration(Duration.millis(550));
		     		t2.setNode(ap);
		     	  	t2.setByY(390);
		     	  	t2.setCycleCount(1);
		     	  	t2.setAutoReverse(false);
		     	  	t2.play();
		     	  	isRatePageExist = false;
//		        	ac.getChildren().remove(ap);
		         } 
		      }));
		  	b1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		         public void handle(MouseEvent event) { 
					 Preview p = new Preview();
					 p.setRatescore(Integer.parseInt((cb.getValue()).toString()));
					 p.setAid(Ablum_id);
					 p.setUid(userinformation.id);
					 p.setContent(ta.getText());
					 Previewdemo pd = new Previewdemo();
		        	 pd.addPeview(p);
					Albumdemo a = new Albumdemo();
					a.updateScore(p);
		        	TranslateTransition t2 = new TranslateTransition();
		     		t2.setDuration(Duration.millis(250));
		     		t2.setNode(ap);
		     	  	t2.setByY(390);
		     	  	t2.setCycleCount(1);
		     	  	t2.setAutoReverse(false);
		     	  	t2.play();
		     	  	isRatePageExist = false;
		         } 
		      }));
		  	
		}
		
	}
	
	void switchPage(VBox vclose,VBox vopen) {
		vclose.setVisible(false);
		vopen.setVisible(true);
	}
	
	void createPreviewPage() {
		if(isPreveiwPageExist)
		{
			switchPage(vb1,vb2);
		}
		else {
			switchPage(vb1,vb2);
			isPreveiwPageExist=true;
			Preview preview = null;
			Previewdemo p = new Previewdemo();
			ArrayList<Preview> a = new ArrayList<Preview>();
			a=p.getPreviewByAlbumId(Ablum_id);
			for(int i=0;i<a.size(); i++) {
				preview = a.get(i);
				AnchorPane ap = new AnchorPane();
				userdemo u = new userdemo();
				User u1 = u.getUserById(preview.getUid());
				Label l1 = new Label(u1.getName());
				AnchorPane.setTopAnchor(l1,5.0);
			  	AnchorPane.setLeftAnchor(l1, 30.0);
				Label l2 = new Label(preview.getContent());
				AnchorPane.setTopAnchor(l2,20.0);
			  	AnchorPane.setLeftAnchor(l2, 60.0);
			  	Label l3 = new Label(preview.getRatescore()+"");
				AnchorPane.setTopAnchor(l3,5.0);
			  	AnchorPane.setLeftAnchor(l3, 300.0);
				ap.getChildren().addAll(l1,l2,l3);
				vb2.getChildren().addAll(ap);
			}
			sp.getChildren().add(vb2);
		}
		
	}
	
	  	@FXML
	    void back(ActionEvent event) {
	  		isRatePageExist = false;
	  	    
	  	    isPreveiwPageExist = false;
	  	    s.switchScene(application.mainPage.foundpageid);
	    }

	    @FXML
	    void torate(ActionEvent event) {
	    	createRatePage();
	    }

	    @FXML
	    void toAlbumList(ActionEvent event) {
				switchPage(vb2,vb1);
	    }

	    @FXML
	    void topreview(ActionEvent event) {
	    	
	    	createPreviewPage();
	    }
	    
	    @FXML
	    void toMain(ActionEvent event) {
	    	if(Maincontroller.isplayAlbumId != AlbumpageController.Ablum_id) {
	    		s.getStage(mainPage.mainpageid).close();
	    		s.updateStageAndScene(mainPage.mainpageid, mainPage.mainpageres);
	    		s.setStage(mainPage.mainpageid);
	    	}
	    }

		
}

package application;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import user.Album;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import connection.Albumdemo;
import connection.Musicdemo;
import user.Music;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

public class Maincontroller  implements ControlledStage,Initializable {
	
	@FXML
    private AnchorPane album_picture;
	
	private StageController s;
	private double xOffset = 0;
	private double yOffset = 0;
	private Stage stage;
	private static boolean rightbutton=false;
	private static boolean buttombutton=false;
	private static boolean isplay = false;
	private static boolean ispaused = false;
	final MediaView mView = new MediaView(null);
	ArrayList<Music> array_music = new ArrayList<Music>();
	private static int isplaymusic_id = 0;
	private AnchorPane point_target = null;
	AnchorPane point = new AnchorPane();
	private static double slider_x = 0.0; 
	AnchorPane  l1 = new AnchorPane();
	static double new_x = 150.0;
	private static double volume = 0.5;
	private Label l2;
	private Label l3;
	public static int isplayAlbumId = 1;
	public static double screenx = 1000;
	public static double screeny = 500;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setcontent();
		album_picture.setOnMouseDragged(event -> {
			if(!l1.isPressed()) {
				event.consume();
	            var stage = getStage();
	            stage.setX(event.getScreenX() - xOffset);
	            stage.setY(event.getScreenY() - yOffset);
			}
//            System.out.print(xOffset+""+"\n");
//            System.out.print(yOffset+"" +"\n");
        });
		album_picture.setOnMousePressed(event -> {
			if(!l1.isPressed()) {
				event.consume();
	            xOffset = event.getSceneX();
	            yOffset = event.getSceneY();
			}
//            System.out.print(event.getScreenX()+""+"\n");
//            System.out.print(event.getScreenY()+""+"\n");
        });
		
	}
	
	public void setStageController(StageController s1) {
		this.s = s1;
		
	}

	
	 private Stage getStage() {
	        if (stage == null) {
	            stage = (Stage) album_picture.getScene().getWindow();
	        }
	        return stage;
	    }

	void playmusic(MediaView mv, String musicpath) {
		if(ispaused) {
			ispaused = false;
			mView.getMediaPlayer().play();
			isplay = true;
		}else {
			isplay = true;
			String url = new File(System.getProperty("user.dir") + musicpath).getAbsoluteFile().toURI().toString();
			Media media = new Media(url);
			MediaPlayer mediaPlayer = new MediaPlayer(media);
	        // 设置音量：0.0 - 1.0
	        mediaPlayer.setVolume(volume);
	        // 设置开始时间
	        mediaPlayer.setStartTime(Duration.seconds(0));
	        mv.setMediaPlayer(mediaPlayer);
	        mediaPlayer.play();
	        changeMusicInformation();
		}
	}
	
	void pausemusic() {
		
		mView.getMediaPlayer().pause();
		ispaused = true;
		isplay = false;
	}

	void changevolume() {
		if(isplay) {
			mView.getMediaPlayer().setVolume(volume);
		}
	}
	
	void changeMusicInformation() {
		l2.setText(array_music.get(isplaymusic_id).getMusicname());
		l3.setText(array_music.get(isplaymusic_id).getAuthor());
	}
	
	void setcontent() {
		Albumdemo ad = new Albumdemo();
		Album a = ad.getAlbumbyid(AlbumpageController.Ablum_id);
		String album_image_path = a.getImagepath();
		String s1 = new File(System.getProperty("user.dir") + album_image_path).getAbsoluteFile().toURI().toString();
  		Image i = new Image(s1,60, 60, false, false);
  		ImageView image = new ImageView(i);
  		album_picture.getChildren().add(image);
  		Musicdemo md = new Musicdemo();
  		isplayAlbumId = AlbumpageController.Ablum_id;
  		array_music=md.getMusicList(AlbumpageController.Ablum_id);
  		//guanbi
  		
  		l1.setStyle("-fx-background-color: white;");
  		l1.setPrefHeight(40);
  		l1.setPrefWidth(40);
  		l1.setOnMouseEntered(event->{
  			l1.setStyle("-fx-background-color: gray;");
  		});
  		l1.setOnMouseExited(event->{
  			l1.setStyle("-fx-background-color: white;");
  		});
  		l1.setOnMouseClicked(event->{
  			if(!buttombutton && !rightbutton) {
  				if(isplay) {pausemusic();}
  	  			var stage = getStage();
  	  			stage.close();
  			}
  		});
  		l1.setVisible(false);
  		l1.setOnMousePressed(event->{
  			if(buttombutton && rightbutton) {
  				slider_x = event.getX();
  			}
  		});
  		l1.setOnMouseDragged(event->{
  			if(rightbutton) {
  				l1.relocate(event.getX()-slider_x+l1.getLayoutX(), l1.getLayoutY());
  				new_x = (event.getX()-slider_x)+l1.getLayoutX();
  				if( new_x>=60 && (new_x<=200) ) {}
  				else if (new_x<=60) {new_x = 60.0;}
  				else {new_x = 200.0;}
  				AnchorPane.setLeftAnchor(l1, new_x);
  				volume = (new_x-60)/140;
  				if(isplay) {changevolume();}
  				
  			}
  		});
  		
  		
  		
  		//rightcontent
  		StackPane sp = new StackPane();
  		VBox vb_right = new VBox(14);
  		vb_right.setPrefHeight(60);
  		vb_right.setPrefWidth(140);
  		vb_right.setAlignment(Pos.CENTER);
//  		vb_right.setVisible(false);
  		HBox hb_right = new HBox(10);
  		hb_right.setPrefHeight(60);
  		hb_right.setPrefWidth(140);
  		hb_right.setVisible(false);
  		hb_right.setAlignment(Pos.CENTER);
  		l2 = new Label(array_music.get(0).getMusicname());
  		l3 = new Label(array_music.get(0).getAuthor());
  		vb_right.getChildren().addAll(l2,l3);
//  		Label l4 = new Label("<<");
//  		Label l5 = new Label(">>");
  		//hb1 <<
  		AnchorPane  hb1 = new AnchorPane();
  		hb1.setStyle("-fx-background-color: white;");
  		hb1.setPrefHeight(60);
  		hb1.setPrefWidth(40);
  		hb1.setOnMouseEntered(event->{
  			hb1.setStyle("-fx-background-color: gray;");
  		});
  		hb1.setOnMouseExited(event->{
  			{hb1.setStyle("-fx-background-color: white;");}
  			
  		});
  		
  		
  		
  		//hb2 |>
  		AnchorPane  hb2 = new AnchorPane();
  		hb2.setStyle("-fx-background-color: white;");
  		hb2.setPrefHeight(60);
  		hb2.setPrefWidth(40);
  		hb2.setOnMouseEntered(event->{
  			hb2.setStyle("-fx-background-color: gray;");
  		});
  		hb2.setOnMouseExited(event->{
  			{hb2.setStyle("-fx-background-color: white;");}
  			
  		});
  		hb2.setOnMouseClicked(event->{
  			if(!isplay) {
  				
  				playmusic(mView,array_music.get(isplaymusic_id).getMusicpath());
  			}
  			else {
  				pausemusic();
  			}
  		});
  		
  		
  		
  		//hb3 >>
  		
  		AnchorPane  hb3 = new AnchorPane();
  		hb3.setStyle("-fx-background-color: white;");
  		hb3.setPrefHeight(60);
  		hb3.setPrefWidth(40);
  		hb3.setOnMouseEntered(event->{
  			hb3.setStyle("-fx-background-color: gray;");
  		});
  		hb3.setOnMouseExited(event->{
  			{hb3.setStyle("-fx-background-color: white;");}
  			
  		});
  		
  		
  		
  		
  		
  		hb_right.getChildren().addAll(hb1,hb2,hb3);
  		
  		sp.setVisible(false);
		sp.setVisible(false);
		sp.setOnMouseEntered(event->{
			vb_right.setVisible(false);
			hb_right.setVisible(true);
		});
		sp.setOnMouseExited(event->{
			vb_right.setVisible(true);
			hb_right.setVisible(false);
		});
		
		
  		AnchorPane  ap1 = new AnchorPane();
  		ap1.setStyle("-fx-background-color: white;");
  		ap1.setPrefHeight(60);
  		ap1.setPrefWidth(40);
  		ap1.setVisible(false);
  		ap1.setOnMouseEntered(event->{
  			ap1.setStyle("-fx-background-color: gray;");
  		});
  		ap1.setOnMouseExited(event->{
  			if(!rightbutton) {ap1.setStyle("-fx-background-color: white;");}
  			
  		});
  		
  		
  		
  		//bottomcontent
  			//point
  		
		point.setPrefHeight(14);
		point.setPrefWidth(14);
		point.setStyle("-fx-background-color: gray;");
		AnchorPane.setLeftAnchor(point, 3.0);
		AnchorPane.setTopAnchor(point, 3.0);
			//
  		AnchorPane  ap2 = new AnchorPane();
  		ap2.setStyle("-fx-background-color: white;");
  		ap2.setPrefHeight(40);
  		ap2.setPrefWidth(60);
  		ap2.setVisible(false);
  		ap2.setOnMouseEntered(event->{
  			ap2.setStyle("-fx-background-color: gray;");
  		});
  		ap2.setOnMouseExited(event->{
  			if(!buttombutton) {ap2.setStyle("-fx-background-color: white;");}
  		});
  		
  		
  		VBox vb2 = new VBox();
  		vb2.setVisible(false);
  		album_picture.getChildren().add(vb2);
  		VBox vb3 = new VBox();
		for(int j=0; j<array_music.size(); j++) {
			AnchorPane ap_music = new AnchorPane();
			ap_music.setPrefHeight(20);
			ap_music.setPrefWidth(240);
			Label l_music = new Label(array_music.get(j).getMusicname());
			l_music.setId(j+"");
			AnchorPane.setLeftAnchor(l_music, 30.0);
			AnchorPane.setTopAnchor(l_music, 5.0);
			ap_music.getChildren().add(l_music);
			ap_music.setStyle("-fx-background-color: white;");
			ap_music.setOnMouseEntered(event->{
				ap_music.setStyle("-fx-background-color: gray;");
	  		});
			ap_music.setOnMouseExited(event->{
	  			{ap_music.setStyle("-fx-background-color: white;");}
	  		});
			
			
			vb3.getChildren().add(ap_music);
			
			ap_music.setOnMouseClicked(event->{
				
				AnchorPane old_target = (AnchorPane) point.getParent();
				old_target.getChildren().remove(point);
				ap_music.getChildren().add(point);
				isplaymusic_id = Integer.valueOf(l_music.getId());
				playmusic(mView,array_music.get(isplaymusic_id).getMusicpath());
				
			});
		}
		
		point_target = (AnchorPane) vb3.getChildren().get(0);
		point_target.getChildren().add(point);
		//for point >> and <<
		hb1.setOnMouseClicked(event->{
  			ispaused = false;
  			if(isplaymusic_id == 0) {
  				isplaymusic_id = array_music.size()-1;
  				playmusic(mView,array_music.get(isplaymusic_id).getMusicpath());
  				updatePoint(vb3,0,isplaymusic_id);
  			}
  			else {
  				updatePoint(vb3,isplaymusic_id,isplaymusic_id-1);
  				isplaymusic_id -= 1;
  				playmusic(mView,array_music.get(isplaymusic_id).getMusicpath());
  				
  			}
  		});
		hb3.setOnMouseClicked(event->{
  			ispaused = false;
  			if(isplaymusic_id == array_music.size()-1) {
  				updatePoint(vb3,isplaymusic_id,0);
  				isplaymusic_id = 0;
  				playmusic(mView,array_music.get(0).getMusicpath());
  			}
  			else {
  				updatePoint(vb3,isplaymusic_id,isplaymusic_id+1);
  				isplaymusic_id +=1;
  				playmusic(mView,array_music.get(isplaymusic_id).getMusicpath());
  			}
  		});
		double height = array_music.size()*20.0;
  		ap2.setOnMouseClicked(event->{
  			if(!buttombutton && rightbutton) {
  				buttombutton = true;
  	  			ap1.setStyle("-fx-background-color: gray;");
  	  			var stage = getStage();
  	  			stage.setWidth(240);
  	  			stage.setHeight(height+100);
  	  			vb2.getChildren().add(vb3);
  	  			TranslateTransition tt = new TranslateTransition();
  				tt.setDuration(Duration.millis(150));
  				tt.setNode(ap2);
  			  	tt.setByY(height+5.0);
  			  	tt.setCycleCount(1);
  			  	tt.setAutoReverse(false);
  			  	tt.play();
  			  	vb2.setVisible(true);
  			  	AnchorPane.setTopAnchor(vb2,60.0);
	  			AnchorPane.setLeftAnchor(vb2, 0.0);
//	  			l1.setVisible(false);
	  			AnchorPane.setTopAnchor(l1,height+65.0);
	  			AnchorPane.setLeftAnchor(l1, new_x);
  			}
  			else if(buttombutton && rightbutton){
  				buttombutton = false;
  	  			ap2.setStyle("-fx-background-color: white;");
  	  			var stage = getStage();
	  			stage.setHeight(60);
	  			vb2.setVisible(false);
	  			vb2.getChildren().remove(vb3);
	  			TranslateTransition tt = new TranslateTransition();
  				tt.setDuration(Duration.millis(150));
  				tt.setNode(ap2);
  			  	tt.setByY(-height-5.0);
  			  	tt.setCycleCount(1);
  			  	tt.setAutoReverse(false);
  			  	tt.play();
  			}
  			else if(!rightbutton & !buttombutton) {
  				//ap2
  				buttombutton = true;
  	  			ap1.setStyle("-fx-background-color: gray;");
  	  			var stage = getStage();
  	  			stage.setWidth(240);
  	  			stage.setHeight(height+100);
  	  			vb2.getChildren().add(vb3);
  	  			TranslateTransition tt = new TranslateTransition();
  				tt.setDuration(Duration.millis(150));
  				tt.setNode(ap2);
  			  	tt.setByY(height+5.0);
  			  	tt.setCycleCount(1);
  			  	tt.setAutoReverse(false);
  			  	tt.play();
  			  	vb2.setVisible(true);
  			  	AnchorPane.setTopAnchor(vb2,60.0);
	  			AnchorPane.setLeftAnchor(vb2, 0.0);
	  			AnchorPane.setTopAnchor(l1,height+65.0);
	  			AnchorPane.setLeftAnchor(l1, new_x);
  	  			//ap1
  	  			rightbutton = true;
				sp.getChildren().addAll(vb_right,hb_right);
	  			ap1.setStyle("-fx-background-color: gray;");
	  			TranslateTransition tt2 = new TranslateTransition();
				tt2.setDuration(Duration.millis(150));
				tt2.setNode(ap1);
			  	tt2.setByX(140);
			  	tt2.setCycleCount(1);
			  	tt2.setAutoReverse(false);
			  	tt2.play();
			  	sp.setVisible(true);
			  	AnchorPane.setTopAnchor(sp,0.0);
			  	AnchorPane.setLeftAnchor(sp, 60.0);
  			}
  		});
  		//ap1_controller
  		ap1.setOnMouseClicked(event->{
  			if(!rightbutton) {
  				rightbutton = true;
  				sp.getChildren().addAll(vb_right,hb_right);
  	  			ap1.setStyle("-fx-background-color: gray;");
  	  			var stage = getStage();
  	  			stage.setWidth(240);
  	  			stage.setHeight(60);
  	  			TranslateTransition tt = new TranslateTransition();
  				tt.setDuration(Duration.millis(150));
  				tt.setNode(ap1);
  			  	tt.setByX(140);
  			  	tt.setCycleCount(1);
  			  	tt.setAutoReverse(false);
  			  	tt.play();
  			  	sp.setVisible(true);
  			  	AnchorPane.setTopAnchor(sp,0.0);
	  			AnchorPane.setLeftAnchor(sp, 60.0);
  			}
  			else if (rightbutton && buttombutton){
  				rightbutton = false;
  				buttombutton = false;
  	  			ap1.setStyle("-fx-background-color: white;");
  	  			var stage = getStage();
	  			stage.setWidth(60);
	  			stage.setHeight(60);
	  			sp.setVisible(false);
	  			sp.getChildren().removeAll(vb_right,hb_right);
	  			TranslateTransition tt = new TranslateTransition();
  				tt.setDuration(Duration.millis(150));
  				tt.setNode(ap1);
  			  	tt.setByX(-140);
  			  	tt.setCycleCount(1);
  			  	tt.setAutoReverse(false);
  			  	tt.play();
  			  	ap2.setStyle("-fx-background-color: white;");
  			  	vb2.getChildren().remove(vb3);
	  			TranslateTransition tt1 = new TranslateTransition();
				tt1.setDuration(Duration.millis(150));
				tt1.setNode(ap2);
			  	tt1.setByY(-height-5.0);
			  	tt1.setCycleCount(1);
			  	tt1.setAutoReverse(false);
			  	tt1.play();
  			}
  			else {
  				rightbutton = false;
  	  			ap1.setStyle("-fx-background-color: white;");
  	  			var stage = getStage();
	  			stage.setWidth(60);
	  			stage.setHeight(60);
	  			sp.setVisible(false);
	  			sp.getChildren().removeAll(vb_right,hb_right);
	  			TranslateTransition tt = new TranslateTransition();
  				tt.setDuration(Duration.millis(150));
  				tt.setNode(ap1);
  			  	tt.setByX(-140);
  			  	tt.setCycleCount(1);
  			  	tt.setAutoReverse(false);
  			  	tt.play();
  			}
  		});
  		
  		
  		
  		
  		
//  		String s2 = new File(System.getProperty("user.dir") + "/src/application/test_music/sucai/zanting.png").getAbsoluteFile().toURI().toString();
//  		Image i1 = new Image(s2,40, 40, false, false);
//  		ImageView l1 = new ImageView(i1);
//  		ImageInput ii_bofang = new ImageInput(); 
//  		ii_bofang.setSource(i1);
//  		Button l1 = new Button();
//  		l1.setEffect(ii_bofang);
  		
  		//
  		album_picture.getChildren().addAll(ap1,ap2,l1,sp);
  		album_picture.setOnMouseEntered(event->{
  			if(!rightbutton & !buttombutton) {
  				var stage = getStage();
  	  			stage.setWidth(100);
  	  			stage.setHeight(100);
  	  			AnchorPane.setTopAnchor(ap1,0.0);
  	  			AnchorPane.setLeftAnchor(ap1, 60.0);
  	  			
  	  			AnchorPane.setTopAnchor(ap2,60.0);
  	  			AnchorPane.setLeftAnchor(ap2, 0.0);
  	  			AnchorPane.setTopAnchor(l1,60.0);
  	  			AnchorPane.setLeftAnchor(l1, 60.0);
  	  			
  	  			ap1.setVisible(true);
  	  			ap2.setVisible(true);
  	  			l1.setVisible(true);
  	  			
  			}
  			else if(rightbutton & !buttombutton) {
  				var stage = getStage();
  				stage.setHeight(100);
  				AnchorPane.setTopAnchor(ap2,60.0);
  	  			AnchorPane.setLeftAnchor(ap2, 0.0);
  	  			AnchorPane.setLeftAnchor(l1, new_x);
  	  			AnchorPane.setTopAnchor(l1,60.0);
  	  			l1.setVisible(true);
  			}
  			
  		});
  		album_picture.setOnMouseExited(event->{

  			if(!rightbutton & !buttombutton) {
  				var stage = getStage();
  	  			stage.setWidth(60);
  	  			stage.setHeight(60);
  			}
  			else if(rightbutton & !buttombutton) {
  				var stage = getStage();
  				stage.setHeight(60);
  			}
  			
  			
  		});
  		
  		
  		
	}
	
	void updatePoint(VBox vb,int old_id,int new_id) {
		point_target = (AnchorPane) vb.getChildren().get(old_id);
		point_target.getChildren().remove(point);
		point_target = (AnchorPane) vb.getChildren().get(new_id);
		point_target.getChildren().add(point);
	}

}

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class admin1Controller implements ControlledStage,Initializable{

    @FXML
    private Button FENPEI;

    @FXML
    private Button TONGJI;

    @FXML
    private Button CAIGOU;

    @FXML
    private Button LINGYONG;

    @FXML
    private Button chuzhi;

    @FXML
    private Button fun1;
    
    @FXML
    private Button fun2;

    @FXML
    private Button WEIXIU;

    @FXML
    private StackPane sp;

    @FXML
    private Button s1;

    @FXML
    private Button shangbao;

	
	private AnchorPane f1;
	private AnchorPane f3;
	private AnchorPane weixiu;
	private AnchorPane fenpei;
	private AnchorPane caigou;
	private AnchorPane baoxiu;
	private AnchorPane f2;
	private AnchorPane lingyong;
	private AnchorPane tongji;
	
	
	private StageController s;
	private ArrayList<AnchorPane> spa =new  ArrayList<AnchorPane>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		try {
			f1=new FXMLLoader(getClass().getResource("fun.fxml")).load();
			f3=new FXMLLoader(getClass().getResource("fun3.fxml")).load();
			weixiu=new FXMLLoader(getClass().getResource("weixiu1.fxml")).load();
			fenpei=new FXMLLoader(getClass().getResource("fenpei.fxml")).load();
			caigou=new FXMLLoader(getClass().getResource("caigou.fxml")).load();
			baoxiu=new FXMLLoader(getClass().getResource("baoxiu.fxml")).load();
			f2=new FXMLLoader(getClass().getResource("f2.fxml")).load();
			lingyong = new FXMLLoader(getClass().getResource("lingyong.fxml")).load();
			tongji = new FXMLLoader(getClass().getResource("tongji.fxml")).load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		sp.getChildren().add(f1);
		sp.getChildren().add(f3);
		sp.getChildren().add(weixiu);
		sp.getChildren().add(fenpei);
		sp.getChildren().add(caigou);
		sp.getChildren().add(baoxiu);
		sp.getChildren().add(f2);
		sp.getChildren().add(lingyong);
		sp.getChildren().add(tongji);
		f2.setVisible(false);
		tongji.setVisible(false);
		lingyong.setVisible(false);
		baoxiu.setVisible(false);
		caigou.setVisible(false);
		fenpei.setVisible(false);
		f3.setVisible(false);
		weixiu.setVisible(false);
		spa.add(f1);
		setUsertype();
		
		
		
	}

	private void setUsertype() {
		// TODO Auto-generated method stub
		if(Mycontroller.usertype.equals("校管理员")) {
//			lingyong.setVisible(false);
			
		}else if(Mycontroller.usertype.equals("校领导")) {
			chuzhi.setVisible(false);
			WEIXIU.setVisible(false);
			shangbao.setVisible(false);
			fun1.setVisible(false);
			fun2.setVisible(false);
		}else if(Mycontroller.usertype.equals("普通用户")) {
			
		}else if(Mycontroller.usertype.equals("市管理员")) {
			
		}
	}

    @FXML
    void zhuxiao(ActionEvent event) {
    	s.setStage(application.mainPage.loginpageid, application.mainPage.Admin1id);
    }
	
	@Override
	public void setStageController(StageController sc) {
		// TODO Auto-generated method stub
		this.s = sc;
		
	}

	@FXML
	public void fun1() {
		System.out.print("父页面正常");
	}
	
    @FXML
    void WeiX(ActionEvent event) {
    	switchpane(weixiu);
    }
	
    void switchpane(AnchorPane p){
    	spa.get(0).setVisible(false);
    	spa.remove(0);
    	spa.add(p);
    	p.setVisible(true);
    }
    
	@FXML
	public void sun1() throws IOException {
		sp.getChildren().remove(f3);
		f3=new FXMLLoader(getClass().getResource("fun3.fxml")).load();
		sp.getChildren().add(f3);
		switchpane(f3);
	}
	

    @FXML
    void Caig(ActionEvent event) throws IOException {
    	sp.getChildren().remove(caigou);
    	caigou=new FXMLLoader(getClass().getResource("caigou.fxml")).load();
		sp.getChildren().add(caigou);
    	switchpane(caigou);
    }

    @FXML
    void fun2(ActionEvent event) throws IOException {
    	sp.getChildren().remove(f2);
		f2=new FXMLLoader(getClass().getResource("f2.fxml")).load();
		sp.getChildren().add(f2);
    	switchpane(f2);
    }

    @FXML
    void Tongj(ActionEvent event) throws IOException {
    	sp.getChildren().remove(tongji);
    	tongji=new FXMLLoader(getClass().getResource("tongji.fxml")).load();
		sp.getChildren().add(tongji);
    	switchpane(tongji);
    }

    @FXML
    void Fenp(ActionEvent event) throws IOException {
    	sp.getChildren().remove(fenpei);
		fenpei=new FXMLLoader(getClass().getResource("fenpei.fxml")).load();
		sp.getChildren().add(fenpei);
    	switchpane(fenpei);
    }

    @FXML
    void Lingy(ActionEvent event) throws IOException {
    	sp.getChildren().remove(lingyong);
    	lingyong=new FXMLLoader(getClass().getResource("lingyong.fxml")).load();
		sp.getChildren().add(lingyong);
    	switchpane(lingyong);
    }

    @FXML
    void Chuz(ActionEvent event) {

    }

    @FXML
    void Shu1(ActionEvent event) {

    }
}

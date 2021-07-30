package application;

import java.net.URL;
import java.util.ResourceBundle;
import connection.userdemo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import user.User;
import user.userinformation;

public class Mycontroller  implements ControlledStage ,Initializable{
	
	StageController s;
	
	public static boolean isclose=false;
	
	@FXML
	private TextField namefield;
	@FXML
	private TextField passwordfield;;
	@FXML
	private Label errortip;
	@FXML
	private Button loginButton;
	@FXML
    private AnchorPane rootLayout;
	@FXML
    private ChoiceBox<String> UserType;
	
	public static String usertype= "校管理员";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		UserType.getItems().addAll("校管理员","校领导","普通用户");
		UserType.getSelectionModel().selectFirst();
		UserType.setTooltip(new Tooltip("选择用户类型"));
//		UserType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observable, Number oldValue, Number newValue) {
//                label.setText(fruits[newValue.intValue()]);
//                usertype = 
//            }
//});
	}
	
	public void setStageController(StageController s1) {
		this.s = s1;
	}
	
	public void gotoMain() {
		s.setStage(application.mainPage.foundpageid);
	}
	
	@FXML
	public void clickonlogin(ActionEvent event) {
		 
			try {
				String u1 = namefield.getText();
				String p1 = passwordfield.getText();
				userdemo ud1 = new userdemo();
				User u = ud1.getUserById(u1);
				if((p1).equals(u.getPassword())) {
					System.out.print(userinformation.id );
					usertype = UserType.getValue();
					System.out.print("用户类型："+usertype);
					if(UserType.getValue().equals(u.getType())) {
						if(UserType.getValue().equals("校管理员")) {
							s.updateStageAndScene(mainPage.Admin1id, mainPage.Admin1Res);
							s.setStage(application.mainPage.Admin1id, application.mainPage.loginpageid);
						}else if(UserType.getValue().equals("校领导")) {
							s.updateStageAndScene(mainPage.Admin1id, mainPage.Admin1Res);
							s.setStage(application.mainPage.Admin1id, application.mainPage.loginpageid);
						}else if(UserType.getValue().equals("普通用户")) {
							
						}
						
					}else {
						errortip.setText("请选择正确用户类型");
						errortip.setVisible(true);
					}

				}else
				{
					errortip.setText("请输入正确的账户和密码登录");
					errortip.setVisible(true);
				}	
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	

//    @FXML
//    void clickTosignUp(ActionEvent event) {
//    	String u1 = namefield.getText();
//		String p1 = passwordfield.getText();
//    	if(u1.equals("") || p1.equals("")) {
//    		errortip.setText("输入账户密码完成注册");
//    		errortip.setVisible(true);
//    	}
//    	else {
//    		userdemo ud1 = new userdemo();
//    		User u = new User();
//    		u.setName(namefield.getText());
//    		u.setPassword(passwordfield.getText());
//    		u.setCollection("*");
//    		if(!ud1.insertUser(u)) {
//    			errortip.setText("注册账户名已存在");
//        		errortip.setVisible(true);
//    		}
//    		else {
//    			errortip.setText("注册成功，点击登录");
//        		errortip.setVisible(true);
//    		}
//    	}
//    }
}

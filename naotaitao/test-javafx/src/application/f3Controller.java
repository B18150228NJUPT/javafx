package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import connection.userdemo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import user.User;
import user.userinformation;

public class f3Controller implements Initializable,ControlledStage{


	
    @FXML
    private Text tx2;
    @FXML
    private Text tx1;
    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private Button b1;

    @FXML
    private TextField t3;
    
    private StageController sc;
    private boolean check = true;
    private String tip="";
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setcontent();
	}

	public void setcontent() {
		// TODO Auto-generated method stub
		userdemo u = new userdemo();
		User user = u.getUserById(userinformation.id);
		t1.setText(user.getCollection());
		tx1.setText(user.getName());
		t2.setText(user.getPassword());
		t3.setText(user.getPhone());
		tx2.setText(user.getType());
		
		b1.setOnAction(new EventHandler<ActionEvent>() {            
		    @Override
		    public void handle(ActionEvent event) {   
		    	
		    	Alert alert2 = new Alert(AlertType.CONFIRMATION);
                //设置对话框标题
                alert2.setTitle("提示");
                 //设置内容
                alert2.setHeaderText("确认更改吗");
                //显示对话框
                Optional<ButtonType> result = alert2.showAndWait();
                //如果点击OK
                if (result.get() == ButtonType.OK){
                	String p = "[1][358]\\d{9}";
                	if(!t3.getText().matches(p)) {
                		tip +="手机号格式错误"+"\n";
                		
                		check = false;
                	}
                	if(!isLetterDigit(t2.getText())) {
                		tip +="手机号格式错误"+"(6到12位，包含数字和字母)"+"\n";
                		check = false;
                	}
                	if(check) {
                		userdemo ud = new userdemo();
        		    	User u = new User(t2.getText(),t1.getText(),t3.getText());
        		    	ud.updateUserByid(u);
        		    	alert2.setAlertType(AlertType.INFORMATION);
        		    	alert2.setHeaderText("提交成功");
        		    	alert2.show();
        		    	check = true;
                	}else
                	{
                		alert2.setAlertType(AlertType.ERROR);
                		alert2.setHeaderText("错误");
                        alert2.setContentText(tip); 
                        alert2.show();
                	}
                } else {
                    event.consume();
                    
                }
                tip="";
		    }
		});
	}


	


	
	public static boolean isLetterDigit(String str) {
	    boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
	    boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
	    for (int i = 0; i < str.length(); i++) {
	        if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
	            isDigit = true;
	        } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
	            isLetter = true;
	        }
	    }
	    String regex = "^[a-zA-Z0-9]{6,12}$";
	    boolean isRight = isDigit && isLetter && str.matches(regex);
	    return isRight;
	}

	@Override
	public void setStageController(StageController sc) {
		// TODO Auto-generated method stub
		this.sc = sc;
	}
	
	
}

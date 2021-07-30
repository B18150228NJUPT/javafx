package application;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

import connection.AssertDao;
import connection.userdemo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import user.Assert;
import user.User;
import user.userinformation;
import java.util.Date;


public class caigouController implements Initializable{

	 @FXML
	    private TextField t4;

	    @FXML
	    private DatePicker t5;

	    @FXML
	    private TextField t6;

	    @FXML
	    private TextField t7;

	    @FXML
	    private TextField t8;

	    @FXML
	    private TextField t9;

	    @FXML
	    private ChoiceBox<String> t1;

	    @FXML
	    private TextField t2;

	    @FXML
	    private TextField t3;

	    @FXML
	    private Button b1;
	    
	    private String tip = "";
	    private boolean check = true;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setcontent();
	}
	
    private void setcontent() {
		// TODO Auto-generated method stub
		t1.getItems().addAll("教务处","政教处","总务处","教研室","年级组","校长办公室");
		t1.getSelectionModel().selectFirst();
		t1.setTooltip(new Tooltip("选择部门"));
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
                	String p = "[1-9]\\d*\\.?\\d* ";
                	String p1 = "[1-9]\\d*";
                	if(!t4.getText().matches(p1)) {
                		tip +="数量格式错误"+"\n";
                		
                		check = false;
                	}
                	if(t5.getValue() == null) {
                		tip +="时间格式错误"+"\n";
                		check = false;
                	}
                	if(!t8.getText().matches(p)&& !t8.getText().matches(p1)) {
                		tip +="资金格式错误"+"\n";
                		check = false;
                	}
                	if(t3.getText().isEmpty()) {
                		tip +="名称格式错误"+"\n";
                		check = false;
                	}
                	if(t4.getText().isEmpty()) {
                		tip +="数量格式错误"+"\n";
                		check = false;
                	}
                	if(check) {
                		LocalDateTime localDateTime = t5.getValue().atStartOfDay();
                		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
                		Instant instant = Instant.from(zonedDateTime);
                		Date d = (Date.from(instant));
                		
                		Assert a = new Assert(t3.getText(),t1.getValue(),t2.getText(),t6.getText(),d,
                				Integer.parseInt(t4.getText()),Float.parseFloat(t8.getText()),t7.getText(),t9.getText());
                		AssertDao ad = new AssertDao();
                		ad.approveAssert(a);
        		    	alert2.setAlertType(AlertType.INFORMATION);
        		    	alert2.setHeaderText("提交成功");
        		    	alert2.show();
                	}else
                	{
                		alert2.setAlertType(AlertType.ERROR);
                		alert2.setHeaderText("错误");
                        alert2.setContentText(tip); 
                        alert2.show();
                        check = true;
                	}
                } else {
                    event.consume();
                    
                }
                tip="";
		    }
		});
	}

	@FXML
    void sub(ActionEvent event) {
		
    }

    
}

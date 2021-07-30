package application;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import connection.AssertDao;
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

public class fenpeiController implements Initializable{

    @FXML
    private ChoiceBox<String> t4;

    @FXML
    private DatePicker t5;

    @FXML
    private TextField t6;

    @FXML
    private TextField t8;

    @FXML
    private TextField t9;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    private Button b1;
	
    private String tip ="";
    private boolean check = true;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setcontent();
	}
	
	public void setAssert(Assert a) {
		t1.setText(a.getAssertNum());
		t3.setText(a.getName());
		
	}
	
	private void setcontent() {
		// TODO Auto-generated method stub
		t4.getItems().addAll("教务处","政教处","总务处","教研室","年级组","校长办公室");
		t4.getSelectionModel().selectFirst();
		t4.setTooltip(new Tooltip("选择部门"));
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
                	String p1 = "\\d{9,11}$";
                	AssertDao ad = new AssertDao();
                	if(!t1.getText().matches(p1) || !ad.findassertbyAssertNum(t1.getText())) {
                		tip +="资产号格式错误"+"\n";
                		
                		check = false;
                	}
                	if(t5.getValue() == null) {
                		tip +="时间格式错误"+"\n";
                		check = false;
                	}
                
                	if(check) {
                		LocalDateTime localDateTime = t5.getValue().atStartOfDay();
                		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
                		Instant instant = Instant.from(zonedDateTime);
                		Date d = (Date.from(instant));
                		
                		Assert a = new Assert(t1.getText(),t2.getText(),t3.getText(),t4.getValue(),t6.getText(),d,
                				t8.getText(),t9.getText());
//                		AssertDao ad = new AssertDao();
                		ad.fenpeiAssert(a);
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
}

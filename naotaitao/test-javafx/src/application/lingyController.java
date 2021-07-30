package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import connection.AssertDao;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import user.Assert;

public class lingyController implements Initializable{

	@FXML
    private TableColumn<Assert, Date> a11;

    @FXML
    private TableColumn<Assert , String> a21;

    @FXML
    private TableColumn<Assert, Boolean> select1;

    @FXML
    private TableView<Assert> tb11;

    @FXML
    private TableColumn<Assert , String> a71;

    @FXML
    private TableColumn<Assert , String> a81;

    @FXML
    private Button f1;

    @FXML
    private TableColumn<Assert , String> a51;

    @FXML
    private TableColumn<Assert , String> a61;

    @FXML
    private TableColumn<Assert , String> a31;

    @FXML
    private TableColumn<Assert , String> a41;

   
    
    private final ObservableList<Assert> data
    = FXCollections.observableArrayList();
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setcontent();
	}

	 @FXML
    boolean linyong(ActionEvent event) {
		 int size = data.size();
	        if (size <= 0) {
	            return false;
	        }
	        for (int i = size - 1; i >= 0; i--) {
	            Assert s = data.get(i);
	            if (s.isIsselected()) {
	            	if(s.getStatus().equals("待领用")) {
	            		s.setStatus("使用中");
	            		data.get(i).setStatus("已领用");
	            		updatelingyongassertinfo(s);
	            		
	            		tb11.refresh();
	            		break;
	            	}  
	            }
	            
	        }
	        return true;	
    }
	
	private boolean updatelingyongassertinfo(Assert s) {
		// TODO Auto-generated method stub
		AssertDao ad = new AssertDao();
		return ad.updatestatus(s);
	}

	private void setcontent() {
		// TODO Auto-generated method stub
		AssertDao ad = new AssertDao();
		ArrayList<Assert> a = ad.getLingyAsserts();
		for(Assert i : a) {
			data.add(i);
		}
		
		System.out.print("领用资产数量："+a.size());
		select1.setCellValueFactory(new PropertyValueFactory<Assert,Boolean>("isselected"));
		a21.setCellValueFactory(new PropertyValueFactory<>("assertNum"));
		a31.setCellValueFactory(new PropertyValueFactory<>("name"));
		a51.setCellValueFactory(new PropertyValueFactory<>("usedepartment"));
		a61.setCellValueFactory(new PropertyValueFactory<>("num"));
		a71.setCellValueFactory(new PropertyValueFactory<>("fuzeren"));
		a11.setCellValueFactory(new PropertyValueFactory<Assert,Date>("fenpeitime"));		
		a41.setCellValueFactory(new PropertyValueFactory<>("fenpeibeizhu"));
		a81.setCellValueFactory(new PropertyValueFactory<>("status"));
		tb11.setItems(data);
		tb11.setEditable(true);
		
		select1.setCellFactory(
                CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(Integer index) {
                        final Assert g = (Assert) tb11.getItems().get(index);
                        ObservableValue<Boolean> ret =
                                new SimpleBooleanProperty(g, "isselected", g.isIsselected());
                        ret.addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {
                                g.setIsselected(newValue);
                            }
                        });
                        return ret;
                    }
                }));

       
	}
}

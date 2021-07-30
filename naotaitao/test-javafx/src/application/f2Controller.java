package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import connection.AssertDao;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import user.Assert;
import javafx.scene.layout.AnchorPane;
public class f2Controller implements Initializable{

    @FXML
    private TableColumn<Assert, String> a1;

    @FXML
    private TableColumn<Assert, String> a2;

    @FXML
    private TableView<Assert> tb1;

    @FXML
    private TableColumn<Assert, String> a3;

    @FXML
    private TableColumn<Assert, String> a4;

    @FXML
    private TableColumn<Assert, Date> a5;

    @FXML
    private TableColumn<Assert, String> a6;

    @FXML
    private TableColumn<Assert, String> a7;

    @FXML
    private TableColumn<Assert, String> a8;
    
    @FXML
    private TableColumn<Assert, Boolean> select;
    @FXML
    private Button f12;

    @FXML
    private Button f11;

    @FXML
    private Button f14;

    @FXML
    private Button f13;

    @FXML
    private Button f1;
    @FXML
    private AnchorPane root;

    @FXML
    private TextField f15;
    @FXML
    private ChoiceBox<String> c1;

	
    private final ObservableList<Assert> data
    = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setcontent();
		c1.getItems().addAll("闲置","待领用","使用中","待维修","维修中","报废");
		f15.setTooltip(new Tooltip("选择分类编号"));
		c1.setTooltip(new Tooltip("选择状态"));
		
	}


    @FXML
    boolean delete(ActionEvent event) {
    	 int size = data.size();
         if (size <= 0) {
             return false;
         }
         for (int i = size - 1; i >= 0; i--) {
             Assert s = data.get(i);
             if (s.isIsselected()) {
                 data.remove(s);
                 deleteAssert(s);
             }
         }
         return true;
    }
	

    @FXML
    void chazhao(ActionEvent event) {
    	 data.remove(0,data.size());
    	AssertDao ad = new AssertDao();
 		ArrayList<Assert> a = ad.getAsserts();
 		for(Assert i : a) {
 			data.add(i);
 		}
    	if(!f15.getText().isEmpty()) {
    		fliterdata(f15.getText());
    	}
    	if(c1.getValue() !=null) {
    		fliterdata1(c1.getValue());
    	}
    	tb1.refresh();
    }
    
    void fliterdata(String s1) {
    	int size = data.size();
    	for (int i = size - 1; i >= 0; i--) {
            Assert s = data.get(i);
            if (!s.getTypeNum().equals(s1)) {
                data.remove(s);
            }
        }
    }
    void fliterdata1(String s1) {
    	int size = data.size();
    	for (int i = size - 1; i >= 0; i--) {
            Assert s = data.get(i);
            if (!s.getStatus().equals(s1)) {
                data.remove(s);
            }
        }
    }
    
    @FXML
    boolean fenpei(ActionEvent event) throws IOException {
    	int size = data.size();
        if (size <= 0) {
            return false;
        }
        for (int i = size - 1; i >= 0; i--) {
            Assert s = data.get(i);
            if (s.isIsselected()) {
            	if(s.getStatus().equals("闲置")) {
            		fenpeipage(s);
//            		data.get(i).setStatus("以修改");
//            		tb1.getItems().get(i).setStatus1("待领用");
//            		data.get(i).setStatus1("待领用");
//            		tb1.setItems(data);
//            		tb1.refresh();
            		break;
            	}  
            }
            
        }
        return true;
    }
    
	private void fenpeipage(Assert s) throws IOException {
		// TODO Auto-generated method stub
		 URL location = getClass().getResource("fenpei.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(location);
		fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		AnchorPane fenpei = fxmlLoader.load(location.openStream());
		fenpeiController control=(fenpeiController)fxmlLoader.getController();
		control.setAssert(s);
//		AnchorPane fenpei=new FXMLLoader(getClass().getResource("fenpei.fxml")).load();
		Label l3 = new Label("X");
	  	l3.setStyle("-fx-font-family: 'Arial Unicode MS';-fx-font-size: 50;");
	  	AnchorPane.setTopAnchor(l3,30.0);
	  	AnchorPane.setLeftAnchor(l3, 800.0);
		fenpei.getChildren().add(l3);
	  	l3.setOnMouseClicked((new EventHandler<MouseEvent>() { 
	         public void handle(MouseEvent event) { 
	        	 root.getChildren().remove(fenpei);
	        	 data.remove(0,data.size());
	        	 AssertDao ad = new AssertDao();
	     		ArrayList<Assert> a = ad.getAsserts();
	     		for(Assert i : a) {
	     			data.add(i);
	     		}
	     		if(!f15.getText().isEmpty()) {
	        		fliterdata(f15.getText());
	        	}
	        	if(c1.getValue() !=null) {
	        		fliterdata1(c1.getValue());
	        	}
	        	tb1.refresh();
	     		
	         } 
	      }));
	  	
		AnchorPane.setTopAnchor(fenpei,650.0);
	  	AnchorPane.setLeftAnchor(fenpei, 0.0);
		TranslateTransition tt = new TranslateTransition();
		tt.setDuration(Duration.millis(350));
		root.getChildren().add(fenpei);
		tt.setNode(fenpei);
	  	tt.setByY(-650);
	  	tt.setCycleCount(1);
	  	tt.setAutoReverse(false);
	  	tt.play();
		
	}


	private void deleteAssert(Assert s) {
		// TODO Auto-generated method stub
		AssertDao ad = new AssertDao();
		ad.deleteAssertByid(s.getAssertNum());
	}


	private void setcontent() {
		// TODO Auto-generated method stub
		AssertDao ad = new AssertDao();
		ArrayList<Assert> a = ad.getAsserts();
		for(Assert i : a) {
			data.add(i);
		}
		
		System.out.print("资产数量："+a.size());
		select.setCellValueFactory(new PropertyValueFactory<Assert,Boolean>("isselected"));
		a1.setCellValueFactory(new PropertyValueFactory<>("typeNum"));
		a2.setCellValueFactory(new PropertyValueFactory<>("assertNum"));
		a3.setCellValueFactory(new PropertyValueFactory<>("name"));
		a4.setCellValueFactory(new PropertyValueFactory<>("type"));
		a5.setCellValueFactory(new PropertyValueFactory<Assert,Date>("time"));
		a6.setCellValueFactory(new PropertyValueFactory<>("num"));
		a7.setCellValueFactory(new PropertyValueFactory<>("source"));
		a8.setCellValueFactory(new PropertyValueFactory<>("status"));

		tb1.setItems(data);
		tb1.setEditable(true);
		
		select.setCellFactory(
                CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(Integer index) {
                        final Assert g = (Assert) tb1.getItems().get(index);
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

        Callback<TableColumn<Assert, String>,
                TableCell<Assert, String>> cellFactory
                = (TableColumn<Assert, String> p) -> new EditingCell();
        a1.setCellFactory(cellFactory);
        a2.setCellFactory(cellFactory);
        a1.setOnEditCommit(
                (TableColumn.CellEditEvent<Assert, String> t) -> {
                    ((Assert) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setTypeNum(t.getNewValue());
                });

        a2.setOnEditCommit(
                (TableColumn.CellEditEvent<Assert, String> t) -> {
                    ((Assert) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setAssertNum(t.getNewValue());
                });
       
	}

	
}

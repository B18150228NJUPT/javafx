package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import connection.AssertDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import user.CharData;

public class tongJController implements Initializable{


    @FXML
    private StackPane sp1;

    private  PieChart chart;
    private StackedBarChart<String, Number> stackedBarChart;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setcontent();
	}

    private void setcontent() {
		// TODO Auto-generated method stub
    	ArrayList<String> a = new ArrayList<String>();
    	
    	a.add("笔记本电脑");
    	a.add("台式计算机");
    	a.add("办公桌");
    	a.add("办公椅");
    	
    	
    	
    	AssertDao ad  = new AssertDao();
    	ArrayList<CharData> ac = ad.setChardata(a);
    	ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("台式计算机",ac.get(0).getNum()),
                new PieChart.Data("笔记本电脑", ac.get(1).getNum()),
                new PieChart.Data("办公桌", ac.get(2).getNum()),
                new PieChart.Data("办公椅", ac.get(3).getNum()));
        chart = new PieChart(pieChartData);
        chart.setTitle("资产占比");
        sp1.getChildren().add(chart);	
        //
        ArrayList<String> b = new ArrayList<String>();
        b.add("闲置");
    	b.add("待领用");
    	b.add("使用中");
    	b.add("待维修");
    	b.add("维修中");
    	b.add("报废");
        
        CategoryAxis xAxis = new CategoryAxis();    
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
           (a.get(0), a.get(1), a.get(2),a.get(3)))); 
        xAxis.setLabel("资产名称");
        NumberAxis yAxis = new NumberAxis(); 
        yAxis.setLabel("数量");    
        //Creating the Bar chart 
        stackedBarChart = 
           new StackedBarChart<>(xAxis, yAxis);         
        stackedBarChart.setTitle("资产状态图"); 
        //Prepare XYChart.Series objects by setting data 
        ArrayList< XYChart.Series<String, Number>  > achar = new ArrayList< XYChart.Series<String, Number>  >();
        for(String s:b) {
        	XYChart.Series<String, Number> series1 = new XYChart.Series<>();  
            series1.setName(s); 
            switch(s) {
			case "闲置":
				for(CharData c:ac) {
	            	series1.getData().add(new XYChart.Data<>(c.getName(), c.getXianzhi())); 
	            } 
				break;
			case "待领用":
				for(CharData c:ac) {
	            	series1.getData().add(new XYChart.Data<>(c.getName(), c.getDaiLingyong())); 
	            } 
				break;	
			case "使用中":
				for(CharData c:ac) {
	            	series1.getData().add(new XYChart.Data<>(c.getName(), c.getShiYongzhong())); 
	            } 
				break;
			case "待维修":
				for(CharData c:ac) {
	            	series1.getData().add(new XYChart.Data<>(c.getName(), c.getDaiWeixiu())); 
	            } 
				break;
			case "维修中":
				for(CharData c:ac) {
	            	series1.getData().add(new XYChart.Data<>(c.getName(), c.getWeiXXiuzhong())); 
	            } 
				break;
			case "报废":
				for(CharData c:ac) {
	            	series1.getData().add(new XYChart.Data<>(c.getName(), c.getBaofei())); 
	            } 
				break;
			}
            stackedBarChart.getData().add(series1);
        }
        sp1.getChildren().add(stackedBarChart);	
        stackedBarChart.setVisible(false);
    }

	@FXML
    void switchchar(ActionEvent event) {
		if(chart.isVisible()) {
			chart.setVisible(false);
			stackedBarChart.setVisible(true);
		}else {
			chart.setVisible(true);
			stackedBarChart.setVisible(false);
		}	
    }
	
	
}

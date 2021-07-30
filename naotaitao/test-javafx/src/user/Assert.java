package user;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Assert {
	private boolean isselected;
	private String assertNum;
	private String type;
	private String name;
	private String status;
	private Date time;
	private String num;
	private String source;
	private String typeNum;
	private final StringProperty status1 = new SimpleStringProperty();
	

	  public void setStatus1(String status) {
	        this.status1.set(status);
	    }
	
	  public String getStatus1(){
	        return status1.get();
	    }   

	public Assert(String assertNum, String type, String name, String status, Date time, String num, String source,
			String typeNum) {
		super();
		this.assertNum = assertNum;
		this.type = type;
		this.name = name;
//		this.status1.set(status);
		this.status = status;
		this.time = time;
		this.num = num;
		this.source = source;
		this.typeNum = typeNum;
	}
	//linguong
	public Assert(String assertNum, String name, String status, String num, String usedepartment, String fuzeren,
			Date fenpeitime, String fenpeibeizhu) {
		super();
		this.assertNum = assertNum;
		this.name = name;
		this.status = status;
		this.num = num;
		this.usedepartment = usedepartment;
		this.fuzeren = fuzeren;
		this.fenpeitime = fenpeitime;
		this.fenpeibeizhu = fenpeibeizhu;
	}

	public boolean isIsselected() {
		return isselected;
	}
	public void setIsselected(boolean isselected) {
		this.isselected = isselected;
	}
	//caigou
	private  String department;
	private String model;
	private String proposer;
	private Date approvetime;
	private int approvenum;
	private float approvemoney;
	private String approvereason;
	private String approveOpinion;
	
	//fenpei
	private String usedepartment;
	private String  fuzeren;
	private Date fenpeitime;
	public String getUsedepartment() {
		return usedepartment;
	}

	//weixiu
	private String weixiuposition;
	private int weixiudays;
	private Date weixiutime;
	private String weixiudepart;
	private float weixiumoney;
	private String weixiubeizhu;
	
	
	public Assert(String assertNum, String model, String usedepartment, String weixiuposition, int weixiudays,
			Date weixiutime, String weixiudepart, float weixiumoney, String weixiubeizhu) {
		super();
		this.assertNum = assertNum;
		this.model = model;
		this.usedepartment = usedepartment;
		this.weixiuposition = weixiuposition;
		this.weixiudays = weixiudays;
		this.weixiutime = weixiutime;
		this.weixiudepart = weixiudepart;
		this.weixiumoney = weixiumoney;
		this.weixiubeizhu = weixiubeizhu;
	}

	public String getWeixiuposition() {
		return weixiuposition;
	}

	public void setWeixiuposition(String weixiuposition) {
		this.weixiuposition = weixiuposition;
	}

	public int getWeixiudays() {
		return weixiudays;
	}

	public void setWeixiudays(int weixiudays) {
		this.weixiudays = weixiudays;
	}

	public Date getWeixiutime() {
		return weixiutime;
	}

	public void setWeixiutime(Date weixiutime) {
		this.weixiutime = weixiutime;
	}

	public String getWeixiudepart() {
		return weixiudepart;
	}

	public void setWeixiudepart(String weixiudepart) {
		this.weixiudepart = weixiudepart;
	}

	public float getWeixiumoney() {
		return weixiumoney;
	}

	public void setWeixiumoney(float weixiumoney) {
		this.weixiumoney = weixiumoney;
	}

	public String getWeixiubeizhu() {
		return weixiubeizhu;
	}

	public void setWeixiubeizhu(String weixiubeizhu) {
		this.weixiubeizhu = weixiubeizhu;
	}

	public void setUsedepartment(String usedepartment) {
		this.usedepartment = usedepartment;
	}

	public String getFuzeren() {
		return fuzeren;
	}

	public void setFuzeren(String fuzeren) {
		this.fuzeren = fuzeren;
	}

	public Date getFenpeitime() {
		return fenpeitime;
	}

	public void setFenpeitime(Date fenpeitime) {
		this.fenpeitime = fenpeitime;
	}

	public String getFenpeistatus() {
		return fenpeistatus;
	}

	public void setFenpeistatus(String fenpeistatus) {
		this.fenpeistatus = fenpeistatus;
	}

	public String getFenpeibeizhu() {
		return fenpeibeizhu;
	}

	public void setFenpeibeizhu(String fenpeibeizhu) {
		this.fenpeibeizhu = fenpeibeizhu;
	}
	private String fenpeistatus;
	private String fenpeibeizhu;
	
	
	public Assert(String assertNum, String name, String model, String usedepartment, String fuzeren, Date fenpeitime,
			String fenpeistatus, String fenpeibeizhu) {
		super();
		this.assertNum = assertNum;
		this.name = name;
		this.model = model;
		this.usedepartment = usedepartment;
		this.fuzeren = fuzeren;
		this.fenpeitime = fenpeitime;
		this.fenpeistatus = fenpeistatus;
		this.fenpeibeizhu = fenpeibeizhu;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public Date getApprovetime() {
		return approvetime;
	}
	public void setApprovetime(Date approvetime) {
		this.approvetime = approvetime;
	}
	public int getApprovenum() {
		return approvenum;
	}
	public void setApprovenum(int approvenum) {
		this.approvenum = approvenum;
	}
	public float getApprovemoney() {
		return approvemoney;
	}
	public void setApprovemoney(float approvemoney) {
		this.approvemoney = approvemoney;
	}
	public String getApprovereason() {
		return approvereason;
	}
	public void setApprovereason(String approvereason) {
		this.approvereason = approvereason;
	}
	public String getApproveOpinion() {
		return approveOpinion;
	}
	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}
	public Assert(String name, String department, String model, String proposer, Date approvetime, int approvenum,
			float approvemoney, String approvereason, String approveOpinion) {
		super();
		this.name = name;
		this.department = department;
		this.model = model;
		this.proposer = proposer;
		this.approvetime = approvetime;
		this.approvenum = approvenum;
		this.approvemoney = approvemoney;
		this.approvereason = approvereason;
		this.approveOpinion = approveOpinion;
	}
	
	
	
	
	public String getAssertNum() {
		return assertNum;
	}
	public void setAssertNum(String assertNum) {
		this.assertNum = assertNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTypeNum() {
		return typeNum;
	}
	public void setTypeNum(String typeNum) {
		this.typeNum = typeNum;
	}
	
	
}

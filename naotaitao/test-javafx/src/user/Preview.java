package user;

public class Preview {
	private int id;
	private int aid;
	private int uid;
	private String content;
	private int ratescore;
	public int getRatescore() {
		return ratescore;
	}
	public void setRatescore(int ratescore) {
		this.ratescore = ratescore;
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

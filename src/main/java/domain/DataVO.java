package domain;

public class DataVO {
	private int dataID;
	private String dataTitle;
	private String userID;
	private String dataDate;
	private String dataContent;
	private int dataAvailable;
	
	public int getDataID() {
		return dataID;
	}
	public void setDataID(int dataID) {
		this.dataID = dataID;
	}
	public String getDataTitle() {
		return dataTitle;
	}
	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getDataDate() {
		return dataDate;
	}
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	public String getDataContent() {
		return dataContent;
	}
	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}
	public int getDataAvailable() {
		return dataAvailable;
	}
	public void setDataAvailable(int dataAvailable) {
		this.dataAvailable = dataAvailable;
	}
	
	//toString 해두면 값을 불러올때 string으로 다 변환되어서 호출이 된다. 이걸 하지 않으면 한글로 글자가 안바뀌어서 나옴
	@Override
	public String toString() {
		return "DataVO [dataID=" + dataID + ", dataTitle=" + dataTitle + ", userID=" + userID + ", dataDate=" + dataDate
				+ ", dataContent=" + dataContent + ", dataAvailable=" + dataAvailable + "]";
	}
	
	
}

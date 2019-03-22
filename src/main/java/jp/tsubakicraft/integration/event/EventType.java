package jp.tsubakicraft.integration.event;

public class EventType {
	private String type;
	private String subtype;
	
	public static final String ANONYMOUS_TYPE = "*";
	
	public EventType(String type, String subtype) {
		this.type = type;
		this.subtype = subtype;
	}
	
	public String getType() {
		return type;
	}
	
	public String getSubtype() {
		return subtype;
	}
	
	public String toString() {
		return "(" + type + ":" + subtype + ")";
	}
}

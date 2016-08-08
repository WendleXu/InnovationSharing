package com.yonyou.base.form;

public class QueryArg {

	private String field_name;
	private String condition;
	private String relation;
	private int field_type;
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getField_type() {
		return field_type;
	}

	public void setField_type(int field_type) {
		this.field_type = field_type;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}

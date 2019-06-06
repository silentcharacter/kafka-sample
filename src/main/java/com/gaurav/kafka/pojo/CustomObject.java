package com.gaurav.kafka.pojo;

import java.io.Serializable;

public class CustomObject implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	protected CustomObject()
	{
	}

	public CustomObject(final String id, final String name)
	{
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package com.app.flow;

import java.io.Serializable;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named(value="flowCounter")
@FlowScoped("counterFlow")
public class FlowCounter implements Serializable {

	private int count;

	public void init() {
		count = 0;
		System.out.println("FlowScope init.");
	}
	public void fin() {
		System.out.println("FlowScope fin.");
	}
	public void increment() {
		count++;
	}
	public int getCount() {
		return count;
	}
}

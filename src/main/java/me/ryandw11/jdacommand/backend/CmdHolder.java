package me.ryandw11.jdacommand.backend;

import java.lang.reflect.Method;

public class CmdHolder {
	
	private Method mtd;
	private Object obj;
	
	public CmdHolder(Method mtd, Object obj) {
		this.mtd = mtd;
		this.obj = obj;
	}
	
	public Method getMethod() {
		return mtd;
	}
	
	public Object getObject() {
		return obj;
	}

}

package com.asiainfo.integretion.o2p.servicemigration.comon.util;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.asiainfo.integretion.o2p.servicemigration.common.util.ReflectionUtil;
import com.asiainfo.integretion.o2p.servicemigration.domain.Service;

public class ReflectionUtilTest {
	@Test(expected=IllegalArgumentException.class)
	public void getAttrValueTest() {
		Service service = new Service();
		service.setDescription("aa");
		String busCode = (String)ReflectionUtil.getAttrValue("busCode", service);
		Assert.assertSame("aa", busCode);
		
		ReflectionUtil.getAttrValue("notMethod", service);
	}
	
	@Test
	public void setAttrValueTest() {
		Service service = new Service();
		ReflectionUtil.setAttrValue("busCode", "aa", service);
		Assert.assertSame("aa", service.getDescription());
	}
	
	@Test
	public void invokeMethodTest() {
		Service service = new Service();
		Method m = ReflectionUtil.findMethod(Service.class, "setBusCode", String.class);
		ReflectionUtil.invokeMethod(m, service, "aa");
		Assert.assertSame("aa", service.getDescription());
		
		m = ReflectionUtil.findMethod(Service.class, "getBusCode");
		String value = (String) ReflectionUtil.invokeMethod(m, service);
		Assert.assertSame("aa", value);
	}
}

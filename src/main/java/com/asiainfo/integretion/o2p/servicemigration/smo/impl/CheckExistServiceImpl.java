package com.asiainfo.integretion.o2p.servicemigration.smo.impl;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.jms.IllegalStateException;

import org.springframework.stereotype.Service;

import com.asiainfo.integretion.o2p.servicemigration.common.util.ReflectionUtil;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ApiInvokeMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.BaseMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.CheckMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ContractMapper;
import com.asiainfo.integretion.o2p.servicemigration.domain.App;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBean;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBeanHelper;
import com.asiainfo.integretion.o2p.servicemigration.domain.Component;
import com.asiainfo.integretion.o2p.servicemigration.domain.Contract;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractVersion;
import com.asiainfo.integretion.o2p.servicemigration.domain.Endpoint;
import com.asiainfo.integretion.o2p.servicemigration.domain.FileShare;
import com.asiainfo.integretion.o2p.servicemigration.domain.GetValueExpr;
import com.asiainfo.integretion.o2p.servicemigration.domain.MessageFlow;
import com.asiainfo.integretion.o2p.servicemigration.domain.Org;
import com.asiainfo.integretion.o2p.servicemigration.domain.RouteCondition;
import com.asiainfo.integretion.o2p.servicemigration.domain.RoutePolicy;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplementNode;
import com.asiainfo.integretion.o2p.servicemigration.smo.ICheckExistService;

@Service("checkExistService")
public class CheckExistServiceImpl implements ICheckExistService{
	@Resource(name="baseMapper")
	BaseMapper baseMapper;
	@Resource(name="checkMapper")
	CheckMapper checkMapper;
	@Resource(name="apiInvokeMapper")
	ApiInvokeMapper apiInvokeMapper;
	@Resource(name="contractMapper")
	ContractMapper contractMapper;

	@Override
	public boolean checkExist(BasedBean targetObj, BasedBean rootBean, Integer tenantId) throws IllegalStateException {
		if(targetObj == null) {
			return false;
		}
		targetObj.attrTransform(false);
		if(targetObj instanceof TechnologyImplementNode || targetObj instanceof Component || targetObj instanceof Org || targetObj instanceof App || targetObj instanceof FileShare || targetObj instanceof Contract || targetObj instanceof RoutePolicy) {
			String name = targetObj.getClass().getName().substring(targetObj.getClass().getName().lastIndexOf(".")+1);
			Method m = null;
			BasedBean bean = null;
			if(targetObj instanceof Contract) {
				m = ReflectionUtil.findMethod(ContractMapper.class, "query"+name+"ByCodeWithOne", String.class, Integer.class);
			} else {
				m = ReflectionUtil.findMethod(BaseMapper.class, "query"+name+"ByCodeWithOne", String.class, Integer.class);
			}
			if(m != null) {
				if(targetObj instanceof Contract) {
					bean  = (BasedBean) ReflectionUtil.invokeMethod(m, contractMapper, targetObj.codeValue(), tenantId);
				} else {
					bean  = (BasedBean) ReflectionUtil.invokeMethod(m, baseMapper, targetObj.codeValue(), tenantId);
				}
				if(bean == null) {
					return false;
				} else {
					if(targetObj.equals(bean)) {
						return true;
					} else {
						throw new IllegalStateException("targetObj:" + name + " code:" + targetObj.codeValue() + " exist in the database or the import doc, and the conent not same the import one");
					}
				}
			}
		}  else if(targetObj instanceof MessageFlow || targetObj instanceof Endpoint || targetObj instanceof RoutePolicy || targetObj instanceof RouteCondition || targetObj instanceof GetValueExpr
				|| targetObj instanceof ContractVersion || targetObj instanceof RoutePolicy) {
			String name = targetObj.getClass().getName().substring(targetObj.getClass().getName().lastIndexOf(".")+1);
			Method m = null;
			BasedBean bean = null;
			if(targetObj instanceof ContractVersion) {
				m = ReflectionUtil.findMethod(ContractMapper.class, "query"+name+"ByCodeWithOne", String.class, Integer.class);
			} else {
				m = ReflectionUtil.findMethod(ApiInvokeMapper.class, "query"+name+"ByCodeWithOne", String.class, Integer.class);
			}
			if(m != null) {
				if(targetObj instanceof ContractVersion) {
					bean =  (BasedBean) ReflectionUtil.invokeMethod(m, contractMapper, targetObj.codeValue(), tenantId);
				} else {
					bean = (BasedBean) ReflectionUtil.invokeMethod(m, apiInvokeMapper, targetObj.codeValue(), tenantId);
				}
				if(bean == null) {
					return false;
				} else {
					return true;
//					if(targetObj.fullEquals(bean, rootBean)) {
//						return true;
//					} else {
//						return false;
//						//throw new IllegalStateException("targetObj:" + name + " code:" + targetObj.codeValue() + " exist in the database, and the conent not same the import one");
//					}
				}
			}
		}
		return false;
	}
	
}

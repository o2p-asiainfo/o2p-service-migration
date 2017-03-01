package com.asiainfo.integretion.o2p.servicemigration.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.asiainfo.integration.o2p.session.web.http.CookieUtil;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.WebConstants;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.smo.IMigrationService;

@Component
@Aspect
public class LogInterceptor {
	private static final Log LOG = LogFactory.getLog(LogInterceptor.class);
	@Resource(name="migrationService")
	private IMigrationService migrationService;
	
	@Pointcut("execution(* com.asiainfo.integretion.o2p.servicemigration.controller.ServiceController.importAction(..)) and args(request)")  
    private void beforeImport(){}
	
	@SuppressWarnings("unchecked")
	@Before("com.asiainfo.integretion.o2p.servicemigration.interceptor.LogInterceptor.beforeImport() && args(request)")  
    public void doBeforeImport(HttpServletRequest request) {
		try{
			UserRoleInfo userRoleInfo = null;
			Object tenantObj = CookieUtil.getTenantId(request);
			if(tenantObj==null){
				return;
			}
			userRoleInfo = (UserRoleInfo) request.getSession().getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY);
	        String ip = getIpAddr(request);
			migrationService.assembLogAndLog((Map<String, ServiceObject>)request.getSession().getAttribute("serviceObjectMap"), ip, userRoleInfo);
		}catch(Exception e){
			LOG.error(e);
		}
    }
	
	public static String getIpAddr(HttpServletRequest request) {  
		String ipAddress = request.getHeader("x-forwarded-for");  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getRemoteAddr();
        }  
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
            if(ipAddress.indexOf(",")>0){  
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
            }  
        }  
        return ipAddress; 
    }
}

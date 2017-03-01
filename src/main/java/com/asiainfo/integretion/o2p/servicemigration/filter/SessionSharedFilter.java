package com.asiainfo.integretion.o2p.servicemigration.filter;

import javax.servlet.FilterConfig;

import com.asiainfo.integration.o2p.session.web.sso.SsoSessionRepositoryFilter;

public class SessionSharedFilter extends SsoSessionRepositoryFilter {
	
	public void init(FilterConfig config) {
		try{
			super.init(config);
		}catch(Exception e){
			System.out.println(e);
		}
	}
   

}

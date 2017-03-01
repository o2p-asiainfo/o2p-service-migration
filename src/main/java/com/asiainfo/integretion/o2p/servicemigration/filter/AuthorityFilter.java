package com.asiainfo.integretion.o2p.servicemigration.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ailk.eaap.o2p.common.interceptor.UserNameFilter;

public class AuthorityFilter extends UserNameFilter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try{
			super.doFilter(request, response, chain);
		}catch(Exception e){
			System.out.println(e);
		}
	}
    

}

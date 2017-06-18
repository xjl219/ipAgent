package com.xujl.ipagent.thrift;
import org.apache.thrift.TException;

import com.xujl.ipagent.*;
import com.xujl.ipagent.core.ProxyIPool;
public class AgentServiceHandler implements AgentService.Iface{

	@Override
	public String disable(String domain, String ip) throws TException {
		
		return ProxyIPool.disableIP(domain, ip);
	}

	@Override
	public String enrol(String domain, int type, String url, String rule,long interval) throws TException {
		ProxyIPool.addDomain(domain, type, url, rule,interval);
		return String.format("registered domain:%s, type:%s, url:%s, check:%s,interval:%d",domain, type, url, rule,interval);
	}

	@Override
	public String get(String domain) throws TException {
		// TODO Auto-generated method stub
		return ProxyIPool.getIP(domain);
	}

	@Override
	public int getAvailableByDomain(String domain) throws TException {
		return ProxyIPool.getAvailableByDomain(domain);
	}

}

package com.xujl.ipagent.core.checkers;

import org.apache.commons.httpclient.methods.GetMethod;

import com.xujl.ipagent.core.Domain;
import com.xujl.ipagent.core.ProxyIPool;

public class StatusChecker extends Checker {


	public StatusChecker(String name, Domain domain) {
		super(name, domain);
	}

	public void run() {
		int idx = -1;
		while ((idx = domain.ipMap.nextClearBit(0)) > -1) {
			try {
				
				Long ip = ProxyIPool.ips.get(idx);

				String[] longToIP = longToIP(ip).split(":");

				hc.setProxy(longToIP[0], Integer.parseInt(longToIP[1]));
				client.setHostConfiguration(hc);
				GetMethod get = new GetMethod(domain.url);
				int executeMethod = client.executeMethod(get);
			
				domain.ipMap.set(idx, executeMethod == domain.check.hashCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("StatusChecker");
	}

}

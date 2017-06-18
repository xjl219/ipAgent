package com.xujl.ipagent.core.checkers;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.xujl.ipagent.core.Domain;
import com.xujl.ipagent.core.ProxyIPool;

public class LengthChecker extends Thread {

	String name;
	Domain domain;
	HttpClient client = new HttpClient();
	HostConfiguration hc = new HostConfiguration();

	public LengthChecker(String name, Domain domain) {
		this.domain = domain;
		this.name = name;
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
				client.executeMethod(get);
				domain.ipMap.set(idx, get.getResponseBodyAsString().length() > domain.check.hashCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("LengthChecker");
	}

	public long ipToLong(String strIp) {
		long[] ip = new long[4];
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1, strIp.indexOf(":")));
		int port = Integer.parseInt(strIp.substring(strIp.indexOf(":") + 1));
		return (ip[0] << 40) + (ip[1] << 32) + (ip[2] << 24) + (ip[3] << 16) + port;
	}

	public String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		sb.append(String.valueOf((longIp >>> 40)));
		sb.append(".");
		sb.append(String.valueOf((longIp & 0x00FFFFFFFFFFl) >>> 32));
		sb.append(".");
		sb.append(String.valueOf((longIp & 0x0000FFFFFFFFl) >>> 24));
		sb.append(".");
		sb.append(String.valueOf((longIp & 0x000000FFFFFFl) >>> 16));
		sb.append(":");
		sb.append(String.valueOf((longIp & 0x00000000FFFFl)));
		return sb.toString();
	}

	public static void main(String[] args) {
		new LengthChecker("", null).start();
	}
}

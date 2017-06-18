package com.xujl.ipagent.core.checkers;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;

import com.xujl.ipagent.core.Domain;

public abstract class Checker extends Thread  implements Runnable{

	String name;
	Domain domain;
	HttpClient client = new HttpClient();
	HostConfiguration hc = new HostConfiguration();

	public Checker(String name, Domain domain) {
		this.domain = domain;
		this.name = name;
	}

	public abstract void run();

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


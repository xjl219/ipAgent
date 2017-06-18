package com.xujl.ipagent.client;

import org.apache.thrift.TException;

import com.xujl.ipagent.AgentService;

public interface IClient  extends AgentService.Iface{
	public static final int CHECK_TYPE_STATUS=0x01;
		
	
	public static final int CHECK_TYPE_LENGTH_LT=0x02;
		
	
	public static final int CHECK_TYPE_CONTAINS=0x04;
	public String disable( String ip) throws TException ;
	public String enrol(int type, String url, String rule,long interval) throws TException ;
	public String get() throws TException ;
	public int getAvailableByDomain() throws TException ;
}

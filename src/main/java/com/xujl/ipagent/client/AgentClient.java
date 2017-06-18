package com.xujl.ipagent.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;

import com.xujl.ipagent.AgentService;
import com.xujl.ipagent.AgentService.Client;

public class AgentClient implements IClient{
	 Client client ;
	 String domain, host,  port;
	private AgentClient(String domain,String host, String port) throws Exception{
		this.domain=domain;
		this.host=host;
		this.port=port;
		 String rpcAddres="http://"+host+":" + port + "/api/";
			TTransport transport = new THttpClient(rpcAddres);
			TProtocolFactory protocolFactory=new TBinaryProtocol.Factory();
		    TProtocol protocol = protocolFactory.getProtocol(transport);
		     client = new AgentService.Client(protocol);
	}
	public static IClient me(String domain,String host, String port) throws Exception{
		return new AgentClient(domain,host,port);
	}
	@Override
	public String disable(String domain, String ip) throws TException {
		return client.disable(domain, ip);
	}
	@Override
	public String enrol(String domain, int type, String url, String rule,long interval) throws TException {
		return client.enrol(domain, type, url, rule,interval);
	}
	@Override
	public String enrol(int type, String url, String rule, long interval) throws TException {
		return client.enrol(domain, type, url, rule,interval);
	}
	@Override
	public String get(String domain) throws TException {
		return client.get(domain);
	}
	@Override
	public int getAvailableByDomain(String domain) throws TException {
		return client.getAvailableByDomain(domain);
	}
	@Override
	public String disable(String ip) throws TException {
		return disable(domain, ip);
	}
	@Override
	public String get() throws TException {
		return get(domain);
	}
	@Override
	public int getAvailableByDomain() throws TException {
		return getAvailableByDomain(domain);
	}


}

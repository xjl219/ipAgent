package com.xujl.ipagent.client;

public class Helper {

	public static void main(String[] args) {

		try {
			IClient agent =AgentClient.me("domain", "localhost", "8080") ;
			agent.enrol(8, "http://www.baidu.com", "0", 1000);
			final String x = agent.get();
			System.out.println(x);
			System.out.println(agent.disable(x));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

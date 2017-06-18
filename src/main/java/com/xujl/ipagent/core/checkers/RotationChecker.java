package com.xujl.ipagent.core.checkers;

import com.xujl.ipagent.core.Domain;

public class RotationChecker extends Checker implements Runnable{
	public RotationChecker(String name, Domain domain) {
		super(name, domain);
	} 
	public void run(){
		if(domain.ipMap.cardinality() < domain.check.hashCode())
			domain.ipMap.set(0,domain.ipMap.size());
		
		
	}
}

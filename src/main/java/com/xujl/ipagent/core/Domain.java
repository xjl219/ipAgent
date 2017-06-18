package com.xujl.ipagent.core;

import java.io.Serializable;
import java.util.BitSet;

public class  Domain  implements Serializable{
			/**
	 * 
	 */
	private static final long serialVersionUID = -4593250378783800429L;
			Domain(BitSet ipMap,
			 int type,
			 String url,
			 Object check,long interval){
				this.check=check;
				this.ipMap= ipMap;
				this.type=type;
				this.url=url;
				this.interval=interval;
			}
			public	final BitSet ipMap;
			public	final int type;
			public		final String url;
			public		final Object check;
			public	final long interval;
		}


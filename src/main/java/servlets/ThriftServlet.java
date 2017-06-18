package servlets;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;

public class ThriftServlet extends TServlet {

	public ThriftServlet(TProcessor processor, TProtocolFactory protocolFactory) {
		super(processor, protocolFactory);
		// TODO Auto-generated constructor stub
	}

}

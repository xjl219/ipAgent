package ipAgent;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.boot.web.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import com.xujl.ipagent.AgentService;
import com.xujl.ipagent.thrift.AgentServiceHandler;

@SpringBootConfiguration
@EnableAutoConfiguration
@RestController
@ComponentScan(basePackages="com.xujl")
public class AgentApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		String[] dest=new String[args.length]; 
//		System.arraycopy(args, 1, dest, 0, args.length-1);
//		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		
		ConfigurableApplicationContext run = SpringApplication.run(AgentApplication.class,dest);
	}
	   @Bean
	    public TProtocolFactory tProtocolFactory() {
	        return new TBinaryProtocol.Factory();
	    }
	   @Bean
	   public AgentServiceHandler hdanler() {
	        return new AgentServiceHandler();
	    }
	@Bean
	public ServletRegistrationBean  api(TProtocolFactory protocolFactory,AgentServiceHandler hdanler) {
		ServletRegistrationBean servlet =new ServletRegistrationBean(new TServlet(new AgentService.Processor<AgentServiceHandler>(hdanler), protocolFactory), "/api/");
		return servlet;
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AgentApplication.class);
	}
}

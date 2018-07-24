package com.zy.quartz.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * H2服务配置文件.
 * 
 * @author Tiger
 */
@Configuration
public class H2ServerConfig implements DisposableBean {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	 // TCP port for remote connections, default 9092
	@Value("${h2.tcp.port:9092}")
	private String h2TcpPort;

	// Web port, default 8082
	@Value("${h2.web.port:8082}")
	private String h2WebPort;

	private Server tcpServer = null;
	
	private Server webServer = null;
	
	/**
	 * TCP connection to connect with SQL clients to the embedded h2 database.
	 *
	 * Connect to "jdbc:h2:tcp://localhost:9092/mem:testdb", username "sa", password empty.
	 * @return Server
	 * @throws SQLException SQLException
	 */
	@Bean
	@ConditionalOnExpression("${h2.tcp.enabled:true}")
	public Server h2TcpServer() throws SQLException {
		this.logger.debug("h2 tcp start");
		this.tcpServer = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", this.h2TcpPort).start();
		return this.tcpServer;
	}

	/**
	 * Web console for the embedded h2 database.
	 * Go to http://localhost:8082 and connect to the database "jdbc:h2:mem:testdb", username "sa", password empty.
	 * @return Server
	 * @throws SQLException SQLException
	 */
	@Bean
	@ConditionalOnExpression("${h2.web.enabled:false}")
	public Server h2WebServer() throws SQLException {
		this.logger.debug("h2 web start");
		this.webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", this.h2WebPort).start();
		return this.webServer;
	}

	@Override
	public void destroy() throws Exception {
		if (this.tcpServer != null) {
			this.logger.debug("h2 tcp stop");
			this.tcpServer.stop();
			this.tcpServer.shutdown();
		}
		
		if (this.webServer != null) {
			this.logger.debug("h2 web stop");
			this.webServer.stop();
			this.webServer.shutdown();
		}
	}

}

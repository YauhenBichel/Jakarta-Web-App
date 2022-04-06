package com.holidaysystem.configuration;
/*
@PropertySource("classpath:mdb.properties")
@Configuration
public class MdbConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(MdbConfig.class);

	@Autowired
	private Environment environment;

	@Bean
	public JndiTemplate jndiTemplate() {
    return new JndiTemplate();
	}

	@Bean
	public QueueConnectionFactory queueConnectionFactory(JndiTemplate jndiTemplate) throws NamingException {
    QueueConnectionFactory factory = jndiTemplate.lookup(environment.getProperty("ARTEMIS_JMS_CONNECTION_FACTORY"), QueueConnectionFactory.class);
    LOGGER.info("got the connection factory");
    return factory;
	}

	@Bean
	public Queue jmsQueue(JndiTemplate jndiTemplate) throws NamingException {
    Queue queue = jndiTemplate.lookup(environment.getProperty("ARTEMIS_QUEUE_LOOKUP"), Queue.class);
    LOGGER.info("got the queue");
    return queue;
	}

	@Bean(destroyMethod = "close")
	@Scope("prototype")
	public QueueConnection queueConnection(QueueConnectionFactory queueConnectionFactory) throws JMSException {
    QueueConnection queueConnection = queueConnectionFactory.createQueueConnection(environment.getProperty("WILDFLY_USER"), environment.getProperty("WILDFLY_PASSWORD"));
    LOGGER.info("created a connection");
    return queueConnection;
	}

	@Bean(destroyMethod = "close")
	@Scope("prototype")
	public QueueSession queueSession(QueueConnection queueConnection) throws JMSException {
    QueueSession queueSession = queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
    LOGGER.info("created a queueSession");
    return queueSession;

	}

}
*/
package com.siping.integrate.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.stroma.framework.core.exception.ExceptionUtils;
import org.stroma.framework.core.log.LoggerFactory;
import org.stroma.framework.core.util.IOUtils;
import org.stroma.framework.core.util.StringUtils;

public enum EndPoint {

    SERVICE("http://127.0.0.1:8080", "http://192.168.10.103:88", "http://127.0.0.1:8080", "/hyh_service");

    private final String context;

    private final String localPoint;

    private final String testPoint;

    private final String productPoint;

    static boolean awsOpend = false;

    static String dev; // only value DEV or PROD,when deploy in local
                       // environment use DEV,other use PROD

    static final Logger LOGGER = LoggerFactory.getLogger(EndPoint.class);

    static {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("site-master.properties");
        Properties enviroment = new Properties();
        try {
            enviroment.load(stream);
            awsOpend = Boolean.valueOf(enviroment.getProperty("site.aws"));
            dev = String.valueOf(enviroment.getProperty("site.environment"));

        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.stackTrace(e));
        } finally {
            IOUtils.close(stream);
        }
    }

    private EndPoint(final String localPoint, final String testPoint, final String productPoint, final String context) {
        this.localPoint = localPoint;
        this.testPoint = testPoint;
        this.productPoint = productPoint;
        this.context = context;
    }

    @Override
    public String toString() {
        String point = "";
        if (dev.equalsIgnoreCase("DEV")) {
            point = localPoint;
        } else {
            point = awsOpend ? productPoint : testPoint;
        }
        return StringUtils.hasText(context) ? point + context : point;
    }
}

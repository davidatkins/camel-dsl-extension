package uk.co.davidatkins;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Created by david on 30/12/14.
*/
class MyLogger {

    private enum LogLevel {
        INFO, ERROR;
    }

    public static final Processor info(final String message) {
        return buildProcessor(message, LogLevel.INFO);
    }

    public static final Processor error(final String message) {
        return buildProcessor(message, LogLevel.ERROR);
    }

    private static final Processor buildProcessor(final String message, final LogLevel level) {

        return new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {

                String routeId = exchange.getUnitOfWork().getRouteContext().getRoute().getId();
                String contextId = exchange.getContext().getName();

                Logger logger = LoggerFactory.getLogger(routeId + "." + contextId);

                if(LogLevel.INFO == level) {
                    logger.info(message);
                } else if(LogLevel.ERROR == level) {
                    logger.error(message);
                }

            }

        };

    };

}

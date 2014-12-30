package uk.co.davidatkins;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class DslExtensionTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {

        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                // @formatter:off

                from("direct:main-route")
                        .bean(MyLogger.error("Route Called"));

                // @formatter:on

            }
        };

    }

    @Test
    public void callRoute() {
        template().requestBody("direct:main-route", "Hello", String.class);
    }

}

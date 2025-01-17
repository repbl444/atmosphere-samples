/*
 * Copyright 2008-2022 Async-IO.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.nettosphere.samples.chat;

import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Nettosphere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A bootstrap class that start Nettosphere and the Atmosphere Chat samples.
 *
*/
public class NettosphereJerseyChat {

    private static final Logger logger = LoggerFactory.getLogger(Nettosphere.class);

    public static void main(String[] args) throws IOException {
        Config.Builder b = new Config.Builder();
        b.resource(ResourceChat.class)
                // For *-distrubution
                .resource("./webapps")
                // For mvn exec:java
                .resource("./src/main/resources")
                // For running inside an IDE
                .resource("./nettosphere-samples/jersey-chat/src/main/resources")
                .port(8080)
                .host("127.0.0.1")
                .initParam("org.atmosphere.websocket.messageContentType","application/json")
                .initParam("org.atmosphere.websocket.messageMethod","POST")
                .initParam("com.sun.jersey.api.json.POJOMappingFeature","true")
                .build();
        Nettosphere s = new Nettosphere.Builder().config(b.build()).build();
        s.start();
        String a = "";

        logger.info("NettoSphere Jersey Chat Server started on port {}", 8080);
        logger.info("Type quit to stop the server");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!(a.equals("quit"))) {
            a = br.readLine();
        }
        System.exit(-1);
    }

}

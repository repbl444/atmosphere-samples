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
/**
 * This code was donated by Dan Vulpe https://github.com/dvulpe/atmosphere-ws-pubsub
 */
package org.atmosphere.samples.pubsub.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.atmosphere.cpr.AtmosphereResource;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UnsubscribeCommand.class, name = "unsubscribe"),
        @JsonSubTypes.Type(value = SubscribeCommand.class, name = "subscribe")})
public abstract class BaseCommand implements Command {
    protected AtmosphereResource resource;
    protected String channel;

    @Override
    public void setResource(AtmosphereResource resource) {
        this.resource = resource;
    }

    @Override
    public AtmosphereResource getResource(){
        return resource;
    }


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}

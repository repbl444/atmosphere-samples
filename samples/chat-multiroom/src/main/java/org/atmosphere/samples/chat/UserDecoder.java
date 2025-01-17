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
package org.atmosphere.samples.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Decoder;

import java.io.IOException;

/**
 * Decode a String into a {@link org.atmosphere.samples.chat.UserMessage}.
 */
public class UserDecoder implements Decoder<String, UserMessage> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserMessage decode(String s) {
        try {
            return mapper.readValue(s, UserMessage.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

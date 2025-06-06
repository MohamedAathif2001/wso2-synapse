/*
 * Copyright (c) 2025, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.synapse.core.synctx;

import java.util.Arrays;

public class Message {
    private byte[] rawPayload;
    private String contentType;

    public Message() {
        this.rawPayload = new byte[0];
        this.contentType = "";
    }

    public Message(byte[] rawPayload, String contentType) {
        this.rawPayload = rawPayload;
        this.contentType = contentType;
    }

    public byte[] getRawPayload() {
        return rawPayload;
    }

    public void setRawPayload(byte[] rawPayload) {
        this.rawPayload = rawPayload;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "rawPayload=" + Arrays.toString(rawPayload) +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}

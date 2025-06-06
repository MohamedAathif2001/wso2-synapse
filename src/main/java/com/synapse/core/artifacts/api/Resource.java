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

package com.synapse.core.artifacts.api;

import com.synapse.core.artifacts.Sequence;

public class Resource {
    private String methods;
    private String uriTemplate;
    private Sequence inSequence;
    private Sequence faultSequence;

    public Resource(String methods, String uriTemplate, Sequence inSequence, Sequence faultSequence) {
        this.methods = methods;
        this.uriTemplate = uriTemplate;
        this.inSequence = inSequence;
        this.faultSequence = faultSequence;
    }

    public Resource() {

    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getUriTemplate() {
        return uriTemplate;
    }

    public void setUriTemplate(String uriTemplate) {
        this.uriTemplate = uriTemplate;
    }

    public Sequence getInSequence() {
        return inSequence;
    }

    public void setInSequence(Sequence inSequence) {
        this.inSequence = inSequence;
    }

    public Sequence getFaultSequence() {
        return faultSequence;
    }

    public void setFaultSequence(Sequence faultSequence) {
        this.faultSequence = faultSequence;
    }
}

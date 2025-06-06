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

package com.synapse.core.deployers;

import com.synapse.core.artifacts.inbound.Inbound;
import com.synapse.core.artifacts.inbound.Parameter;
import com.synapse.core.artifacts.utils.Position;
import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InboundDeployer {

    private static final String SYNAPSE_NAMESPACE = "http://ws.apache.org/ns/synapse";
    private static final Logger log = LogManager.getLogger(InboundDeployer.class);

    public Inbound unmarshal(String xmlData, Position position) throws XMLStreamException {
        OMElement inboundElement = AXIOMUtil.stringToOM(xmlData);

        if (inboundElement == null || !"inboundEndpoint".equals(inboundElement.getLocalName())) {
            return null;
        }

        Inbound newInbound = new Inbound();
        newInbound.setPosition(position);

        OMAttribute nameAttr = inboundElement.getAttribute(new QName("name"));
        OMAttribute sequenceAttr = inboundElement.getAttribute(new QName("sequence"));
        OMAttribute protocolAttr = inboundElement.getAttribute(new QName("protocol"));
        OMAttribute suspendAttr = inboundElement.getAttribute(new QName("suspend"));
        OMAttribute onErrorAttr = inboundElement.getAttribute(new QName("onError"));

        if (nameAttr != null) newInbound.setName(nameAttr.getAttributeValue());
        if (sequenceAttr != null) newInbound.setSequence(sequenceAttr.getAttributeValue());
        if (protocolAttr != null) newInbound.setProtocol(protocolAttr.getAttributeValue());
        if (suspendAttr != null) newInbound.setSuspend(String.valueOf(Boolean.parseBoolean(suspendAttr.getAttributeValue())));
        if (onErrorAttr != null) newInbound.setOnError(onErrorAttr.getAttributeValue());

        QName parametersQName = new QName(SYNAPSE_NAMESPACE, "parameters");
        QName parameterQName = new QName(SYNAPSE_NAMESPACE, "parameter");

        OMElement parametersElement = inboundElement.getFirstChildWithName(parametersQName);
        if (parametersElement != null) {
            List<Parameter> parameters = new ArrayList<>();
            Iterator<?> it = parametersElement.getChildrenWithName(parameterQName);

            while (it.hasNext()) {
                OMElement paramElement = (OMElement) it.next();
                OMAttribute paramNameAttr = paramElement.getAttribute(new QName("name"));
                if (paramNameAttr != null) {
                    String paramName = paramNameAttr.getAttributeValue();
                    String paramValue = paramElement.getText().trim();
                    parameters.add(new Parameter(paramName, paramValue));
                    log.debug("Added parameter {} to {}", paramName, newInbound.getName());
                }
            }
            newInbound.setParameters(parameters);
        } else {
            log.debug("No inbound parameters found in XML");
        }
        return newInbound;
    }
}

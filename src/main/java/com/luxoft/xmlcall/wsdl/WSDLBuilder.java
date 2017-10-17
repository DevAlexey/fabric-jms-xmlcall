package com.luxoft.xmlcall.wsdl;

import com.google.protobuf.Descriptors;
import com.luxoft.xmlcall.proto.XmlCall;
import com.luxoft.xmlcall.shared.Strings;
import org.apache.commons.text.StrSubstitutor;

import java.util.*;
import java.util.function.BiConsumer;

public class WSDLBuilder
{
    private static final String xmlHeader =
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";

    private static final String definitionsBegin =
            // ServiceName=HelloService
            // namespace=http://www.luxoft.com/wsdl
            "<${wsdl}definitions name=\"${serviceName}\"\n" +
                    " targetNamespace=\"${namespace}\"\n"+
                    " xmlns=\"http://schemas.xmlsoap.org/wsdl/\"\n" +
                    " xmlns:tns=\"${namespace}\"\n"+
                    " xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n"+
                    " xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\"\n"+
                    " xmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\"" +
                    ">\n";
    private static final String definitionsEnd =
            "</${wsdl}definitions>\n";

    public static final String soapHttpTransport = "http://schemas.xmlsoap.org/soap/http";
    public static final String soapJmsTransport = "http://www.w3.org/2010/soapjms/";

    private static final String RequestSuffix = Strings.RequestSuffix;
    private static final String ResponseSuffix = Strings.ResponceSuffix;

    private final String xs = "xs:";
    private final String wsdl = "wsdl:";
    private final String tns = "tns:";
    private final String namespace;
    private final String serviceName;
    private final Descriptors.Descriptor faultType;
    private final Set<Descriptors.Descriptor> extraInput;
    private final Set<Descriptors.Descriptor> extraOutput;
    private final boolean useMethodSpecificMessages;

    private final List<Descriptors.ServiceDescriptor> serviceDescriptors;
    private final Set<Descriptors.Descriptor> messageTypes = new HashSet<>();
    private final Set<Descriptors.Descriptor> inputTypes = new HashSet<>();
    private final Set<Descriptors.Descriptor> outputTypes = new HashSet<>();
    private final Set<Descriptors.Descriptor> regularTypes = new HashSet<>();

    private class Builder
    {
        private StringBuilder sb = new StringBuilder();
        private HashMap<String, String> map = new HashMap<>();
        private StrSubstitutor sub = new StrSubstitutor(map);

        Builder()
        {
            map.put("xs", xs);
            map.put("wsdl", wsdl);
            map.put("tns", tns);
            map.put("soap", "soap:");
            map.put("namespace", namespace);
            map.put("serviceName", serviceName);
            map.put("soapNamespace", namespace);
        }

        Builder set(String name, String value)
        {
            map.put(name, value);
            return this;
        }

        public Builder appendLiteral(String s) {
            sb.append(s);
            return this;
        }

        Builder append(String str)
        {
            sb.append(sub.replace(str));
            return this;
        }

        @Override
        public String toString() {
            return sb.toString();
        }

    }

    public WSDLBuilder(List<Descriptors.ServiceDescriptor> serviceDescriptors,
                       String targetNamespace,
                       String serviceName,
                       Descriptors.Descriptor faultType,
                       Set<Descriptors.Descriptor> extraInput,
                       Set<Descriptors.Descriptor> extraOutput)
    {
        this.namespace = targetNamespace;
        this.serviceName = serviceName;
        this.serviceDescriptors = serviceDescriptors;
        this.faultType = faultType;
        this.extraInput = extraInput;
        this.extraOutput = extraOutput;
        this.useMethodSpecificMessages = (extraInput != null && !extraInput.isEmpty())
                || (extraOutput != null && !extraOutput.isEmpty());

        markTypesRecursively(faultType, regularTypes);
        messageTypes.add(faultType);
        if (this.extraInput != null) {
            for (Descriptors.Descriptor descriptor : extraInput) {
                markTypesRecursively(descriptor, regularTypes);
            }
            // messageTypes.add(extraInput);
        }

        if (this.extraOutput != null) {
            for (Descriptors.Descriptor descriptor : extraOutput) {
                markTypesRecursively(descriptor, regularTypes);
            }
            // messageTypes.add(extraOutput);
        }

        for (Descriptors.ServiceDescriptor serviceDescriptor : serviceDescriptors) {
            for (Descriptors.MethodDescriptor methodDescriptor : serviceDescriptor.getMethods()) {
                final Descriptors.Descriptor outputType = methodDescriptor.getOutputType();
                final Descriptors.Descriptor inputType = methodDescriptor.getInputType();
                markTypesRecursively(inputType, regularTypes);
                markTypesRecursively(outputType, regularTypes);

                messageTypes.add(inputType);
                messageTypes.add(outputType);

                inputTypes.add(inputType);
                outputTypes.add(outputType);
            }
        }

    }

    private String getEntryName(Descriptors.FieldDescriptor fieldDescriptor)
    {
        final Descriptors.FieldDescriptor.Type type = fieldDescriptor.getType();
        switch (type) {
            case DOUBLE:
            case FLOAT:
            case INT64:
            case SINT64:
            case UINT64:
            case INT32:
            case SINT32:
            case UINT32:
            case SFIXED64:
            case FIXED64:
            case SFIXED32:
            case FIXED32:
            case BOOL:
            case STRING:
            case BYTES:
                return "entry";
            case GROUP:
            case MESSAGE:
                return fieldDescriptor.getMessageType().getFullName();

            case ENUM: // todo: map to enumeration
                throw new RuntimeException("Unhandled type: " + type.name());
        }

        throw new InternalError("Unhandled type: " + type.name());
    }

    private String getXSDType(Descriptors.FieldDescriptor fieldDescriptor)
    {
        final Descriptors.FieldDescriptor.Type type = fieldDescriptor.getType();
        switch (type) {
        case DOUBLE: return xs + "double";
        case FLOAT:  return xs + "float";
        case INT64:  return xs + "long";
        case SINT64: return xs + "long";
        case UINT64: return xs + "unsignedLong";
        case INT32:  return xs + "int";
        case SINT32: return xs + "int";
        case UINT32: return xs + "unsignedInt";
        case SFIXED64: return xs + "long";
        case FIXED64: return xs + "unsignedLong";
        case SFIXED32: return xs + "int";
        case FIXED32: return xs + "unsignedInt";
        case BOOL: return xs + "boolean";
        case STRING: return xs + "string";
        case BYTES: return xs + "base64Binary";
        case GROUP:
        case MESSAGE:
            return tns + fieldDescriptor.getMessageType().getFullName();

        case ENUM: // todo: map to enumeration
            throw new RuntimeException("Unhandled type: " + type.name());
        }

        throw new InternalError("Unhandled type: " + type.name());
    }

    private String buildXSDType(Descriptors.Descriptor type)
    {
        Builder builder = new Builder()
                .set("typeName", type.getFullName());

        final List<Descriptors.FieldDescriptor> fields = type.getFields();

        if (fields.isEmpty()) {
            builder.append("<${xs}complexType name=\"${typeName}\"/>");
        }
        else {
            builder.append("<${xs}complexType name=\"${typeName}\">");
            builder.append("<${xs}all>");

            for (Descriptors.FieldDescriptor fieldDescriptor : fields) {
                builder.set("fieldName", fieldDescriptor.getName());
                builder.set("fieldType", getXSDType(fieldDescriptor));

                if (fieldDescriptor.isRepeated()) {
                    builder.set("entryName", getEntryName(fieldDescriptor));
                    builder.append(
                            "<xs:element name=\"${fieldName}\">" +
                                    "<xs:complexType>" +
                                        "<xs:sequence>" +
                                            "<xs:element name=\"${entryName}\" type=\"${fieldType}\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>" +
                                        "</xs:sequence>" +
                                    "</xs:complexType>" +
                                "</xs:element>");

                    // builder.append("<${xs}element name=\"${fieldName}\" type=\"${fieldType}\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>");
                }
                else
                    builder.append("<${xs}element name=\"${fieldName}\" type=\"${fieldType}\"/>");
            }
            builder.append("</${xs}all>");
            builder.append("</${xs}complexType>");
        }
        return builder.toString();
    }

    private String buildXSDMessageElement(Descriptors.Descriptor type, Set<Descriptors.Descriptor> extraFields, String elementName)
    {
        Builder builder = new Builder();
        builder.set("elementName", elementName);
        builder.set("typeName", type.getFullName());

        if (extraFields == null || extraFields.isEmpty())
            builder.append("<${xs}element name=\"${elementName}\" type=\"${tns}${typeName}\"/>\n");
        else {
            builder.set("paramName", type.getName());
            builder.append("<${xs}element name=\"${elementName}\">\n");
            builder.append("<${xs}complexType>\n");
            builder.append("<${xs}all>\n");
            builder.append("<${xs}element name=\"${paramName}\" type=\"${tns}${typeName}\"/>\n");

            for (Descriptors.Descriptor e : extraFields) {
                builder.set("paramName", e.getName());
                builder.set("paramType", e.getFullName());
                builder.append("<${xs}element name=\"${paramName}\" type=\"${tns}${paramType}\"/>\n");
            }
            builder.append("</${xs}all>\n");
            builder.append("</${xs}complexType>\n");
            builder.append("</${xs}element>\n");
        }
        return builder.toString();
    }

    private String buildXSDMessageElements()
    {
        Builder builder = new Builder();

        if (useMethodSpecificMessages) {
            for (Descriptors.ServiceDescriptor serviceDescriptor : serviceDescriptors) {
                for (Descriptors.MethodDescriptor methodDescriptor : serviceDescriptor.getMethods()) {
                    builder.appendLiteral(buildXSDMessageElement(methodDescriptor.getInputType(), extraInput, methodDescriptor.getFullName() + RequestSuffix));
                    builder.appendLiteral(buildXSDMessageElement(methodDescriptor.getOutputType(), extraOutput, methodDescriptor.getFullName() + ResponseSuffix));
                }
            }

            builder.appendLiteral(buildXSDMessageElement(faultType, null, faultType.getFullName()));
        }
        else {
            for (Descriptors.Descriptor e : messageTypes)
                builder.appendLiteral(buildXSDMessageElement(e, null, e.getFullName()));
        }
        return builder.toString();
    }

    private String buildXSDTypes(Set<Descriptors.Descriptor> types)
    {
        Builder builder = new Builder();

        for (Descriptors.Descriptor descriptor : types) {
            builder.appendLiteral(buildXSDType(descriptor));
            builder.appendLiteral("\n");
        }

        return builder.toString();
    }

    private String buildTypesSection()
    {
        Builder builder = new Builder();
        builder.append("<${wsdl}types>\n");
        builder.appendLiteral(buildInlineXmlSchema());
        builder.append("</${wsdl}types>\n");
        return builder.toString();
    }

    private String buildMessagesSection()
    {
        Builder builder = new Builder();

        if (useMethodSpecificMessages) {
            for (Descriptors.ServiceDescriptor serviceDescriptor : serviceDescriptors) {
                for (Descriptors.MethodDescriptor methodDescriptor : serviceDescriptor.getMethods()) {
                    // input message
                    builder.set("messageName", methodDescriptor.getFullName() + RequestSuffix);
                    builder.set("paramType", methodDescriptor.getFullName() + RequestSuffix);
                    builder.set("paramName", methodDescriptor.getInputType().getName());

                    builder.append("<${wsdl}message name=\"${messageName}\">");
                    builder.append("<${wsdl}part name=\"${paramName}\" element=\"${tns}${paramType}\"/>");
                    builder.append("</${wsdl}message>\n");

                    // output message
                    builder.set("messageName", methodDescriptor.getFullName() + ResponseSuffix);
                    builder.set("paramType", methodDescriptor.getFullName() + ResponseSuffix);
                    builder.set("paramName", methodDescriptor.getOutputType().getName());

                    builder.append("<${wsdl}message name=\"${messageName}\">");
                    builder.append("<${wsdl}part name=\"${paramName}\" element=\"${tns}${paramType}\"/>");
                    builder.append("</${wsdl}message>\n");
                }
            }

            // fault message
            builder.set("messageName", faultType.getFullName());
            builder.set("paramType", faultType.getFullName());
            builder.set("paramName", faultType.getName());

            builder.append("<${wsdl}message name=\"${messageName}\">");
            builder.append("<${wsdl}part name=\"${paramName}\" element=\"${tns}${paramType}\"/>");
            builder.append("</${wsdl}message>\n");
        }
        else {
            for (Descriptors.Descriptor messageType : messageTypes) {
                builder.set("messageName", messageType.getFullName());
                builder.set("paramType", messageType.getFullName());
                builder.set("paramName", messageType.getName());

                builder.append("<${wsdl}message name=\"${messageName}\">");
                builder.append("<${wsdl}part name=\"${paramName}\" element=\"${tns}${paramType}\"/>");
                builder.append("</${wsdl}message>\n");
            }
        }

        return builder.toString();
    }


    private void markTypesRecursively(Descriptors.Descriptor descriptor,
                                      Set<Descriptors.Descriptor> set)
    {
        if (!set.add(descriptor))
            return;

        for (Descriptors.FieldDescriptor fieldDescriptor : descriptor.getFields()) {
            switch (fieldDescriptor.getType()) {
                case DOUBLE:
                case FLOAT:
                case INT64:
                case UINT64:
                case INT32:
                case FIXED64:
                case FIXED32:
                case BOOL:
                case STRING:
                case BYTES:
                case UINT32:
                case SFIXED32:
                case SFIXED64:
                case SINT32:
                case SINT64:
                    break;
                case ENUM:
                case GROUP:
                case MESSAGE:
                    final Descriptors.Descriptor messageType = fieldDescriptor.getMessageType();
                    markTypesRecursively(messageType, set);
                    break;
            }
        }
    }

    private String buildPortsSection(Descriptors.ServiceDescriptor serviceDescriptor)
    {
        Builder builder = new Builder()
                .set("serviceName", serviceDescriptor.getName());

        serviceDescriptor.getMethods();

        builder.append("<${wsdl}portType name=\"${serviceName}\">\n");
        for (Descriptors.MethodDescriptor methodDescriptor : serviceDescriptor.getMethods()) {

            final XmlCall.ExecType execType = methodDescriptor.getOptions().getExtension(XmlCall.execType);
            builder.set("methodName", methodDescriptor.getFullName());
            builder.append("<${wsdl}operation name=\"${methodName}\">");

            switch (execType) {

                case UNKNOWN:
                case UNRECOGNIZED:
                    throw new RuntimeException("Unhandled execType: " + execType.ordinal());

                case QUERY:
                case INVOKE:
                    if (useMethodSpecificMessages) {
                        builder.set("inputType", methodDescriptor.getFullName() + RequestSuffix);
                        builder.set("outputType", methodDescriptor.getFullName() + ResponseSuffix);
                        builder.set("faultType", faultType.getFullName());
                    } else {
                        builder.set("inputType", methodDescriptor.getInputType().getFullName());
                        builder.set("outputType", methodDescriptor.getOutputType().getFullName());
                        builder.set("faultType", faultType.getFullName());
                    }

                    builder.append("<${wsdl}input message=\"${tns}${inputType}\"/>");
                    builder.append("<${wsdl}output message=\"${tns}${outputType}\"/>");
                    builder.append("<${wsdl}fault name=\"fault\" message=\"${tns}${faultType}\"/>");
                    break;
                case EVENT:
                    if (useMethodSpecificMessages)
                        builder.set("outputType", methodDescriptor.getFullName());
                    else
                        builder.set("outputType", methodDescriptor.getOutputType().getFullName());
                    builder.append("<${wsdl}output message=\"${tns}${outputType}\"/>");
                    break;
            }
            builder.append("</${wsdl}operation>\n");
        }
        builder.append("</${wsdl}portType>\n");
        return builder.toString();
    }

    private String buildSoap(String soapAddress, String soapTransportSchema)
    {
        // for JMSL
        //   - soapAddress: jms:topic:test.cxf.jmstransport.topic
        //   - soapTransportSchema: http://www.w3.org/2010/soapjms/
        // for HTTP:
        //    - soapTransportSchema: http://schemas.xmlsoap.org/soap/http
        //
        Builder builder = new Builder();

        for (Descriptors.ServiceDescriptor serviceDescriptor : serviceDescriptors) {
            builder.set("bindingName", serviceDescriptor.getName() + "Binding");
            builder.set("portType", serviceDescriptor.getName());
            builder.set("soapTransport", soapTransportSchema);
            builder.set("soapAddress", soapAddress);

            builder.append("<${wsdl}binding name=\"${bindingName}\" type=\"${tns}${portType}\">\n");
            builder.append("<${soap}binding style=\"document\" transport=\"${soapTransport}\"/>\n");

            for (Descriptors.MethodDescriptor methodDescriptor : serviceDescriptor.getMethods()) {
                final XmlCall.ExecType execType = methodDescriptor.getOptions().getExtension(XmlCall.execType);

                builder.set("methodName", methodDescriptor.getFullName());
                builder.append("<${wsdl}operation name=\"${methodName}\">");
                builder.append("<${soap}operation soapAction=\"\"/>");

                switch (execType) {
                    case UNKNOWN:
                    case UNRECOGNIZED:
                        break;
                    case QUERY:
                    case INVOKE:
                        builder.append("<${wsdl}input>");
                        builder.append("<${soap}body use=\"literal\"/>");
                        builder.append("</${wsdl}input>");

                        builder.append("<${wsdl}output>");
                        builder.append("<${soap}body use=\"literal\"/>");
                        builder.append("</${wsdl}output>");

                        builder.append("<${wsdl}fault name=\"fault\">");
                        builder.append("<${soap}fault name=\"fault\" use=\"literal\"/>");
                        builder.append("</${wsdl}fault>");
                        break;
                    case EVENT:
                        builder.append("<${wsdl}output>");
                        builder.append("<${soap}body use=\"literal\"/>");
                        builder.append("</${wsdl}output>");
                        break;
                }
                builder.append("</${wsdl}operation>\n");
            }
            builder.append("</${wsdl}binding>\n");
        }

        /* services */
        builder.append("<${wsdl}service name=\"${serviceName}\">\n");
        for (Descriptors.ServiceDescriptor serviceDescriptor : serviceDescriptors) {
            builder.set("bindingName", serviceDescriptor.getFullName() + "Binding");
            builder.set("portName", serviceDescriptor.getName() + "Port");

            builder.append("<${wsdl}port binding=\"${tns}${bindingName}\" name=\"${portName}\">");
            builder.append("<soap:address location=\"${soapAddress}\"/>");
            builder.append("</${wsdl}port>\n");
        }
        builder.append("</${wsdl}service>\n");
        return builder.toString();
    }

    private String buildInlineXmlSchema()
    {
        Builder builder = new Builder();

        final String header = "<${xs}schema\n" +
                " xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" +
                " xmlns:tns=\"${namespace}\"\n" +
                " targetNamespace=\"${namespace}\"\n" +
                " elementFormDefault=\"qualified\">\n";

        final String footer = "</${xs}schema>\n";

        return builder.append(header)
                .appendLiteral(buildXSDTypes(this.regularTypes))
                .appendLiteral(buildXSDMessageElements())
                .append(footer)
                .toString();
    }

    private void buildXmSchemaItem(Descriptors.Descriptor elementType,
                               Set<Descriptors.Descriptor> extraFields,
                               String elementName,
                               BiConsumer<String, String> continuation)
    {
        Set<Descriptors.Descriptor> descriptors = new HashSet<>();
        final String header = "<${xs}schema\n" +
                " xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" +
                " xmlns:tns=\"${namespace}\"\n" +
                " targetNamespace=\"${namespace}\"\n" +
                " elementFormDefault=\"qualified\">\n";

        final String footer = "</${xs}schema>\n";
        Builder builder = new Builder();
        builder.set("namespace", namespace + elementType.getFullName());

        builder.append(header);

        descriptors.clear();
        markTypesRecursively(elementType, descriptors);

        if (extraFields != null) {
            for (Descriptors.Descriptor e : extraFields) {
                markTypesRecursively(e, descriptors);
            }
        }
        builder.appendLiteral(buildXSDTypes(descriptors));
        builder.appendLiteral(buildXSDMessageElement(elementType, extraFields, elementName));

        continuation.accept(elementName, builder.toString());
    }

    public void buildXmlSchema(BiConsumer<String, String> continuation)
    {

        if (useMethodSpecificMessages) {
            for (Descriptors.ServiceDescriptor serviceDescriptor : serviceDescriptors) {
                for (Descriptors.MethodDescriptor methodDescriptor : serviceDescriptor.getMethods()) {
                    buildXmSchemaItem(methodDescriptor.getInputType(), extraInput, methodDescriptor.getFullName() + RequestSuffix, continuation);
                    buildXmSchemaItem(methodDescriptor.getOutputType(), extraOutput, methodDescriptor.getFullName() + ResponseSuffix, continuation);
                }
            }

            buildXmSchemaItem(faultType, null, faultType.getFullName(), continuation);
        }
        else {
            for (Descriptors.Descriptor e : messageTypes)
                buildXmSchemaItem(e, null, e.getFullName(), continuation);
        }
    }

    public String buildWSDL(String soapAddress, String soapTransportSchema)
    {
        Builder builder = new Builder();

        builder.append(xmlHeader);
        builder.append(definitionsBegin);

        builder.appendLiteral(buildTypesSection());
        builder.appendLiteral(buildMessagesSection());

        for (Descriptors.ServiceDescriptor serviceDescriptor : serviceDescriptors) {
            builder.appendLiteral(buildPortsSection(serviceDescriptor));
        }

        builder.appendLiteral(buildSoap(soapAddress, soapTransportSchema));
        builder.append(definitionsEnd);

        return builder.toString();
    }
}

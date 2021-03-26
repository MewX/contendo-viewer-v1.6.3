package org.apache.xerces.jaxp.validation;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.parsers.SAXParser;
import org.apache.xerces.parsers.XML11Configuration;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParseException;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.Serializer;
import org.apache.xml.serialize.SerializerFactory;
import org.xml.sax.SAXException;

final class StreamValidatorHelper implements ValidatorHelper {
  private static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
  
  private static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  private static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
  
  private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
  
  private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  private SoftReference fConfiguration = new SoftReference(null);
  
  private final XMLSchemaValidator fSchemaValidator;
  
  private final XMLSchemaValidatorComponentManager fComponentManager;
  
  private SoftReference fParser = new SoftReference(null);
  
  private SerializerFactory fSerializerFactory;
  
  public StreamValidatorHelper(XMLSchemaValidatorComponentManager paramXMLSchemaValidatorComponentManager) {
    this.fComponentManager = paramXMLSchemaValidatorComponentManager;
    this.fSchemaValidator = (XMLSchemaValidator)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validator/schema");
  }
  
  public void validate(Source paramSource, Result paramResult) throws SAXException, IOException {
    if (paramResult instanceof StreamResult || paramResult == null) {
      StreamSource streamSource = (StreamSource)paramSource;
      StreamResult streamResult = (StreamResult)paramResult;
      XMLInputSource xMLInputSource = new XMLInputSource(streamSource.getPublicId(), streamSource.getSystemId(), null);
      xMLInputSource.setByteStream(streamSource.getInputStream());
      xMLInputSource.setCharacterStream(streamSource.getReader());
      boolean bool = false;
      XMLParserConfiguration xMLParserConfiguration = this.fConfiguration.get();
      if (xMLParserConfiguration == null) {
        xMLParserConfiguration = initialize();
        bool = true;
      } else if (this.fComponentManager.getFeature("http://apache.org/xml/features/internal/parser-settings")) {
        xMLParserConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver"));
        xMLParserConfiguration.setProperty("http://apache.org/xml/properties/internal/error-handler", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-handler"));
        xMLParserConfiguration.setProperty("http://apache.org/xml/properties/security-manager", this.fComponentManager.getProperty("http://apache.org/xml/properties/security-manager"));
      } 
      this.fComponentManager.reset();
      if (streamResult != null) {
        Serializer serializer;
        if (this.fSerializerFactory == null)
          this.fSerializerFactory = SerializerFactory.getSerializerFactory("xml"); 
        if (streamResult.getWriter() != null) {
          serializer = this.fSerializerFactory.makeSerializer(streamResult.getWriter(), new OutputFormat());
        } else if (streamResult.getOutputStream() != null) {
          serializer = this.fSerializerFactory.makeSerializer(streamResult.getOutputStream(), new OutputFormat());
        } else if (streamResult.getSystemId() != null) {
          String str = streamResult.getSystemId();
          OutputStream outputStream = XMLEntityManager.createOutputStream(str);
          serializer = this.fSerializerFactory.makeSerializer(outputStream, new OutputFormat());
        } else {
          throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(this.fComponentManager.getLocale(), "StreamResultNotInitialized", null));
        } 
        SAXParser sAXParser = this.fParser.get();
        if (bool || sAXParser == null) {
          sAXParser = new SAXParser(xMLParserConfiguration);
          this.fParser = new SoftReference(sAXParser);
        } else {
          sAXParser.reset();
        } 
        xMLParserConfiguration.setDocumentHandler((XMLDocumentHandler)this.fSchemaValidator);
        this.fSchemaValidator.setDocumentHandler((XMLDocumentHandler)sAXParser);
        sAXParser.setContentHandler(serializer.asContentHandler());
      } else {
        this.fSchemaValidator.setDocumentHandler(null);
      } 
      try {
        xMLParserConfiguration.parse(xMLInputSource);
      } catch (XMLParseException xMLParseException) {
        throw Util.toSAXParseException(xMLParseException);
      } catch (XNIException xNIException) {
        throw Util.toSAXException(xNIException);
      } finally {
        this.fSchemaValidator.setDocumentHandler(null);
      } 
      return;
    } 
    throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(this.fComponentManager.getLocale(), "SourceResultMismatch", new Object[] { paramSource.getClass().getName(), paramResult.getClass().getName() }));
  }
  
  private XMLParserConfiguration initialize() {
    XML11Configuration xML11Configuration = new XML11Configuration();
    xML11Configuration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver"));
    xML11Configuration.setProperty("http://apache.org/xml/properties/internal/error-handler", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-handler"));
    XMLErrorReporter xMLErrorReporter = (XMLErrorReporter)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    xML11Configuration.setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter);
    if (xMLErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
      XMLMessageFormatter xMLMessageFormatter = new XMLMessageFormatter();
      xMLErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", (MessageFormatter)xMLMessageFormatter);
      xMLErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", (MessageFormatter)xMLMessageFormatter);
    } 
    xML11Configuration.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table"));
    xML11Configuration.setProperty("http://apache.org/xml/properties/internal/validation-manager", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager"));
    xML11Configuration.setProperty("http://apache.org/xml/properties/security-manager", this.fComponentManager.getProperty("http://apache.org/xml/properties/security-manager"));
    xML11Configuration.setDocumentHandler((XMLDocumentHandler)this.fSchemaValidator);
    xML11Configuration.setDTDHandler(null);
    xML11Configuration.setDTDContentModelHandler(null);
    this.fConfiguration = new SoftReference(xML11Configuration);
    return (XMLParserConfiguration)xML11Configuration;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/StreamValidatorHelper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
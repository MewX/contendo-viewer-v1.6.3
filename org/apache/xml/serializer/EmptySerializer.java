/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.Transformer;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EmptySerializer
/*     */   implements SerializationHandler
/*     */ {
/*     */   protected static final String ERR = "EmptySerializer method not over-ridden";
/*     */   
/*     */   private static void throwUnimplementedException() {}
/*     */   
/*     */   public ContentHandler asContentHandler() throws IOException {
/*  67 */     throwUnimplementedException();
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContentHandler(ContentHandler ch) {
/*  75 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  82 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Properties getOutputFormat() {
/*  89 */     throwUnimplementedException();
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream getOutputStream() {
/*  97 */     throwUnimplementedException();
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Writer getWriter() {
/* 105 */     throwUnimplementedException();
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset() {
/* 113 */     throwUnimplementedException();
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void serialize(Node node) throws IOException {
/* 121 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCdataSectionElements(Vector URI_and_localNames) {
/* 128 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setEscaping(boolean escape) throws SAXException {
/* 135 */     throwUnimplementedException();
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndent(boolean indent) {
/* 143 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndentAmount(int spaces) {
/* 150 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutputFormat(Properties format) {
/* 157 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutputStream(OutputStream output) {
/* 164 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(String version) {
/* 171 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWriter(Writer writer) {
/* 178 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransformer(Transformer transformer) {
/* 185 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transformer getTransformer() {
/* 192 */     throwUnimplementedException();
/* 193 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flushPending() throws SAXException {
/* 200 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(String uri, String localName, String rawName, String type, String value) throws SAXException {
/* 213 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttributes(Attributes atts) throws SAXException {
/* 220 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(String name, String value) {
/* 227 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(String chars) throws SAXException {
/* 235 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String elemName) throws SAXException {
/* 242 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument() throws SAXException {
/* 249 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String uri, String localName, String qName) throws SAXException {
/* 257 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String qName) throws SAXException {
/* 264 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void namespaceAfterStartElement(String uri, String prefix) throws SAXException {
/* 272 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean startPrefixMapping(String prefix, String uri, boolean shouldFlush) throws SAXException {
/* 283 */     throwUnimplementedException();
/* 284 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void entityReference(String entityName) throws SAXException {
/* 291 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamespaceMappings getNamespaceMappings() {
/* 298 */     throwUnimplementedException();
/* 299 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrefix(String uri) {
/* 306 */     throwUnimplementedException();
/* 307 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI(String name, boolean isElement) {
/* 314 */     throwUnimplementedException();
/* 315 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURIFromPrefix(String prefix) {
/* 322 */     throwUnimplementedException();
/* 323 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentLocator(Locator arg0) {
/* 330 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() throws SAXException {
/* 337 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPrefixMapping(String arg0, String arg1) throws SAXException {
/* 345 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPrefixMapping(String arg0) throws SAXException {
/* 352 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
/* 364 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String arg0, String arg1, String arg2) throws SAXException {
/* 372 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
/* 379 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
/* 387 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String arg0, String arg1) throws SAXException {
/* 395 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skippedEntity(String arg0) throws SAXException {
/* 402 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(String comment) throws SAXException {
/* 409 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDTD(String arg0, String arg1, String arg2) throws SAXException {
/* 417 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDTD() throws SAXException {
/* 424 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startEntity(String arg0) throws SAXException {
/* 431 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endEntity(String arg0) throws SAXException {
/* 438 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startCDATA() throws SAXException {
/* 445 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCDATA() throws SAXException {
/* 452 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(char[] arg0, int arg1, int arg2) throws SAXException {
/* 459 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDoctypePublic() {
/* 466 */     throwUnimplementedException();
/* 467 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDoctypeSystem() {
/* 474 */     throwUnimplementedException();
/* 475 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncoding() {
/* 482 */     throwUnimplementedException();
/* 483 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIndent() {
/* 490 */     throwUnimplementedException();
/* 491 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndentAmount() {
/* 498 */     throwUnimplementedException();
/* 499 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMediaType() {
/* 506 */     throwUnimplementedException();
/* 507 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getOmitXMLDeclaration() {
/* 514 */     throwUnimplementedException();
/* 515 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStandalone() {
/* 522 */     throwUnimplementedException();
/* 523 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersion() {
/* 530 */     throwUnimplementedException();
/* 531 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCdataSectionElements(Hashtable h) throws Exception {
/* 538 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoctype(String system, String pub) {
/* 545 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoctypePublic(String doctype) {
/* 552 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoctypeSystem(String doctype) {
/* 559 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(String encoding) {
/* 566 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMediaType(String mediatype) {
/* 573 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOmitXMLDeclaration(boolean b) {
/* 580 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStandalone(String standalone) {
/* 587 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void elementDecl(String arg0, String arg1) throws SAXException {
/* 594 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attributeDecl(String arg0, String arg1, String arg2, String arg3, String arg4) throws SAXException {
/* 607 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalEntityDecl(String arg0, String arg1) throws SAXException {
/* 615 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void externalEntityDecl(String arg0, String arg1, String arg2) throws SAXException {
/* 623 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void warning(SAXParseException arg0) throws SAXException {
/* 630 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(SAXParseException arg0) throws SAXException {
/* 637 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatalError(SAXParseException arg0) throws SAXException {
/* 644 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DOMSerializer asDOMSerializer() throws IOException {
/* 651 */     throwUnimplementedException();
/* 652 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNamespaceMappings(NamespaceMappings mappings) {
/* 659 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSourceLocator(SourceLocator locator) {
/* 667 */     throwUnimplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUniqueAttribute(String name, String value, int flags) throws SAXException {
/* 676 */     throwUnimplementedException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/EmptySerializer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
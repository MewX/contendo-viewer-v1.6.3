/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.util.HashMap;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.batik.util.XMLResourceDescriptor;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ import org.w3c.dom.svg.SVGDocument;
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
/*     */ public class XMLInputHandler
/*     */   implements SquiggleInputHandler
/*     */ {
/*  65 */   public static final String[] XVG_MIME_TYPES = new String[] { "image/xml+xsl+svg" };
/*     */ 
/*     */   
/*  68 */   public static final String[] XVG_FILE_EXTENSIONS = new String[] { ".xml", ".xsl" };
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ERROR_NO_XML_STYLESHEET_PROCESSING_INSTRUCTION = "XMLInputHandler.error.no.xml.stylesheet.processing.instruction";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ERROR_TRANSFORM_OUTPUT_NOT_SVG = "XMLInputHandler.error.transform.output.not.svg";
/*     */ 
/*     */   
/*     */   public static final String ERROR_TRANSFORM_PRODUCED_NO_CONTENT = "XMLInputHandler.error.transform.produced.no.content";
/*     */ 
/*     */   
/*     */   public static final String ERROR_TRANSFORM_OUTPUT_WRONG_NS = "XMLInputHandler.error.transform.output.wrong.ns";
/*     */ 
/*     */   
/*     */   public static final String ERROR_RESULT_GENERATED_EXCEPTION = "XMLInputHandler.error.result.generated.exception";
/*     */ 
/*     */   
/*     */   public static final String XSL_PROCESSING_INSTRUCTION_TYPE = "text/xsl";
/*     */ 
/*     */   
/*     */   public static final String PSEUDO_ATTRIBUTE_TYPE = "type";
/*     */ 
/*     */   
/*     */   public static final String PSEUDO_ATTRIBUTE_HREF = "href";
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getHandledMimeTypes() {
/*  99 */     return XVG_MIME_TYPES;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getHandledExtensions() {
/* 106 */     return XVG_FILE_EXTENSIONS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 113 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File f) {
/* 120 */     return (f.isFile() && accept(f.getPath()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(ParsedURL purl) {
/* 127 */     if (purl == null) {
/* 128 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     String path = purl.getPath();
/* 136 */     return accept(path);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(String path) {
/* 144 */     if (path == null) {
/* 145 */       return false;
/*     */     }
/*     */     
/* 148 */     for (String XVG_FILE_EXTENSION : XVG_FILE_EXTENSIONS) {
/* 149 */       if (path.endsWith(XVG_FILE_EXTENSION)) {
/* 150 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handle(ParsedURL purl, JSVGViewerFrame svgViewerFrame) throws Exception {
/* 161 */     String uri = purl.toString();
/*     */     
/* 163 */     TransformerFactory tFactory = TransformerFactory.newInstance();
/*     */ 
/*     */ 
/*     */     
/* 167 */     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/* 168 */     dbf.setValidating(false);
/* 169 */     dbf.setNamespaceAware(true);
/*     */     
/* 171 */     DocumentBuilder db = dbf.newDocumentBuilder();
/*     */     
/* 173 */     Document inDoc = db.parse(uri);
/*     */ 
/*     */     
/* 176 */     String xslStyleSheetURI = extractXSLProcessingInstruction(inDoc);
/*     */ 
/*     */     
/* 179 */     if (xslStyleSheetURI == null)
/*     */     {
/* 181 */       xslStyleSheetURI = uri;
/*     */     }
/*     */     
/* 184 */     ParsedURL parsedXSLStyleSheetURI = new ParsedURL(uri, xslStyleSheetURI);
/*     */ 
/*     */     
/* 187 */     Transformer transformer = tFactory.newTransformer(new StreamSource(parsedXSLStyleSheetURI.toString()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     transformer.setURIResolver(new DocumentURIResolver(parsedXSLStyleSheetURI.toString()));
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
/* 209 */     StringWriter sw = new StringWriter();
/* 210 */     StreamResult result = new StreamResult(sw);
/* 211 */     transformer.transform(new DOMSource(inDoc), result);
/*     */     
/* 213 */     sw.flush();
/* 214 */     sw.close();
/*     */     
/* 216 */     String parser = XMLResourceDescriptor.getXMLParserClassName();
/* 217 */     SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
/* 218 */     SVGDocument outDoc = null;
/*     */     
/*     */     try {
/* 221 */       outDoc = f.createSVGDocument(uri, new StringReader(sw.toString()));
/*     */     }
/* 223 */     catch (Exception e) {
/* 224 */       System.err.println("======================================");
/* 225 */       System.err.println(sw.toString());
/* 226 */       System.err.println("======================================");
/*     */       
/* 228 */       throw new IllegalArgumentException(Resources.getString("XMLInputHandler.error.result.generated.exception"));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     svgViewerFrame.getJSVGCanvas().setSVGDocument(outDoc);
/* 236 */     svgViewerFrame.setSVGDocument(outDoc, uri, outDoc.getTitle());
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
/*     */   protected void checkAndPatch(Document doc) {
/* 249 */     Element root = doc.getDocumentElement();
/* 250 */     Node realRoot = root.getFirstChild();
/* 251 */     String svgNS = "http://www.w3.org/2000/svg";
/*     */     
/* 253 */     if (realRoot == null) {
/* 254 */       throw new IllegalArgumentException(Resources.getString("XMLInputHandler.error.transform.produced.no.content"));
/*     */     }
/*     */ 
/*     */     
/* 258 */     if (realRoot.getNodeType() != 1 || !"svg".equals(realRoot.getLocalName()))
/*     */     {
/*     */       
/* 261 */       throw new IllegalArgumentException(Resources.getString("XMLInputHandler.error.transform.output.not.svg"));
/*     */     }
/*     */ 
/*     */     
/* 265 */     if (!svgNS.equals(realRoot.getNamespaceURI())) {
/* 266 */       throw new IllegalArgumentException(Resources.getString("XMLInputHandler.error.transform.output.wrong.ns"));
/*     */     }
/*     */ 
/*     */     
/* 270 */     Node child = realRoot.getFirstChild();
/* 271 */     while (child != null) {
/* 272 */       root.appendChild(child);
/* 273 */       child = realRoot.getFirstChild();
/*     */     } 
/*     */     
/* 276 */     NamedNodeMap attrs = realRoot.getAttributes();
/* 277 */     int n = attrs.getLength();
/* 278 */     for (int i = 0; i < n; i++) {
/* 279 */       root.setAttributeNode((Attr)attrs.item(i));
/*     */     }
/*     */     
/* 282 */     root.removeChild(realRoot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String extractXSLProcessingInstruction(Document doc) {
/* 290 */     Node child = doc.getFirstChild();
/* 291 */     while (child != null) {
/* 292 */       if (child.getNodeType() == 7) {
/* 293 */         ProcessingInstruction pi = (ProcessingInstruction)child;
/*     */ 
/*     */         
/* 296 */         HashMap<String, String> table = new HashMap<String, String>();
/* 297 */         DOMUtilities.parseStyleSheetPIData(pi.getData(), table);
/*     */ 
/*     */         
/* 300 */         Object type = table.get("type");
/* 301 */         if ("text/xsl".equals(type)) {
/* 302 */           Object href = table.get("href");
/* 303 */           if (href != null) {
/* 304 */             return href.toString();
/*     */           }
/* 306 */           return null;
/*     */         } 
/*     */       } 
/*     */       
/* 310 */       child = child.getNextSibling();
/*     */     } 
/*     */     
/* 313 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public class DocumentURIResolver
/*     */     implements URIResolver
/*     */   {
/*     */     String documentURI;
/*     */ 
/*     */     
/*     */     public DocumentURIResolver(String documentURI) {
/* 324 */       this.documentURI = documentURI;
/*     */     }
/*     */     
/*     */     public Source resolve(String href, String base) {
/* 328 */       if (base == null || "".equals(base)) {
/* 329 */         base = this.documentURI;
/*     */       }
/*     */       
/* 332 */       ParsedURL purl = new ParsedURL(base, href);
/*     */       
/* 334 */       return new StreamSource(purl.toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/XMLInputHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
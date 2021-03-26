/*     */ package org.apache.xalan.client;
/*     */ 
/*     */ import java.applet.Applet;
/*     */ import java.awt.Graphics;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import javax.xml.transform.Templates;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.xalan.res.XSLMessages;
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
/*     */ public class XSLTProcessorApplet
/*     */   extends Applet
/*     */ {
/*  61 */   TransformerFactory m_tfactory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String m_styleURL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String m_documentURL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   private final String PARAM_styleURL = "styleURL";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private final String PARAM_documentURL = "documentURL";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   private String m_styleURLOfCached = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   private String m_documentURLOfCached = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   private URL m_codeBase = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   private String m_treeURL = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   private URL m_documentBase = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   private transient Thread m_callThread = null;
/*     */ 
/*     */ 
/*     */   
/* 125 */   private transient TrustedAgent m_trustedAgent = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   private transient Thread m_trustedWorker = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   private transient String m_htmlText = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   private transient String m_sourceText = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   private transient String m_nameOfIDAttrOfElemToModify = null;
/*     */ 
/*     */ 
/*     */   
/* 149 */   private transient String m_elemIdToModify = null;
/*     */ 
/*     */ 
/*     */   
/* 153 */   private transient String m_attrNameToSet = null;
/*     */ 
/*     */ 
/*     */   
/* 157 */   private transient String m_attrValueToSet = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private Enumeration m_keys;
/*     */ 
/*     */ 
/*     */   
/*     */   transient Hashtable m_parameters;
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAppletInfo() {
/* 170 */     return "Name: XSLTProcessorApplet\r\nAuthor: Scott Boag";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[][] getParameterInfo() {
/* 181 */     String[][] info = { { "styleURL", "String", "URL to an XSL stylesheet" }, { "documentURL", "String", "URL to an XML document" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     return info;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 205 */     String param = getParameter("styleURL");
/*     */ 
/*     */     
/* 208 */     this.m_parameters = new Hashtable();
/*     */     
/* 210 */     if (param != null) {
/* 211 */       setStyleURL(param);
/*     */     }
/*     */ 
/*     */     
/* 215 */     param = getParameter("documentURL");
/*     */     
/* 217 */     if (param != null) {
/* 218 */       setDocumentURL(param);
/*     */     }
/* 220 */     this.m_codeBase = getCodeBase();
/* 221 */     this.m_documentBase = getDocumentBase();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     resize(320, 240);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 239 */     this.m_trustedAgent = new TrustedAgent(this);
/* 240 */     Thread currentThread = Thread.currentThread();
/* 241 */     this.m_trustedWorker = new Thread(currentThread.getThreadGroup(), this.m_trustedAgent);
/*     */     
/* 243 */     this.m_trustedWorker.start();
/*     */ 
/*     */     
/* 246 */     try { this.m_tfactory = TransformerFactory.newInstance();
/* 247 */       showStatus("Causing Transformer and Parser to Load and JIT...");
/*     */ 
/*     */       
/* 250 */       StringReader xmlbuf = new StringReader("<?xml version='1.0'?><foo/>");
/* 251 */       StringReader xslbuf = new StringReader("<?xml version='1.0'?><xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform' version='1.0'><xsl:template match='foo'><out/></xsl:template></xsl:stylesheet>");
/*     */       
/* 253 */       PrintWriter pw = new PrintWriter(new StringWriter());
/*     */       
/* 255 */       synchronized (this.m_tfactory) {
/*     */         
/* 257 */         Templates templates = this.m_tfactory.newTemplates(new StreamSource(xslbuf));
/* 258 */         Transformer transformer = templates.newTransformer();
/* 259 */         transformer.transform(new StreamSource(xmlbuf), new StreamResult(pw));
/*     */       } 
/* 261 */       System.out.println("Primed the pump!");
/* 262 */       showStatus("Ready to go!"); } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 266 */       showStatus("Could not prime the pump!");
/* 267 */       System.out.println("Could not prime the pump!");
/* 268 */       e.printStackTrace(); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 284 */     if (null != this.m_trustedWorker) {
/*     */       
/* 286 */       this.m_trustedWorker.stop();
/*     */ 
/*     */       
/* 289 */       this.m_trustedWorker = null;
/*     */     } 
/*     */     
/* 292 */     this.m_styleURLOfCached = null;
/* 293 */     this.m_documentURLOfCached = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroy() {
/* 301 */     if (null != this.m_trustedWorker) {
/*     */       
/* 303 */       this.m_trustedWorker.stop();
/*     */ 
/*     */       
/* 306 */       this.m_trustedWorker = null;
/*     */     } 
/* 308 */     this.m_styleURLOfCached = null;
/* 309 */     this.m_documentURLOfCached = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStyleURL(String urlString) {
/* 319 */     this.m_styleURL = urlString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentURL(String urlString) {
/* 329 */     this.m_documentURL = urlString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void freeCache() {
/* 339 */     this.m_styleURLOfCached = null;
/* 340 */     this.m_documentURLOfCached = null;
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
/*     */ 
/*     */   
/*     */   public void setStyleSheetAttribute(String nameOfIDAttrOfElemToModify, String elemId, String attrName, String value) {
/* 355 */     this.m_nameOfIDAttrOfElemToModify = nameOfIDAttrOfElemToModify;
/* 356 */     this.m_elemIdToModify = elemId;
/* 357 */     this.m_attrNameToSet = attrName;
/* 358 */     this.m_attrValueToSet = value;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStylesheetParam(String key, String expr) {
/* 380 */     this.m_parameters.put(key, expr);
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
/*     */   public String escapeString(String s) {
/* 393 */     StringBuffer sb = new StringBuffer();
/* 394 */     int length = s.length();
/*     */     
/* 396 */     for (int i = 0; i < length; i++) {
/*     */       
/* 398 */       char ch = s.charAt(i);
/*     */       
/* 400 */       if ('<' == ch) {
/*     */         
/* 402 */         sb.append("&lt;");
/*     */       }
/* 404 */       else if ('>' == ch) {
/*     */         
/* 406 */         sb.append("&gt;");
/*     */       }
/* 408 */       else if ('&' == ch) {
/*     */         
/* 410 */         sb.append("&amp;");
/*     */       }
/* 412 */       else if ('?' <= ch && ch < '?') {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 417 */         if (i + 1 >= length)
/*     */         {
/* 419 */           throw new RuntimeException(XSLMessages.createMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(ch) }));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 428 */         int next = s.charAt(++i);
/*     */         
/* 430 */         if (56320 > next || next >= 57344) {
/* 431 */           throw new RuntimeException(XSLMessages.createMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(ch) + " " + Integer.toHexString(next) }));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 439 */         next = (ch - 55296 << 10) + next - 56320 + 65536;
/*     */         
/* 441 */         sb.append("&#x");
/* 442 */         sb.append(Integer.toHexString(next));
/* 443 */         sb.append(";");
/*     */       }
/*     */       else {
/*     */         
/* 447 */         sb.append(ch);
/*     */       } 
/*     */     } 
/* 450 */     return sb.toString();
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
/*     */   public String getHtmlText() {
/* 462 */     this.m_trustedAgent.m_getData = true;
/* 463 */     this.m_callThread = Thread.currentThread();
/*     */     
/*     */     try {
/* 466 */       synchronized (this.m_callThread)
/*     */       
/* 468 */       { this.m_callThread.wait(); } 
/* 469 */     } catch (InterruptedException ie) {
/*     */ 
/*     */ 
/*     */       
/* 473 */       System.out.println(ie.getMessage());
/*     */     } 
/* 475 */     return this.m_htmlText;
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
/*     */   
/*     */   public String getTreeAsText(String treeURL) throws IOException {
/* 489 */     this.m_treeURL = treeURL;
/* 490 */     this.m_trustedAgent.m_getData = true;
/* 491 */     this.m_trustedAgent.m_getSource = true;
/* 492 */     this.m_callThread = Thread.currentThread();
/*     */     
/*     */     try {
/* 495 */       synchronized (this.m_callThread)
/*     */       
/* 497 */       { this.m_callThread.wait(); } 
/* 498 */     } catch (InterruptedException ie) {
/*     */ 
/*     */ 
/*     */       
/* 502 */       System.out.println(ie.getMessage());
/*     */     } 
/* 504 */     return this.m_sourceText;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getSource() throws TransformerException {
/* 515 */     StringWriter osw = new StringWriter();
/* 516 */     PrintWriter pw = new PrintWriter(osw, false);
/* 517 */     String text = "";
/*     */ 
/*     */     
/* 520 */     try { URL docURL = new URL(this.m_documentBase, this.m_treeURL);
/* 521 */       synchronized (this.m_tfactory)
/*     */       
/* 523 */       { Transformer transformer = this.m_tfactory.newTransformer();
/* 524 */         StreamSource source = new StreamSource(docURL.toString());
/* 525 */         StreamResult result = new StreamResult(pw);
/* 526 */         transformer.transform(source, result);
/* 527 */         text = osw.toString(); }  }
/* 528 */     catch (MalformedURLException e)
/*     */     
/*     */     { 
/*     */       
/* 532 */       e.printStackTrace();
/* 533 */       System.exit(-1); } catch (Exception any_error)
/*     */     
/*     */     { 
/*     */       
/* 537 */       any_error.printStackTrace(); }
/*     */     
/* 539 */     return text;
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
/*     */   public String getSourceTreeAsText() throws Exception {
/* 552 */     return getTreeAsText(this.m_documentURL);
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
/*     */   public String getStyleTreeAsText() throws Exception {
/* 565 */     return getTreeAsText(this.m_styleURL);
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
/*     */   public String getResultTreeAsText() throws Exception {
/* 578 */     return escapeString(getHtmlText());
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
/*     */ 
/*     */ 
/*     */   
/*     */   public String transformToHtml(String doc, String style) {
/* 594 */     if (null != doc)
/*     */     {
/* 596 */       this.m_documentURL = doc;
/*     */     }
/*     */     
/* 599 */     if (null != style)
/*     */     {
/* 601 */       this.m_styleURL = style;
/*     */     }
/*     */     
/* 604 */     return getHtmlText();
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
/*     */ 
/*     */   
/*     */   public String transformToHtml(String doc) {
/* 619 */     if (null != doc)
/*     */     {
/* 621 */       this.m_documentURL = doc;
/*     */     }
/*     */     
/* 624 */     this.m_styleURL = null;
/*     */     
/* 626 */     return getHtmlText();
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
/*     */   private String processTransformation() throws TransformerException {
/* 639 */     String htmlData = null;
/* 640 */     showStatus("Waiting for Transformer and Parser to finish loading and JITing...");
/*     */     
/* 642 */     synchronized (this.m_tfactory) {
/*     */       
/* 644 */       URL documentURL = null;
/* 645 */       URL styleURL = null;
/* 646 */       StringWriter osw = new StringWriter();
/* 647 */       PrintWriter pw = new PrintWriter(osw, false);
/* 648 */       StreamResult result = new StreamResult(pw);
/*     */       
/* 650 */       showStatus("Begin Transformation...");
/*     */ 
/*     */       
/* 653 */       try { documentURL = new URL(this.m_codeBase, this.m_documentURL);
/* 654 */         StreamSource xmlSource = new StreamSource(documentURL.toString());
/*     */         
/* 656 */         styleURL = new URL(this.m_codeBase, this.m_styleURL);
/* 657 */         StreamSource xslSource = new StreamSource(styleURL.toString());
/*     */         
/* 659 */         Transformer transformer = this.m_tfactory.newTransformer(xslSource);
/*     */         
/* 661 */         this.m_keys = this.m_parameters.keys();
/* 662 */         while (this.m_keys.hasMoreElements()) {
/* 663 */           Object key = this.m_keys.nextElement();
/* 664 */           Object expression = this.m_parameters.get(key);
/* 665 */           transformer.setParameter((String)key, expression);
/*     */         } 
/* 667 */         transformer.transform(xmlSource, result); } catch (TransformerConfigurationException tfe)
/*     */       
/*     */       { 
/*     */         
/* 671 */         tfe.printStackTrace();
/* 672 */         System.exit(-1); } catch (MalformedURLException e)
/*     */       
/*     */       { 
/*     */         
/* 676 */         e.printStackTrace();
/* 677 */         System.exit(-1); }
/*     */ 
/*     */       
/* 680 */       showStatus("Transformation Done!");
/* 681 */       htmlData = osw.toString();
/*     */     } 
/* 683 */     return htmlData;
/*     */   }
/*     */   
/*     */   class TrustedAgent implements Runnable {
/*     */     public boolean m_getData;
/*     */     public boolean m_getSource;
/*     */     private final XSLTProcessorApplet this$0;
/*     */     
/*     */     TrustedAgent(XSLTProcessorApplet this$0) {
/* 692 */       this.this$0 = this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 698 */       this.m_getData = false;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 703 */       this.m_getSource = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       while (true) {
/* 713 */         this.this$0.m_trustedWorker; Thread.yield();
/*     */         
/* 715 */         if (this.m_getData) {
/*     */ 
/*     */ 
/*     */           
/* 719 */           try { this.m_getData = false;
/* 720 */             this.this$0.m_htmlText = null;
/* 721 */             this.this$0.m_sourceText = null;
/* 722 */             if (this.m_getSource) {
/*     */               
/* 724 */               this.m_getSource = false;
/* 725 */               this.this$0.m_sourceText = this.this$0.getSource();
/*     */               continue;
/*     */             } 
/* 728 */             this.this$0.m_htmlText = this.this$0.processTransformation(); } catch (Exception e)
/*     */           
/*     */           { 
/*     */             
/* 732 */             e.printStackTrace(); }
/*     */           
/*     */           finally
/*     */           
/* 736 */           { synchronized (this.this$0.m_callThread) {
/*     */               
/* 738 */               this.this$0.m_callThread.notify();
/*     */             }  }
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/* 746 */         try { this.this$0.m_trustedWorker; Thread.sleep(50L); } catch (InterruptedException ie)
/*     */         
/*     */         { 
/*     */           
/* 750 */           ie.printStackTrace(); }
/*     */       
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/client/XSLTProcessorApplet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
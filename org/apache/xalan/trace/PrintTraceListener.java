/*     */ package org.apache.xalan.trace;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemTemplate;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.ElemTextLiteral;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.ref.DTMNodeProxy;
/*     */ import org.w3c.dom.Node;
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
/*     */ public class PrintTraceListener
/*     */   implements TraceListenerEx3
/*     */ {
/*     */   PrintWriter m_pw;
/*     */   public boolean m_traceTemplates;
/*     */   public boolean m_traceElements;
/*     */   public boolean m_traceGeneration;
/*     */   public boolean m_traceSelection;
/*     */   public boolean m_traceExtension;
/*     */   int m_indent;
/*     */   
/*     */   public PrintTraceListener(PrintWriter pw) {
/*  64 */     this.m_traceTemplates = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     this.m_traceElements = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     this.m_traceGeneration = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     this.m_traceSelection = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     this.m_traceExtension = false;
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
/* 140 */     this.m_indent = 0;
/*     */     this.m_pw = pw;
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
/*     */   public void trace(TracerEvent ev) {
/* 156 */     _trace(ev);
/*     */   }
/*     */   
/*     */   public void _trace(TracerEvent ev) {
/*     */     switch (ev.m_styleNode.getXSLToken()) {
/*     */       case 78:
/*     */         if (this.m_traceElements) {
/*     */           this.m_pw.print(ev.m_styleNode.getSystemId() + " Line #" + ev.m_styleNode.getLineNumber() + ", " + "Column #" + ev.m_styleNode.getColumnNumber() + " -- " + ev.m_styleNode.getNodeName() + ": ");
/*     */           ElemTextLiteral etl = (ElemTextLiteral)ev.m_styleNode;
/*     */           String chars = new String(etl.getChars(), 0, (etl.getChars()).length);
/*     */           this.m_pw.println("    " + chars.trim());
/*     */         } 
/*     */         return;
/*     */       case 19:
/*     */         if (this.m_traceTemplates || this.m_traceElements) {
/*     */           ElemTemplate et = (ElemTemplate)ev.m_styleNode;
/*     */           this.m_pw.print(et.getSystemId() + " Line #" + et.getLineNumber() + ", " + "Column #" + et.getColumnNumber() + ": " + et.getNodeName() + " ");
/*     */           if (null != et.getMatch())
/*     */             this.m_pw.print("match='" + et.getMatch().getPatternString() + "' "); 
/*     */           if (null != et.getName())
/*     */             this.m_pw.print("name='" + et.getName() + "' "); 
/*     */           this.m_pw.println();
/*     */         } 
/*     */         return;
/*     */     } 
/*     */     if (this.m_traceElements)
/*     */       this.m_pw.println(ev.m_styleNode.getSystemId() + " Line #" + ev.m_styleNode.getLineNumber() + ", " + "Column #" + ev.m_styleNode.getColumnNumber() + ": " + ev.m_styleNode.getNodeName()); 
/*     */   }
/*     */   
/*     */   public void traceEnd(TracerEvent ev) {}
/*     */   
/*     */   public void selected(SelectionEvent ev) throws TransformerException {
/* 188 */     if (this.m_traceSelection) {
/* 189 */       ElemTemplateElement ete = ev.m_styleNode;
/* 190 */       Node sourceNode = ev.m_sourceNode;
/*     */       
/* 192 */       SourceLocator locator = null;
/* 193 */       if (sourceNode instanceof DTMNodeProxy) {
/* 194 */         int nodeHandler = ((DTMNodeProxy)sourceNode).getDTMNodeNumber();
/* 195 */         locator = ((DTMNodeProxy)sourceNode).getDTM().getSourceLocatorFor(nodeHandler);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 200 */       if (locator != null) {
/* 201 */         this.m_pw.println("Selected source node '" + sourceNode.getNodeName() + "', at " + locator);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 207 */         this.m_pw.println("Selected source node '" + sourceNode.getNodeName() + "'");
/*     */       } 
/*     */       
/* 210 */       if (ev.m_styleNode.getLineNumber() == 0) {
/*     */ 
/*     */ 
/*     */         
/* 214 */         ElemTemplateElement parent = ete.getParentElem();
/*     */ 
/*     */         
/* 217 */         if (parent == ete.getStylesheetRoot().getDefaultRootRule()) {
/* 218 */           this.m_pw.print("(default root rule) ");
/* 219 */         } else if (parent == ete.getStylesheetRoot().getDefaultTextRule()) {
/*     */           
/* 221 */           this.m_pw.print("(default text rule) ");
/* 222 */         } else if (parent == ete.getStylesheetRoot().getDefaultRule()) {
/* 223 */           this.m_pw.print("(default rule) ");
/*     */         } 
/*     */         
/* 226 */         this.m_pw.print(ete.getNodeName() + ", " + ev.m_attributeName + "='" + ev.m_xpath.getPatternString() + "': ");
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 234 */         this.m_pw.print(ev.m_styleNode.getSystemId() + " Line #" + ev.m_styleNode.getLineNumber() + ", " + "Column #" + ev.m_styleNode.getColumnNumber() + ": " + ete.getNodeName() + ", " + ev.m_attributeName + "='" + ev.m_xpath.getPatternString() + "': ");
/*     */       } 
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
/* 250 */       if (ev.m_selection.getType() == 4) {
/* 251 */         this.m_pw.println();
/*     */         
/* 253 */         DTMIterator nl = ev.m_selection.iter();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 259 */         int currentPos = -1;
/* 260 */         currentPos = nl.getCurrentPos();
/* 261 */         nl.setShouldCacheNodes(true);
/* 262 */         DTMIterator clone = null;
/*     */ 
/*     */ 
/*     */         
/* 266 */         try { clone = nl.cloneWithReset(); } catch (CloneNotSupportedException cnse)
/*     */         
/* 268 */         { this.m_pw.println("     [Can't trace nodelist because it it threw a CloneNotSupportedException]");
/*     */           
/*     */           return; }
/*     */         
/* 272 */         int pos = clone.nextNode();
/*     */         
/* 274 */         if (-1 == pos) {
/* 275 */           this.m_pw.println("     [empty node list]");
/*     */         } else {
/* 277 */           while (-1 != pos) {
/*     */             
/* 279 */             DTM dtm = ev.m_processor.getXPathContext().getDTM(pos);
/* 280 */             this.m_pw.print("     ");
/* 281 */             this.m_pw.print(Integer.toHexString(pos));
/* 282 */             this.m_pw.print(": ");
/* 283 */             this.m_pw.println(dtm.getNodeName(pos));
/* 284 */             pos = clone.nextNode();
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 289 */         nl.runTo(-1);
/* 290 */         nl.setCurrentPos(currentPos);
/*     */       }
/*     */       else {
/*     */         
/* 294 */         this.m_pw.println(ev.m_selection.str());
/*     */       } 
/*     */     } 
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
/*     */   public void selectEnd(EndSelectionEvent ev) throws TransformerException {}
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
/*     */   public void generated(GenerateEvent ev) {
/* 321 */     if (this.m_traceGeneration) {
/*     */       String chars;
/* 323 */       switch (ev.m_eventtype) {
/*     */         
/*     */         case 1:
/* 326 */           this.m_pw.println("STARTDOCUMENT");
/*     */           break;
/*     */         case 2:
/* 329 */           this.m_pw.println("ENDDOCUMENT");
/*     */           break;
/*     */         case 3:
/* 332 */           this.m_pw.println("STARTELEMENT: " + ev.m_name);
/*     */           break;
/*     */         case 4:
/* 335 */           this.m_pw.println("ENDELEMENT: " + ev.m_name);
/*     */           break;
/*     */         
/*     */         case 5:
/* 339 */           chars = new String(ev.m_characters, ev.m_start, ev.m_length);
/*     */           
/* 341 */           this.m_pw.println("CHARACTERS: " + chars);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 10:
/* 346 */           chars = new String(ev.m_characters, ev.m_start, ev.m_length);
/*     */           
/* 348 */           this.m_pw.println("CDATA: " + chars);
/*     */           break;
/*     */         
/*     */         case 8:
/* 352 */           this.m_pw.println("COMMENT: " + ev.m_data);
/*     */           break;
/*     */         case 7:
/* 355 */           this.m_pw.println("PI: " + ev.m_name + ", " + ev.m_data);
/*     */           break;
/*     */         case 9:
/* 358 */           this.m_pw.println("ENTITYREF: " + ev.m_name);
/*     */           break;
/*     */         case 6:
/* 361 */           this.m_pw.println("IGNORABLEWHITESPACE");
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void extension(ExtensionEvent ev) {
/* 373 */     if (this.m_traceExtension)
/* 374 */       switch (ev.m_callType) {
/*     */         case 0:
/* 376 */           this.m_pw.println("EXTENSION: " + ((Class)ev.m_method).getName() + "#<init>");
/*     */           break;
/*     */         case 1:
/* 379 */           this.m_pw.println("EXTENSION: " + ((Method)ev.m_method).getDeclaringClass().getName() + "#" + ((Method)ev.m_method).getName());
/*     */           break;
/*     */         case 2:
/* 382 */           this.m_pw.println("EXTENSION: " + ((Constructor)ev.m_method).getDeclaringClass().getName() + "#<init>");
/*     */           break;
/*     */       }  
/*     */   }
/*     */   
/*     */   public void extensionEnd(ExtensionEvent ev) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/trace/PrintTraceListener.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
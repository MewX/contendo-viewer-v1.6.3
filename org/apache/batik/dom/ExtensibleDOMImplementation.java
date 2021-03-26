/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.batik.css.engine.CSSContext;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.ShorthandManager;
/*     */ import org.apache.batik.css.engine.value.ValueManager;
/*     */ import org.apache.batik.css.parser.ExtendedParser;
/*     */ import org.apache.batik.css.parser.ExtendedParserWrapper;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.apache.batik.util.Service;
/*     */ import org.apache.batik.util.XMLResourceDescriptor;
/*     */ import org.apache.batik.xml.XMLUtilities;
/*     */ import org.w3c.css.sac.Parser;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.css.DOMImplementationCSS;
/*     */ import org.w3c.dom.css.ViewCSS;
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
/*     */ public abstract class ExtensibleDOMImplementation
/*     */   extends AbstractDOMImplementation
/*     */   implements StyleSheetFactory, DOMImplementationCSS
/*     */ {
/*     */   protected DoublyIndexedTable customFactories;
/*     */   protected List customValueManagers;
/*     */   protected List customShorthandManagers;
/*     */   
/*     */   public ExtensibleDOMImplementation() {
/*  81 */     for (Object o : getDomExtensions()) {
/*  82 */       DomExtension de = (DomExtension)o;
/*  83 */       de.registerTags(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerCustomElementFactory(String namespaceURI, String localName, ElementFactory factory) {
/*  93 */     if (this.customFactories == null) {
/*  94 */       this.customFactories = new DoublyIndexedTable();
/*     */     }
/*  96 */     this.customFactories.put(namespaceURI, localName, factory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerCustomCSSValueManager(ValueManager vm) {
/* 103 */     if (this.customValueManagers == null) {
/* 104 */       this.customValueManagers = new LinkedList();
/*     */     }
/* 106 */     this.customValueManagers.add(vm);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerCustomCSSShorthandManager(ShorthandManager sm) {
/* 113 */     if (this.customShorthandManagers == null) {
/* 114 */       this.customShorthandManagers = new LinkedList();
/*     */     }
/* 116 */     this.customShorthandManagers.add(sm);
/*     */   }
/*     */ 
/*     */   
/*     */   public CSSEngine createCSSEngine(AbstractStylableDocument doc, CSSContext ctx) {
/*     */     Parser p;
/*     */     ValueManager[] vms;
/*     */     ShorthandManager[] sms;
/* 124 */     String pn = XMLResourceDescriptor.getCSSParserClassName();
/*     */     
/*     */     try {
/* 127 */       p = Class.forName(pn).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/* 128 */     } catch (ClassNotFoundException e) {
/* 129 */       throw new DOMException((short)15, formatMessage("css.parser.class", new Object[] { pn }));
/*     */     
/*     */     }
/* 132 */     catch (InstantiationException e) {
/* 133 */       throw new DOMException((short)15, formatMessage("css.parser.creation", new Object[] { pn }));
/*     */     
/*     */     }
/* 136 */     catch (IllegalAccessException e) {
/* 137 */       throw new DOMException((short)15, formatMessage("css.parser.access", new Object[] { pn }));
/*     */     
/*     */     }
/* 140 */     catch (NoSuchMethodException e) {
/* 141 */       throw new DOMException((short)15, formatMessage("css.parser.access", new Object[] { pn }));
/*     */     
/*     */     }
/* 144 */     catch (InvocationTargetException e) {
/* 145 */       throw new DOMException((short)15, formatMessage("css.parser.access", new Object[] { pn }));
/*     */     } 
/*     */ 
/*     */     
/* 149 */     ExtendedParser ep = ExtendedParserWrapper.wrap(p);
/*     */ 
/*     */     
/* 152 */     if (this.customValueManagers == null) {
/* 153 */       vms = new ValueManager[0];
/*     */     } else {
/* 155 */       vms = new ValueManager[this.customValueManagers.size()];
/* 156 */       Iterator<ValueManager> it = this.customValueManagers.iterator();
/* 157 */       int i = 0;
/* 158 */       while (it.hasNext()) {
/* 159 */         vms[i++] = it.next();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 164 */     if (this.customShorthandManagers == null) {
/* 165 */       sms = new ShorthandManager[0];
/*     */     } else {
/* 167 */       sms = new ShorthandManager[this.customShorthandManagers.size()];
/* 168 */       Iterator<ShorthandManager> it = this.customShorthandManagers.iterator();
/* 169 */       int i = 0;
/* 170 */       while (it.hasNext()) {
/* 171 */         sms[i++] = it.next();
/*     */       }
/*     */     } 
/*     */     
/* 175 */     CSSEngine result = createCSSEngine(doc, ctx, ep, vms, sms);
/* 176 */     doc.setCSSEngine(result);
/* 177 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract CSSEngine createCSSEngine(AbstractStylableDocument paramAbstractStylableDocument, CSSContext paramCSSContext, ExtendedParser paramExtendedParser, ValueManager[] paramArrayOfValueManager, ShorthandManager[] paramArrayOfShorthandManager);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract ViewCSS createViewCSS(AbstractStylableDocument paramAbstractStylableDocument);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElementNS(AbstractDocument document, String namespaceURI, String qualifiedName) {
/* 198 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/* 199 */       namespaceURI = null;
/*     */     }
/* 201 */     if (namespaceURI == null) {
/* 202 */       return new GenericElement(qualifiedName.intern(), document);
/*     */     }
/* 204 */     if (this.customFactories != null) {
/* 205 */       String name = DOMUtilities.getLocalName(qualifiedName);
/*     */       
/* 207 */       ElementFactory cef = (ElementFactory)this.customFactories.get(namespaceURI, name);
/* 208 */       if (cef != null) {
/* 209 */         return cef.create(DOMUtilities.getPrefix(qualifiedName), document);
/*     */       }
/*     */     } 
/*     */     
/* 213 */     return new GenericElementNS(namespaceURI.intern(), qualifiedName.intern(), document);
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
/*     */   public DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) {
/* 225 */     if (qualifiedName == null) {
/* 226 */       qualifiedName = "";
/*     */     }
/* 228 */     int test = XMLUtilities.testXMLQName(qualifiedName);
/* 229 */     if ((test & 0x1) == 0) {
/* 230 */       throw new DOMException((short)5, formatMessage("xml.name", new Object[] { qualifiedName }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 235 */     if ((test & 0x2) == 0) {
/* 236 */       throw new DOMException((short)5, formatMessage("invalid.qname", new Object[] { qualifiedName }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 241 */     return new GenericDocumentType(qualifiedName, publicId, systemId);
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
/* 258 */   protected static List extensions = null;
/*     */   
/*     */   protected static synchronized List getDomExtensions() {
/* 261 */     if (extensions != null) {
/* 262 */       return extensions;
/*     */     }
/* 264 */     extensions = new LinkedList();
/*     */     
/* 266 */     Iterator<DomExtension> iter = Service.providers(DomExtension.class);
/*     */     
/* 268 */     label18: while (iter.hasNext()) {
/* 269 */       DomExtension de = iter.next();
/* 270 */       float priority = de.getPriority();
/* 271 */       ListIterator<DomExtension> li = extensions.listIterator();
/*     */       while (true) {
/* 273 */         if (!li.hasNext()) {
/* 274 */           li.add(de);
/*     */           continue label18;
/*     */         } 
/* 277 */         DomExtension lde = li.next();
/* 278 */         if (lde.getPriority() > priority) {
/* 279 */           li.previous();
/* 280 */           li.add(de);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 286 */     return extensions;
/*     */   }
/*     */   
/*     */   public static interface ElementFactory {
/*     */     Element create(String param1String, Document param1Document);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/ExtensibleDOMImplementation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
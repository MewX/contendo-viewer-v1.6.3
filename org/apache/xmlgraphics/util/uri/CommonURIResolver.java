/*     */ package org.apache.xmlgraphics.util.uri;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import org.apache.xmlgraphics.util.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommonURIResolver
/*     */   implements URIResolver
/*     */ {
/*  42 */   private final List uriResolvers = new LinkedList();
/*     */   
/*     */   private static final class DefaultInstanceHolder {
/*  45 */     private static final CommonURIResolver INSTANCE = new CommonURIResolver();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonURIResolver() {
/*  54 */     Iterator<URIResolver> iter = Service.providers(URIResolver.class);
/*  55 */     while (iter.hasNext()) {
/*  56 */       URIResolver resolver = iter.next();
/*  57 */       register(resolver);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CommonURIResolver getDefaultURIResolver() {
/*  67 */     return DefaultInstanceHolder.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public Source resolve(String href, String base) {
/*  72 */     synchronized (this.uriResolvers) {
/*  73 */       Iterator<URIResolver> it = this.uriResolvers.iterator();
/*  74 */       while (it.hasNext()) {
/*  75 */         URIResolver currentResolver = it.next();
/*     */         try {
/*  77 */           Source result = currentResolver.resolve(href, base);
/*  78 */           if (result != null) {
/*  79 */             return result;
/*     */           }
/*  81 */         } catch (TransformerException transformerException) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(URIResolver uriResolver) {
/*  96 */     synchronized (this.uriResolvers) {
/*  97 */       this.uriResolvers.add(uriResolver);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unregister(URIResolver uriResolver) {
/* 108 */     synchronized (this.uriResolvers) {
/* 109 */       this.uriResolvers.remove(uriResolver);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/uri/CommonURIResolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
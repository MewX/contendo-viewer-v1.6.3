/*    */ package org.cyberneko.html.b;
/*    */ 
/*    */ import org.apache.xerces.xni.Augmentations;
/*    */ import org.apache.xerces.xni.NamespaceContext;
/*    */ import org.apache.xerces.xni.XMLDocumentHandler;
/*    */ import org.apache.xerces.xni.XMLLocator;
/*    */ import org.apache.xerces.xni.parser.XMLDocumentFilter;
/*    */ import org.apache.xerces.xni.parser.XMLDocumentSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class a
/*    */ {
/* 32 */   private static final a a = c();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static a a() {
/* 40 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   private static a c() {
/* 45 */     String[] classNames = { "org.cyberneko.html.xercesbridge.XercesBridge_2_3", "org.cyberneko.html.xercesbridge.XercesBridge_2_2", "org.cyberneko.html.xercesbridge.XercesBridge_2_1", "org.cyberneko.html.xercesbridge.XercesBridge_2_0" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 52 */     for (int i = 0; i != classNames.length; i++) {
/* 53 */       String className = classNames[i];
/* 54 */       a bridge = a(className);
/* 55 */       if (bridge != null) {
/* 56 */         return bridge;
/*    */       }
/*    */     } 
/* 59 */     throw new IllegalStateException("Failed to create XercesBridge instance");
/*    */   }
/*    */   
/*    */   private static a a(String className) {
/*    */     
/* 64 */     try { return (a)Class.forName(className).newInstance(); }
/*    */     
/* 66 */     catch (ClassNotFoundException classNotFoundException) {  }
/* 67 */     catch (SecurityException securityException) {  }
/* 68 */     catch (LinkageError linkageError) {  }
/* 69 */     catch (IllegalArgumentException illegalArgumentException) {  }
/* 70 */     catch (IllegalAccessException illegalAccessException) {  }
/* 71 */     catch (InstantiationException instantiationException) {}
/*    */     
/* 73 */     return null;
/*    */   }
/*    */   
/*    */   public void a(NamespaceContext namespaceContext, String ns, String avalue) {}
/*    */   
/*    */   public abstract String b();
/*    */   
/*    */   public abstract void a(XMLDocumentHandler paramXMLDocumentHandler, XMLLocator paramXMLLocator, String paramString, NamespaceContext paramNamespaceContext, Augmentations paramAugmentations);
/*    */   
/*    */   public void a(XMLDocumentHandler documentHandler, String prefix, String uri, Augmentations augs) {}
/*    */   
/*    */   public void a(XMLDocumentHandler documentHandler, String prefix, Augmentations augs) {}
/*    */   
/*    */   public void a(XMLDocumentFilter filter, XMLDocumentSource lastSource) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/b/a.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
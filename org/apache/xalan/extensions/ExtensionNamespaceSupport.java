/*     */ package org.apache.xalan.extensions;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import javax.xml.transform.TransformerException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtensionNamespaceSupport
/*     */ {
/*  34 */   String m_namespace = null;
/*  35 */   String m_handlerClass = null;
/*  36 */   Class[] m_sig = null;
/*  37 */   Object[] m_args = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensionNamespaceSupport(String namespace, String handlerClass, Object[] constructorArgs) {
/*  43 */     this.m_namespace = namespace;
/*  44 */     this.m_handlerClass = handlerClass;
/*  45 */     this.m_args = constructorArgs;
/*     */     
/*  47 */     this.m_sig = new Class[this.m_args.length];
/*  48 */     for (int i = 0; i < this.m_args.length; i++) {
/*     */       
/*  50 */       if (this.m_args[i] != null) {
/*  51 */         this.m_sig[i] = this.m_args[i].getClass();
/*     */       } else {
/*     */         
/*  54 */         this.m_sig = null;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNamespace() {
/*  62 */     return this.m_namespace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensionHandler launch() throws TransformerException {
/*  71 */     ExtensionHandler handler = null;
/*     */ 
/*     */     
/*  74 */     try { Class cl = ExtensionHandler.getClassForName(this.m_handlerClass);
/*  75 */       Constructor con = null;
/*     */       
/*  77 */       if (this.m_sig != null) {
/*  78 */         con = cl.getConstructor(this.m_sig);
/*     */       } else {
/*     */         
/*  81 */         Constructor[] cons = (Constructor[])cl.getConstructors();
/*  82 */         for (int i = 0; i < cons.length; i++) {
/*     */           
/*  84 */           if ((cons[i].getParameterTypes()).length == this.m_args.length) {
/*     */             
/*  86 */             con = cons[i];
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*  92 */       if (con != null)
/*  93 */       { handler = con.newInstance(this.m_args); }
/*     */       else
/*  95 */       { throw new TransformerException("ExtensionHandler constructor not found"); }  } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/*  99 */       throw new TransformerException(e); }
/*     */     
/* 101 */     return handler;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExtensionNamespaceSupport.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
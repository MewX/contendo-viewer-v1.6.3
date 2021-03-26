/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Vector;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectPool
/*     */   implements Serializable
/*     */ {
/*     */   private final Class objectType;
/*     */   private final Vector freeStack;
/*     */   
/*     */   public ObjectPool(Class type) {
/*  49 */     this.objectType = type;
/*  50 */     this.freeStack = new Vector();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectPool(String className) {
/*     */     
/*  62 */     try { this.objectType = ObjectFactory.findProviderClass(className, ObjectFactory.findClassLoader(), true); } catch (ClassNotFoundException cnfe)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/*  67 */       throw new WrappedRuntimeException(cnfe); }
/*     */     
/*  69 */     this.freeStack = new Vector();
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
/*     */   public ObjectPool(Class type, int size) {
/*  82 */     this.objectType = type;
/*  83 */     this.freeStack = new Vector(size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectPool() {
/*  92 */     this.objectType = null;
/*  93 */     this.freeStack = new Vector();
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
/*     */   public synchronized Object getInstanceIfFree() {
/* 106 */     if (!this.freeStack.isEmpty()) {
/*     */ 
/*     */ 
/*     */       
/* 110 */       Object result = this.freeStack.lastElement();
/*     */       
/* 112 */       this.freeStack.setSize(this.freeStack.size() - 1);
/*     */       
/* 114 */       return result;
/*     */     } 
/*     */     
/* 117 */     return null;
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
/*     */   public synchronized Object getInstance() {
/* 130 */     if (this.freeStack.isEmpty()) {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/*     */         
/* 136 */         return this.objectType.newInstance(); } catch (InstantiationException ex)
/*     */       {  }
/* 138 */       catch (IllegalAccessException illegalAccessException) {}
/*     */ 
/*     */ 
/*     */       
/* 142 */       throw new RuntimeException(XMLMessages.createXMLMessage("ER_EXCEPTION_CREATING_POOL", null));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     Object result = this.freeStack.lastElement();
/*     */     
/* 150 */     this.freeStack.setSize(this.freeStack.size() - 1);
/*     */     
/* 152 */     return result;
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
/*     */   public synchronized void freeInstance(Object obj) {
/* 169 */     this.freeStack.addElement(obj);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/ObjectPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
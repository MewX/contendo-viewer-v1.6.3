/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.apache.commons.collections.Factory;
/*     */ import org.apache.commons.collections.FunctorException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrototypeFactory
/*     */ {
/*     */   public static Factory getInstance(Object prototype) {
/*  58 */     if (prototype == null) {
/*  59 */       return ConstantFactory.NULL_INSTANCE;
/*     */     }
/*     */     try {
/*  62 */       Method method = prototype.getClass().getMethod("clone", null);
/*  63 */       return new PrototypeCloneFactory(prototype, method);
/*     */     }
/*  65 */     catch (NoSuchMethodException ex) {
/*     */       try {
/*  67 */         prototype.getClass().getConstructor(new Class[] { prototype.getClass() });
/*  68 */         return new InstantiateFactory(prototype.getClass(), new Class[] { prototype.getClass() }, new Object[] { prototype });
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*  73 */       catch (NoSuchMethodException ex2) {
/*  74 */         if (prototype instanceof Serializable) {
/*  75 */           return new PrototypeSerializationFactory((Serializable)prototype);
/*     */         }
/*     */ 
/*     */         
/*  79 */         throw new IllegalArgumentException("The prototype must be cloneable via a public clone method");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class PrototypeCloneFactory
/*     */     implements Serializable, Factory
/*     */   {
/*     */     static final long serialVersionUID = 5604271422565175555L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final Object iPrototype;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private transient Method iCloneMethod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private PrototypeCloneFactory(Object prototype, Method method) {
/* 110 */       this.iPrototype = prototype;
/* 111 */       this.iCloneMethod = method;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void findCloneMethod() {
/*     */       try {
/* 119 */         this.iCloneMethod = this.iPrototype.getClass().getMethod("clone", null);
/*     */       }
/* 121 */       catch (NoSuchMethodException ex) {
/* 122 */         throw new IllegalArgumentException("PrototypeCloneFactory: The clone method must exist and be public ");
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object create() {
/* 133 */       if (this.iCloneMethod == null) {
/* 134 */         findCloneMethod();
/*     */       }
/*     */       
/*     */       try {
/* 138 */         return this.iCloneMethod.invoke(this.iPrototype, null);
/*     */       }
/* 140 */       catch (IllegalAccessException ex) {
/* 141 */         throw new FunctorException("PrototypeCloneFactory: Clone method must be public", ex);
/* 142 */       } catch (InvocationTargetException ex) {
/* 143 */         throw new FunctorException("PrototypeCloneFactory: Clone method threw an exception", ex);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class PrototypeSerializationFactory
/*     */     implements Serializable, Factory
/*     */   {
/*     */     static final long serialVersionUID = -8704966966139178833L;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final Serializable iPrototype;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private PrototypeSerializationFactory(Serializable prototype) {
/* 166 */       this.iPrototype = prototype;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object create() {
/* 175 */       ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
/* 176 */       ByteArrayInputStream bais = null;
/*     */       try {
/* 178 */         ObjectOutputStream out = new ObjectOutputStream(baos);
/* 179 */         out.writeObject(this.iPrototype);
/*     */         
/* 181 */         bais = new ByteArrayInputStream(baos.toByteArray());
/* 182 */         ObjectInputStream in = new ObjectInputStream(bais);
/* 183 */         return in.readObject();
/*     */       }
/* 185 */       catch (ClassNotFoundException ex) {
/* 186 */         throw new FunctorException(ex);
/* 187 */       } catch (IOException ex) {
/* 188 */         throw new FunctorException(ex);
/*     */       } finally {
/*     */         try {
/* 191 */           if (bais != null) {
/* 192 */             bais.close();
/*     */           }
/* 194 */         } catch (IOException iOException) {}
/*     */ 
/*     */         
/*     */         try {
/* 198 */           if (baos != null) {
/* 199 */             baos.close();
/*     */           }
/* 201 */         } catch (IOException iOException) {}
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/PrototypeFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
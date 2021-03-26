/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.Factory;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.functors.FactoryTransformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LazyMap
/*     */   extends AbstractMapDecorator
/*     */   implements Serializable, Map
/*     */ {
/*     */   private static final long serialVersionUID = 7990956402564206740L;
/*     */   protected final Transformer factory;
/*     */   
/*     */   public static Map decorate(Map map, Factory factory) {
/*  76 */     return new LazyMap(map, factory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map decorate(Map map, Transformer factory) {
/*  87 */     return new LazyMap(map, factory);
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
/*     */   protected LazyMap(Map map, Factory factory) {
/*  99 */     super(map);
/* 100 */     if (factory == null) {
/* 101 */       throw new IllegalArgumentException("Factory must not be null");
/*     */     }
/* 103 */     this.factory = FactoryTransformer.getInstance(factory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LazyMap(Map map, Transformer factory) {
/* 114 */     super(map);
/* 115 */     if (factory == null) {
/* 116 */       throw new IllegalArgumentException("Factory must not be null");
/*     */     }
/* 118 */     this.factory = factory;
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
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 130 */     out.defaultWriteObject();
/* 131 */     out.writeObject(this.map);
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 143 */     in.defaultReadObject();
/* 144 */     this.map = (Map)in.readObject();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 150 */     if (!this.map.containsKey(key)) {
/* 151 */       Object value = this.factory.transform(key);
/* 152 */       this.map.put(key, value);
/* 153 */       return value;
/*     */     } 
/* 155 */     return this.map.get(key);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/LazyMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedMap
/*     */   extends AbstractInputCheckedMapDecorator
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7412622456128415156L;
/*     */   protected final Predicate keyPredicate;
/*     */   protected final Predicate valuePredicate;
/*     */   
/*     */   public static Map decorate(Map map, Predicate keyPredicate, Predicate valuePredicate) {
/*  70 */     return new PredicatedMap(map, keyPredicate, valuePredicate);
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
/*     */   protected PredicatedMap(Map map, Predicate keyPredicate, Predicate valuePredicate) {
/*  83 */     super(map);
/*  84 */     this.keyPredicate = keyPredicate;
/*  85 */     this.valuePredicate = valuePredicate;
/*     */     
/*  87 */     Iterator it = map.entrySet().iterator();
/*  88 */     while (it.hasNext()) {
/*  89 */       Map.Entry entry = it.next();
/*  90 */       Object key = entry.getKey();
/*  91 */       Object value = entry.getValue();
/*  92 */       validate(key, value);
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
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 105 */     out.defaultWriteObject();
/* 106 */     out.writeObject(this.map);
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
/* 118 */     in.defaultReadObject();
/* 119 */     this.map = (Map)in.readObject();
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
/*     */   protected void validate(Object key, Object value) {
/* 131 */     if (this.keyPredicate != null && !this.keyPredicate.evaluate(key)) {
/* 132 */       throw new IllegalArgumentException("Cannot add key - Predicate rejected it");
/*     */     }
/* 134 */     if (this.valuePredicate != null && !this.valuePredicate.evaluate(value)) {
/* 135 */       throw new IllegalArgumentException("Cannot add value - Predicate rejected it");
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
/*     */   protected Object checkSetValue(Object value) {
/* 147 */     if (!this.valuePredicate.evaluate(value)) {
/* 148 */       throw new IllegalArgumentException("Cannot set value - Predicate rejected it");
/*     */     }
/* 150 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSetValueChecking() {
/* 160 */     return (this.valuePredicate != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 165 */     validate(key, value);
/* 166 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 170 */     Iterator it = mapToCopy.entrySet().iterator();
/* 171 */     while (it.hasNext()) {
/* 172 */       Map.Entry entry = it.next();
/* 173 */       Object key = entry.getKey();
/* 174 */       Object value = entry.getValue();
/* 175 */       validate(key, value);
/*     */     } 
/* 177 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/PredicatedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
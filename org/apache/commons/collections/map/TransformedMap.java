/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformedMap
/*     */   extends AbstractInputCheckedMapDecorator
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7023152376788900464L;
/*     */   protected final Transformer keyTransformer;
/*     */   protected final Transformer valueTransformer;
/*     */   
/*     */   public static Map decorate(Map map, Transformer keyTransformer, Transformer valueTransformer) {
/*  66 */     return new TransformedMap(map, keyTransformer, valueTransformer);
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
/*     */   protected TransformedMap(Map map, Transformer keyTransformer, Transformer valueTransformer) {
/*  82 */     super(map);
/*  83 */     this.keyTransformer = keyTransformer;
/*  84 */     this.valueTransformer = valueTransformer;
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
/*  96 */     out.defaultWriteObject();
/*  97 */     out.writeObject(this.map);
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
/* 109 */     in.defaultReadObject();
/* 110 */     this.map = (Map)in.readObject();
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
/*     */   protected Object transformKey(Object object) {
/* 123 */     if (this.keyTransformer == null) {
/* 124 */       return object;
/*     */     }
/* 126 */     return this.keyTransformer.transform(object);
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
/*     */   protected Object transformValue(Object object) {
/* 138 */     if (this.valueTransformer == null) {
/* 139 */       return object;
/*     */     }
/* 141 */     return this.valueTransformer.transform(object);
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
/*     */   protected Map transformMap(Map map) {
/* 153 */     LinkedMap linkedMap = new LinkedMap(map.size());
/* 154 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 155 */       Map.Entry entry = it.next();
/* 156 */       linkedMap.put(transformKey(entry.getKey()), transformValue(entry.getValue()));
/*     */     } 
/* 158 */     return (Map)linkedMap;
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
/* 169 */     return this.valueTransformer.transform(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSetValueChecking() {
/* 179 */     return (this.valueTransformer != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 184 */     key = transformKey(key);
/* 185 */     value = transformValue(value);
/* 186 */     return getMap().put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 190 */     mapToCopy = transformMap(mapToCopy);
/* 191 */     getMap().putAll(mapToCopy);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/TransformedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
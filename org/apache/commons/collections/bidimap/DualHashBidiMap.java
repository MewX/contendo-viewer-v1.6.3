/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DualHashBidiMap
/*     */   extends AbstractDualBidiMap
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 721969328361808L;
/*     */   
/*     */   public DualHashBidiMap() {
/*  54 */     super(new HashMap(), new HashMap());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DualHashBidiMap(Map map) {
/*  64 */     super(new HashMap(), new HashMap());
/*  65 */     putAll(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DualHashBidiMap(Map normalMap, Map reverseMap, BidiMap inverseBidiMap) {
/*  76 */     super(normalMap, reverseMap, inverseBidiMap);
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
/*     */   protected BidiMap createBidiMap(Map normalMap, Map reverseMap, BidiMap inverseBidiMap) {
/*  88 */     return new DualHashBidiMap(normalMap, reverseMap, inverseBidiMap);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  94 */     out.defaultWriteObject();
/*  95 */     out.writeObject(this.maps[0]);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  99 */     in.defaultReadObject();
/* 100 */     this.maps[0] = new HashMap();
/* 101 */     this.maps[1] = new HashMap();
/* 102 */     Map map = (Map)in.readObject();
/* 103 */     putAll(map);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/DualHashBidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
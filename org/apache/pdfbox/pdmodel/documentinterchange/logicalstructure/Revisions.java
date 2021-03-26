/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Revisions<T>
/*     */ {
/*     */   private List<T> objects;
/*     */   private List<Integer> revisionNumbers;
/*     */   
/*     */   private List<T> getObjects() {
/*  35 */     if (this.objects == null)
/*     */     {
/*  37 */       this.objects = new ArrayList<T>();
/*     */     }
/*  39 */     return this.objects;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Integer> getRevisionNumbers() {
/*  44 */     if (this.revisionNumbers == null)
/*     */     {
/*  46 */       this.revisionNumbers = new ArrayList<Integer>();
/*     */     }
/*  48 */     return this.revisionNumbers;
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
/*     */   
/*     */   public T getObject(int index) {
/*  67 */     return getObjects().get(index);
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
/*     */   public int getRevisionNumber(int index) {
/*  79 */     return ((Integer)getRevisionNumbers().get(index)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addObject(T object, int revisionNumber) {
/*  90 */     getObjects().add(object);
/*  91 */     getRevisionNumbers().add(Integer.valueOf(revisionNumber));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setRevisionNumber(T object, int revisionNumber) {
/* 102 */     int index = getObjects().indexOf(object);
/* 103 */     if (index > -1)
/*     */     {
/* 105 */       getRevisionNumbers().set(index, Integer.valueOf(revisionNumber));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 116 */     return getObjects().size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 125 */     StringBuilder sb = new StringBuilder();
/* 126 */     for (int i = 0; i < getObjects().size(); i++) {
/*     */       
/* 128 */       if (i > 0)
/*     */       {
/* 130 */         sb.append("; ");
/*     */       }
/* 132 */       sb.append("object=").append(getObjects().get(i))
/* 133 */         .append(", revisionNumber=").append(getRevisionNumber(i));
/*     */     } 
/* 135 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/Revisions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
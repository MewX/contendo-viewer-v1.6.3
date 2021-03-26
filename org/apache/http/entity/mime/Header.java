/*     */ package org.apache.http.entity.mime;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Header
/*     */   implements Iterable<MinimalField>
/*     */ {
/*  49 */   private final List<MinimalField> fields = new LinkedList<MinimalField>();
/*  50 */   private final Map<String, List<MinimalField>> fieldMap = new HashMap<String, List<MinimalField>>();
/*     */ 
/*     */   
/*     */   public void addField(MinimalField field) {
/*  54 */     if (field == null) {
/*     */       return;
/*     */     }
/*  57 */     String key = field.getName().toLowerCase(Locale.ROOT);
/*  58 */     List<MinimalField> values = this.fieldMap.get(key);
/*  59 */     if (values == null) {
/*  60 */       values = new LinkedList<MinimalField>();
/*  61 */       this.fieldMap.put(key, values);
/*     */     } 
/*  63 */     values.add(field);
/*  64 */     this.fields.add(field);
/*     */   }
/*     */   
/*     */   public List<MinimalField> getFields() {
/*  68 */     return new ArrayList<MinimalField>(this.fields);
/*     */   }
/*     */   
/*     */   public MinimalField getField(String name) {
/*  72 */     if (name == null) {
/*  73 */       return null;
/*     */     }
/*  75 */     String key = name.toLowerCase(Locale.ROOT);
/*  76 */     List<MinimalField> list = this.fieldMap.get(key);
/*  77 */     if (list != null && !list.isEmpty()) {
/*  78 */       return list.get(0);
/*     */     }
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   public List<MinimalField> getFields(String name) {
/*  84 */     if (name == null) {
/*  85 */       return null;
/*     */     }
/*  87 */     String key = name.toLowerCase(Locale.ROOT);
/*  88 */     List<MinimalField> list = this.fieldMap.get(key);
/*  89 */     if (list == null || list.isEmpty()) {
/*  90 */       return Collections.emptyList();
/*     */     }
/*  92 */     return new ArrayList<MinimalField>(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public int removeFields(String name) {
/*  97 */     if (name == null) {
/*  98 */       return 0;
/*     */     }
/* 100 */     String key = name.toLowerCase(Locale.ROOT);
/* 101 */     List<MinimalField> removed = this.fieldMap.remove(key);
/* 102 */     if (removed == null || removed.isEmpty()) {
/* 103 */       return 0;
/*     */     }
/* 105 */     this.fields.removeAll(removed);
/* 106 */     return removed.size();
/*     */   }
/*     */   
/*     */   public void setField(MinimalField field) {
/* 110 */     if (field == null) {
/*     */       return;
/*     */     }
/* 113 */     String key = field.getName().toLowerCase(Locale.ROOT);
/* 114 */     List<MinimalField> list = this.fieldMap.get(key);
/* 115 */     if (list == null || list.isEmpty()) {
/* 116 */       addField(field);
/*     */       return;
/*     */     } 
/* 119 */     list.clear();
/* 120 */     list.add(field);
/* 121 */     int firstOccurrence = -1;
/* 122 */     int index = 0;
/* 123 */     for (Iterator<MinimalField> it = this.fields.iterator(); it.hasNext(); index++) {
/* 124 */       MinimalField f = it.next();
/* 125 */       if (f.getName().equalsIgnoreCase(field.getName())) {
/* 126 */         it.remove();
/* 127 */         if (firstOccurrence == -1) {
/* 128 */           firstOccurrence = index;
/*     */         }
/*     */       } 
/*     */     } 
/* 132 */     this.fields.add(firstOccurrence, field);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<MinimalField> iterator() {
/* 137 */     return Collections.<MinimalField>unmodifiableList(this.fields).iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 142 */     return this.fields.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/Header.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
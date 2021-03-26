/*     */ package org.apache.commons.csv;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public final class CSVRecord
/*     */   implements Serializable, Iterable<String>
/*     */ {
/*  33 */   private static final String[] EMPTY_STRING_ARRAY = new String[0];
/*     */ 
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   
/*     */   private final long characterPosition;
/*     */ 
/*     */   
/*     */   private final String comment;
/*     */ 
/*     */   
/*     */   private final Map<String, Integer> mapping;
/*     */   
/*     */   private final long recordNumber;
/*     */   
/*     */   private final String[] values;
/*     */ 
/*     */   
/*     */   CSVRecord(String[] values, Map<String, Integer> mapping, String comment, long recordNumber, long characterPosition) {
/*  53 */     this.recordNumber = recordNumber;
/*  54 */     this.values = (values != null) ? values : EMPTY_STRING_ARRAY;
/*  55 */     this.mapping = mapping;
/*  56 */     this.comment = comment;
/*  57 */     this.characterPosition = characterPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String get(Enum<?> e) {
/*  68 */     return get(e.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String get(int i) {
/*  79 */     return this.values[i];
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
/*     */   public String get(String name) {
/*  96 */     if (this.mapping == null) {
/*  97 */       throw new IllegalStateException("No header mapping was specified, the record values can't be accessed by name");
/*     */     }
/*     */     
/* 100 */     Integer index = this.mapping.get(name);
/* 101 */     if (index == null) {
/* 102 */       throw new IllegalArgumentException(String.format("Mapping for %s not found, expected one of %s", new Object[] { name, this.mapping
/* 103 */               .keySet() }));
/*     */     }
/*     */     try {
/* 106 */       return this.values[index.intValue()];
/* 107 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 108 */       throw new IllegalArgumentException(String.format("Index for header '%s' is %d but CSVRecord only has %d values!", new Object[] { name, index, 
/*     */               
/* 110 */               Integer.valueOf(this.values.length) }));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCharacterPosition() {
/* 121 */     return this.characterPosition;
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
/*     */   public String getComment() {
/* 133 */     return this.comment;
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
/*     */   public long getRecordNumber() {
/* 148 */     return this.recordNumber;
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
/*     */   public boolean isConsistent() {
/* 162 */     return (this.mapping == null || this.mapping.size() == this.values.length);
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
/*     */   public boolean hasComment() {
/* 175 */     return (this.comment != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMapped(String name) {
/* 186 */     return (this.mapping != null && this.mapping.containsKey(name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSet(String name) {
/* 197 */     return (isMapped(name) && ((Integer)this.mapping.get(name)).intValue() < this.values.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<String> iterator() {
/* 207 */     return toList().iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   <M extends Map<String, String>> M putIn(M map) {
/* 218 */     if (this.mapping == null) {
/* 219 */       return map;
/*     */     }
/* 221 */     for (Map.Entry<String, Integer> entry : this.mapping.entrySet()) {
/* 222 */       int col = ((Integer)entry.getValue()).intValue();
/* 223 */       if (col < this.values.length) {
/* 224 */         map.put(entry.getKey(), this.values[col]);
/*     */       }
/*     */     } 
/* 227 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 236 */     return this.values.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<String> toList() {
/* 247 */     return Arrays.asList(this.values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> toMap() {
/* 256 */     return putIn(new HashMap<>(this.values.length));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 267 */     return "CSVRecord [comment=" + this.comment + ", mapping=" + this.mapping + ", recordNumber=" + this.recordNumber + ", values=" + 
/*     */       
/* 269 */       Arrays.toString((Object[])this.values) + "]";
/*     */   }
/*     */   
/*     */   String[] values() {
/* 273 */     return this.values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/csv/CSVRecord.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
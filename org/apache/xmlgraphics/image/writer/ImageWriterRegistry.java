/*     */ package org.apache.xmlgraphics.image.writer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import org.apache.xmlgraphics.util.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ImageWriterRegistry
/*     */ {
/*     */   private static volatile ImageWriterRegistry instance;
/*  39 */   private Map<String, List<ImageWriter>> imageWriterMap = new HashMap<String, List<ImageWriter>>();
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<String, Integer> preferredOrder;
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageWriterRegistry() {
/*  48 */     Properties props = new Properties();
/*  49 */     InputStream in = getClass().getResourceAsStream("default-preferred-order.properties");
/*  50 */     if (in != null) {
/*     */       try {
/*     */         try {
/*  53 */           props.load(in);
/*     */         } finally {
/*  55 */           in.close();
/*     */         } 
/*  57 */       } catch (IOException ioe) {
/*  58 */         throw new RuntimeException("Could not load default preferred order due to I/O error: " + ioe.getMessage());
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  63 */     setPreferredOrder(props);
/*  64 */     setup();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageWriterRegistry(Properties preferredOrder) {
/*  75 */     setPreferredOrder(preferredOrder);
/*  76 */     setup();
/*     */   }
/*     */   
/*     */   private void setPreferredOrder(Properties preferredOrder) {
/*  80 */     Map<String, Integer> order = new HashMap<String, Integer>();
/*  81 */     for (Map.Entry<Object, Object> entry : preferredOrder.entrySet()) {
/*  82 */       order.put(entry.getKey().toString(), Integer.valueOf(Integer.parseInt(entry.getValue().toString())));
/*     */     }
/*     */     
/*  85 */     this.preferredOrder = order;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImageWriterRegistry getInstance() {
/*  90 */     if (instance == null) {
/*  91 */       instance = new ImageWriterRegistry();
/*     */     }
/*  93 */     return instance;
/*     */   }
/*     */   
/*     */   private void setup() {
/*  97 */     Iterator<ImageWriter> iter = Service.providers(ImageWriter.class);
/*  98 */     while (iter.hasNext()) {
/*  99 */       ImageWriter writer = iter.next();
/* 100 */       register(writer);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getPriority(ImageWriter writer) {
/* 105 */     String key = writer.getClass().getName();
/* 106 */     Integer value = this.preferredOrder.get(key);
/* 107 */     while (value == null) {
/* 108 */       int pos = key.lastIndexOf(".");
/* 109 */       if (pos < 0) {
/*     */         break;
/*     */       }
/* 112 */       key = key.substring(0, pos);
/* 113 */       value = this.preferredOrder.get(key);
/*     */     } 
/* 115 */     return (value != null) ? value.intValue() : 0;
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
/*     */   public void register(ImageWriter writer, int priority) {
/* 127 */     String key = writer.getClass().getName();
/*     */     
/* 129 */     this.preferredOrder.put(key, Integer.valueOf(priority));
/*     */     
/* 131 */     register(writer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void register(ImageWriter writer) {
/* 140 */     List<ImageWriter> entries = this.imageWriterMap.get(writer.getMIMEType());
/* 141 */     if (entries == null) {
/* 142 */       entries = new ArrayList<ImageWriter>();
/* 143 */       this.imageWriterMap.put(writer.getMIMEType(), entries);
/*     */     } 
/*     */     
/* 146 */     int priority = getPriority(writer);
/* 147 */     ListIterator<ImageWriter> li = entries.listIterator();
/* 148 */     while (li.hasNext()) {
/* 149 */       ImageWriter w = li.next();
/* 150 */       if (getPriority(w) < priority) {
/* 151 */         li.previous();
/*     */         break;
/*     */       } 
/*     */     } 
/* 155 */     li.add(writer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized ImageWriter getWriterFor(String mime) {
/* 165 */     List<ImageWriter> entries = this.imageWriterMap.get(mime);
/* 166 */     if (entries == null) {
/* 167 */       return null;
/*     */     }
/* 169 */     Iterator<ImageWriter> iter = entries.iterator();
/* 170 */     while (iter.hasNext()) {
/* 171 */       ImageWriter writer = iter.next();
/* 172 */       if (writer.isFunctional()) {
/* 173 */         return writer;
/*     */       }
/*     */     } 
/* 176 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/ImageWriterRegistry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
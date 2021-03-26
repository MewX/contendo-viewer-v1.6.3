/*    */ package org.apache.batik.ext.awt.image.spi;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractRegistryEntry
/*    */   implements ErrorConstants, RegistryEntry
/*    */ {
/*    */   String name;
/*    */   float priority;
/*    */   List exts;
/*    */   List mimeTypes;
/*    */   
/*    */   public AbstractRegistryEntry(String name, float priority, String[] exts, String[] mimeTypes) {
/* 41 */     this.name = name;
/* 42 */     this.priority = priority;
/*    */     
/* 44 */     this.exts = new ArrayList(exts.length);
/* 45 */     for (String ext : exts) this.exts.add(ext); 
/* 46 */     this.exts = Collections.unmodifiableList(this.exts);
/*    */     
/* 48 */     this.mimeTypes = new ArrayList(mimeTypes.length);
/* 49 */     for (String mimeType : mimeTypes) this.mimeTypes.add(mimeType); 
/* 50 */     this.mimeTypes = Collections.unmodifiableList(this.mimeTypes);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRegistryEntry(String name, float priority, String ext, String mimeType) {
/* 57 */     this.name = name;
/* 58 */     this.priority = priority;
/*    */     
/* 60 */     this.exts = new ArrayList(1);
/* 61 */     this.exts.add(ext);
/* 62 */     this.exts = Collections.unmodifiableList(this.exts);
/*    */     
/* 64 */     this.mimeTypes = new ArrayList(1);
/* 65 */     this.mimeTypes.add(mimeType);
/* 66 */     this.mimeTypes = Collections.unmodifiableList(this.mimeTypes);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFormatName() {
/* 71 */     return this.name;
/*    */   }
/*    */   
/*    */   public List getStandardExtensions() {
/* 75 */     return this.exts;
/*    */   }
/*    */   
/*    */   public List getMimeTypes() {
/* 79 */     return this.mimeTypes;
/*    */   }
/*    */   
/*    */   public float getPriority() {
/* 83 */     return this.priority;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/AbstractRegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
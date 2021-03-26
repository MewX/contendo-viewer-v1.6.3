/*     */ package org.apache.xmlgraphics.ps.dsc.events;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.xmlgraphics.ps.PSGenerator;
/*     */ import org.apache.xmlgraphics.ps.PSResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DSCCommentBeginDocument
/*     */   extends AbstractDSCComment
/*     */ {
/*     */   private PSResource resource;
/*     */   private Float version;
/*     */   private String type;
/*     */   
/*     */   public DSCCommentBeginDocument() {}
/*     */   
/*     */   public DSCCommentBeginDocument(PSResource resource) {
/*  51 */     this.resource = resource;
/*  52 */     if (resource != null && !"file".equals(resource.getType())) {
/*  53 */       throw new IllegalArgumentException("Resource must be of type 'file'");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DSCCommentBeginDocument(PSResource resource, Float version, String type) {
/*  64 */     this(resource);
/*  65 */     this.version = version;
/*  66 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getVersion() {
/*  74 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  82 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  87 */     return "BeginDocument";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PSResource getResource() {
/*  95 */     return this.resource;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasValues() {
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void parseValue(String value) {
/* 105 */     List params = splitParams(value);
/* 106 */     Iterator<String> iter = params.iterator();
/* 107 */     String name = iter.next();
/* 108 */     this.resource = new PSResource("file", name);
/* 109 */     if (iter.hasNext()) {
/* 110 */       this.version = Float.valueOf(iter.next().toString());
/* 111 */       this.type = null;
/* 112 */       if (iter.hasNext()) {
/* 113 */         this.type = iter.next();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void generate(PSGenerator gen) throws IOException {
/* 120 */     List<String> params = new ArrayList();
/* 121 */     params.add(getResource().getName());
/* 122 */     if (getVersion() != null) {
/* 123 */       params.add(getVersion());
/* 124 */       if (getType() != null) {
/* 125 */         params.add(getType());
/*     */       }
/*     */     } 
/* 128 */     gen.writeDSCComment(getName(), params.toArray());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentBeginDocument.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.xmlgraphics.ps.dsc.events;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.xmlgraphics.ps.PSGenerator;
/*     */ import org.apache.xmlgraphics.ps.PSProcSet;
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
/*     */ public abstract class AbstractResourcesDSCComment
/*     */   extends AbstractDSCComment
/*     */ {
/*     */   private Set resources;
/*     */   
/*     */   public AbstractResourcesDSCComment() {}
/*     */   
/*     */   public AbstractResourcesDSCComment(Collection resources) {
/*  53 */     addResources(resources);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasValues() {
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   private void prepareResourceSet() {
/*  62 */     if (this.resources == null) {
/*  63 */       this.resources = new TreeSet();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addResource(PSResource res) {
/*  72 */     prepareResourceSet();
/*  73 */     this.resources.add(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addResources(Collection resources) {
/*  81 */     if (resources != null) {
/*  82 */       prepareResourceSet();
/*  83 */       this.resources.addAll(resources);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getResources() {
/*  92 */     return Collections.unmodifiableSet(this.resources);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   static final Set RESOURCE_TYPES = new HashSet();
/*     */   
/*     */   static {
/* 101 */     RESOURCE_TYPES.add("font");
/* 102 */     RESOURCE_TYPES.add("procset");
/* 103 */     RESOURCE_TYPES.add("file");
/* 104 */     RESOURCE_TYPES.add("pattern");
/* 105 */     RESOURCE_TYPES.add("form");
/* 106 */     RESOURCE_TYPES.add("encoding");
/*     */   }
/*     */ 
/*     */   
/*     */   public void parseValue(String value) {
/* 111 */     List params = splitParams(value);
/* 112 */     String currentResourceType = null;
/* 113 */     Iterator<String> iter = params.iterator();
/* 114 */     while (iter.hasNext()) {
/* 115 */       String name = iter.next();
/* 116 */       if (RESOURCE_TYPES.contains(name)) {
/* 117 */         currentResourceType = name;
/*     */       }
/* 119 */       if (currentResourceType == null) {
/* 120 */         throw new IllegalArgumentException("<resources> must begin with a resource type. Found: " + name);
/*     */       }
/*     */       
/* 123 */       if ("font".equals(currentResourceType)) {
/* 124 */         String fontname = iter.next();
/* 125 */         addResource(new PSResource(name, fontname)); continue;
/* 126 */       }  if ("form".equals(currentResourceType)) {
/* 127 */         String formname = iter.next();
/* 128 */         addResource(new PSResource(name, formname)); continue;
/* 129 */       }  if ("procset".equals(currentResourceType)) {
/* 130 */         String procname = iter.next();
/* 131 */         String version = iter.next();
/* 132 */         String revision = iter.next();
/* 133 */         addResource((PSResource)new PSProcSet(procname, Float.parseFloat(version), Integer.parseInt(revision))); continue;
/*     */       } 
/* 135 */       if ("file".equals(currentResourceType)) {
/* 136 */         String filename = iter.next();
/* 137 */         addResource(new PSResource(name, filename)); continue;
/*     */       } 
/* 139 */       throw new IllegalArgumentException("Invalid resource type: " + currentResourceType);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate(PSGenerator gen) throws IOException {
/* 146 */     if (this.resources == null || this.resources.size() == 0) {
/*     */       return;
/*     */     }
/* 149 */     StringBuffer sb = new StringBuffer();
/* 150 */     sb.append("%%").append(getName()).append(": ");
/* 151 */     boolean first = true;
/* 152 */     Iterator<PSResource> i = this.resources.iterator();
/* 153 */     while (i.hasNext()) {
/* 154 */       if (!first) {
/* 155 */         gen.writeln(sb.toString());
/* 156 */         sb.setLength(0);
/* 157 */         sb.append("%%+ ");
/*     */       } 
/* 159 */       PSResource res = i.next();
/* 160 */       sb.append(res.getResourceSpecification());
/* 161 */       first = false;
/*     */     } 
/* 163 */     gen.writeln(sb.toString());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/AbstractResourcesDSCComment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
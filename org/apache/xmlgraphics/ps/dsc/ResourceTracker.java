/*     */ package org.apache.xmlgraphics.ps.dsc;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.xmlgraphics.ps.PSGenerator;
/*     */ import org.apache.xmlgraphics.ps.PSResource;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentDocumentNeededResources;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentDocumentSuppliedResources;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentPageResources;
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
/*     */ public class ResourceTracker
/*     */ {
/*     */   private Set documentSuppliedResources;
/*     */   private Set documentNeededResources;
/*     */   private Set usedResources;
/*     */   private Set pageResources;
/*     */   private Map resourceUsageCounts;
/*     */   
/*     */   public Set getDocumentSuppliedResources() {
/*  55 */     if (this.documentSuppliedResources != null) {
/*  56 */       return Collections.unmodifiableSet(this.documentSuppliedResources);
/*     */     }
/*  58 */     return Collections.EMPTY_SET;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getDocumentNeededResources() {
/*  67 */     if (this.documentNeededResources != null) {
/*  68 */       return Collections.unmodifiableSet(this.documentNeededResources);
/*     */     }
/*  70 */     return Collections.EMPTY_SET;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyStartNewPage() {
/*  79 */     if (this.pageResources != null) {
/*  80 */       this.pageResources.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerSuppliedResource(PSResource res) {
/*  90 */     if (this.documentSuppliedResources == null) {
/*  91 */       this.documentSuppliedResources = new HashSet();
/*     */     }
/*  93 */     this.documentSuppliedResources.add(res);
/*  94 */     if (this.documentNeededResources != null) {
/*  95 */       this.documentNeededResources.remove(res);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerNeededResource(PSResource res) {
/* 105 */     if (this.documentSuppliedResources == null || !this.documentSuppliedResources.contains(res)) {
/* 106 */       if (this.documentNeededResources == null) {
/* 107 */         this.documentNeededResources = new HashSet();
/*     */       }
/* 109 */       this.documentNeededResources.add(res);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void preparePageResources() {
/* 114 */     if (this.pageResources == null) {
/* 115 */       this.pageResources = new HashSet();
/*     */     }
/*     */   }
/*     */   
/*     */   private void prepareUsageCounts() {
/* 120 */     if (this.resourceUsageCounts == null) {
/* 121 */       this.resourceUsageCounts = new HashMap<Object, Object>();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyResourceUsageOnPage(PSResource res) {
/* 130 */     preparePageResources();
/* 131 */     this.pageResources.add(res);
/*     */     
/* 133 */     prepareUsageCounts();
/* 134 */     Counter counter = (Counter)this.resourceUsageCounts.get(res);
/* 135 */     if (counter == null) {
/* 136 */       this.resourceUsageCounts.put(res, new Counter());
/*     */     } else {
/* 138 */       counter.inc();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyResourceUsageOnPage(Collection resources) {
/* 147 */     preparePageResources();
/* 148 */     Iterator<PSResource> iter = resources.iterator();
/* 149 */     while (iter.hasNext()) {
/* 150 */       PSResource res = iter.next();
/* 151 */       notifyResourceUsageOnPage(res);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isResourceSupplied(PSResource res) {
/* 161 */     return (this.documentSuppliedResources != null && this.documentSuppliedResources.contains(res));
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
/*     */   public void writeResources(boolean pageLevel, PSGenerator gen) throws IOException {
/* 173 */     if (pageLevel) {
/* 174 */       writePageResources(gen);
/*     */     } else {
/* 176 */       writeDocumentResources(gen);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePageResources(PSGenerator gen) throws IOException {
/* 187 */     (new DSCCommentPageResources(this.pageResources)).generate(gen);
/* 188 */     if (this.usedResources == null) {
/* 189 */       this.usedResources = new HashSet();
/*     */     }
/* 191 */     this.usedResources.addAll(this.pageResources);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDocumentResources(PSGenerator gen) throws IOException {
/* 200 */     if (this.usedResources != null) {
/* 201 */       Iterator<PSResource> iter = this.usedResources.iterator();
/* 202 */       while (iter.hasNext()) {
/* 203 */         PSResource res = iter.next();
/* 204 */         if (this.documentSuppliedResources == null || !this.documentSuppliedResources.contains(res))
/*     */         {
/* 206 */           registerNeededResource(res);
/*     */         }
/*     */       } 
/*     */     } 
/* 210 */     (new DSCCommentDocumentNeededResources(this.documentNeededResources)).generate(gen);
/* 211 */     (new DSCCommentDocumentSuppliedResources(this.documentSuppliedResources)).generate(gen);
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
/*     */   public void declareInlined(PSResource res) {
/* 223 */     if (this.documentNeededResources != null) {
/* 224 */       this.documentNeededResources.remove(res);
/*     */     }
/* 226 */     if (this.documentSuppliedResources != null) {
/* 227 */       this.documentSuppliedResources.remove(res);
/*     */     }
/* 229 */     if (this.pageResources != null) {
/* 230 */       this.pageResources.remove(res);
/*     */     }
/* 232 */     if (this.usedResources != null) {
/* 233 */       this.usedResources.remove(res);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getUsageCount(PSResource res) {
/* 243 */     Counter counter = (Counter)this.resourceUsageCounts.get(res);
/* 244 */     return (counter != null) ? counter.getCount() : 0L;
/*     */   }
/*     */   
/*     */   private static class Counter
/*     */   {
/* 249 */     private long count = 1L;
/*     */     
/*     */     public void inc() {
/* 252 */       this.count++;
/*     */     }
/*     */     
/*     */     public long getCount() {
/* 256 */       return this.count;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 260 */       return Long.toString(this.count);
/*     */     }
/*     */     
/*     */     private Counter() {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/ResourceTracker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
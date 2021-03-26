/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.xmlgraphics.ps.PSGenerator;
/*    */ import org.apache.xmlgraphics.ps.PSProcSet;
/*    */ import org.apache.xmlgraphics.ps.PSResource;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractResourceDSCComment
/*    */   extends AbstractDSCComment
/*    */ {
/*    */   private PSResource resource;
/*    */   
/*    */   public AbstractResourceDSCComment() {}
/*    */   
/*    */   public AbstractResourceDSCComment(PSResource resource) {
/* 48 */     this.resource = resource;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PSResource getResource() {
/* 56 */     return this.resource;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasValues() {
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void parseValue(String value) {
/* 66 */     List params = splitParams(value);
/* 67 */     Iterator<String> iter = params.iterator();
/* 68 */     String name = iter.next();
/* 69 */     if ("font".equals(name)) {
/* 70 */       String fontname = iter.next();
/* 71 */       this.resource = new PSResource(name, fontname);
/* 72 */     } else if ("procset".equals(name)) {
/* 73 */       String procname = iter.next();
/* 74 */       String version = iter.next();
/* 75 */       String revision = iter.next();
/* 76 */       this.resource = (PSResource)new PSProcSet(procname, Float.parseFloat(version), Integer.parseInt(revision));
/*    */     }
/* 78 */     else if ("file".equals(name)) {
/* 79 */       String filename = iter.next();
/* 80 */       this.resource = new PSResource(name, filename);
/* 81 */     } else if ("form".equals(name)) {
/* 82 */       String formname = iter.next();
/* 83 */       this.resource = new PSResource(name, formname);
/* 84 */     } else if ("pattern".equals(name)) {
/* 85 */       String patternname = iter.next();
/* 86 */       this.resource = new PSResource(name, patternname);
/* 87 */     } else if ("encoding".equals(name)) {
/* 88 */       String encodingname = iter.next();
/* 89 */       this.resource = new PSResource(name, encodingname);
/*    */     } else {
/* 91 */       throw new IllegalArgumentException("Invalid resource type: " + name);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 97 */     gen.writeDSCComment(getName(), getResource());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/AbstractResourceDSCComment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
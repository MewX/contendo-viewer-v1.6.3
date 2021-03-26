/*    */ package org.apache.xmlgraphics.ps;
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
/*    */ public class PSProcSet
/*    */   extends PSResource
/*    */ {
/*    */   private float version;
/*    */   private int revision;
/*    */   
/*    */   public PSProcSet(String name) {
/* 35 */     this(name, 1.0F, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PSProcSet(String name, float version, int revision) {
/* 45 */     super("procset", name);
/* 46 */     this.version = version;
/* 47 */     this.revision = revision;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getVersion() {
/* 52 */     return this.version;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRevision() {
/* 57 */     return this.revision;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResourceSpecification() {
/* 62 */     StringBuffer sb = new StringBuffer();
/* 63 */     sb.append(getType()).append(" ").append(PSGenerator.convertStringToDSC(getName()));
/* 64 */     sb.append(" ").append(PSGenerator.convertRealToDSC(getVersion()));
/* 65 */     sb.append(" ").append(Integer.toString(getRevision()));
/* 66 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/PSProcSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
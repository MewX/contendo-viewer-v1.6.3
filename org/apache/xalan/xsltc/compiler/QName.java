/*    */ package org.apache.xalan.xsltc.compiler;
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
/*    */ final class QName
/*    */ {
/*    */   private final String _localname;
/*    */   private String _prefix;
/*    */   private String _namespace;
/*    */   private String _stringRep;
/*    */   private int _hashCode;
/*    */   
/*    */   public QName(String namespace, String prefix, String localname) {
/* 35 */     this._namespace = namespace;
/* 36 */     this._prefix = prefix;
/* 37 */     this._localname = localname;
/*    */     
/* 39 */     this._stringRep = (namespace != null && !namespace.equals("")) ? (namespace + ':' + localname) : localname;
/*    */ 
/*    */ 
/*    */     
/* 43 */     this._hashCode = this._stringRep.hashCode() + 19;
/*    */   }
/*    */   
/*    */   public void clearNamespace() {
/* 47 */     this._namespace = "";
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return this._stringRep;
/*    */   }
/*    */   
/*    */   public String getStringRep() {
/* 55 */     return this._stringRep;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 59 */     return (this == other);
/*    */   }
/*    */   
/*    */   public String getLocalPart() {
/* 63 */     return this._localname;
/*    */   }
/*    */   
/*    */   public String getNamespace() {
/* 67 */     return this._namespace;
/*    */   }
/*    */   
/*    */   public String getPrefix() {
/* 71 */     return this._prefix;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 75 */     return this._hashCode;
/*    */   }
/*    */   
/*    */   public String dump() {
/* 79 */     return new String("QName: " + this._namespace + "(" + this._prefix + "):" + this._localname);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/QName.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
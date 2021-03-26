/*    */ package org.apache.xalan.xsltc.runtime;
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
/*    */ public class Parameter
/*    */ {
/*    */   public String _name;
/*    */   public Object _value;
/*    */   public boolean _isDefault;
/*    */   
/*    */   public Parameter(String name, Object value) {
/* 34 */     this._name = name;
/* 35 */     this._value = value;
/* 36 */     this._isDefault = true;
/*    */   }
/*    */   
/*    */   public Parameter(String name, Object value, boolean isDefault) {
/* 40 */     this._name = name;
/* 41 */     this._value = value;
/* 42 */     this._isDefault = isDefault;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/Parameter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
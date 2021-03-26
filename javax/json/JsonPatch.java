/*     */ package javax.json;
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
/*     */ public interface JsonPatch
/*     */ {
/*     */   <T extends JsonStructure> T apply(T paramT);
/*     */   
/*     */   JsonArray toJsonArray();
/*     */   
/*     */   public enum Operation
/*     */   {
/*  85 */     ADD("add"),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     REMOVE("remove"),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     REPLACE("replace"),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     MOVE("move"),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     COPY("copy"),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     TEST("test");
/*     */     
/*     */     private final String a;
/*     */     
/*     */     Operation(String operationName) {
/* 115 */       this.a = operationName;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String operationName() {
/* 124 */       return this.a;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static Operation fromOperationName(String operationName) {
/* 135 */       for (Operation op : values()) {
/* 136 */         if (op.operationName().equalsIgnoreCase(operationName)) {
/* 137 */           return op;
/*     */         }
/*     */       } 
/* 140 */       throw new JsonException("Illegal value for the operationName of the JSON patch operation: " + operationName);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/JsonPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
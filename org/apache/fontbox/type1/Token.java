/*     */ package org.apache.fontbox.type1;
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
/*     */ class Token
/*     */ {
/*     */   enum Kind
/*     */   {
/*  35 */     NONE, STRING, NAME, LITERAL, REAL, INTEGER,
/*  36 */     START_ARRAY, END_ARRAY,
/*  37 */     START_PROC, END_PROC,
/*  38 */     START_DICT, END_DICT,
/*  39 */     CHARSTRING;
/*     */   }
/*     */ 
/*     */   
/*  43 */   static final Kind STRING = Kind.STRING;
/*  44 */   static final Kind NAME = Kind.NAME;
/*  45 */   static final Kind LITERAL = Kind.LITERAL;
/*  46 */   static final Kind REAL = Kind.REAL;
/*  47 */   static final Kind INTEGER = Kind.INTEGER;
/*  48 */   static final Kind START_ARRAY = Kind.START_ARRAY;
/*  49 */   static final Kind END_ARRAY = Kind.END_ARRAY;
/*  50 */   static final Kind START_PROC = Kind.START_PROC;
/*  51 */   static final Kind END_PROC = Kind.END_PROC;
/*  52 */   static final Kind CHARSTRING = Kind.CHARSTRING;
/*  53 */   static final Kind START_DICT = Kind.START_DICT;
/*  54 */   static final Kind END_DICT = Kind.END_DICT;
/*     */ 
/*     */   
/*     */   private String text;
/*     */ 
/*     */   
/*     */   private byte[] data;
/*     */ 
/*     */   
/*     */   private final Kind kind;
/*     */ 
/*     */   
/*     */   Token(String text, Kind type) {
/*  67 */     this.text = text;
/*  68 */     this.kind = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Token(char character, Kind type) {
/*  78 */     this.text = Character.toString(character);
/*  79 */     this.kind = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Token(byte[] data, Kind type) {
/*  90 */     this.data = data;
/*  91 */     this.kind = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/*  96 */     return this.text;
/*     */   }
/*     */ 
/*     */   
/*     */   public Kind getKind() {
/* 101 */     return this.kind;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int intValue() {
/* 107 */     return (int)Float.parseFloat(this.text);
/*     */   }
/*     */ 
/*     */   
/*     */   public float floatValue() {
/* 112 */     return Float.parseFloat(this.text);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean booleanValue() {
/* 117 */     return this.text.equals("true");
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getData() {
/* 122 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 131 */     if (this.kind == CHARSTRING)
/*     */     {
/* 133 */       return "Token[kind=CHARSTRING, data=" + this.data.length + " bytes]";
/*     */     }
/*     */ 
/*     */     
/* 137 */     return "Token[kind=" + this.kind + ", text=" + this.text + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/type1/Token.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
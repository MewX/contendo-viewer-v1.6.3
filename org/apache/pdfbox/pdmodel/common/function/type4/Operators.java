/*     */ package org.apache.pdfbox.pdmodel.common.function.type4;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class Operators
/*     */ {
/*  28 */   private static final Operator ABS = new ArithmeticOperators.Abs();
/*  29 */   private static final Operator ADD = new ArithmeticOperators.Add();
/*  30 */   private static final Operator ATAN = new ArithmeticOperators.Atan();
/*  31 */   private static final Operator CEILING = new ArithmeticOperators.Ceiling();
/*  32 */   private static final Operator COS = new ArithmeticOperators.Cos();
/*  33 */   private static final Operator CVI = new ArithmeticOperators.Cvi();
/*  34 */   private static final Operator CVR = new ArithmeticOperators.Cvr();
/*  35 */   private static final Operator DIV = new ArithmeticOperators.Div();
/*  36 */   private static final Operator EXP = new ArithmeticOperators.Exp();
/*  37 */   private static final Operator FLOOR = new ArithmeticOperators.Floor();
/*  38 */   private static final Operator IDIV = new ArithmeticOperators.IDiv();
/*  39 */   private static final Operator LN = new ArithmeticOperators.Ln();
/*  40 */   private static final Operator LOG = new ArithmeticOperators.Log();
/*  41 */   private static final Operator MOD = new ArithmeticOperators.Mod();
/*  42 */   private static final Operator MUL = new ArithmeticOperators.Mul();
/*  43 */   private static final Operator NEG = new ArithmeticOperators.Neg();
/*  44 */   private static final Operator ROUND = new ArithmeticOperators.Round();
/*  45 */   private static final Operator SIN = new ArithmeticOperators.Sin();
/*  46 */   private static final Operator SQRT = new ArithmeticOperators.Sqrt();
/*  47 */   private static final Operator SUB = new ArithmeticOperators.Sub();
/*  48 */   private static final Operator TRUNCATE = new ArithmeticOperators.Truncate();
/*     */ 
/*     */   
/*  51 */   private static final Operator AND = new BitwiseOperators.And();
/*  52 */   private static final Operator BITSHIFT = new BitwiseOperators.Bitshift();
/*  53 */   private static final Operator EQ = new RelationalOperators.Eq();
/*  54 */   private static final Operator FALSE = new BitwiseOperators.False();
/*  55 */   private static final Operator GE = new RelationalOperators.Ge();
/*  56 */   private static final Operator GT = new RelationalOperators.Gt();
/*  57 */   private static final Operator LE = new RelationalOperators.Le();
/*  58 */   private static final Operator LT = new RelationalOperators.Lt();
/*  59 */   private static final Operator NE = new RelationalOperators.Ne();
/*  60 */   private static final Operator NOT = new BitwiseOperators.Not();
/*  61 */   private static final Operator OR = new BitwiseOperators.Or();
/*  62 */   private static final Operator TRUE = new BitwiseOperators.True();
/*  63 */   private static final Operator XOR = new BitwiseOperators.Xor();
/*     */ 
/*     */   
/*  66 */   private static final Operator IF = new ConditionalOperators.If();
/*  67 */   private static final Operator IFELSE = new ConditionalOperators.IfElse();
/*     */ 
/*     */   
/*  70 */   private static final Operator COPY = new StackOperators.Copy();
/*  71 */   private static final Operator DUP = new StackOperators.Dup();
/*  72 */   private static final Operator EXCH = new StackOperators.Exch();
/*  73 */   private static final Operator INDEX = new StackOperators.Index();
/*  74 */   private static final Operator POP = new StackOperators.Pop();
/*  75 */   private static final Operator ROLL = new StackOperators.Roll();
/*     */   
/*  77 */   private final Map<String, Operator> operators = new HashMap<String, Operator>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Operators() {
/*  84 */     this.operators.put("add", ADD);
/*  85 */     this.operators.put("abs", ABS);
/*  86 */     this.operators.put("atan", ATAN);
/*  87 */     this.operators.put("ceiling", CEILING);
/*  88 */     this.operators.put("cos", COS);
/*  89 */     this.operators.put("cvi", CVI);
/*  90 */     this.operators.put("cvr", CVR);
/*  91 */     this.operators.put("div", DIV);
/*  92 */     this.operators.put("exp", EXP);
/*  93 */     this.operators.put("floor", FLOOR);
/*  94 */     this.operators.put("idiv", IDIV);
/*  95 */     this.operators.put("ln", LN);
/*  96 */     this.operators.put("log", LOG);
/*  97 */     this.operators.put("mod", MOD);
/*  98 */     this.operators.put("mul", MUL);
/*  99 */     this.operators.put("neg", NEG);
/* 100 */     this.operators.put("round", ROUND);
/* 101 */     this.operators.put("sin", SIN);
/* 102 */     this.operators.put("sqrt", SQRT);
/* 103 */     this.operators.put("sub", SUB);
/* 104 */     this.operators.put("truncate", TRUNCATE);
/*     */     
/* 106 */     this.operators.put("and", AND);
/* 107 */     this.operators.put("bitshift", BITSHIFT);
/* 108 */     this.operators.put("eq", EQ);
/* 109 */     this.operators.put("false", FALSE);
/* 110 */     this.operators.put("ge", GE);
/* 111 */     this.operators.put("gt", GT);
/* 112 */     this.operators.put("le", LE);
/* 113 */     this.operators.put("lt", LT);
/* 114 */     this.operators.put("ne", NE);
/* 115 */     this.operators.put("not", NOT);
/* 116 */     this.operators.put("or", OR);
/* 117 */     this.operators.put("true", TRUE);
/* 118 */     this.operators.put("xor", XOR);
/*     */     
/* 120 */     this.operators.put("if", IF);
/* 121 */     this.operators.put("ifelse", IFELSE);
/*     */     
/* 123 */     this.operators.put("copy", COPY);
/* 124 */     this.operators.put("dup", DUP);
/* 125 */     this.operators.put("exch", EXCH);
/* 126 */     this.operators.put("index", INDEX);
/* 127 */     this.operators.put("pop", POP);
/* 128 */     this.operators.put("roll", ROLL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Operator getOperator(String operatorName) {
/* 138 */     return this.operators.get(operatorName);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/type4/Operators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
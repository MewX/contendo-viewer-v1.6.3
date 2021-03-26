/*     */ package org.apache.bcel.generic;
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
/*     */ public interface InstructionConstants
/*     */ {
/*  79 */   public static final Instruction NOP = new NOP();
/*  80 */   public static final Instruction ACONST_NULL = new ACONST_NULL();
/*  81 */   public static final Instruction ICONST_M1 = new ICONST(-1);
/*  82 */   public static final Instruction ICONST_0 = new ICONST(0);
/*  83 */   public static final Instruction ICONST_1 = new ICONST(1);
/*  84 */   public static final Instruction ICONST_2 = new ICONST(2);
/*  85 */   public static final Instruction ICONST_3 = new ICONST(3);
/*  86 */   public static final Instruction ICONST_4 = new ICONST(4);
/*  87 */   public static final Instruction ICONST_5 = new ICONST(5);
/*  88 */   public static final Instruction LCONST_0 = new LCONST(0L);
/*  89 */   public static final Instruction LCONST_1 = new LCONST(1L);
/*  90 */   public static final Instruction FCONST_0 = new FCONST(0.0F);
/*  91 */   public static final Instruction FCONST_1 = new FCONST(1.0F);
/*  92 */   public static final Instruction FCONST_2 = new FCONST(2.0F);
/*  93 */   public static final Instruction DCONST_0 = new DCONST(0.0D);
/*  94 */   public static final Instruction DCONST_1 = new DCONST(1.0D);
/*  95 */   public static final ArrayInstruction IALOAD = new IALOAD();
/*  96 */   public static final ArrayInstruction LALOAD = new LALOAD();
/*  97 */   public static final ArrayInstruction FALOAD = new FALOAD();
/*  98 */   public static final ArrayInstruction DALOAD = new DALOAD();
/*  99 */   public static final ArrayInstruction AALOAD = new AALOAD();
/* 100 */   public static final ArrayInstruction BALOAD = new BALOAD();
/* 101 */   public static final ArrayInstruction CALOAD = new CALOAD();
/* 102 */   public static final ArrayInstruction SALOAD = new SALOAD();
/* 103 */   public static final ArrayInstruction IASTORE = new IASTORE();
/* 104 */   public static final ArrayInstruction LASTORE = new LASTORE();
/* 105 */   public static final ArrayInstruction FASTORE = new FASTORE();
/* 106 */   public static final ArrayInstruction DASTORE = new DASTORE();
/* 107 */   public static final ArrayInstruction AASTORE = new AASTORE();
/* 108 */   public static final ArrayInstruction BASTORE = new BASTORE();
/* 109 */   public static final ArrayInstruction CASTORE = new CASTORE();
/* 110 */   public static final ArrayInstruction SASTORE = new SASTORE();
/* 111 */   public static final StackInstruction POP = new POP();
/* 112 */   public static final StackInstruction POP2 = new POP2();
/* 113 */   public static final StackInstruction DUP = new DUP();
/* 114 */   public static final StackInstruction DUP_X1 = new DUP_X1();
/* 115 */   public static final StackInstruction DUP_X2 = new DUP_X2();
/* 116 */   public static final StackInstruction DUP2 = new DUP2();
/* 117 */   public static final StackInstruction DUP2_X1 = new DUP2_X1();
/* 118 */   public static final StackInstruction DUP2_X2 = new DUP2_X2();
/* 119 */   public static final StackInstruction SWAP = new SWAP();
/* 120 */   public static final ArithmeticInstruction IADD = new IADD();
/* 121 */   public static final ArithmeticInstruction LADD = new LADD();
/* 122 */   public static final ArithmeticInstruction FADD = new FADD();
/* 123 */   public static final ArithmeticInstruction DADD = new DADD();
/* 124 */   public static final ArithmeticInstruction ISUB = new ISUB();
/* 125 */   public static final ArithmeticInstruction LSUB = new LSUB();
/* 126 */   public static final ArithmeticInstruction FSUB = new FSUB();
/* 127 */   public static final ArithmeticInstruction DSUB = new DSUB();
/* 128 */   public static final ArithmeticInstruction IMUL = new IMUL();
/* 129 */   public static final ArithmeticInstruction LMUL = new LMUL();
/* 130 */   public static final ArithmeticInstruction FMUL = new FMUL();
/* 131 */   public static final ArithmeticInstruction DMUL = new DMUL();
/* 132 */   public static final ArithmeticInstruction IDIV = new IDIV();
/* 133 */   public static final ArithmeticInstruction LDIV = new LDIV();
/* 134 */   public static final ArithmeticInstruction FDIV = new FDIV();
/* 135 */   public static final ArithmeticInstruction DDIV = new DDIV();
/* 136 */   public static final ArithmeticInstruction IREM = new IREM();
/* 137 */   public static final ArithmeticInstruction LREM = new LREM();
/* 138 */   public static final ArithmeticInstruction FREM = new FREM();
/* 139 */   public static final ArithmeticInstruction DREM = new DREM();
/* 140 */   public static final ArithmeticInstruction INEG = new INEG();
/* 141 */   public static final ArithmeticInstruction LNEG = new LNEG();
/* 142 */   public static final ArithmeticInstruction FNEG = new FNEG();
/* 143 */   public static final ArithmeticInstruction DNEG = new DNEG();
/* 144 */   public static final ArithmeticInstruction ISHL = new ISHL();
/* 145 */   public static final ArithmeticInstruction LSHL = new LSHL();
/* 146 */   public static final ArithmeticInstruction ISHR = new ISHR();
/* 147 */   public static final ArithmeticInstruction LSHR = new LSHR();
/* 148 */   public static final ArithmeticInstruction IUSHR = new IUSHR();
/* 149 */   public static final ArithmeticInstruction LUSHR = new LUSHR();
/* 150 */   public static final ArithmeticInstruction IAND = new IAND();
/* 151 */   public static final ArithmeticInstruction LAND = new LAND();
/* 152 */   public static final ArithmeticInstruction IOR = new IOR();
/* 153 */   public static final ArithmeticInstruction LOR = new LOR();
/* 154 */   public static final ArithmeticInstruction IXOR = new IXOR();
/* 155 */   public static final ArithmeticInstruction LXOR = new LXOR();
/* 156 */   public static final ConversionInstruction I2L = new I2L();
/* 157 */   public static final ConversionInstruction I2F = new I2F();
/* 158 */   public static final ConversionInstruction I2D = new I2D();
/* 159 */   public static final ConversionInstruction L2I = new L2I();
/* 160 */   public static final ConversionInstruction L2F = new L2F();
/* 161 */   public static final ConversionInstruction L2D = new L2D();
/* 162 */   public static final ConversionInstruction F2I = new F2I();
/* 163 */   public static final ConversionInstruction F2L = new F2L();
/* 164 */   public static final ConversionInstruction F2D = new F2D();
/* 165 */   public static final ConversionInstruction D2I = new D2I();
/* 166 */   public static final ConversionInstruction D2L = new D2L();
/* 167 */   public static final ConversionInstruction D2F = new D2F();
/* 168 */   public static final ConversionInstruction I2B = new I2B();
/* 169 */   public static final ConversionInstruction I2C = new I2C();
/* 170 */   public static final ConversionInstruction I2S = new I2S();
/* 171 */   public static final Instruction LCMP = new LCMP();
/* 172 */   public static final Instruction FCMPL = new FCMPL();
/* 173 */   public static final Instruction FCMPG = new FCMPG();
/* 174 */   public static final Instruction DCMPL = new DCMPL();
/* 175 */   public static final Instruction DCMPG = new DCMPG();
/* 176 */   public static final ReturnInstruction IRETURN = new IRETURN();
/* 177 */   public static final ReturnInstruction LRETURN = new LRETURN();
/* 178 */   public static final ReturnInstruction FRETURN = new FRETURN();
/* 179 */   public static final ReturnInstruction DRETURN = new DRETURN();
/* 180 */   public static final ReturnInstruction ARETURN = new ARETURN();
/* 181 */   public static final ReturnInstruction RETURN = new RETURN();
/* 182 */   public static final Instruction ARRAYLENGTH = new ARRAYLENGTH();
/* 183 */   public static final Instruction ATHROW = new ATHROW();
/* 184 */   public static final Instruction MONITORENTER = new MONITORENTER();
/* 185 */   public static final Instruction MONITOREXIT = new MONITOREXIT();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public static final LocalVariableInstruction THIS = new ALOAD(0);
/* 191 */   public static final LocalVariableInstruction ALOAD_0 = THIS;
/* 192 */   public static final LocalVariableInstruction ALOAD_1 = new ALOAD(1);
/* 193 */   public static final LocalVariableInstruction ALOAD_2 = new ALOAD(2);
/* 194 */   public static final LocalVariableInstruction ILOAD_0 = new ILOAD(0);
/* 195 */   public static final LocalVariableInstruction ILOAD_1 = new ILOAD(1);
/* 196 */   public static final LocalVariableInstruction ILOAD_2 = new ILOAD(2);
/* 197 */   public static final LocalVariableInstruction ASTORE_0 = new ASTORE(0);
/* 198 */   public static final LocalVariableInstruction ASTORE_1 = new ASTORE(1);
/* 199 */   public static final LocalVariableInstruction ASTORE_2 = new ASTORE(2);
/* 200 */   public static final LocalVariableInstruction ISTORE_0 = new ISTORE(0);
/* 201 */   public static final LocalVariableInstruction ISTORE_1 = new ISTORE(1);
/* 202 */   public static final LocalVariableInstruction ISTORE_2 = new ISTORE(2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public static final Instruction[] INSTRUCTIONS = new Instruction[256];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public static final Clinit bla = new Clinit();
/*     */   
/*     */   public static class Clinit {
/*     */     Clinit() {
/* 217 */       InstructionConstants.INSTRUCTIONS[0] = InstructionConstants.NOP;
/* 218 */       InstructionConstants.INSTRUCTIONS[1] = InstructionConstants.ACONST_NULL;
/* 219 */       InstructionConstants.INSTRUCTIONS[2] = InstructionConstants.ICONST_M1;
/* 220 */       InstructionConstants.INSTRUCTIONS[3] = InstructionConstants.ICONST_0;
/* 221 */       InstructionConstants.INSTRUCTIONS[4] = InstructionConstants.ICONST_1;
/* 222 */       InstructionConstants.INSTRUCTIONS[5] = InstructionConstants.ICONST_2;
/* 223 */       InstructionConstants.INSTRUCTIONS[6] = InstructionConstants.ICONST_3;
/* 224 */       InstructionConstants.INSTRUCTIONS[7] = InstructionConstants.ICONST_4;
/* 225 */       InstructionConstants.INSTRUCTIONS[8] = InstructionConstants.ICONST_5;
/* 226 */       InstructionConstants.INSTRUCTIONS[9] = InstructionConstants.LCONST_0;
/* 227 */       InstructionConstants.INSTRUCTIONS[10] = InstructionConstants.LCONST_1;
/* 228 */       InstructionConstants.INSTRUCTIONS[11] = InstructionConstants.FCONST_0;
/* 229 */       InstructionConstants.INSTRUCTIONS[12] = InstructionConstants.FCONST_1;
/* 230 */       InstructionConstants.INSTRUCTIONS[13] = InstructionConstants.FCONST_2;
/* 231 */       InstructionConstants.INSTRUCTIONS[14] = InstructionConstants.DCONST_0;
/* 232 */       InstructionConstants.INSTRUCTIONS[15] = InstructionConstants.DCONST_1;
/* 233 */       InstructionConstants.INSTRUCTIONS[46] = InstructionConstants.IALOAD;
/* 234 */       InstructionConstants.INSTRUCTIONS[47] = InstructionConstants.LALOAD;
/* 235 */       InstructionConstants.INSTRUCTIONS[48] = InstructionConstants.FALOAD;
/* 236 */       InstructionConstants.INSTRUCTIONS[49] = InstructionConstants.DALOAD;
/* 237 */       InstructionConstants.INSTRUCTIONS[50] = InstructionConstants.AALOAD;
/* 238 */       InstructionConstants.INSTRUCTIONS[51] = InstructionConstants.BALOAD;
/* 239 */       InstructionConstants.INSTRUCTIONS[52] = InstructionConstants.CALOAD;
/* 240 */       InstructionConstants.INSTRUCTIONS[53] = InstructionConstants.SALOAD;
/* 241 */       InstructionConstants.INSTRUCTIONS[79] = InstructionConstants.IASTORE;
/* 242 */       InstructionConstants.INSTRUCTIONS[80] = InstructionConstants.LASTORE;
/* 243 */       InstructionConstants.INSTRUCTIONS[81] = InstructionConstants.FASTORE;
/* 244 */       InstructionConstants.INSTRUCTIONS[82] = InstructionConstants.DASTORE;
/* 245 */       InstructionConstants.INSTRUCTIONS[83] = InstructionConstants.AASTORE;
/* 246 */       InstructionConstants.INSTRUCTIONS[84] = InstructionConstants.BASTORE;
/* 247 */       InstructionConstants.INSTRUCTIONS[85] = InstructionConstants.CASTORE;
/* 248 */       InstructionConstants.INSTRUCTIONS[86] = InstructionConstants.SASTORE;
/* 249 */       InstructionConstants.INSTRUCTIONS[87] = InstructionConstants.POP;
/* 250 */       InstructionConstants.INSTRUCTIONS[88] = InstructionConstants.POP2;
/* 251 */       InstructionConstants.INSTRUCTIONS[89] = InstructionConstants.DUP;
/* 252 */       InstructionConstants.INSTRUCTIONS[90] = InstructionConstants.DUP_X1;
/* 253 */       InstructionConstants.INSTRUCTIONS[91] = InstructionConstants.DUP_X2;
/* 254 */       InstructionConstants.INSTRUCTIONS[92] = InstructionConstants.DUP2;
/* 255 */       InstructionConstants.INSTRUCTIONS[93] = InstructionConstants.DUP2_X1;
/* 256 */       InstructionConstants.INSTRUCTIONS[94] = InstructionConstants.DUP2_X2;
/* 257 */       InstructionConstants.INSTRUCTIONS[95] = InstructionConstants.SWAP;
/* 258 */       InstructionConstants.INSTRUCTIONS[96] = InstructionConstants.IADD;
/* 259 */       InstructionConstants.INSTRUCTIONS[97] = InstructionConstants.LADD;
/* 260 */       InstructionConstants.INSTRUCTIONS[98] = InstructionConstants.FADD;
/* 261 */       InstructionConstants.INSTRUCTIONS[99] = InstructionConstants.DADD;
/* 262 */       InstructionConstants.INSTRUCTIONS[100] = InstructionConstants.ISUB;
/* 263 */       InstructionConstants.INSTRUCTIONS[101] = InstructionConstants.LSUB;
/* 264 */       InstructionConstants.INSTRUCTIONS[102] = InstructionConstants.FSUB;
/* 265 */       InstructionConstants.INSTRUCTIONS[103] = InstructionConstants.DSUB;
/* 266 */       InstructionConstants.INSTRUCTIONS[104] = InstructionConstants.IMUL;
/* 267 */       InstructionConstants.INSTRUCTIONS[105] = InstructionConstants.LMUL;
/* 268 */       InstructionConstants.INSTRUCTIONS[106] = InstructionConstants.FMUL;
/* 269 */       InstructionConstants.INSTRUCTIONS[107] = InstructionConstants.DMUL;
/* 270 */       InstructionConstants.INSTRUCTIONS[108] = InstructionConstants.IDIV;
/* 271 */       InstructionConstants.INSTRUCTIONS[109] = InstructionConstants.LDIV;
/* 272 */       InstructionConstants.INSTRUCTIONS[110] = InstructionConstants.FDIV;
/* 273 */       InstructionConstants.INSTRUCTIONS[111] = InstructionConstants.DDIV;
/* 274 */       InstructionConstants.INSTRUCTIONS[112] = InstructionConstants.IREM;
/* 275 */       InstructionConstants.INSTRUCTIONS[113] = InstructionConstants.LREM;
/* 276 */       InstructionConstants.INSTRUCTIONS[114] = InstructionConstants.FREM;
/* 277 */       InstructionConstants.INSTRUCTIONS[115] = InstructionConstants.DREM;
/* 278 */       InstructionConstants.INSTRUCTIONS[116] = InstructionConstants.INEG;
/* 279 */       InstructionConstants.INSTRUCTIONS[117] = InstructionConstants.LNEG;
/* 280 */       InstructionConstants.INSTRUCTIONS[118] = InstructionConstants.FNEG;
/* 281 */       InstructionConstants.INSTRUCTIONS[119] = InstructionConstants.DNEG;
/* 282 */       InstructionConstants.INSTRUCTIONS[120] = InstructionConstants.ISHL;
/* 283 */       InstructionConstants.INSTRUCTIONS[121] = InstructionConstants.LSHL;
/* 284 */       InstructionConstants.INSTRUCTIONS[122] = InstructionConstants.ISHR;
/* 285 */       InstructionConstants.INSTRUCTIONS[123] = InstructionConstants.LSHR;
/* 286 */       InstructionConstants.INSTRUCTIONS[124] = InstructionConstants.IUSHR;
/* 287 */       InstructionConstants.INSTRUCTIONS[125] = InstructionConstants.LUSHR;
/* 288 */       InstructionConstants.INSTRUCTIONS[126] = InstructionConstants.IAND;
/* 289 */       InstructionConstants.INSTRUCTIONS[127] = InstructionConstants.LAND;
/* 290 */       InstructionConstants.INSTRUCTIONS[128] = InstructionConstants.IOR;
/* 291 */       InstructionConstants.INSTRUCTIONS[129] = InstructionConstants.LOR;
/* 292 */       InstructionConstants.INSTRUCTIONS[130] = InstructionConstants.IXOR;
/* 293 */       InstructionConstants.INSTRUCTIONS[131] = InstructionConstants.LXOR;
/* 294 */       InstructionConstants.INSTRUCTIONS[133] = InstructionConstants.I2L;
/* 295 */       InstructionConstants.INSTRUCTIONS[134] = InstructionConstants.I2F;
/* 296 */       InstructionConstants.INSTRUCTIONS[135] = InstructionConstants.I2D;
/* 297 */       InstructionConstants.INSTRUCTIONS[136] = InstructionConstants.L2I;
/* 298 */       InstructionConstants.INSTRUCTIONS[137] = InstructionConstants.L2F;
/* 299 */       InstructionConstants.INSTRUCTIONS[138] = InstructionConstants.L2D;
/* 300 */       InstructionConstants.INSTRUCTIONS[139] = InstructionConstants.F2I;
/* 301 */       InstructionConstants.INSTRUCTIONS[140] = InstructionConstants.F2L;
/* 302 */       InstructionConstants.INSTRUCTIONS[141] = InstructionConstants.F2D;
/* 303 */       InstructionConstants.INSTRUCTIONS[142] = InstructionConstants.D2I;
/* 304 */       InstructionConstants.INSTRUCTIONS[143] = InstructionConstants.D2L;
/* 305 */       InstructionConstants.INSTRUCTIONS[144] = InstructionConstants.D2F;
/* 306 */       InstructionConstants.INSTRUCTIONS[145] = InstructionConstants.I2B;
/* 307 */       InstructionConstants.INSTRUCTIONS[146] = InstructionConstants.I2C;
/* 308 */       InstructionConstants.INSTRUCTIONS[147] = InstructionConstants.I2S;
/* 309 */       InstructionConstants.INSTRUCTIONS[148] = InstructionConstants.LCMP;
/* 310 */       InstructionConstants.INSTRUCTIONS[149] = InstructionConstants.FCMPL;
/* 311 */       InstructionConstants.INSTRUCTIONS[150] = InstructionConstants.FCMPG;
/* 312 */       InstructionConstants.INSTRUCTIONS[151] = InstructionConstants.DCMPL;
/* 313 */       InstructionConstants.INSTRUCTIONS[152] = InstructionConstants.DCMPG;
/* 314 */       InstructionConstants.INSTRUCTIONS[172] = InstructionConstants.IRETURN;
/* 315 */       InstructionConstants.INSTRUCTIONS[173] = InstructionConstants.LRETURN;
/* 316 */       InstructionConstants.INSTRUCTIONS[174] = InstructionConstants.FRETURN;
/* 317 */       InstructionConstants.INSTRUCTIONS[175] = InstructionConstants.DRETURN;
/* 318 */       InstructionConstants.INSTRUCTIONS[176] = InstructionConstants.ARETURN;
/* 319 */       InstructionConstants.INSTRUCTIONS[177] = InstructionConstants.RETURN;
/* 320 */       InstructionConstants.INSTRUCTIONS[190] = InstructionConstants.ARRAYLENGTH;
/* 321 */       InstructionConstants.INSTRUCTIONS[191] = InstructionConstants.ATHROW;
/* 322 */       InstructionConstants.INSTRUCTIONS[194] = InstructionConstants.MONITORENTER;
/* 323 */       InstructionConstants.INSTRUCTIONS[195] = InstructionConstants.MONITOREXIT;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/InstructionConstants.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
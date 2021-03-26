/*     */ package org.apache.commons.lang3.builder;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.Validate;
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
/*     */ public class DiffBuilder
/*     */   implements Builder<DiffResult>
/*     */ {
/*     */   private final List<Diff<?>> diffs;
/*     */   private final boolean objectsTriviallyEqual;
/*     */   private final Object left;
/*     */   private final Object right;
/*     */   private final ToStringStyle style;
/*     */   
/*     */   public DiffBuilder(Object lhs, Object rhs, ToStringStyle style, boolean testTriviallyEqual) {
/* 106 */     Validate.isTrue((lhs != null), "lhs cannot be null", new Object[0]);
/* 107 */     Validate.isTrue((rhs != null), "rhs cannot be null", new Object[0]);
/*     */     
/* 109 */     this.diffs = new ArrayList<>();
/* 110 */     this.left = lhs;
/* 111 */     this.right = rhs;
/* 112 */     this.style = style;
/*     */ 
/*     */     
/* 115 */     this.objectsTriviallyEqual = (testTriviallyEqual && (lhs == rhs || lhs.equals(rhs)));
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
/*     */   public DiffBuilder(Object lhs, Object rhs, ToStringStyle style) {
/* 147 */     this(lhs, rhs, style, true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final boolean lhs, final boolean rhs) {
/* 167 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 169 */     if (this.objectsTriviallyEqual) {
/* 170 */       return this;
/*     */     }
/* 172 */     if (lhs != rhs) {
/* 173 */       this.diffs.add(new Diff<Boolean>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Boolean getLeft() {
/* 178 */               return Boolean.valueOf(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Boolean getRight() {
/* 183 */               return Boolean.valueOf(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 187 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final boolean[] lhs, final boolean[] rhs) {
/* 207 */     validateFieldNameNotNull(fieldName);
/* 208 */     if (this.objectsTriviallyEqual) {
/* 209 */       return this;
/*     */     }
/* 211 */     if (!Arrays.equals(lhs, rhs)) {
/* 212 */       this.diffs.add(new Diff<Boolean[]>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Boolean[] getLeft() {
/* 217 */               return ArrayUtils.toObject(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Boolean[] getRight() {
/* 222 */               return ArrayUtils.toObject(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 226 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final byte lhs, final byte rhs) {
/* 246 */     validateFieldNameNotNull(fieldName);
/* 247 */     if (this.objectsTriviallyEqual) {
/* 248 */       return this;
/*     */     }
/* 250 */     if (lhs != rhs) {
/* 251 */       this.diffs.add(new Diff<Byte>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Byte getLeft() {
/* 256 */               return Byte.valueOf(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Byte getRight() {
/* 261 */               return Byte.valueOf(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 265 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final byte[] lhs, final byte[] rhs) {
/* 285 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 287 */     if (this.objectsTriviallyEqual) {
/* 288 */       return this;
/*     */     }
/* 290 */     if (!Arrays.equals(lhs, rhs)) {
/* 291 */       this.diffs.add(new Diff<Byte[]>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Byte[] getLeft() {
/* 296 */               return ArrayUtils.toObject(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Byte[] getRight() {
/* 301 */               return ArrayUtils.toObject(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 305 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final char lhs, final char rhs) {
/* 325 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 327 */     if (this.objectsTriviallyEqual) {
/* 328 */       return this;
/*     */     }
/* 330 */     if (lhs != rhs) {
/* 331 */       this.diffs.add(new Diff<Character>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Character getLeft() {
/* 336 */               return Character.valueOf(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Character getRight() {
/* 341 */               return Character.valueOf(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 345 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final char[] lhs, final char[] rhs) {
/* 365 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 367 */     if (this.objectsTriviallyEqual) {
/* 368 */       return this;
/*     */     }
/* 370 */     if (!Arrays.equals(lhs, rhs)) {
/* 371 */       this.diffs.add(new Diff<Character[]>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Character[] getLeft() {
/* 376 */               return ArrayUtils.toObject(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Character[] getRight() {
/* 381 */               return ArrayUtils.toObject(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 385 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final double lhs, final double rhs) {
/* 405 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 407 */     if (this.objectsTriviallyEqual) {
/* 408 */       return this;
/*     */     }
/* 410 */     if (Double.doubleToLongBits(lhs) != Double.doubleToLongBits(rhs)) {
/* 411 */       this.diffs.add(new Diff<Double>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Double getLeft() {
/* 416 */               return Double.valueOf(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Double getRight() {
/* 421 */               return Double.valueOf(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 425 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final double[] lhs, final double[] rhs) {
/* 445 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 447 */     if (this.objectsTriviallyEqual) {
/* 448 */       return this;
/*     */     }
/* 450 */     if (!Arrays.equals(lhs, rhs)) {
/* 451 */       this.diffs.add(new Diff<Double[]>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Double[] getLeft() {
/* 456 */               return ArrayUtils.toObject(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Double[] getRight() {
/* 461 */               return ArrayUtils.toObject(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 465 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final float lhs, final float rhs) {
/* 485 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 487 */     if (this.objectsTriviallyEqual) {
/* 488 */       return this;
/*     */     }
/* 490 */     if (Float.floatToIntBits(lhs) != Float.floatToIntBits(rhs)) {
/* 491 */       this.diffs.add(new Diff<Float>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Float getLeft() {
/* 496 */               return Float.valueOf(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Float getRight() {
/* 501 */               return Float.valueOf(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 505 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final float[] lhs, final float[] rhs) {
/* 525 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 527 */     if (this.objectsTriviallyEqual) {
/* 528 */       return this;
/*     */     }
/* 530 */     if (!Arrays.equals(lhs, rhs)) {
/* 531 */       this.diffs.add(new Diff<Float[]>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Float[] getLeft() {
/* 536 */               return ArrayUtils.toObject(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Float[] getRight() {
/* 541 */               return ArrayUtils.toObject(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 545 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final int lhs, final int rhs) {
/* 565 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 567 */     if (this.objectsTriviallyEqual) {
/* 568 */       return this;
/*     */     }
/* 570 */     if (lhs != rhs) {
/* 571 */       this.diffs.add(new Diff<Integer>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Integer getLeft() {
/* 576 */               return Integer.valueOf(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Integer getRight() {
/* 581 */               return Integer.valueOf(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 585 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final int[] lhs, final int[] rhs) {
/* 605 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 607 */     if (this.objectsTriviallyEqual) {
/* 608 */       return this;
/*     */     }
/* 610 */     if (!Arrays.equals(lhs, rhs)) {
/* 611 */       this.diffs.add(new Diff<Integer[]>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Integer[] getLeft() {
/* 616 */               return ArrayUtils.toObject(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Integer[] getRight() {
/* 621 */               return ArrayUtils.toObject(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 625 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final long lhs, final long rhs) {
/* 645 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 647 */     if (this.objectsTriviallyEqual) {
/* 648 */       return this;
/*     */     }
/* 650 */     if (lhs != rhs) {
/* 651 */       this.diffs.add(new Diff<Long>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Long getLeft() {
/* 656 */               return Long.valueOf(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Long getRight() {
/* 661 */               return Long.valueOf(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 665 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final long[] lhs, final long[] rhs) {
/* 685 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 687 */     if (this.objectsTriviallyEqual) {
/* 688 */       return this;
/*     */     }
/* 690 */     if (!Arrays.equals(lhs, rhs)) {
/* 691 */       this.diffs.add(new Diff<Long[]>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Long[] getLeft() {
/* 696 */               return ArrayUtils.toObject(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Long[] getRight() {
/* 701 */               return ArrayUtils.toObject(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 705 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final short lhs, final short rhs) {
/* 725 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 727 */     if (this.objectsTriviallyEqual) {
/* 728 */       return this;
/*     */     }
/* 730 */     if (lhs != rhs) {
/* 731 */       this.diffs.add(new Diff<Short>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Short getLeft() {
/* 736 */               return Short.valueOf(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Short getRight() {
/* 741 */               return Short.valueOf(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 745 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final short[] lhs, final short[] rhs) {
/* 765 */     validateFieldNameNotNull(fieldName);
/*     */     
/* 767 */     if (this.objectsTriviallyEqual) {
/* 768 */       return this;
/*     */     }
/* 770 */     if (!Arrays.equals(lhs, rhs)) {
/* 771 */       this.diffs.add(new Diff<Short[]>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Short[] getLeft() {
/* 776 */               return ArrayUtils.toObject(lhs);
/*     */             }
/*     */ 
/*     */             
/*     */             public Short[] getRight() {
/* 781 */               return ArrayUtils.toObject(rhs);
/*     */             }
/*     */           });
/*     */     }
/* 785 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final Object lhs, final Object rhs) {
/*     */     Object objectToTest;
/* 805 */     validateFieldNameNotNull(fieldName);
/* 806 */     if (this.objectsTriviallyEqual) {
/* 807 */       return this;
/*     */     }
/* 809 */     if (lhs == rhs) {
/* 810 */       return this;
/*     */     }
/*     */ 
/*     */     
/* 814 */     if (lhs != null) {
/* 815 */       objectToTest = lhs;
/*     */     } else {
/*     */       
/* 818 */       objectToTest = rhs;
/*     */     } 
/*     */     
/* 821 */     if (objectToTest.getClass().isArray()) {
/* 822 */       if (objectToTest instanceof boolean[]) {
/* 823 */         return append(fieldName, (boolean[])lhs, (boolean[])rhs);
/*     */       }
/* 825 */       if (objectToTest instanceof byte[]) {
/* 826 */         return append(fieldName, (byte[])lhs, (byte[])rhs);
/*     */       }
/* 828 */       if (objectToTest instanceof char[]) {
/* 829 */         return append(fieldName, (char[])lhs, (char[])rhs);
/*     */       }
/* 831 */       if (objectToTest instanceof double[]) {
/* 832 */         return append(fieldName, (double[])lhs, (double[])rhs);
/*     */       }
/* 834 */       if (objectToTest instanceof float[]) {
/* 835 */         return append(fieldName, (float[])lhs, (float[])rhs);
/*     */       }
/* 837 */       if (objectToTest instanceof int[]) {
/* 838 */         return append(fieldName, (int[])lhs, (int[])rhs);
/*     */       }
/* 840 */       if (objectToTest instanceof long[]) {
/* 841 */         return append(fieldName, (long[])lhs, (long[])rhs);
/*     */       }
/* 843 */       if (objectToTest instanceof short[]) {
/* 844 */         return append(fieldName, (short[])lhs, (short[])rhs);
/*     */       }
/*     */       
/* 847 */       return append(fieldName, (Object[])lhs, (Object[])rhs);
/*     */     } 
/*     */ 
/*     */     
/* 851 */     if (lhs != null && lhs.equals(rhs)) {
/* 852 */       return this;
/*     */     }
/*     */     
/* 855 */     this.diffs.add(new Diff(fieldName)
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */           
/*     */           public Object getLeft() {
/* 860 */             return lhs;
/*     */           }
/*     */ 
/*     */           
/*     */           public Object getRight() {
/* 865 */             return rhs;
/*     */           }
/*     */         });
/*     */     
/* 869 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffBuilder append(String fieldName, final Object[] lhs, final Object[] rhs) {
/* 889 */     validateFieldNameNotNull(fieldName);
/* 890 */     if (this.objectsTriviallyEqual) {
/* 891 */       return this;
/*     */     }
/*     */     
/* 894 */     if (!Arrays.equals(lhs, rhs)) {
/* 895 */       this.diffs.add(new Diff<Object[]>(fieldName)
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Object[] getLeft() {
/* 900 */               return lhs;
/*     */             }
/*     */ 
/*     */             
/*     */             public Object[] getRight() {
/* 905 */               return rhs;
/*     */             }
/*     */           });
/*     */     }
/*     */     
/* 910 */     return this;
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
/*     */   public DiffBuilder append(String fieldName, DiffResult diffResult) {
/* 951 */     validateFieldNameNotNull(fieldName);
/* 952 */     Validate.isTrue((diffResult != null), "Diff result cannot be null", new Object[0]);
/* 953 */     if (this.objectsTriviallyEqual) {
/* 954 */       return this;
/*     */     }
/*     */     
/* 957 */     for (Diff<?> diff : diffResult.getDiffs()) {
/* 958 */       append(fieldName + "." + diff.getFieldName(), diff
/* 959 */           .getLeft(), diff.getRight());
/*     */     }
/*     */     
/* 962 */     return this;
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
/*     */ 
/*     */   
/*     */   public DiffResult build() {
/* 976 */     return new DiffResult(this.left, this.right, this.diffs, this.style);
/*     */   }
/*     */   
/*     */   private void validateFieldNameNotNull(String fieldName) {
/* 980 */     Validate.isTrue((fieldName != null), "Field name cannot be null", new Object[0]);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/builder/DiffBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
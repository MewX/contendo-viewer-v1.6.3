/*      */ package com.github.jaiimageio.plugins.tiff;
/*      */ 
/*      */ import com.github.jaiimageio.impl.plugins.tiff.TIFFFieldNode;
/*      */ import java.util.StringTokenizer;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TIFFField
/*      */   implements Comparable
/*      */ {
/*  292 */   private static final String[] typeNames = new String[] { null, "Byte", "Ascii", "Short", "Long", "Rational", "SByte", "Undefined", "SShort", "SLong", "SRational", "Float", "Double", "IFDPointer" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  299 */   private static final boolean[] isIntegral = new boolean[] { 
/*      */       false, true, false, true, true, false, true, true, true, true, 
/*      */       false, false, false, false };
/*      */ 
/*      */   
/*      */   private TIFFTag tag;
/*      */ 
/*      */   
/*      */   private int tagNumber;
/*      */ 
/*      */   
/*      */   private int type;
/*      */ 
/*      */   
/*      */   private int count;
/*      */ 
/*      */   
/*      */   private Object data;
/*      */ 
/*      */ 
/*      */   
/*      */   private TIFFField() {}
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getAttribute(Node node, String attrName) {
/*  325 */     NamedNodeMap attrs = node.getAttributes();
/*  326 */     return attrs.getNamedItem(attrName).getNodeValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void initData(Node node, int[] otype, int[] ocount, Object[] odata) {
/*  333 */     Object data = null;
/*      */     
/*  335 */     String typeName = node.getNodeName();
/*  336 */     typeName = typeName.substring(4);
/*  337 */     typeName = typeName.substring(0, typeName.length() - 1);
/*  338 */     int type = getTypeByName(typeName);
/*  339 */     if (type == -1) {
/*  340 */       throw new IllegalArgumentException("typeName = " + typeName);
/*      */     }
/*      */     
/*  343 */     Node child = node.getFirstChild();
/*      */     
/*  345 */     int count = 0;
/*  346 */     while (child != null) {
/*  347 */       String childTypeName = child.getNodeName().substring(4);
/*  348 */       if (!typeName.equals(childTypeName));
/*      */ 
/*      */ 
/*      */       
/*  352 */       count++;
/*  353 */       child = child.getNextSibling();
/*      */     } 
/*      */     
/*  356 */     if (count > 0) {
/*  357 */       data = createArrayForType(type, count);
/*  358 */       child = node.getFirstChild();
/*  359 */       int idx = 0;
/*  360 */       while (child != null) {
/*  361 */         String numerator, denominator; int slashPos; String value = getAttribute(child, "value");
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  366 */         switch (type) {
/*      */           case 2:
/*  368 */             ((String[])data)[idx] = value;
/*      */             break;
/*      */           case 1:
/*      */           case 6:
/*  372 */             ((byte[])data)[idx] = 
/*  373 */               (byte)Integer.parseInt(value);
/*      */             break;
/*      */           case 3:
/*  376 */             ((char[])data)[idx] = 
/*  377 */               (char)Integer.parseInt(value);
/*      */             break;
/*      */           case 8:
/*  380 */             ((short[])data)[idx] = 
/*  381 */               (short)Integer.parseInt(value);
/*      */             break;
/*      */           case 9:
/*  384 */             ((int[])data)[idx] = 
/*  385 */               Integer.parseInt(value);
/*      */             break;
/*      */           case 4:
/*      */           case 13:
/*  389 */             ((long[])data)[idx] = 
/*  390 */               Long.parseLong(value);
/*      */             break;
/*      */           case 11:
/*  393 */             ((float[])data)[idx] = 
/*  394 */               Float.parseFloat(value);
/*      */             break;
/*      */           case 12:
/*  397 */             ((double[])data)[idx] = 
/*  398 */               Double.parseDouble(value);
/*      */             break;
/*      */           case 10:
/*  401 */             slashPos = value.indexOf("/");
/*  402 */             numerator = value.substring(0, slashPos);
/*  403 */             denominator = value.substring(slashPos + 1);
/*      */             
/*  405 */             ((int[][])data)[idx] = new int[2];
/*  406 */             ((int[][])data)[idx][0] = 
/*  407 */               Integer.parseInt(numerator);
/*  408 */             ((int[][])data)[idx][1] = 
/*  409 */               Integer.parseInt(denominator);
/*      */             break;
/*      */           case 5:
/*  412 */             slashPos = value.indexOf("/");
/*  413 */             numerator = value.substring(0, slashPos);
/*  414 */             denominator = value.substring(slashPos + 1);
/*      */             
/*  416 */             ((long[][])data)[idx] = new long[2];
/*  417 */             ((long[][])data)[idx][0] = 
/*  418 */               Long.parseLong(numerator);
/*  419 */             ((long[][])data)[idx][1] = 
/*  420 */               Long.parseLong(denominator);
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  426 */         idx++;
/*  427 */         child = child.getNextSibling();
/*      */       } 
/*      */     } 
/*      */     
/*  431 */     otype[0] = type;
/*  432 */     ocount[0] = count;
/*  433 */     odata[0] = data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static TIFFField createFromMetadataNode(TIFFTagSet tagSet, Node node) {
/*      */     TIFFTag tag;
/*  453 */     if (node == null) {
/*  454 */       throw new IllegalArgumentException("node == null!");
/*      */     }
/*  456 */     String name = node.getNodeName();
/*  457 */     if (!name.equals("TIFFField")) {
/*  458 */       throw new IllegalArgumentException("!name.equals(\"TIFFField\")");
/*      */     }
/*      */     
/*  461 */     int tagNumber = Integer.parseInt(getAttribute(node, "number"));
/*      */     
/*  463 */     if (tagSet != null) {
/*  464 */       tag = tagSet.getTag(tagNumber);
/*      */     } else {
/*  466 */       tag = new TIFFTag("unknown", tagNumber, 0, null);
/*      */     } 
/*      */     
/*  469 */     int type = 7;
/*  470 */     int count = 0;
/*  471 */     Object data = null;
/*      */     
/*  473 */     Node child = node.getFirstChild();
/*  474 */     if (child != null) {
/*  475 */       String typeName = child.getNodeName();
/*  476 */       if (typeName.equals("TIFFUndefined")) {
/*  477 */         String values = getAttribute(child, "value");
/*  478 */         StringTokenizer st = new StringTokenizer(values, ",");
/*  479 */         count = st.countTokens();
/*      */         
/*  481 */         byte[] bdata = new byte[count];
/*  482 */         for (int i = 0; i < count; i++) {
/*  483 */           bdata[i] = (byte)Integer.parseInt(st.nextToken());
/*      */         }
/*      */         
/*  486 */         type = 7;
/*  487 */         data = bdata;
/*      */       } else {
/*  489 */         int[] otype = new int[1];
/*  490 */         int[] ocount = new int[1];
/*  491 */         Object[] odata = new Object[1];
/*      */         
/*  493 */         initData(node.getFirstChild(), otype, ocount, odata);
/*  494 */         type = otype[0];
/*  495 */         count = ocount[0];
/*  496 */         data = odata[0];
/*      */       } 
/*      */     } else {
/*  499 */       int t = 13;
/*  500 */       while (t >= 1 && !tag.isDataTypeOK(t)) {
/*  501 */         t--;
/*      */       }
/*  503 */       type = t;
/*      */     } 
/*      */     
/*  506 */     return new TIFFField(tag, type, count, data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TIFFField(TIFFTag tag, int type, int count, Object data) {
/*  552 */     if (tag == null)
/*  553 */       throw new IllegalArgumentException("tag == null!"); 
/*  554 */     if (type < 1 || type > 13)
/*  555 */       throw new IllegalArgumentException("Unknown data type " + type); 
/*  556 */     if (count < 0) {
/*  557 */       throw new IllegalArgumentException("count < 0!");
/*      */     }
/*  559 */     this.tag = tag;
/*  560 */     this.tagNumber = tag.getNumber();
/*  561 */     this.type = type;
/*  562 */     this.count = count;
/*  563 */     this.data = data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TIFFField(TIFFTag tag, int type, int count) {
/*  575 */     this(tag, type, count, createArrayForType(type, count));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TIFFField(TIFFTag tag, int value) {
/*  593 */     if (tag == null) {
/*  594 */       throw new IllegalArgumentException("tag == null!");
/*      */     }
/*  596 */     if (value < 0) {
/*  597 */       throw new IllegalArgumentException("value < 0!");
/*      */     }
/*      */     
/*  600 */     this.tag = tag;
/*  601 */     this.tagNumber = tag.getNumber();
/*  602 */     this.count = 1;
/*      */     
/*  604 */     if (value < 65536) {
/*  605 */       this.type = 3;
/*  606 */       char[] cdata = new char[1];
/*  607 */       cdata[0] = (char)value;
/*  608 */       this.data = cdata;
/*      */     } else {
/*  610 */       this.type = 4;
/*  611 */       long[] ldata = new long[1];
/*  612 */       ldata[0] = value;
/*  613 */       this.data = ldata;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TIFFTag getTag() {
/*  623 */     return this.tag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTagNumber() {
/*  632 */     return this.tagNumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getType() {
/*  643 */     return this.type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getTypeName(int dataType) {
/*  656 */     if (dataType < 1 || dataType > 13)
/*      */     {
/*  658 */       throw new IllegalArgumentException("Unknown data type " + dataType);
/*      */     }
/*      */     
/*  661 */     return typeNames[dataType];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getTypeByName(String typeName) {
/*  672 */     for (int i = 1; i <= 13; i++) {
/*  673 */       if (typeName.equals(typeNames[i])) {
/*  674 */         return i;
/*      */       }
/*      */     } 
/*      */     
/*  678 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object createArrayForType(int dataType, int count) {
/*  693 */     if (count < 0) {
/*  694 */       throw new IllegalArgumentException("count < 0!");
/*      */     }
/*  696 */     switch (dataType) {
/*      */       case 1:
/*      */       case 6:
/*      */       case 7:
/*  700 */         return new byte[count];
/*      */       case 2:
/*  702 */         return new String[count];
/*      */       case 3:
/*  704 */         return new char[count];
/*      */       case 4:
/*      */       case 13:
/*  707 */         return new long[count];
/*      */       case 5:
/*  709 */         return new long[count][2];
/*      */       case 8:
/*  711 */         return new short[count];
/*      */       case 9:
/*  713 */         return new int[count];
/*      */       case 10:
/*  715 */         return new int[count][2];
/*      */       case 11:
/*  717 */         return new float[count];
/*      */       case 12:
/*  719 */         return new double[count];
/*      */     } 
/*  721 */     throw new IllegalArgumentException("Unknown data type " + dataType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getAsNativeNode() {
/*  738 */     return (Node)new TIFFFieldNode(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isIntegral() {
/*  748 */     return isIntegral[this.type];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCount() {
/*  758 */     return this.count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getData() {
/*  767 */     return this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getAsBytes() {
/*  785 */     return (byte[])this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char[] getAsChars() {
/*  796 */     return (char[])this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short[] getAsShorts() {
/*  807 */     return (short[])this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getAsInts() {
/*  819 */     if (this.data instanceof int[])
/*  820 */       return (int[])this.data; 
/*  821 */     if (this.data instanceof char[]) {
/*  822 */       char[] cdata = (char[])this.data;
/*  823 */       int[] idata = new int[cdata.length];
/*  824 */       for (int i = 0; i < cdata.length; i++) {
/*  825 */         idata[i] = cdata[i] & Character.MAX_VALUE;
/*      */       }
/*  827 */       return idata;
/*  828 */     }  if (this.data instanceof short[]) {
/*  829 */       short[] sdata = (short[])this.data;
/*  830 */       int[] idata = new int[sdata.length];
/*  831 */       for (int i = 0; i < sdata.length; i++) {
/*  832 */         idata[i] = sdata[i];
/*      */       }
/*  834 */       return idata;
/*      */     } 
/*  836 */     throw new ClassCastException("Data not char[], short[], or int[]!");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long[] getAsLongs() {
/*  850 */     return (long[])this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float[] getAsFloats() {
/*  861 */     return (float[])this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double[] getAsDoubles() {
/*  872 */     return (double[])this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getAsSRationals() {
/*  883 */     return (int[][])this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long[][] getAsRationals() {
/*  894 */     return (long[][])this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAsInt(int index) {
/*      */     int[] ivalue;
/*      */     long[] lvalue;
/*      */     String s;
/*  924 */     switch (this.type) { case 1:
/*      */       case 7:
/*  926 */         return ((byte[])this.data)[index] & 0xFF;
/*      */       case 6:
/*  928 */         return ((byte[])this.data)[index];
/*      */       case 3:
/*  930 */         return ((char[])this.data)[index] & Character.MAX_VALUE;
/*      */       case 8:
/*  932 */         return ((short[])this.data)[index];
/*      */       case 9:
/*  934 */         return ((int[])this.data)[index];
/*      */       case 4: case 13:
/*  936 */         return (int)((long[])this.data)[index];
/*      */       case 11:
/*  938 */         return (int)((float[])this.data)[index];
/*      */       case 12:
/*  940 */         return (int)((double[])this.data)[index];
/*      */       case 10:
/*  942 */         ivalue = getAsSRational(index);
/*  943 */         return (int)(ivalue[0] / ivalue[1]);
/*      */       case 5:
/*  945 */         lvalue = getAsRational(index);
/*  946 */         return (int)(lvalue[0] / lvalue[1]);
/*      */       case 2:
/*  948 */         s = ((String[])this.data)[index];
/*  949 */         return (int)Double.parseDouble(s); }
/*      */     
/*  951 */     throw new ClassCastException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getAsLong(int index) {
/*      */     int[] ivalue;
/*      */     long[] lvalue;
/*      */     String s;
/*  969 */     switch (this.type) { case 1:
/*      */       case 7:
/*  971 */         return (((byte[])this.data)[index] & 0xFF);
/*      */       case 6:
/*  973 */         return ((byte[])this.data)[index];
/*      */       case 3:
/*  975 */         return (((char[])this.data)[index] & Character.MAX_VALUE);
/*      */       case 8:
/*  977 */         return ((short[])this.data)[index];
/*      */       case 9:
/*  979 */         return ((int[])this.data)[index];
/*      */       case 4: case 13:
/*  981 */         return ((long[])this.data)[index];
/*      */       case 10:
/*  983 */         ivalue = getAsSRational(index);
/*  984 */         return (long)(ivalue[0] / ivalue[1]);
/*      */       case 5:
/*  986 */         lvalue = getAsRational(index);
/*  987 */         return (long)(lvalue[0] / lvalue[1]);
/*      */       case 2:
/*  989 */         s = ((String[])this.data)[index];
/*  990 */         return (long)Double.parseDouble(s); }
/*      */     
/*  992 */     throw new ClassCastException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAsFloat(int index) {
/*      */     int[] ivalue;
/*      */     long[] lvalue;
/*      */     String s;
/* 1020 */     switch (this.type) { case 1:
/*      */       case 7:
/* 1022 */         return (((byte[])this.data)[index] & 0xFF);
/*      */       case 6:
/* 1024 */         return ((byte[])this.data)[index];
/*      */       case 3:
/* 1026 */         return (((char[])this.data)[index] & Character.MAX_VALUE);
/*      */       case 8:
/* 1028 */         return ((short[])this.data)[index];
/*      */       case 9:
/* 1030 */         return ((int[])this.data)[index];
/*      */       case 4: case 13:
/* 1032 */         return (float)((long[])this.data)[index];
/*      */       case 11:
/* 1034 */         return ((float[])this.data)[index];
/*      */       case 12:
/* 1036 */         return (float)((double[])this.data)[index];
/*      */       case 10:
/* 1038 */         ivalue = getAsSRational(index);
/* 1039 */         return (float)(ivalue[0] / ivalue[1]);
/*      */       case 5:
/* 1041 */         lvalue = getAsRational(index);
/* 1042 */         return (float)(lvalue[0] / lvalue[1]);
/*      */       case 2:
/* 1044 */         s = ((String[])this.data)[index];
/* 1045 */         return (float)Double.parseDouble(s); }
/*      */     
/* 1047 */     throw new ClassCastException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getAsDouble(int index) {
/*      */     int[] ivalue;
/*      */     long[] lvalue;
/*      */     String s;
/* 1069 */     switch (this.type) { case 1:
/*      */       case 7:
/* 1071 */         return (((byte[])this.data)[index] & 0xFF);
/*      */       case 6:
/* 1073 */         return ((byte[])this.data)[index];
/*      */       case 3:
/* 1075 */         return (((char[])this.data)[index] & Character.MAX_VALUE);
/*      */       case 8:
/* 1077 */         return ((short[])this.data)[index];
/*      */       case 9:
/* 1079 */         return ((int[])this.data)[index];
/*      */       case 4: case 13:
/* 1081 */         return ((long[])this.data)[index];
/*      */       case 11:
/* 1083 */         return ((float[])this.data)[index];
/*      */       case 12:
/* 1085 */         return ((double[])this.data)[index];
/*      */       case 10:
/* 1087 */         ivalue = getAsSRational(index);
/* 1088 */         return ivalue[0] / ivalue[1];
/*      */       case 5:
/* 1090 */         lvalue = getAsRational(index);
/* 1091 */         return lvalue[0] / lvalue[1];
/*      */       case 2:
/* 1093 */         s = ((String[])this.data)[index];
/* 1094 */         return Double.parseDouble(s); }
/*      */     
/* 1096 */     throw new ClassCastException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAsString(int index) {
/* 1108 */     return ((String[])this.data)[index];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getAsSRational(int index) {
/* 1119 */     return ((int[][])this.data)[index];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long[] getAsRational(int index) {
/* 1130 */     return ((long[][])this.data)[index];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getValueAsString(int index) {
/*      */     int[] ivalue;
/*      */     String srationalString;
/*      */     long[] lvalue;
/*      */     String rationalString;
/* 1145 */     switch (this.type) {
/*      */       case 2:
/* 1147 */         return ((String[])this.data)[index];
/*      */       case 1: case 7:
/* 1149 */         return Integer.toString(((byte[])this.data)[index] & 0xFF);
/*      */       case 6:
/* 1151 */         return Integer.toString(((byte[])this.data)[index]);
/*      */       case 3:
/* 1153 */         return Integer.toString(((char[])this.data)[index] & Character.MAX_VALUE);
/*      */       case 8:
/* 1155 */         return Integer.toString(((short[])this.data)[index]);
/*      */       case 9:
/* 1157 */         return Integer.toString(((int[])this.data)[index]);
/*      */       case 4: case 13:
/* 1159 */         return Long.toString(((long[])this.data)[index]);
/*      */       case 11:
/* 1161 */         return Float.toString(((float[])this.data)[index]);
/*      */       case 12:
/* 1163 */         return Double.toString(((double[])this.data)[index]);
/*      */       case 10:
/* 1165 */         ivalue = getAsSRational(index);
/*      */         
/* 1167 */         if (ivalue[1] != 0 && ivalue[0] % ivalue[1] == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1172 */           srationalString = Integer.toString(ivalue[0] / ivalue[1]) + "/1";
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1178 */           srationalString = Integer.toString(ivalue[0]) + "/" + Integer.toString(ivalue[1]);
/*      */         } 
/* 1180 */         return srationalString;
/*      */       case 5:
/* 1182 */         lvalue = getAsRational(index);
/*      */         
/* 1184 */         if (lvalue[1] != 0L && lvalue[0] % lvalue[1] == 0L) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1189 */           rationalString = Long.toString(lvalue[0] / lvalue[1]) + "/1";
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1195 */           rationalString = Long.toString(lvalue[0]) + "/" + Long.toString(lvalue[1]);
/*      */         } 
/* 1197 */         return rationalString;
/*      */     } 
/* 1199 */     throw new ClassCastException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int compareTo(Object o) {
/* 1215 */     if (o == null) {
/* 1216 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1219 */     int oTagNumber = ((TIFFField)o).getTagNumber();
/* 1220 */     if (this.tagNumber < oTagNumber)
/* 1221 */       return -1; 
/* 1222 */     if (this.tagNumber > oTagNumber) {
/* 1223 */       return 1;
/*      */     }
/* 1225 */     return 0;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/plugins/tiff/TIFFField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
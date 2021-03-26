/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Box
/*     */ {
/*  75 */   private static Hashtable names = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  80 */     names.put(new Integer(1783636000), "JPEG2000SignatureBox");
/*  81 */     names.put(new Integer(1718909296), "JPEG2000FileTypeBox");
/*     */ 
/*     */ 
/*     */     
/*  85 */     names.put(new Integer(1785737833), "JPEG2000IntellectualPropertyRightsBox");
/*     */     
/*  87 */     names.put(new Integer(2020437024), "JPEG2000XMLBox");
/*  88 */     names.put(new Integer(1970628964), "JPEG2000UUIDBox");
/*  89 */     names.put(new Integer(1969843814), "JPEG2000UUIDInfoBox");
/*     */ 
/*     */     
/*  92 */     names.put(new Integer(1785737832), "JPEG2000HeaderSuperBox");
/*  93 */     names.put(new Integer(1785737827), "JPEG2000CodeStreamBox");
/*     */ 
/*     */     
/*  96 */     names.put(new Integer(1768449138), "JPEG2000HeaderBox");
/*     */ 
/*     */     
/*  99 */     names.put(new Integer(1651532643), "JPEG2000BitsPerComponentBox");
/* 100 */     names.put(new Integer(1668246642), "JPEG2000ColorSpecificationBox");
/* 101 */     names.put(new Integer(1885564018), "JPEG2000PaletteBox");
/* 102 */     names.put(new Integer(1668112752), "JPEG2000ComponentMappingBox");
/* 103 */     names.put(new Integer(1667523942), "JPEG2000ChannelDefinitionBox");
/* 104 */     names.put(new Integer(1919251232), "JPEG2000ResolutionBox");
/*     */ 
/*     */     
/* 107 */     names.put(new Integer(1919251299), "JPEG2000CaptureResolutionBox");
/* 108 */     names.put(new Integer(1919251300), "JPEG2000DefaultDisplayResolutionBox");
/*     */ 
/*     */ 
/*     */     
/* 112 */     names.put(new Integer(1970041716), "JPEG2000UUIDListBox");
/* 113 */     names.put(new Integer(1970433056), "JPEG2000DataEntryURLBox");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   private static Hashtable boxClasses = new Hashtable<Object, Object>();
/*     */   protected int length;
/*     */   protected long extraLength;
/*     */   
/*     */   static {
/* 125 */     boxClasses.put(new Integer(1783636000), SignatureBox.class);
/* 126 */     boxClasses.put(new Integer(1718909296), FileTypeBox.class);
/*     */ 
/*     */ 
/*     */     
/* 130 */     boxClasses.put(new Integer(1785737833), Box.class);
/* 131 */     boxClasses.put(new Integer(2020437024), XMLBox.class);
/* 132 */     boxClasses.put(new Integer(1970628964), UUIDBox.class);
/*     */ 
/*     */     
/* 135 */     boxClasses.put(new Integer(1768449138), HeaderBox.class);
/*     */ 
/*     */     
/* 138 */     boxClasses.put(new Integer(1651532643), BitsPerComponentBox.class);
/* 139 */     boxClasses.put(new Integer(1668246642), ColorSpecificationBox.class);
/* 140 */     boxClasses.put(new Integer(1885564018), PaletteBox.class);
/* 141 */     boxClasses.put(new Integer(1668112752), ComponentMappingBox.class);
/* 142 */     boxClasses.put(new Integer(1667523942), ChannelDefinitionBox.class);
/* 143 */     boxClasses.put(new Integer(1919251232), ResolutionBox.class);
/*     */ 
/*     */     
/* 146 */     boxClasses.put(new Integer(1919251299), ResolutionBox.class);
/* 147 */     boxClasses.put(new Integer(1919251300), ResolutionBox.class);
/*     */ 
/*     */     
/* 150 */     boxClasses.put(new Integer(1970041716), UUIDListBox.class);
/* 151 */     boxClasses.put(new Integer(1970433056), DataEntryURLBox.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int type;
/*     */   protected byte[] data;
/*     */   
/*     */   public static String getName(int type) {
/* 159 */     String name = (String)names.get(new Integer(type));
/* 160 */     return (name == null) ? "unknown" : name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class getBoxClass(int type) {
/* 166 */     if (type == 1785737832 || type == 1919251232)
/* 167 */       return null; 
/* 168 */     return (Class)boxClasses.get(new Integer(type));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTypeByName(String name) {
/* 173 */     Enumeration<Integer> keys = names.keys();
/* 174 */     while (keys.hasMoreElements()) {
/* 175 */       Integer i = keys.nextElement();
/* 176 */       if (name.equals(names.get(i)))
/* 177 */         return getTypeString(i.intValue()); 
/*     */     } 
/* 179 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Box createBox(int type, Node node) throws IIOInvalidTreeException {
/* 187 */     Class boxClass = (Class)boxClasses.get(new Integer(type));
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 192 */       Constructor<Box> cons = boxClass.getConstructor(new Class[] { Node.class });
/* 193 */       if (cons != null) {
/* 194 */         return cons.newInstance(new Object[] { node });
/*     */       }
/* 196 */     } catch (NoSuchMethodException e) {
/*     */       
/* 198 */       e.printStackTrace();
/* 199 */       return new Box(node);
/* 200 */     } catch (InvocationTargetException e) {
/* 201 */       e.printStackTrace();
/* 202 */       return new Box(node);
/* 203 */     } catch (IllegalAccessException e) {
/* 204 */       e.printStackTrace();
/* 205 */       return new Box(node);
/* 206 */     } catch (InstantiationException e) {
/* 207 */       e.printStackTrace();
/* 208 */       return new Box(node);
/*     */     } 
/*     */     
/* 211 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object getAttribute(Node node, String name) {
/* 216 */     NamedNodeMap map = node.getAttributes();
/* 217 */     node = map.getNamedItem(name);
/* 218 */     return (node != null) ? node.getNodeValue() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] parseByteArray(String value) {
/* 223 */     if (value == null) {
/* 224 */       return null;
/*     */     }
/* 226 */     StringTokenizer token = new StringTokenizer(value);
/* 227 */     int count = token.countTokens();
/*     */     
/* 229 */     byte[] buf = new byte[count];
/* 230 */     int i = 0;
/* 231 */     while (token.hasMoreElements()) {
/* 232 */       buf[i++] = (new Byte(token.nextToken())).byteValue();
/*     */     }
/* 234 */     return buf;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int[] parseIntArray(String value) {
/* 239 */     if (value == null) {
/* 240 */       return null;
/*     */     }
/* 242 */     StringTokenizer token = new StringTokenizer(value);
/* 243 */     int count = token.countTokens();
/*     */     
/* 245 */     int[] buf = new int[count];
/* 246 */     int i = 0;
/* 247 */     while (token.hasMoreElements()) {
/* 248 */       buf[i++] = (new Integer(token.nextToken())).intValue();
/*     */     }
/* 250 */     return buf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String getStringElementValue(Node node) {
/* 257 */     if (node instanceof IIOMetadataNode) {
/* 258 */       Object obj = ((IIOMetadataNode)node).getUserObject();
/* 259 */       if (obj instanceof String) {
/* 260 */         return (String)obj;
/*     */       }
/*     */     } 
/* 263 */     return node.getNodeValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected static byte getByteElementValue(Node node) {
/* 268 */     if (node instanceof IIOMetadataNode) {
/* 269 */       Object obj = ((IIOMetadataNode)node).getUserObject();
/* 270 */       if (obj instanceof Byte) {
/* 271 */         return ((Byte)obj).byteValue();
/*     */       }
/*     */     } 
/* 274 */     String value = node.getNodeValue();
/* 275 */     if (value != null)
/* 276 */       return (new Byte(value)).byteValue(); 
/* 277 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int getIntElementValue(Node node) {
/* 282 */     if (node instanceof IIOMetadataNode) {
/* 283 */       Object obj = ((IIOMetadataNode)node).getUserObject();
/* 284 */       if (obj instanceof Integer) {
/* 285 */         return ((Integer)obj).intValue();
/*     */       }
/*     */     } 
/* 288 */     String value = node.getNodeValue();
/* 289 */     if (value != null)
/* 290 */       return (new Integer(value)).intValue(); 
/* 291 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static short getShortElementValue(Node node) {
/* 296 */     if (node instanceof IIOMetadataNode) {
/* 297 */       Object obj = ((IIOMetadataNode)node).getUserObject();
/* 298 */       if (obj instanceof Short)
/* 299 */         return ((Short)obj).shortValue(); 
/*     */     } 
/* 301 */     String value = node.getNodeValue();
/* 302 */     if (value != null)
/* 303 */       return (new Short(value)).shortValue(); 
/* 304 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static byte[] getByteArrayElementValue(Node node) {
/* 309 */     if (node instanceof IIOMetadataNode) {
/* 310 */       Object obj = ((IIOMetadataNode)node).getUserObject();
/* 311 */       if (obj instanceof byte[]) {
/* 312 */         return (byte[])obj;
/*     */       }
/*     */     } 
/* 315 */     return parseByteArray(node.getNodeValue());
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int[] getIntArrayElementValue(Node node) {
/* 320 */     if (node instanceof IIOMetadataNode) {
/* 321 */       Object obj = ((IIOMetadataNode)node).getUserObject();
/* 322 */       if (obj instanceof int[]) {
/* 323 */         return (int[])obj;
/*     */       }
/*     */     } 
/* 326 */     return parseIntArray(node.getNodeValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copyInt(byte[] data, int pos, int value) {
/* 333 */     data[pos++] = (byte)(value >> 24);
/* 334 */     data[pos++] = (byte)(value >> 16);
/* 335 */     data[pos++] = (byte)(value >> 8);
/* 336 */     data[pos++] = (byte)(value & 0xFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTypeString(int type) {
/* 343 */     byte[] buf = new byte[4];
/* 344 */     for (int i = 3; i >= 0; i--) {
/* 345 */       buf[i] = (byte)(type & 0xFF);
/* 346 */       type >>>= 8;
/*     */     } 
/*     */     
/* 349 */     return new String(buf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTypeInt(String s) {
/* 357 */     byte[] buf = s.getBytes();
/* 358 */     int t = buf[0];
/* 359 */     for (int i = 1; i < 4; i++) {
/* 360 */       t = t << 8 | buf[i];
/*     */     }
/*     */     
/* 363 */     return t;
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
/*     */   public Box(int length, int type, byte[] data) {
/* 383 */     this.type = type;
/* 384 */     setLength(length);
/* 385 */     setContent(data);
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
/*     */   public Box(int length, int type, long extraLength, byte[] data) {
/* 402 */     this.type = type;
/* 403 */     setLength(length);
/* 404 */     if (length == 1)
/* 405 */       setExtraLength(extraLength); 
/* 406 */     setContent(data);
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
/*     */   public Box(ImageInputStream iis, int pos) throws IOException {
/* 418 */     read(iis, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Box(Node node) throws IIOInvalidTreeException {
/* 428 */     NodeList children = node.getChildNodes();
/*     */     
/* 430 */     String value = (String)getAttribute(node, "Type");
/* 431 */     this.type = getTypeInt(value);
/* 432 */     if (value == null || names.get(new Integer(this.type)) == null) {
/* 433 */       throw new IIOInvalidTreeException("Type is not defined", node);
/*     */     }
/* 435 */     value = (String)getAttribute(node, "Length");
/* 436 */     if (value != null) {
/* 437 */       this.length = (new Integer(value)).intValue();
/*     */     }
/* 439 */     value = (String)getAttribute(node, "ExtraLength");
/* 440 */     if (value != null) {
/* 441 */       this.extraLength = (new Long(value)).longValue();
/*     */     }
/* 443 */     for (int i = 0; i < children.getLength(); i++) {
/* 444 */       Node child = children.item(i);
/* 445 */       if ("Content".equals(child.getNodeName())) {
/* 446 */         if (child instanceof IIOMetadataNode) {
/* 447 */           IIOMetadataNode cnode = (IIOMetadataNode)child;
/*     */           try {
/* 449 */             this.data = (byte[])cnode.getUserObject();
/* 450 */           } catch (Exception exception) {}
/*     */         } else {
/*     */           
/* 453 */           this.data = getByteArrayElementValue(child);
/*     */         } 
/*     */         
/* 456 */         if (this.data == null) {
/* 457 */           value = node.getNodeValue();
/* 458 */           if (value != null) {
/* 459 */             this.data = value.getBytes();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 470 */     String name = getName(getType());
/* 471 */     if (name == null) {
/* 472 */       name = "unknown";
/*     */     }
/* 474 */     IIOMetadataNode node = new IIOMetadataNode(name);
/* 475 */     setDefaultAttributes(node);
/* 476 */     IIOMetadataNode child = new IIOMetadataNode("Content");
/* 477 */     child.setUserObject(this.data);
/* 478 */     child.setNodeValue(ImageUtil.convertObjectToString(this.data));
/* 479 */     node.appendChild(child);
/*     */     
/* 481 */     return node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IIOMetadataNode getNativeNodeForSimpleBox() {
/*     */     try {
/* 493 */       Method m = getClass().getMethod("getElementNames", (Class[])null);
/*     */       
/* 495 */       String[] elementNames = (String[])m.invoke(null, (Object[])null);
/*     */       
/* 497 */       IIOMetadataNode node = new IIOMetadataNode(getName(getType()));
/* 498 */       setDefaultAttributes(node);
/* 499 */       for (int i = 0; i < elementNames.length; i++) {
/* 500 */         IIOMetadataNode child = new IIOMetadataNode(elementNames[i]);
/* 501 */         m = getClass().getMethod("get" + elementNames[i], (Class[])null);
/*     */         
/* 503 */         Object obj = m.invoke(this, (Object[])null);
/* 504 */         child.setUserObject(obj);
/* 505 */         child.setNodeValue(ImageUtil.convertObjectToString(obj));
/* 506 */         node.appendChild(child);
/*     */       } 
/* 508 */       return node;
/* 509 */     } catch (Exception e) {
/* 510 */       throw new IllegalArgumentException(I18N.getString("Box0"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setDefaultAttributes(IIOMetadataNode node) {
/* 518 */     node.setAttribute("Length", Integer.toString(this.length));
/* 519 */     node.setAttribute("Type", getTypeString(this.type));
/*     */     
/* 521 */     if (this.length == 1) {
/* 522 */       node.setAttribute("ExtraLength", Long.toString(this.extraLength));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 528 */     return this.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 533 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getExtraLength() {
/* 538 */     return this.extraLength;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getContent() {
/* 543 */     if (this.data == null)
/* 544 */       compose(); 
/* 545 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLength(int length) {
/* 550 */     this.length = length;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraLength(long extraLength) {
/* 555 */     if (this.length != 1)
/* 556 */       throw new IllegalArgumentException(I18N.getString("Box1")); 
/* 557 */     this.extraLength = extraLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContent(byte[] data) {
/* 564 */     if (data != null && ((this.length == 1 && this.extraLength - 16L != data.length) || (this.length != 1 && this.length - 8 != data.length)))
/*     */     {
/*     */       
/* 567 */       throw new IllegalArgumentException(I18N.getString("Box2")); } 
/* 568 */     this.data = data;
/* 569 */     if (data != null) {
/* 570 */       parse(data);
/*     */     }
/*     */   }
/*     */   
/*     */   public void write(ImageOutputStream ios) throws IOException {
/* 575 */     ios.writeInt(this.length);
/* 576 */     ios.writeInt(this.type);
/* 577 */     if (this.length == 1) {
/* 578 */       ios.writeLong(this.extraLength);
/* 579 */       ios.write(this.data, 0, (int)this.extraLength);
/* 580 */     } else if (this.data != null) {
/* 581 */       ios.write(this.data, 0, this.length);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(ImageInputStream iis, int pos) throws IOException {
/* 588 */     iis.mark();
/* 589 */     iis.seek(pos);
/* 590 */     this.length = iis.readInt();
/* 591 */     this.type = iis.readInt();
/* 592 */     int dataLength = 0;
/* 593 */     if (this.length == 0) {
/*     */       
/* 595 */       long streamLength = iis.length();
/* 596 */       if (streamLength != -1L) {
/*     */         
/* 598 */         dataLength = (int)(streamLength - iis.getStreamPosition());
/*     */       } else {
/*     */         
/* 601 */         long dataPos = iis.getStreamPosition();
/* 602 */         int bufLen = 1024;
/* 603 */         byte[] buf = new byte[bufLen];
/* 604 */         long savePos = dataPos;
/*     */         try {
/* 606 */           iis.readFully(buf);
/* 607 */           dataLength += bufLen;
/* 608 */           savePos = iis.getStreamPosition();
/* 609 */         } catch (EOFException eofe) {
/* 610 */           iis.seek(savePos);
/* 611 */           for (; iis.read() != -1; dataLength++);
/*     */         } 
/* 613 */         iis.seek(dataPos);
/*     */       } 
/* 615 */     } else if (this.length == 1) {
/*     */       
/* 617 */       this.extraLength = iis.readLong();
/* 618 */       dataLength = (int)(this.extraLength - 16L);
/* 619 */     } else if (this.length >= 8 && this.length < 1) {
/*     */       
/* 621 */       dataLength = this.length - 8;
/*     */     } else {
/*     */       
/* 624 */       throw new IIOException("Illegal value " + this.length + " for box length parameter.");
/*     */     } 
/*     */     
/* 627 */     this.data = new byte[dataLength];
/* 628 */     iis.readFully(this.data);
/* 629 */     iis.reset();
/*     */   }
/*     */   
/*     */   protected void parse(byte[] data) {}
/*     */   
/*     */   protected void compose() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/Box.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
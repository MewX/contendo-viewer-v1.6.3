/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import java.awt.image.ColorModel;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadataFormatImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class J2KMetadataFormat
/*     */   extends IIOMetadataFormatImpl
/*     */ {
/*  61 */   private static Hashtable parents = new Hashtable<Object, Object>();
/*     */   private static J2KMetadataFormat instance;
/*     */   
/*     */   static {
/*  65 */     parents.put("JPEG2000SignatureBox", "com_sun_media_imageio_plugins_jpeg2000_image_1.0");
/*  66 */     parents.put("JPEG2000FileTypeBox", "com_sun_media_imageio_plugins_jpeg2000_image_1.0");
/*  67 */     parents.put("OtherBoxes", "com_sun_media_imageio_plugins_jpeg2000_image_1.0");
/*     */ 
/*     */ 
/*     */     
/*  71 */     parents.put("JPEG2000HeaderSuperBox", "OtherBoxes");
/*  72 */     parents.put("JPEG2000CodeStreamBox", "OtherBoxes");
/*     */     
/*  74 */     parents.put("JPEG2000IntellectualPropertyRightsBox", "OtherBoxes");
/*  75 */     parents.put("JPEG2000XMLBox", "OtherBoxes");
/*  76 */     parents.put("JPEG2000UUIDBox", "OtherBoxes");
/*  77 */     parents.put("JPEG2000UUIDInfoBox", "OtherBoxes");
/*     */ 
/*     */     
/*  80 */     parents.put("JPEG2000HeaderBox", "JPEG2000HeaderSuperBox");
/*  81 */     parents.put("OptionalBoxes", "JPEG2000HeaderSuperBox");
/*     */ 
/*     */     
/*  84 */     parents.put("JPEG2000BitsPerComponentBox", "OptionalBoxes");
/*  85 */     parents.put("JPEG2000ColorSpecificationBox", "OptionalBoxes");
/*  86 */     parents.put("JPEG2000PaletteBox", "OptionalBoxes");
/*  87 */     parents.put("JPEG2000ComponentMappingBox", "OptionalBoxes");
/*  88 */     parents.put("JPEG2000ChannelDefinitionBox", "OptionalBoxes");
/*  89 */     parents.put("JPEG2000ResolutionBox", "OptionalBoxes");
/*     */ 
/*     */     
/*  92 */     parents.put("JPEG2000CaptureResolutionBox", "JPEG2000ResolutionBox");
/*  93 */     parents.put("JPEG2000DefaultDisplayResolutionBox", "JPEG2000ResolutionBox");
/*     */ 
/*     */ 
/*     */     
/*  97 */     parents.put("JPEG2000UUIDListBox", "JPEG2000UUIDInfoBox");
/*  98 */     parents.put("JPEG2000DataEntryURLBox", "JPEG2000UUIDInfoBox");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized J2KMetadataFormat getInstance() {
/* 104 */     if (instance == null)
/* 105 */       instance = new J2KMetadataFormat(); 
/* 106 */     return instance;
/*     */   }
/*     */   
/* 109 */   String resourceBaseName = getClass().getName() + "Resources";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   J2KMetadataFormat() {
/* 116 */     super("com_sun_media_imageio_plugins_jpeg2000_image_1.0", 1);
/* 117 */     setResourceBaseName(this.resourceBaseName);
/* 118 */     addElements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addElements() {
/* 125 */     addElement("JPEG2000SignatureBox", 
/* 126 */         getParent("JPEG2000SignatureBox"), 0);
/*     */ 
/*     */     
/* 129 */     addElement("JPEG2000FileTypeBox", 
/* 130 */         getParent("JPEG2000FileTypeBox"), 1);
/*     */     
/* 132 */     addElement("OtherBoxes", 
/* 133 */         getParent("OtherBoxes"), 3);
/*     */ 
/*     */     
/* 136 */     addElement("JPEG2000HeaderSuperBox", 
/* 137 */         getParent("JPEG2000HeaderSuperBox"), 3);
/*     */     
/* 139 */     addElement("JPEG2000CodeStreamBox", 
/* 140 */         getParent("JPEG2000CodeStreamBox"), 0);
/*     */     
/* 142 */     addElement("JPEG2000IntellectualPropertyRightsBox", 
/* 143 */         getParent("JPEG2000IntellectualPropertyRightsBox"), 1);
/*     */     
/* 145 */     addElement("JPEG2000XMLBox", 
/* 146 */         getParent("JPEG2000XMLBox"), 1);
/*     */     
/* 148 */     addElement("JPEG2000UUIDBox", 
/* 149 */         getParent("JPEG2000UUIDBox"), 1);
/*     */     
/* 151 */     addElement("JPEG2000UUIDInfoBox", 
/* 152 */         getParent("JPEG2000UUIDInfoBox"), 1);
/*     */ 
/*     */     
/* 155 */     addElement("JPEG2000HeaderBox", "JPEG2000HeaderSuperBox", 1);
/*     */ 
/*     */     
/* 158 */     addElement("OptionalBoxes", "JPEG2000HeaderSuperBox", 3);
/*     */ 
/*     */     
/* 161 */     addElement("JPEG2000BitsPerComponentBox", "OptionalBoxes", 1);
/*     */ 
/*     */     
/* 164 */     addElement("JPEG2000ColorSpecificationBox", "OptionalBoxes", 1);
/*     */ 
/*     */     
/* 167 */     addElement("JPEG2000PaletteBox", "OptionalBoxes", 1);
/*     */ 
/*     */     
/* 170 */     addElement("JPEG2000ComponentMappingBox", "OptionalBoxes", 1);
/*     */ 
/*     */     
/* 173 */     addElement("JPEG2000ChannelDefinitionBox", "OptionalBoxes", 1);
/*     */ 
/*     */     
/* 176 */     addElement("JPEG2000ResolutionBox", "OptionalBoxes", 1);
/*     */ 
/*     */ 
/*     */     
/* 180 */     addElement("JPEG2000CaptureResolutionBox", "JPEG2000ResolutionBox", 1);
/*     */ 
/*     */     
/* 183 */     addElement("JPEG2000DefaultDisplayResolutionBox", "JPEG2000ResolutionBox", 1);
/*     */ 
/*     */ 
/*     */     
/* 187 */     addElement("JPEG2000UUIDListBox", "JPEG2000UUIDInfoBox", 1);
/*     */ 
/*     */     
/* 190 */     addElement("JPEG2000DataEntryURLBox", "JPEG2000UUIDInfoBox", 1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 195 */     Enumeration<String> keys = parents.keys();
/* 196 */     while (keys.hasMoreElements()) {
/* 197 */       String s = keys.nextElement();
/* 198 */       if (s.startsWith("JPEG2000")) {
/* 199 */         addAttribute(s, "Length", 2, true, null);
/* 200 */         addAttribute(s, "Type", 0, true, Box.getTypeByName(s));
/* 201 */         addAttribute(s, "ExtraLength", 0, false, null);
/*     */ 
/*     */ 
/*     */         
/* 205 */         Class c = Box.getBoxClass(Box.getTypeInt(Box.getTypeByName(s)));
/*     */         
/*     */         try {
/* 208 */           Method m = c.getMethod("getElementNames", (Class[])null);
/* 209 */           String[] elementNames = (String[])m.invoke(null, (Object[])null);
/*     */           
/* 211 */           for (int i = 0; i < elementNames.length; i++)
/* 212 */             addElement(elementNames[i], s, 0); 
/* 213 */         } catch (Exception exception) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 219 */     addAttribute("JPEG2000SignatureBox", "Signature", 0, true, "0D0A870A");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     addElement("BitDepth", "JPEG2000BitsPerComponentBox", 0);
/*     */ 
/*     */ 
/*     */     
/* 229 */     addElement("NumberEntries", "JPEG2000PaletteBox", 0);
/*     */ 
/*     */ 
/*     */     
/* 233 */     addElement("NumberColors", "JPEG2000PaletteBox", 0);
/*     */ 
/*     */ 
/*     */     
/* 237 */     addElement("BitDepth", "JPEG2000PaletteBox", 0);
/*     */ 
/*     */ 
/*     */     
/* 241 */     addElement("LUT", "JPEG2000PaletteBox", 1, 1024);
/*     */ 
/*     */ 
/*     */     
/* 245 */     addElement("LUTRow", "LUT", 0);
/*     */ 
/*     */ 
/*     */     
/* 249 */     addElement("Component", "JPEG2000ComponentMappingBox", 0);
/*     */ 
/*     */ 
/*     */     
/* 253 */     addElement("ComponentType", "JPEG2000ComponentMappingBox", 0);
/*     */ 
/*     */ 
/*     */     
/* 257 */     addElement("ComponentAssociation", "JPEG2000ComponentMappingBox", 0);
/*     */ 
/*     */ 
/*     */     
/* 261 */     addElement("NumberOfDefinition", "JPEG2000ChannelDefinitionBox", 0);
/*     */ 
/*     */ 
/*     */     
/* 265 */     addElement("Definitions", "JPEG2000ChannelDefinitionBox", 0, 9);
/*     */ 
/*     */ 
/*     */     
/* 269 */     addElement("ChannelNumber", "Definitions", 0);
/*     */ 
/*     */ 
/*     */     
/* 273 */     addElement("ChannelType", "Definitions", 0);
/*     */ 
/*     */     
/* 276 */     addElement("ChannelAssociation", "Definitions", 0);
/*     */ 
/*     */     
/* 279 */     addElement("CodeStream", "JPEG2000CodeStreamBox", 0);
/*     */ 
/*     */     
/* 282 */     addElement("Content", "JPEG2000IntellectualPropertyRightsBox", 0);
/*     */ 
/*     */     
/* 285 */     addElement("Content", "JPEG2000XMLBox", 0);
/*     */ 
/*     */     
/* 288 */     addElement("UUID", "JPEG2000UUIDBox", 0);
/*     */ 
/*     */     
/* 291 */     addElement("Data", "JPEG2000UUIDBox", 0);
/*     */ 
/*     */     
/* 294 */     addElement("NumberUUID", "JPEG2000UUIDListBox", 0);
/*     */ 
/*     */     
/* 297 */     addElement("UUID", "JPEG2000UUIDListBox", 0);
/*     */ 
/*     */     
/* 300 */     addElement("Version", "JPEG2000DataEntryURLBox", 0);
/*     */ 
/*     */     
/* 303 */     addElement("Flags", "JPEG2000DataEntryURLBox", 0);
/*     */ 
/*     */     
/* 306 */     addElement("URL", "JPEG2000DataEntryURLBox", 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getParent(String elementName) {
/* 312 */     return (String)parents.get(elementName);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canNodeAppear(String elementName, ImageTypeSpecifier imageType) {
/* 317 */     ColorModel cm = imageType.getColorModel();
/* 318 */     if (!(cm instanceof java.awt.image.IndexColorModel) && 
/* 319 */       "JPEG2000PaletteBox".equals(elementName))
/* 320 */       return false; 
/* 321 */     if (!cm.hasAlpha() && 
/* 322 */       "JPEG2000ChannelDefinitionBox".equals(elementName)) {
/* 323 */       return false;
/*     */     }
/* 325 */     if (getParent(elementName) != null)
/* 326 */       return true; 
/* 327 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isLeaf(String name) {
/* 331 */     Set keys = parents.keySet();
/* 332 */     Iterator iterator = keys.iterator();
/* 333 */     while (iterator.hasNext()) {
/* 334 */       if (name.equals(parents.get(iterator.next()))) {
/* 335 */         return false;
/*     */       }
/*     */     } 
/* 338 */     return true;
/*     */   }
/*     */   
/*     */   public boolean singleInstance(String name) {
/* 342 */     return (!name.equals("JPEG2000IntellectualPropertyRightsBox") && 
/* 343 */       !name.equals("JPEG2000XMLBox") && 
/* 344 */       !name.equals("JPEG2000UUIDBox") && 
/* 345 */       !name.equals("JPEG2000UUIDInfoBox") && 
/* 346 */       !name.equals("JPEG2000UUIDListBox") && 
/* 347 */       !name.equals("JPEG2000DataEntryURLBox"));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
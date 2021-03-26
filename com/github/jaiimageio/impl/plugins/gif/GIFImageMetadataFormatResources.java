/*    */ package com.github.jaiimageio.impl.plugins.gif;
/*    */ 
/*    */ import java.util.ListResourceBundle;
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
/*    */ public class GIFImageMetadataFormatResources
/*    */   extends ListResourceBundle
/*    */ {
/*    */   protected Object[][] getContents() {
/* 55 */     return new Object[][] { { "ImageDescriptor", "The image descriptor" }, { "LocalColorTable", "The local color table" }, { "ColorTableEntry", "A local color table entry" }, { "GraphicControlExtension", "A graphic control extension" }, { "PlainTextExtension", "A plain text (text grid) extension" }, { "ApplicationExtensions", "A set of application extensions" }, { "ApplicationExtension", "An application extension" }, { "CommentExtensions", "A set of comments" }, { "CommentExtension", "A comment" }, { "ImageDescriptor/imageLeftPosition", "The X offset of the image relative to the screen origin" }, { "ImageDescriptor/imageTopPosition", "The Y offset of the image relative to the screen origin" }, { "ImageDescriptor/imageWidth", "The width of the image" }, { "ImageDescriptor/imageHeight", "The height of the image" }, { "ImageDescriptor/interlaceFlag", "True if the image is stored using interlacing" }, { "LocalColorTable/sizeOfLocalColorTable", "The number of entries in the local color table" }, { "LocalColorTable/sortFlag", "True if the local color table is sorted by frequency" }, { "ColorTableEntry/index", "The index of the color table entry" }, { "ColorTableEntry/red", "The red value for the color table entry" }, { "ColorTableEntry/green", "The green value for the color table entry" }, { "ColorTableEntry/blue", "The blue value for the color table entry" }, { "GraphicControlExtension/disposalMethod", "The disposal method for this frame" }, { "GraphicControlExtension/userInputFlag", "True if the frame should be advanced based on user input" }, { "GraphicControlExtension/transparentColorFlag", "True if a transparent color exists" }, { "GraphicControlExtension/delayTime", "The time to delay between frames, in hundredths of a second" }, { "GraphicControlExtension/transparentColorIndex", "The transparent color, if transparentColorFlag is true" }, { "PlainTextExtension/textGridLeft", "The X offset of the text grid" }, { "PlainTextExtension/textGridTop", "The Y offset of the text grid" }, { "PlainTextExtension/textGridWidth", "The number of columns in the text grid" }, { "PlainTextExtension/textGridHeight", "The number of rows in the text grid" }, { "PlainTextExtension/characterCellWidth", "The width of a character cell" }, { "PlainTextExtension/characterCellHeight", "The height of a character cell" }, { "PlainTextExtension/textForegroundColor", "The text foreground color index" }, { "PlainTextExtension/textBackgroundColor", "The text background color index" }, { "ApplicationExtension/applicationID", "The application ID" }, { "ApplicationExtension/authenticationCode", "The authentication code" }, { "CommentExtension/value", "The comment" } };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/gif/GIFImageMetadataFormatResources.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
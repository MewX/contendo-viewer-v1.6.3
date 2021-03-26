/*     */ package org.apache.pdfbox.contentstream;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingColor;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingColorN;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingColorSpace;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingDeviceCMYKColor;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingDeviceGrayColor;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingDeviceRGBColor;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetStrokingColor;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetStrokingColorN;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetStrokingColorSpace;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetStrokingDeviceCMYKColor;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetStrokingDeviceGrayColor;
/*     */ import org.apache.pdfbox.contentstream.operator.color.SetStrokingDeviceRGBColor;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.AppendRectangleToPath;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.BeginInlineImage;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.ClipEvenOddRule;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.ClipNonZeroRule;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.CloseAndStrokePath;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.CloseFillEvenOddAndStrokePath;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.CloseFillNonZeroAndStrokePath;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.ClosePath;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.CurveTo;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.CurveToReplicateFinalPoint;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.CurveToReplicateInitialPoint;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.DrawObject;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.EndPath;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.FillEvenOddAndStrokePath;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.FillEvenOddRule;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.FillNonZeroAndStrokePath;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.FillNonZeroRule;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.LegacyFillNonZeroRule;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.LineTo;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.MoveTo;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.ShadingFill;
/*     */ import org.apache.pdfbox.contentstream.operator.graphics.StrokePath;
/*     */ import org.apache.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequence;
/*     */ import org.apache.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequenceWithProperties;
/*     */ import org.apache.pdfbox.contentstream.operator.markedcontent.EndMarkedContentSequence;
/*     */ import org.apache.pdfbox.contentstream.operator.state.Concatenate;
/*     */ import org.apache.pdfbox.contentstream.operator.state.Restore;
/*     */ import org.apache.pdfbox.contentstream.operator.state.Save;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetFlatness;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetGraphicsStateParameters;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetLineCapStyle;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetLineDashPattern;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetLineJoinStyle;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetLineMiterLimit;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetLineWidth;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetMatrix;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetRenderingIntent;
/*     */ import org.apache.pdfbox.contentstream.operator.text.BeginText;
/*     */ import org.apache.pdfbox.contentstream.operator.text.EndText;
/*     */ import org.apache.pdfbox.contentstream.operator.text.MoveText;
/*     */ import org.apache.pdfbox.contentstream.operator.text.MoveTextSetLeading;
/*     */ import org.apache.pdfbox.contentstream.operator.text.NextLine;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetCharSpacing;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetFontAndSize;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetTextHorizontalScaling;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetTextLeading;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetTextRenderingMode;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetTextRise;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetWordSpacing;
/*     */ import org.apache.pdfbox.contentstream.operator.text.ShowText;
/*     */ import org.apache.pdfbox.contentstream.operator.text.ShowTextAdjusted;
/*     */ import org.apache.pdfbox.contentstream.operator.text.ShowTextLine;
/*     */ import org.apache.pdfbox.contentstream.operator.text.ShowTextLineAndSpace;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDFGraphicsStreamEngine
/*     */   extends PDFStreamEngine
/*     */ {
/*     */   private final PDPage page;
/*     */   
/*     */   protected PDFGraphicsStreamEngine(PDPage page) {
/* 107 */     this.page = page;
/*     */     
/* 109 */     addOperator((OperatorProcessor)new CloseFillNonZeroAndStrokePath());
/* 110 */     addOperator((OperatorProcessor)new FillNonZeroAndStrokePath());
/* 111 */     addOperator((OperatorProcessor)new CloseFillEvenOddAndStrokePath());
/* 112 */     addOperator((OperatorProcessor)new FillEvenOddAndStrokePath());
/* 113 */     addOperator((OperatorProcessor)new BeginInlineImage());
/* 114 */     addOperator((OperatorProcessor)new BeginText());
/* 115 */     addOperator((OperatorProcessor)new CurveTo());
/* 116 */     addOperator((OperatorProcessor)new Concatenate());
/* 117 */     addOperator((OperatorProcessor)new SetStrokingColorSpace());
/* 118 */     addOperator((OperatorProcessor)new SetNonStrokingColorSpace());
/* 119 */     addOperator((OperatorProcessor)new SetLineDashPattern());
/* 120 */     addOperator((OperatorProcessor)new DrawObject());
/* 121 */     addOperator((OperatorProcessor)new EndText());
/* 122 */     addOperator((OperatorProcessor)new FillNonZeroRule());
/* 123 */     addOperator((OperatorProcessor)new LegacyFillNonZeroRule());
/* 124 */     addOperator((OperatorProcessor)new FillEvenOddRule());
/* 125 */     addOperator((OperatorProcessor)new SetStrokingDeviceGrayColor());
/* 126 */     addOperator((OperatorProcessor)new SetNonStrokingDeviceGrayColor());
/* 127 */     addOperator((OperatorProcessor)new SetGraphicsStateParameters());
/* 128 */     addOperator((OperatorProcessor)new ClosePath());
/* 129 */     addOperator((OperatorProcessor)new SetFlatness());
/* 130 */     addOperator((OperatorProcessor)new SetLineJoinStyle());
/* 131 */     addOperator((OperatorProcessor)new SetLineCapStyle());
/* 132 */     addOperator((OperatorProcessor)new SetStrokingDeviceCMYKColor());
/* 133 */     addOperator((OperatorProcessor)new SetNonStrokingDeviceCMYKColor());
/* 134 */     addOperator((OperatorProcessor)new LineTo());
/* 135 */     addOperator((OperatorProcessor)new MoveTo());
/* 136 */     addOperator((OperatorProcessor)new SetLineMiterLimit());
/* 137 */     addOperator((OperatorProcessor)new EndPath());
/* 138 */     addOperator((OperatorProcessor)new Save());
/* 139 */     addOperator((OperatorProcessor)new Restore());
/* 140 */     addOperator((OperatorProcessor)new AppendRectangleToPath());
/* 141 */     addOperator((OperatorProcessor)new SetStrokingDeviceRGBColor());
/* 142 */     addOperator((OperatorProcessor)new SetNonStrokingDeviceRGBColor());
/* 143 */     addOperator((OperatorProcessor)new SetRenderingIntent());
/* 144 */     addOperator((OperatorProcessor)new CloseAndStrokePath());
/* 145 */     addOperator((OperatorProcessor)new StrokePath());
/* 146 */     addOperator((OperatorProcessor)new SetStrokingColor());
/* 147 */     addOperator((OperatorProcessor)new SetNonStrokingColor());
/* 148 */     addOperator((OperatorProcessor)new SetStrokingColorN());
/* 149 */     addOperator((OperatorProcessor)new SetNonStrokingColorN());
/* 150 */     addOperator((OperatorProcessor)new ShadingFill());
/* 151 */     addOperator((OperatorProcessor)new NextLine());
/* 152 */     addOperator((OperatorProcessor)new SetCharSpacing());
/* 153 */     addOperator((OperatorProcessor)new MoveText());
/* 154 */     addOperator((OperatorProcessor)new MoveTextSetLeading());
/* 155 */     addOperator((OperatorProcessor)new SetFontAndSize());
/* 156 */     addOperator((OperatorProcessor)new ShowText());
/* 157 */     addOperator((OperatorProcessor)new ShowTextAdjusted());
/* 158 */     addOperator((OperatorProcessor)new SetTextLeading());
/* 159 */     addOperator((OperatorProcessor)new SetMatrix());
/* 160 */     addOperator((OperatorProcessor)new SetTextRenderingMode());
/* 161 */     addOperator((OperatorProcessor)new SetTextRise());
/* 162 */     addOperator((OperatorProcessor)new SetWordSpacing());
/* 163 */     addOperator((OperatorProcessor)new SetTextHorizontalScaling());
/* 164 */     addOperator((OperatorProcessor)new CurveToReplicateInitialPoint());
/* 165 */     addOperator((OperatorProcessor)new SetLineWidth());
/* 166 */     addOperator((OperatorProcessor)new ClipNonZeroRule());
/* 167 */     addOperator((OperatorProcessor)new ClipEvenOddRule());
/* 168 */     addOperator((OperatorProcessor)new CurveToReplicateFinalPoint());
/* 169 */     addOperator((OperatorProcessor)new ShowTextLine());
/* 170 */     addOperator((OperatorProcessor)new ShowTextLineAndSpace());
/* 171 */     addOperator((OperatorProcessor)new BeginMarkedContentSequence());
/* 172 */     addOperator((OperatorProcessor)new BeginMarkedContentSequenceWithProperties());
/* 173 */     addOperator((OperatorProcessor)new EndMarkedContentSequence());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final PDPage getPage() {
/* 184 */     return this.page;
/*     */   }
/*     */   
/*     */   public abstract void appendRectangle(Point2D paramPoint2D1, Point2D paramPoint2D2, Point2D paramPoint2D3, Point2D paramPoint2D4) throws IOException;
/*     */   
/*     */   public abstract void drawImage(PDImage paramPDImage) throws IOException;
/*     */   
/*     */   public abstract void clip(int paramInt) throws IOException;
/*     */   
/*     */   public abstract void moveTo(float paramFloat1, float paramFloat2) throws IOException;
/*     */   
/*     */   public abstract void lineTo(float paramFloat1, float paramFloat2) throws IOException;
/*     */   
/*     */   public abstract void curveTo(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) throws IOException;
/*     */   
/*     */   public abstract Point2D getCurrentPoint() throws IOException;
/*     */   
/*     */   public abstract void closePath() throws IOException;
/*     */   
/*     */   public abstract void endPath() throws IOException;
/*     */   
/*     */   public abstract void strokePath() throws IOException;
/*     */   
/*     */   public abstract void fillPath(int paramInt) throws IOException;
/*     */   
/*     */   public abstract void fillAndStrokePath(int paramInt) throws IOException;
/*     */   
/*     */   public abstract void shadingFill(COSName paramCOSName) throws IOException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/PDFGraphicsStreamEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
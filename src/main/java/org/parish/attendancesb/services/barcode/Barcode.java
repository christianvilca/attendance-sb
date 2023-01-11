package org.parish.attendancesb.services.barcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Barcode {

    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 96, 32);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.MARGIN, 0);

        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200, hints);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static BufferedImage generateEAN13BarcodeImageByBarcode4j(String barcodeText) {
        EAN13Bean barcodeGenerator = new EAN13Bean();
        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(80, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        barcodeGenerator.setBarHeight(10);

        barcodeGenerator.generateBarcode(canvas, barcodeText);
        return canvas.getBufferedImage();
    }

    public static void main(String[] args) throws Exception {
        String nameFile = "Christian Tony bar";
        nameFile = "123456789012";
        nameFile = "000000000001";
//        nameFile = "000000000002";
        String rootPath = "C:/Users/Christian/IdeaProjects/attendance-sb/" + nameFile + "qr1.png"; //"C:/java/"
        File outputFile = new File(rootPath);
//        BufferedImage bImage = Barcode.generateQRCodeImage(nameFile);
        BufferedImage bImage = Barcode.generateEAN13BarcodeImageByBarcode4j(nameFile);
//        BufferedImage bImage = Barcode.generateEAN13BarcodeImage(nameFile);

        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package interfaz;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class GeneradorQR {

    public static BufferedImage generateQRCodeImage(String text, int width, int height)
            throws WriterException {
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    
    public static ImageIcon generateQRCodeIcon(String text, int width, int height)
            throws WriterException {
        BufferedImage qrImage = generateQRCodeImage(text, width, height);
        return new ImageIcon(qrImage);
    }

}


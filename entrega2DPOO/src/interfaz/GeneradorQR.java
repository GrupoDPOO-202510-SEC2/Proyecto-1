package interfaz;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GeneradorQR {

    /**
     * Generates a QR code image from the given text.
     *
     * @param text The text to encode in the QR code.
     * @param width The width of the QR code image.
     * @param height The height of the QR code image.
     * @return A BufferedImage representing the QR code.
     * @throws WriterException If an error occurs during encoding.
     */
    public static BufferedImage generateQRCodeImage(String text, int width, int height)
            throws WriterException {
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * Generates an ImageIcon from the given text encoded as a QR code.
     *
     * @param text The text to encode in the QR code.
     * @param width The width of the QR code image.
     * @param height The height of the QR code image.
     * @return An ImageIcon representing the QR code.
     * @throws WriterException If an error occurs during encoding.
     */
    public static ImageIcon generateQRCodeIcon(String text)
            throws WriterException {
        BufferedImage qrImage = generateQRCodeImage(text, 100, 100);
        return new ImageIcon(qrImage);
    }

    public static void main(String[] args) {
        try {
            ImageIcon qrCodeIcon = generateQRCodeIcon("Hello, World!");
            JLabel label = new JLabel(qrCodeIcon);
            JOptionPane.showMessageDialog(null, label, "QR Code", JOptionPane.PLAIN_MESSAGE);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}


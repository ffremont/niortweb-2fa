package com.github.ffremont.mfa.image;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public final class QrCodeUtils {

    public static BufferedImage gen(String barcodeText){
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                null;
        try {
            bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        } catch (WriterException e) {
            throw new RuntimeException("Oups sur la génération du QRcode",e);
        }

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}

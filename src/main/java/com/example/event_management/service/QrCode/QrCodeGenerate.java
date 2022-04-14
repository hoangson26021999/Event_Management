package com.example.event_management.service.QrCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QrCodeGenerate {

    @Getter
    @Setter
    private String input = "Hello";

    @Value("${path}")
    private String outputFile= "src/main/resources/QrCode/QrTest4.jpg" ;

    public void generateQrCode () throws IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix = null;
        try {
            matrix = qrCodeWriter.encode(input, BarcodeFormat.QR_CODE, 200, 200);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        Path path = FileSystems.getDefault().getPath(outputFile);
        MatrixToImageWriter.writeToPath(matrix, "JPG", path);

        /* // Write to byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);
        byte[] pngByteArray = outputStream.toByteArray();
        for (int i = 0; i < pngByteArray.length; i++) {
            System.out.print(pngByteArray[i]);
        }*/

    }

}

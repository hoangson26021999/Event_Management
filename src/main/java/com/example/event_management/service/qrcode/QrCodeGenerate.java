package com.example.event_management.service.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;

@Service
public class QrCodeGenerate {

    @Getter
    @Setter
    private String input ;

    private String outputFile= "src/main/resources/QrCode/QrTest.jpg" ;

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


    // Function to read the QR file
    public String readQR(String path, String charset, Map hashMap)  throws FileNotFoundException, IOException,  NotFoundException
    {
        BinaryBitmap binaryBitmap
                = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(
                                new FileInputStream(path)))));

        Result result
                = new MultiFormatReader().decode(binaryBitmap);

        return result.getText();
    }

}

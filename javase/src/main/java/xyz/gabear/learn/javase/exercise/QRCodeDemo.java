package xyz.gabear.learn.javase.exercise;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeDemo {
    private static int WIDTH = 300;
    private static int HEIGHT = 300;
    private static String FORMAT = "png";

    public static void main(String[] args) {
        // String content = "Java生成二维码";
        // vCard文件
        String content = "BEGIN:VCARD\n" +
                "VERSION:2.1\n" +
                "N:Gump;Forrest\n" +
                "FN:Forrest Gump\n" +
                "ORG:Gump Shrimp Co.\n" +
                "TITLE:Shrimp Man\n" +
                "TEL;WORK;VOICE:(111) 555-1212\n" +
                "TEL;HOME;VOICE:(404) 555-1212\n" +
                "ADR;WORK:;;100 Waters Edge;Baytown;LA;30314;United States of America\n" +
                "LABEL;WORK;ENCODING=QUOTED-PRINTABLE:100 Waters Edge=0D=0ABaytown, LA 30314=0D=0AUnited States of America\n" +
                "ADR;HOME:;;42 Plantation St.;Baytown;LA;30314;United States of America\n" +
                "LABEL;HOME;ENCODING=QUOTED-PRINTABLE:42 Plantation St.=0D=0ABaytown, LA 30314=0D=0AUnited States of America\n" +
                "EMAIL;PREF;INTERNET:forrestgump@walladalla.com\n" +
                "REV:20080424T195243Z\n" +
                "END:VCARD";
        String pathname = "D:/card.png";
        boolean qrCode = encodeQRCode(content, pathname);
        if (qrCode) {
            Result result = decodeQRCode(pathname);
            System.out.println("解析结果：" + result.toString());
            System.out.println("二维码格式类型：" + result.getBarcodeFormat());
            System.out.println("二维码文本内容：" + result.getText());
        } else {
            System.out.println("二维码生成失败");
        }
    }

    public static Result decodeQRCode(String pathname) {
        Result result = null;
        try {
            File file = new File(pathname);
            BufferedImage image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            result = new MultiFormatReader().decode(binaryBitmap, hints);
        } catch (IOException | NotFoundException e) {
            return null;
        }
        return result;
    }

    public static boolean encodeQRCode(String content, String pathname) {
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.MARGIN, 1);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            Path path = new File(pathname).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, path);
        } catch (WriterException | IOException e) {
            return false;
        }
        return true;
    }
}

package com.jim.util.qrcode;

import com.google.zxing.common.BitMatrix;
import net.nicoulaj.bonita.connectors.EncodeQRCode;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/29/13
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class QRCodeDemo {
    public static void main(String[] args) {
        EncodeQRCode qrCode = new EncodeQRCode();
        qrCode.setText("Hello");

        BitMatrix matrix = qrCode.getMatrix();

    }
}

package com.dantsu.thermalprinter.async;

import android.content.Context;

import com.dantsu.escposprinter.connection.DeviceConnection;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections;
import com.dantsu.escposprinter.exceptions.EscPosConnectionException;

public class AsyncBluetoothEscPosPrint extends AsyncEscPosPrint {
    public AsyncBluetoothEscPosPrint(Context context, OnPrintFinished onPrintFinished) {
        super(context, onPrintFinished,"BLE-Task");
    }

    public PrinterStatus doInBackground(AsyncEscPosPrinter... printersData) {
        if (printersData.length == 0) {
            return new PrinterStatus(null, Constant.FINISH_NO_PRINTER);
        }

        AsyncEscPosPrinter printerData = printersData[0];
        DeviceConnection deviceConnection = printerData.getPrinterConnection();

        this.publishProgress(Constant.PROGRESS_CONNECTING);

        if (deviceConnection == null) {
            printersData[0] = new AsyncEscPosPrinter(
                    BluetoothPrintersConnections.selectFirstPaired(),
                    printerData.getPrinterDpi(),
                    printerData.getPrinterWidthMM(),
                    printerData.getPrinterNbrCharactersPerLine()
            );
            printersData[0].setTextsToPrint(printerData.getTextsToPrint());
        } else {
            try {
                deviceConnection.connect();
            } catch (EscPosConnectionException e) {
                e.printStackTrace();
            }
        }

        return super.doInBackground(printersData);
    }
}

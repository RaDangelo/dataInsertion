package br.com.rafaeldangelobergami.datainsertion;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button requestAccessButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestAccessButton = (Button)findViewById(R.id.button13);
        requestAccessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendData() throws IOException {
        // Find all available drivers from attached devices.
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            return;
        }

        UsbSerialPort port = driver.getPorts().get(0);
        try {
            port.open(connection);
            port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            String data = "0123456#";
            byte buffer[] = data.getBytes();
            int numBytesRead = port.write(buffer, 1000);
//            Log.d(TAG, "Read " + numBytesRead + " bytes.");
        } catch (IOException e) {
            // Deal with error.
        } finally {
            port.close();
        }
    }

    public void sendZero() {
        System.out.println("test");
    }

    public void sendSharp() {

    }

    public void sendAsterisk() {

    }

    public void sendOne() {

    }

    public void sendTwo() {

    }

    public void sendThree() {

    }

    public void sendFour() {

    }

    public void sendFive() {

    }

    public void sendSix() {

    }

    public void sendSeven() {

    }

    public void sendEight() {

    }

    public void sendNine() {

    }


}

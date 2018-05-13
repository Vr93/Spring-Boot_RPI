/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vegard
 */
@Service
public class PCF8591 {
    private static final int PCF8591 = 0x48; // address pin not connected (FLOATING)
    private I2CBus i2c;
    private I2CDevice device;
    private int analogValues[]; // Array of analog inputs (A0, A1, A2, A3).
    private int analogOutValue; // Value of the analog out pin. (AOUT).

    private boolean I2C_DEV_READ;

    public PCF8591(){
        this.analogValues = new int[4];
        //this.analogOutValue = 0;

        try {
            i2c = I2CFactory.getInstance(I2CBus.BUS_1);
            this.device = i2c.getDevice(PCF8591);
            //classThread();
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void classThread(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(5000);

                    }  catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        t.start();
    }



    public int[] getAnalogValues(){
        try {
                for (int i = 0; i <= 3; i++) {
                    device.read((byte) (0x40 | (i & 3)));
                    analogValues[i] = device.read((byte) (0x40 | (i & 3)));
                    analogValues[i] = device.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return analogValues;
    }

    /*public void setAnalogValue(int value){
        try {
            device.write((byte) 0x40, (byte) value);
            setAnalogOutValueChange(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAnalogOutValue() {
        return analogOutValue;
    }

    private void setAnalogOutValueChange(int analogOutValue) {
        this.analogOutValue = analogOutValue;
    }*/

    /*public int getAnalogValue0(){
        List<Integer> validAddresses = new ArrayList<Integer>();

        for (int i = 1; i < 128; i++) {
            try {
                I2CDevice device  = i2c.getDevice(i);
                device.write((byte)0);
                validAddresses.add(i);
            } catch (Exception ignore) { }
        }

        System.out.println("Found: ---");
        for (int a : validAddresses) {
            System.out.println("Address: " + Integer.toHexString(a));
        }
        System.out.println("----------");
        return 100;
    }*/
    
}

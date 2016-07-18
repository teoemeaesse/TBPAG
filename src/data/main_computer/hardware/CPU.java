package data.main_computer.hardware;

import data.main_computer.Hardware;

/**
 * Created by Tomás on 07/18/2016.
 */
public class CPU extends Hardware {
    public double frequency;
    public int cores;
    public boolean assembled = false;

    public CPU(double frequency, int cores){
        this.frequency = frequency;
        this.cores = cores;
    }
}

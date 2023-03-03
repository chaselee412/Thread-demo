package com.caiyu.UDPtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TalkSend implements Runnable{
    DatagramSocket socket = null;
    BufferedReader reader = null;
    DatagramPacket packet = null;
    private int fromPort;
    private String toIP;
    private int toPort;

    public TalkSend(int fromPort, String toIP, int toPort) {
        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toPort = toPort;
        try {
            socket = new DatagramSocket(fromPort);
            reader = new BufferedReader(new InputStreamReader(System.in));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public TalkSend() {
    }

    @Override
    public void run() {


        while (true){

            try {
                String data = reader.readLine();
                byte[] datas = data.getBytes();

                DatagramPacket packet = new DatagramPacket(datas,0,datas.length,new InetSocketAddress(this.toIP,this.toPort));
                socket.send(packet);
                if(data.equals("bye")){ //多一个引号引发的血案提前跳出break
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        socket.close();
    }
}

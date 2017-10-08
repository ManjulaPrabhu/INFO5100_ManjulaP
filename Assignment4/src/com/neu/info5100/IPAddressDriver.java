package com.neu.info5100;
import java.util.Scanner;

public class IPAddressDriver {
    public static void main(String[] args){
        IPAddress ip=new IPAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));
    }

    private static class IPAddress {
        String dottedDecimal;
        int position;
        int firstOctet,secondOctet,thirdOctet,fourthOctet;
        String[] octets;

        IPAddress(String dottecDecimal){
            this.dottedDecimal=dottecDecimal;
        }

        String getDottedDecimal(){
            return dottedDecimal;
        }

        int getOctet(int position){
            octets=(dottedDecimal.split("[.]"));
            switch(position){
                case 1: firstOctet=Integer.parseInt(octets[0]);
                return firstOctet;
                case 2: secondOctet=Integer.parseInt(octets[1]);
                return secondOctet;
                case 3: thirdOctet=Integer.parseInt(octets[2]);
                return thirdOctet;
                case 4: fourthOctet=Integer.parseInt(octets[3]);
                return fourthOctet;

                default: System.out.println("Enter a valid Position");
            }
            return 0;
        }
    }
}

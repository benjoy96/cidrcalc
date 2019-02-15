package main.java.com.github.benjoy96.cidrcalc;

import java.util.Scanner;

public class Calculator {
	private static int[] convertToSubnetMask(int mask) {
		int[] subnetMask = new int[4];
		for (int i = 0; i < subnetMask.length; i++) {
			if (mask >= 8) {
				subnetMask[i] = 255;
				mask = mask - 8;
			}
			else if ((mask > 0) && (mask < 8)) {
				subnetMask[i] = (256 - (int) Math.pow(2, (8 - mask)));
				break;
			}
		}
		return subnetMask;
	}
	private static String toNetworkAddress(String[] ipAddress, int mask) {
		int firstOctet = Integer.parseInt(ipAddress[0]) & convertToSubnetMask(mask)[0];
		int secondOctet = Integer.parseInt(ipAddress[1]) & convertToSubnetMask(mask)[1];
		int thirdOctet = Integer.parseInt(ipAddress[2]) & convertToSubnetMask(mask)[2];
		int fourthOctet = Integer.parseInt(ipAddress[3]) & convertToSubnetMask(mask)[3];
		String networkAddress = String.format("%d.%d.%d.%d", firstOctet, secondOctet, thirdOctet, fourthOctet );
		return networkAddress;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter IP address and CIDR mask:");
		String[] data = input.nextLine().split("/");
		input.close();
		String[] ipAddress = data[0].split("\\.");
		int mask = Integer.parseInt(data[1]);
		System.out.println("Network Address: " + toNetworkAddress(ipAddress, mask));
		System.out.println("Subnet Mask: " + String.format("%d.%d.%d.%d", convertToSubnetMask(mask)[0], convertToSubnetMask(mask)[1], convertToSubnetMask(mask)[2], convertToSubnetMask(mask)[3]));
	}
}

package Q2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Q2b {
    private int totalPorts;
    private Set<Integer> blacklist;
    private int whitelistedCount;
    private Random random;

    public Q2b(int totalPorts, int[] blacklistedPorts) {
        this.totalPorts = totalPorts;
        this.blacklist = new HashSet<>();
        for (int port : blacklistedPorts) {
            // Populate the blacklist set
            blacklist.add(port);
        }
        this.whitelistedCount = totalPorts - blacklist.size();
        this.random = new Random();
    }

    public int getWhitelistedPort() {
        int randomIndex = random.nextInt(whitelistedCount);
        int whitelistedPort = 0;
        for (int port = 0; port < totalPorts; port++) {
            if (!blacklist.contains(port)) {
                if (randomIndex == 0) {
                    whitelistedPort = port;
                    break;
                }
                randomIndex--;
            }
        }
        return whitelistedPort;
    }

    public static void main(String[] args) {
        int[] blacklistedPorts = { 2, 3, 5 };
        Q2b portPicker = new Q2b(7, blacklistedPorts);
        System.out.println(portPicker.getWhitelistedPort());
        System.out.println(portPicker.getWhitelistedPort());
        System.out.println(portPicker.getWhitelistedPort());
        System.out.println(portPicker.getWhitelistedPort());
        System.out.println(portPicker.getWhitelistedPort());
    }
}

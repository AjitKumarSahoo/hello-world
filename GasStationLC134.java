package Practice;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 10/13/2016.
 */
public class GasStationLC134 {

    private int canCompleteCircuit(int[] gas, int[] cost) {

        int n = gas.length, remGas = 0, start = 0, sum = 0;

        for(int i = 0; i < n; i++){
            remGas += gas[i] - cost[i];
            sum += remGas;
            if(remGas < 0){
                remGas = 0;
                start = i + 1;
            }
        }
        return sum < 0 ? -1 : start;
    }

    public static void main(String[] args) {
        System.out.println(new GasStationLC134().canCompleteCircuit(new int[]{1,2}, new int[]{2,1}));
    }

}




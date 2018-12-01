import java.util.ArrayList;


/**
 * <h1>CargoPlane</h1> Represents a Cargo Plane
 */
public class CargoPlane extends Vehicle {
    final double GAS_RATE = 2.33;

    /**
     * Default Constructor
     */
    //============================================================================
    //TODO
    
    //============================================================================

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================
    //TODO
    
    //============================================================================

    /**
     * Overides its superclass method. Instead, after each iteration, the range will
     * increase by 10.
     *
     * @param warehousePackages List of packages to add from
     */
    @Override
    public void fill(ArrayList<Package> warehousePackages) {
    	//TODO
        
    }

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Cargo Plane.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 2.33)
     * </p>
     */

    @Override
    public double getProfit() {
        double revenue = 0;
        int maxRange = 0;
        double cost = 0;
        for (int i = 0; i < getPackages().size(); i++) {
            revenue += getPackages().get(i).getPrice();
        }
        for (int i = 0; i < getPackages().size(); i++) {
            int zip = getPackages().get(i).getDestination().getZipCode();
            int distance = Math.abs(zip - getZipDest());
            if (distance >= maxRange) {
                maxRange = distance;
            }
        }
        if(maxRange%10 != 0) {
            maxRange = maxRange - (maxRange%10) + 10;
        }
        cost = maxRange * GAS_RATE;
        return (revenue - cost);
    }

    /**
     * Generates a String of the Cargo Plane report. Cargo plane report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in cargo plane</li>
     * </ul>
     *
     * @return Cargo Plane Report
     */
    @Override
    public String report() {
        String license = "License Plate No.: " + getLicensePlate();
        String destination = "Destination: " + getZipDest();
        String weight = "Weight Load: " + getCurrentWeight() + "/" + getMaxWeight();
        String profit = String.format("Net Profit: %.2f", getProfit());
        String labels = "";
        for (int i = 0; i < getPackages().size() ; i++) {
            labels += getPackages().get(i).shippingLabel();
        }
        String report ="======== Cargo Plane Report =======\n"
                + license + "\n" + destination + "\n" + weight + "\n" + profit +"\n" + labels;
        return report;
       
    }

   
   
}
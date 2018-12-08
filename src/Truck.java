/**
 * CS18000 Project 5 - Amazon
 *
 * <h1>Truck</h1> Represents a Truck
 *
 * @author Jacquie Yu, Siddarth Pillai
 * @version 2018-12-06
 */
public class Truck extends Vehicle {

    private final double gasRate = 1.66;

    /**
     * Default Constructor
     */
    //============================================================================
    Truck() {
        super();
    }
    //============================================================================

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================
    public Truck(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }

    //============================================================================

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Vehicle.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 1.66)
     * </p>
     */
    @Override
    public double getProfit() {
        double revenue = 0;
        int maxRange = 0;
        double cost = 0;
        double profit = 0;
        if (getPackages().size() == 0) {
            return 0;
        }
        for (int i = 0; i < getPackages().size(); i++) {
            revenue += getPackages().get(i).getPrice();
        }
        for (int i = 0; i < getPackages().size(); i++) {
            int zip = getPackages().get(i).getDestination().getZipCode();
            int distance = Math.abs(zip - getZipDest());
            if (distance > maxRange) {
                maxRange = distance;
            }
        }
        cost = maxRange * gasRate;
        profit = revenue - cost;
        System.out.println("revenue: " + revenue + "\nCost: " + cost + "\nProfit: " + profit);

        String letsTryRounding;
        double actualProfitsRounded;

        if (profit < 0) {
            letsTryRounding = String.format("%.2f", (profit * -1));
            actualProfitsRounded = Double.parseDouble(letsTryRounding) * -1;
        } else {
            letsTryRounding = String.format("%.2f", (profit));
            actualProfitsRounded = Double.parseDouble(letsTryRounding);
        }
//        System.out.println(maxRange);

        return (profit);
    }

    /**
     * Generates a String of the truck report. Truck report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in truck</li>
     * </ul>
     *
     * @return Truck Report
     */
    @Override
    public String report() {
        String license = "License Plate No.: " + getLicensePlate();
        String destination = "Destination: " + getZipDest();
        String weight = "Weight Load: " + getCurrentWeight() + "/" + getMaxWeight();
        String profit = String.format("Net Profit: $%.2f", getProfit());
        String labels = "=====Shipping Labels=====\n";
        for (int i = 0; i < getPackages().size(); i++) {
            labels += getPackages().get(i).shippingLabel();
            System.out.println("Inside report: " + getPackages().get(i).getWeight());
        }
        labels = labels.concat("==============================");
        String report = "======== Truck Report =======\n"
                + license + "\n" + destination + "\n" + weight + "\n" + profit + "\n" + labels;
        return report;

    }


}
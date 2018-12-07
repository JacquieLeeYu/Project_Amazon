import java.util.ArrayList;

/**
 * CS18000 Project 5 - Amazon
 *
 * <h1>Vehicle</h1> Represents a vehicle
 *
 * @author Jacquie Yu, Siddarth Pillai
 * @version 2018-12-06
 */

public class Vehicle implements Profitable {
    private String licensePlate;
    private double maxWeight;
    private double currentWeight;
    private int zipDest;
    private ArrayList<Package> packages;


    /**
     * Default Constructor
     */
    //============================================================================
    Vehicle() {
        this("", 0);
        this.currentWeight = 0;
        this.zipDest = 0;
        this.packages = new ArrayList<>();
    }

    //============================================================================


    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight of vehicle
     */
    //============================================================================
    public Vehicle(String licensePlate, double maxWeight) {
        this.licensePlate = licensePlate;
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        this.zipDest = 0;
        this.packages = new ArrayList<>();
    }

    //============================================================================


    /**
     * Returns the license plate of this vehicle
     *
     * @return license plate of this vehicle
     */
    public String getLicensePlate() {
        return licensePlate;
    }


    /**
     * Updates the license plate of vehicle
     *
     * @param licensePlate license plate to be updated to
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    /**
     * Returns the maximum weight this vehicle can carry
     *
     * @return the maximum weight that this vehicle can carry
     */
    public double getMaxWeight() {
        return maxWeight;
    }


    /**
     * Updates the maximum weight of this vehicle
     *
     * @param maxWeight max weight to be updated to
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }


    /**
     * Returns the current weight of all packages inside vehicle
     *
     * @return current weight of all packages inside vehicle
     */
    public double getCurrentWeight() {
        return currentWeight;
    }


    /**
     * Returns the current ZIP code desitnation of the vehicle
     *
     * @return current ZIP code destination of vehicle
     */
    public int getZipDest() {
        return zipDest;
    }


    /**
     * Updates the ZIP code destination of vehicle
     *
     * @param zipDest ZIP code destination to be updated to
     */
    public void setZipDest(int zipDest) {
        this.zipDest = zipDest;
        System.out.println("new zip =" + zipDest);
    }


    /**
     * Returns ArrayList of packages currently in Vehicle
     *
     * @return ArrayList of packages in vehicle
     */
    public ArrayList<Package> getPackages() {
        return packages;
    }


    /**
     * Adds Package to the vehicle only if has room to carry it (adding it would not
     * cause it to go over its maximum carry weight).
     *
     * @param pkg Package to add
     * @return whether or not it was successful in adding the package
     */
    public boolean addPackage(Package pkg) {
        if ((currentWeight + pkg.getWeight()) < maxWeight) {
            packages.add(pkg);
            return true;
        } else return false;
    }


    /**
     * Clears vehicle of packages and resets its weight to zero
     */
    public void empty() {
        this.packages = new ArrayList<>();
        this.currentWeight = 0;
    }


    /**
     * Returns true if the Vehicle has reached its maximum weight load, false
     * otherwise.
     *
     * @return whether or not Vehicle is full
     */
    public boolean isFull() {
        return (currentWeight >= maxWeight);
    }

    @Override
    public double getProfit() {
        return 0;
    }

    @Override
    public String report() {
        return null;
    }

    /**
     * Fills vehicle with packages with preference of date added and range of its
     * destination zip code. It will iterate over the packages intially at a range
     * of zero and fill it with as many as it can within its range without going
     * over its maximum weight. The amount the range increases is dependent on the
     * vehicle that is using this. This range it increases by after each iteration
     * is by default one.
     *
     * @param warehousePackages List of packages to add from
     */
    public void fill(ArrayList<Package> warehousePackages) {
        int diffCounter = 0;
        int maxRange = 0;
        boolean checkOnce = false;
        boolean checkTwice = false;
        setZipDest(warehousePackages.get(0).getDestination().getZipCode());
        while (!isFull() && warehousePackages.size() != 0 && !checkTwice) {
            for (int i = 0; i < warehousePackages.size(); i++) {
                int destination = warehousePackages.get(i).getDestination().getZipCode();
                int difference = Math.abs(destination - this.zipDest);
                if (difference == diffCounter) {
                    if (!((warehousePackages.get(i).getWeight() + this.currentWeight) > this.maxWeight)) {
                        addPackage(warehousePackages.get(i));
                        warehousePackages.remove(i);
                        break;
                    } else {
                        if (!checkOnce) {
                            checkOnce = true;
                        } else {
                            checkTwice = true;
                        }
                    }
                }
            }
            diffCounter++;
        }

    }


}
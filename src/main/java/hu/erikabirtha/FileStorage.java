package hu.erikabirtha;

import hu.erikabirtha.entity.Vehicle;

public class FileStorage implements VehiclePersistence {

    @Override
    public void saveVehicle(Vehicle vehicle) {
        System.out.println("File mentés!");

    }

    @Override
    public Vehicle loadVehicle(String s) {
        return null;
    }
}

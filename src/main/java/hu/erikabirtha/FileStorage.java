package hu.erikabirtha;

import com.sun.source.tree.BreakTree;
import hu.erikabirtha.entity.Vehicle;

import java.io.*;


public class FileStorage implements VehiclePersistence {
    public FileStorage() {
        clearFile();
    }

    private void clearFile() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("vehicleDatas.txt");
            fileWriter.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    final String DATA_SEPARATOR = ";";

    @Override
    public void saveVehicle(Vehicle vehicle) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("vehicleDatas.txt", true);
            fileWriter.write(generateVehicleDataRow(vehicle) + System.lineSeparator());
            fileWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    private String generateVehicleDataRow(Vehicle vehicle) {
        return vehicle.getRegistrationNumber() + DATA_SEPARATOR +
                vehicle.getMake() + DATA_SEPARATOR +
                vehicle.getModel() + DATA_SEPARATOR +
                vehicle.getNumberOfSeats() + DATA_SEPARATOR +
                vehicle.getVehicleType();
    }

    @Override
    public Vehicle findVehicleByRegistrationNumber(String registrationNumber) {
        String vehicleLine;
        Vehicle result = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("vehicleDatas.txt"));
            while ((vehicleLine = bufferedReader.readLine()) != null) {
                Vehicle vehicle = parseVehicle(vehicleLine);
                if (vehicle.getRegistrationNumber().equals(registrationNumber))
                    result = vehicle;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public Vehicle parseVehicle(String vehicleLine) {
        String[] vehicleDatas = vehicleLine.split(DATA_SEPARATOR);
        Vehicle vehicle = new Vehicle("", "", "", 0, "");
        vehicle.setRegistrationNumber(vehicleDatas[0]);
        vehicle.setMake(vehicleDatas[1]);
        vehicle.setModel(vehicleDatas[2]);
        vehicle.setNumberOfSeats(Integer.parseInt(vehicleDatas[3]));
        vehicle.setVehicleType(vehicleDatas[4]);
        return vehicle;
    }
}

import Adapter.EquipmentDiagnostics;
import Adapter.PrinterDiagnosticsAdapter;
import Adapter.PrinterVendorAPI;
import DAO.EquipmentDAO;
import DAO.UserDAO;
import DAO.EquipmentDAOImpl;
import DAO.UserDAOImpl;
import DTO.Equipment;
import DTO.User;

import java.util.List;

public class QuickTest {

    public static void main(String[] args) throws Exception {

        UserDAO userDAO = new UserDAOImpl();
        List<User> users = userDAO.findAll();
        System.out.println("Users found: " + users.size());

        EquipmentDAO equipmentDAO = new EquipmentDAOImpl() {};
        List<Equipment> available = equipmentDAO.findByStatus("Available");
        System.out.println("Available equipment: " + available.size());

        EquipmentDiagnostics diagnostics = new PrinterDiagnosticsAdapter(new PrinterVendorAPI());
        System.out.println("Printer status: " + diagnostics.getStatus());
        System.out.println("Printer wear hours: " + diagnostics.getWearHours());
    }
}

import dto.Receipt;
import model.Task;
import service.Formatter;
import service.InputValidator;

public class Main {
    public static void main(String[] args) {
        InputValidator validator = new InputValidator();
        Task task = validator.createTask();
        ReceiptApplication receiptApplication = new ReceiptApplication();
        Receipt receipt = receiptApplication.run(task);
        Formatter.printReceipt(receipt);
    }
}
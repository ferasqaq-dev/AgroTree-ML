import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String path = "C:\\Users\\feras\\IdeaProjects\\Project2Comp338\\src\\main\\java\\crop_recommendation.csv"; // Your local dataset path

            DecisionTreeModel model = new DecisionTreeModel(path);
            model.performCrossValidation();
            model.trainFinalTree();

            while (true) {
                System.out.println("\nEnter environmental parameters:");
                double[] inputs = new double[7];
                String[] names = {"N", "P", "K", "Temperature (°C)", "Humidity (%)", "pH", "Rainfall (mm)"};

                for (int i = 0; i < 7; i++) {
                    System.out.print(names[i] + ": ");
                    inputs[i] = sc.nextDouble();
                }

                String prediction = model.classifyInstance(inputs);
                System.out.println("Recommended Crop: " + prediction);

                System.out.print("\nDo you want to try again? (y/n): ");
                String again = sc.next();
                if (!again.equalsIgnoreCase("y")) break;
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

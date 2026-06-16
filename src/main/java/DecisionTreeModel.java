import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Evaluation;
import weka.core.DenseInstance;


import java.util.Random;

public class DecisionTreeModel {
    private Instances dataset;
    private J48 tree;

    public DecisionTreeModel(String datasetPath) throws Exception {
        System.out.println("Trying to load: " + datasetPath);
        DataSource source = new DataSource(datasetPath);
        dataset = source.getDataSet();
        if (dataset.classIndex() == -1) {
            dataset.setClassIndex(dataset.numAttributes() - 1); // last column is the label
        }
    }

    public void performCrossValidation() throws Exception {
        int folds = 5;
        Random rand = new Random(1); // reproducible
        Instances randData = new Instances(dataset);
        randData.randomize(rand);
        if (randData.classAttribute().isNominal())
            randData.stratify(folds);

        double totalAccuracy = 0;
        double totalPrecision = 0;
        double totalRecall = 0;

        for (int i = 0; i < folds; i++) {
            Instances train = randData.trainCV(folds, i);
            Instances test = randData.testCV(folds, i);

            J48 foldTree = new J48();
            foldTree.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(foldTree, test);

            System.out.println("Fold " + (i + 1));
            System.out.printf("Accuracy: %.2f%%\n", eval.pctCorrect());
            System.out.printf("Precision: %.2f\n", eval.weightedPrecision());
            System.out.printf("Recall: %.2f\n", eval.weightedRecall());
            System.out.println();

            totalAccuracy += eval.pctCorrect();
            totalPrecision += eval.weightedPrecision();
            totalRecall += eval.weightedRecall();
        }

        System.out.println("Average Results:");
        System.out.printf("Accuracy: %.2f%%\n", totalAccuracy / folds);
        System.out.printf("Precision: %.2f\n", totalPrecision / folds);
        System.out.printf("Recall: %.2f\n", totalRecall / folds);
    }

    public void trainFinalTree() throws Exception {
        tree = new J48();
        tree.buildClassifier(dataset);
        System.out.println("Final Decision Tree:");
        System.out.println(tree);
    }

    public String classifyInstance(double[] inputValues) throws Exception {
        Instance newInst = new DenseInstance(dataset.numAttributes());
        newInst.setDataset(dataset);

        for (int i = 0; i < inputValues.length; i++) {
            newInst.setValue(i, inputValues[i]);
        }

        double labelIndex = tree.classifyInstance(newInst);
        return dataset.classAttribute().value((int) labelIndex);
    }
}

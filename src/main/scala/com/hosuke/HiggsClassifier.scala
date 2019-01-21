import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.classification.LogisticRegression

object HiggsClassifier {

    def main(args: Array[String]): Unit = {

        Logger.getLogger("org").setLevel(Level.OFF)
        Logger.getLogger("akka").setLevel(Level.OFF)

        val spark = SparkSession
            .builder()
            .appName("Higgs Classifier")
            .getOrCreate()

        val higgs_libsvm_train = "hdfs://m7-model-hdp01:8020/user/root/4thModelData/higgs/libsvmformat/train/train.libsvm"

        val training = spark.read.format("libsvm").load(higgs_libsvm_train)

        val lr = new LogisticRegression()
            .setMaxIter(10)
            .setRegParam(0.3)
            .setElasticNetParam(0.8)

        // Fit the model
        val lrModel = lr.fit(training)

        // Print the coefficients and intercept for logistic regression
        println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

        // example
        val trainingSummary = lrModel.binarySummary

        // Obtain the objective per iteration.
        val objectiveHistory = trainingSummary.objectiveHistory
        println("objectiveHistory:")
        objectiveHistory.foreach(loss => println(loss))

        // Obtain the receiver-operating characteristic as a dataframe and areaUnderROC.
        val roc = trainingSummary.roc
        roc.show()
        println(s"areaUnderROC: ${trainingSummary.areaUnderROC}")


    }
}
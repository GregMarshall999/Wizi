package ai;

import java.util.List;

public class TrainingSet
{
    private List<Float> inputs;
    private List<Double> goodOutput;

    public TrainingSet(List<Float> inputs, List<Double> goodOutput)
    {
        this.inputs = inputs;
        this.goodOutput = goodOutput;
    }

    public  List<Float> getInputs()
    {
        return inputs;
    }

    public List<Double> getGoodOutput()
    {
        return goodOutput;
    }
}

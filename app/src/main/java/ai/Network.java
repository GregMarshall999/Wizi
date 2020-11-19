package ai;

import java.util.ArrayList;
import java.util.List;

public class Network
{
    private List<Neuron> neurons;

    public Network()
    {
        neurons = new ArrayList<Neuron>();
    }

    public void addNeurons(int count)
    {
        for(int i=0; i<count; i++)
        {
            neurons.add(new Neuron());
        }
    }

    public void setInputs(List<Float> inputs)
    {
        for (Neuron n : neurons)
            n.setInputs(inputs);
    }

    public List<Double> getOutputs()
    {
        List<Double> outputs = new ArrayList<Double>();
        for (Neuron n : neurons)
            outputs.add(n.getOutput());
        return outputs;
    }

    public void adjustWages(List<Double> goodOutput)
    {
        for(int i=0; i<neurons.size(); i++)
        {
            double delta = goodOutput.get(i) - neurons.get(i).getOutput();
            neurons.get(i).adjustWeights(delta);
        }
    }
}

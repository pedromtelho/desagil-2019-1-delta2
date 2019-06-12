package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    private final NandGate nandLeft;
    private final NandGate nandTopOne;
    private final NandGate nandBottomOne;
    private final NandGate nandTopTwo;
    private final NandGate nandBottomTwo;


    public DeMux() {
        super("DeMux", 2, 2);


        nandLeft = new NandGate();
        nandBottomOne = new NandGate();

        nandTopOne = new NandGate();
        nandTopOne.connect(1, nandLeft);


        nandBottomTwo = new NandGate();
        nandBottomTwo.connect(0, nandBottomOne);
        nandBottomTwo.connect(1, nandBottomOne);

        nandTopTwo = new NandGate();
        nandTopTwo.connect(0, nandTopOne);
        nandTopTwo.connect(1, nandTopOne);

    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin < 0 || outputPin > 1) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        if (outputPin == 0){
            return nandTopTwo.read(0);
        }
        else{
            return nandBottomTwo.read(0);
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTopOne.connect(0, emitter);
                nandBottomOne.connect(1, emitter);
                break;
            case 1:
                nandLeft.connect(0, emitter);
                nandLeft.connect(1, emitter);
                nandBottomOne.connect(0, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}

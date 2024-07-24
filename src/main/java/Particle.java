/*
 * Created 23-Jul-2024
 */

public class Particle {

    public static int OP_NULL = 0;
    public static int OP_POW = 1;
    public static int OP_MUL = 2;
    public static int OP_DIV = 3;
    public static int OP_ADD = 4;
    public static int OP_SUB = 5;

    public static int VALUE_TYPE_NULL = 0;
    public static int VALUE_TYPE_CONSTANT = 1;
    public static int VALUE_TYPE_VARIABLE = 2;
    public static int VALUE_TYPE_FUNCTION = 3;

    protected int op;
    protected int valueType;
    protected String value;
    protected float parsedValue;

    public Particle() {
        op = OP_NULL;
        valueType = VALUE_TYPE_NULL;
        value = "";
        parsedValue = Float.NaN;
    }

    public Particle(Particle particle) {
        op = particle.op;
        valueType = particle.valueType;
        value = particle.value;
        parsedValue = particle.parsedValue;
    }
}

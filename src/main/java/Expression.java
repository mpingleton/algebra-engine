/*
 * Created 23-Jul-2024
 */

import java.util.ArrayList;
import java.util.List;

public class Expression {

    protected List<Particle> particles;

    public Expression() {
        particles = new ArrayList<>();
    }

    public Expression(Expression expression) {
        particles = new ArrayList<>();

        for (int i = 0; i < expression.particles.size(); i++) {
            particles.add(new Particle(expression.particles.get(i)));
        }
    }

    public Expression replaceVariable(VariableBundle bundle) {
        Expression newExpression = new Expression();

        for (int i = 0; i < particles.size(); i++) {
            Particle particle = particles.get(i);
            newExpression.particles.add(particle.replaceVariable(bundle));
        }

        return newExpression;
    }

    public String toString() {
        StringBuilder returnString = new StringBuilder();

        for (int i = 0; i < particles.size(); i++) {
            returnString.append(particles.get(i).toString());
        }

        return returnString.toString();
    }
}

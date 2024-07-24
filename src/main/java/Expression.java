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
}

package com.pgiletich.graphics.scene.object.line;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.GraphicsScene;

public class CDALine extends AbstractLine {

    public CDALine(Line line) {
        super(line);
    }

    private int sign(float a){
        return a >= 0 ? 1 : -1;
    }

    @Override
    public void paint(GraphicsScene scene) {
        Debugger debugger = Debugger.getDebugger();

        Line line = getShape();
        int length = (int) Math.max( // Getting maximum length
                Math.abs(line.start.x - line.end.x),
                Math.abs(line.start.y - line.end.y));
        float dx = (float) ((line.end.x - line.start.x) / (float)length); // values, by which x and y change each step
        float dy = (float) ((line.end.y - line.start.y) / (float)length);
        int i = 0;
        float x = (float) (line.start.x + sign(dx) * 0.5f);
        float y = (float) (line.start.y + sign(dy) * 0.5f);

        while(i++ < length){
            if(!debugger.hasNextStep()){
                scene.setFillAlpha(0.1f);
            }
            scene.fillPixel((int) x, (int) y);  // filling pixel
            x+=dx;
            y+=dy;
        }
    }
}

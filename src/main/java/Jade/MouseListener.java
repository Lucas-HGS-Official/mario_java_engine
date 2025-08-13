package Jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;

    private double xScroll, yScroll, xPos, yPos, xLastPos, yLastPos;
    private boolean isMouseButtonsPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener() {
        this.xScroll = .0;
        this.yScroll = .0;
        this.xPos = .0;
        this.yPos = .0;
        this.xLastPos = .0;
        this.yLastPos = .0;
    }

    public static MouseListener get() {
        if(MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;
    }

    public static void mousePosCallback(long window, double xPos, double yPos) {
        get().xLastPos = get().xPos;
        get().yLastPos = get().yPos;

        get().xPos = xPos;
        get().yPos = yPos;

        get().isDragging = get().isMouseButtonsPressed[0] || get().isMouseButtonsPressed[1] || get().isMouseButtonsPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if(action == GLFW_PRESS && button < get().isMouseButtonsPressed.length) {
            get().isMouseButtonsPressed[button] = true;
        } else if(action == GLFW_RELEASE && button < get().isMouseButtonsPressed.length) {
            get().isMouseButtonsPressed[button] = false;
            get().isDragging = false;
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().xScroll = xOffset;
        get().yScroll = yOffset;
    }

    public static void endFrame() {
        get().xScroll = 0;
        get().yScroll = 0;
        get().xLastPos = get().xPos;
        get().yLastPos = get().yPos;
    }


    public static float getX() {
        return (float)get().xPos;
    }
    public static float getY() {
        return (float)get().yPos;
    }
    public static float getDX() {
        return (float)(get().xLastPos - get().xPos);
    }
    public static float getDY() {
        return (float)(get().yLastPos - get().yPos);
    }
    public static float getScrollX() {
        return (float)get().xScroll;
    }
    public static float getScrollY() {
        return (float)get().yScroll;
    }
    public static boolean getIsDragging() {
        return get().isDragging;
    }
    public static boolean getMouseButtonDown(int button) {
        if(button < get().isMouseButtonsPressed.length) {
            return get().isMouseButtonsPressed[button];
        }
        return false;
    }
}

package hlft.app.stardust;

public class StardustProperties {
    public StardustProperties(@SuppressWarnings("unused") Stardust stardust) {}

    protected double xOffset, yOffset;

    protected boolean isRight;//是否处于右边界调整窗口状态
    protected boolean isBottomRight;//是否处于右下角调整窗口状态
    protected boolean isBottom;//是否处于下边界调整窗口状态
    protected final int resizeWidth = 5;//判定是否为调整窗口状态的范围与边界距离
    protected final double minWidth = 320;//窗口最小宽度
    protected final double minHeight = 420;//窗口最小高度
    protected final double maxWidth = 500;//窗口最大宽度
    protected final double maxHeight = 800;//窗口最大高度
    protected final int initialWidth = 320;//窗口初始宽度
    protected final int initialHeight = 420;//窗口初始高度
    protected int presentWidth = 320;//窗口当前宽度
    protected int presentHeight = 420;//窗口当前高度
}

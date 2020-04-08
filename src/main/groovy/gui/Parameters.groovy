package gui

final class Parameters {

    static final double SIMPLE_VERTEX_RADIUS = 3
    static final double STEINER_VERTEX_RADIUS = 2

    static final int SCALE_MULTIPLIER = 500

    // GraphPane
    static final int WORK_GROUND_SIDE_SIZE = SCALE_MULTIPLIER
    static final int WORK_GROUND_BORDER_SIZE = 10
    static final int MAIN_PANE_WIDTH = WORK_GROUND_SIDE_SIZE + WORK_GROUND_BORDER_SIZE * 2
    static final int MAIN_PANE_HEIGHT = WORK_GROUND_SIDE_SIZE + WORK_GROUND_BORDER_SIZE * 2

    // Hex Colors
    static final String HEX_COLOR_WHITE      = "#ffffff"
    static final String HEX_COLOR_LIGHT_GREY = "#e0e0e0"
    static final String HEX_COLOR_DARK_GREY  = "#303030"
}

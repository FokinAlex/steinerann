package api

final class Parameters {

    static final double SIMPLE_VERTEX_RADIUS = 3
    static final double STEINER_VERTEX_RADIUS = 2

    static final int SCALE_MULTIPLIER = 400

    // Panes:
    static final int WORK_GROUND_SIDE_SIZE = SCALE_MULTIPLIER
    static final int WORK_GROUND_BORDER_SIZE = 10
    static final int WORK_GROUND_BORDERED_SIDE_SIZE = WORK_GROUND_SIDE_SIZE + WORK_GROUND_BORDER_SIZE * 2

    static final int GRAPH_PAGE_MAIN_PANE_WIDTH = WORK_GROUND_BORDERED_SIDE_SIZE
    static final int GRAPH_PAGE_MAIN_PANE_HEIGHT = WORK_GROUND_BORDERED_SIDE_SIZE

    static final int ORL_CASE_PAGE_MAIN_PANE_WIDTH = WORK_GROUND_BORDERED_SIDE_SIZE * 3 - WORK_GROUND_BORDER_SIZE
    static final int ORL_CASE_PAGE_MAIN_PANE_HEIGHT = WORK_GROUND_BORDERED_SIDE_SIZE

    // Hex Colors:
    static final String HEX_COLOR_WHITE      = "#ffffff"
    static final String HEX_COLOR_LIGHT_GREY = "#e0e0e0"
    static final String HEX_COLOR_DARK_GREY  = "#303030"
}

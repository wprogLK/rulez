package dev.wproglk.rulez.engine;

public class Result {
    private final String value;
    private final boolean completed;

    private Result() {
        this.value = null;
        this.completed = false;
    }

    private Result(String value) {
        this.value = value;
        this.completed = true;
    }

    public static Result incompleteResult() {
        return new Result();
    }

    public static Result completedResult(String result) {
        return new Result(result);
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isIncompleted() {
        return !completed;
    }

    public String getValue() {
        return value;
    }

}

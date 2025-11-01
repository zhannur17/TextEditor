abstract class FormatDecorator implements TextFormatStrategy {
    protected TextFormatStrategy wrapped;

    public FormatDecorator(TextFormatStrategy wrapped) {
        this.wrapped = wrapped;
    }

    public abstract String format(String text);
}
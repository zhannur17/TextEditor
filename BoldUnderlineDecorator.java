class BoldUnderlineDecorator extends FormatDecorator {
    public BoldUnderlineDecorator(TextFormatStrategy wrapped) {
        super(wrapped);
    }

    @Override
    public String format(String text) {
        return "<u>" + wrapped.format(text) + "</u>";
    }
}
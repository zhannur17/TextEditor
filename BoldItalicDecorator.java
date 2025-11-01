class BoldItalicDecorator extends FormatDecorator {
    public BoldItalicDecorator(TextFormatStrategy wrapped) {
        super(wrapped);
    }

    @Override
    public String format(String text) {
        return "<i>" + wrapped.format(text) + "</i>";
    }
}
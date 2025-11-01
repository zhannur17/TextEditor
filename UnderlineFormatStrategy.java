class UnderlineFormatStrategy implements TextFormatStrategy {
    @Override
    public String format(String text) {
        return "<u>" + text + "</u>";
    }
}
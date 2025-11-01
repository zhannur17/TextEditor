class BoldFormatStrategy implements TextFormatStrategy {
    @Override
    public String format(String text) {
        return "<b>" + text + "</b>";
    }
}
class ItalicFormatStrategy implements TextFormatStrategy {
    @Override
    public String format(String text) {
        return "<i>" + text + "</i>";
    }
}